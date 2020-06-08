import org.apache.spark.sql.{Row, SparkSession}
import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.sql.catalyst.expressions.Expression
import org.apache.spark.sql.catalyst.util._
import org.apache.spark.sql.types._

package org.apache.spark.sql.udt.point {

  import org.apache.spark.sql.catalyst.InternalRow
  import org.apache.spark.sql.catalyst.expressions.ExpectsInputTypes
  import org.apache.spark.sql.catalyst.expressions.codegen.{CodegenContext, CodegenFallback, ExprCode}

  @SQLUserDefinedType(udt = classOf[PointUDT])
  class Point(val x: Double, val y: Double) extends Serializable {
    override def hashCode(): Int = 31 * (31 * x.hashCode()) + y.hashCode()

    override def equals(other: Any): Boolean = other match {
      case that: Point => this.x == that.x && this.y == that.y
      case _ => false
    }

    override def toString(): String = s"($x, $y)"
  }

  class PointUDT extends UserDefinedType[Point] {
    override def sqlType: DataType = ArrayType(DoubleType, false)

    override def serialize(obj: Point): GenericArrayData = {
      val output = new Array[Double](2)
      output(0) = obj.x
      output(1) = obj.y
      new GenericArrayData(output)
    }

    override def deserialize(datum: Any): Point = {
      datum match {
        case values: ArrayData => new Point(values.getDouble(0), values.getDouble(1))
      }
    }

    override def userClass: Class[Point] = classOf[Point]
  }

  case class Add1(inputExpr: Seq[Expression]) extends Expression with ExpectsInputTypes with CodegenFallback {
    override def nullable: Boolean = false

    override def eval(input: InternalRow): Any = {
      val left = inputExpr(0).eval(input).asInstanceOf[ArrayData]
      val right = inputExpr(1).eval(input).asInstanceOf[ArrayData]
      val result_x = left.getDouble(0) + right.getDouble(0)
      val result_y = left.getDouble(1) + right.getDouble(1)
      val result = new Point(result_x, result_y)
      new PointUDT().serialize(result)
    }

    override def dataType: DataType = new PointUDT

    override def inputTypes: Seq[AbstractDataType] = Seq(new PointUDT, new PointUDT)

    override def children: Seq[Expression] = inputExpr
  }

  case class Add2(inputExpr: Seq[Expression]) extends Expression {
    import org.apache.spark.sql.catalyst.expressions.codegen._
    import org.apache.spark.sql.catalyst.expressions.codegen.Block._

    override def nullable: Boolean = false

    override def eval(input: InternalRow): Any = ???

    override protected def doGenCode(ctx: CodegenContext, ev: ExprCode): ExprCode = {
      val left_code = inputExpr(0).genCode(ctx)
      val right_code = inputExpr(1).genCode(ctx)
      println("******************************************\n")
      println(left_code)
      println(right_code)
      println(ev)
      ev.copy(code =
        code"""
            ${left_code.code}
            ${right_code.code}

            ${CodeGenerator.javaType(DoubleType)} ${ev.value}_x = ${left_code.value}.getDouble(0) + ${right_code.value}.getDouble(0);
          ${CodeGenerator.javaType(DoubleType)} ${ev.value}_y = ${left_code.value}.getDouble(1) + ${right_code.value}.getDouble(1);
          org.apache.spark.sql.udt.point.Point ${ev.value}_p = new org.apache.spark.sql.udt.point.Point(${ev.value}_x, ${ev.value}_y);
          org.apache.spark.sql.udt.point.PointUDT ${ev.value}_u = new org.apache.spark.sql.udt.point.PointUDT();
          ${CodeGenerator.javaType(ArrayType(DoubleType,false))} ${ev.value} = ${ev.value}_u.serialize(${ev.value}_p);
            """,FalseLiteral)
    }

    override def dataType: DataType = new PointUDT

    override def children: Seq[Expression] = inputExpr
  }

}

object PointUdtTest {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.WARN)
    val spark = SparkSession.builder()
      .master("local[*]")
      .appName("point_udt_test")
      .getOrCreate()

    import org.apache.spark.sql.udt.point._

    val data = Seq(
      Row(1, new Point(1, 1), new Point(10, 10)),
      Row(2, new Point(2, 2), new Point(20, 20)),
      Row(3, new Point(3, 3), new Point(30, 30)),
      Row(4, new Point(4, 4), new Point(40, 40)),
      Row(5, new Point(5, 5), new Point(50, 50))
    )

    val rdd_d = spark.sparkContext.parallelize(data)
    val schema = StructType(Array(StructField("idx", IntegerType, false), StructField("point1", new PointUDT, false), StructField("point2", new PointUDT, false)))
    val df = spark.createDataFrame(rdd_d, schema)
    df.createOrReplaceTempView("data")

    spark.sessionState.functionRegistry.createOrReplaceTempFunction("add1",Add1)
    spark.sessionState.functionRegistry.createOrReplaceTempFunction("add2",Add2)

    var rst = spark.sql("select * from data")
    rst.queryExecution.debug.codegen()
    rst.show()

    rst = spark.sql("select idx, add1(point1, point2) from data ")
    rst.explain()
    rst.queryExecution.debug.codegen()
    rst.show()

    rst = spark.sql("select idx, add2(point1, point2) from data ")
    rst.explain()
    rst.queryExecution.debug.codegen()
    rst.show()

    spark.stop()
  }
}
