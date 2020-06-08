import org.apache.spark.sql.{Row, SparkSession}
import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.sql.functions._
import org.apache.spark.sql.catalyst.expressions.Expression
import org.apache.spark.sql.catalyst.util._
import org.apache.spark.sql.types._

object NestLoopTest {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.WARN)
    val spark = SparkSession.builder()
      .master("local[*]")
      .appName("nest_loop_test")
      .getOrCreate()

    val data_score = Seq(
      Row(0, 5),
      Row(1, 15),
      Row(2, 25),
      Row(3, 35),
      Row(4, 45),
      Row(5, 55),
      Row(6, 65),
      Row(7, 75),
      Row(8, 85),
      Row(9, 95),
    )
    val data_grade = Seq(
      Row("A", 0, 10),
      Row("B", 10, 20),
      Row("C", 20, 30),
      Row("D", 30, 40),
      Row("E", 40, 50),
      Row("F", 50, 60),
      Row("G", 60, 70),
      Row("H", 70, 80),
      Row("I", 80, 90),
      Row("J", 90, 100),
    )

    val sch_score = StructType(Array(StructField("id", IntegerType, false), StructField("val", IntegerType, false)))
    val df_score = spark.createDataFrame(spark.sparkContext.parallelize(data_score), sch_score)

    val sch_grade = StructType(Array(StructField("grade", StringType, false), StructField("val0", IntegerType, false), StructField("val1", IntegerType, false)))
    val df_grade = spark.createDataFrame(spark.sparkContext.parallelize(data_grade), sch_grade)

    df_score.show()
    df_grade.show()

    df_score.createOrReplaceTempView("score")
    df_grade.createOrReplaceTempView("grade")

    val rst = spark.sql("select score.id, score.val, grade.grade from score, grade where score.val between grade.val0 and grade.val1")
    rst.explain()
    rst.show()

    val rst2 = df_score.join(df_grade).select(col("id"), col("val"), col("grade")).where(col("val") >= col("val0") && col("val") < col("val1"))
    rst2.explain()
    rst2.show()

    val map_grade: Int => String = x => x match {
      case _ if x >= 0 && x < 10 => "A"
      case _ if x >= 10 && x < 20 => "B"
      case _ if x >= 20 && x < 30 => "C"
      case _ if x >= 30 && x < 40 => "D"
      case _ if x >= 40 && x < 50 => "E"
      case _ if x >= 50 && x < 60 => "F"
      case _ if x >= 60 && x < 70 => "G"
      case _ if x >= 70 && x < 80 => "H"
      case _ if x >= 80 && x < 90 => "I"
      case _ if x >= 90 && x < 100 => "J"
      case _ => "None"
    }

    val row_map: (String, Row) => Row = (field_name, row) => {
      val idx = row.fieldIndex(field_name)
      val grade = map_grade(row.getInt(idx))
//      Row.fromSeq(row.toSeq ++ Seq(grade))
      Row.fromSeq(row.toSeq :+ grade)
    }

    val rdd_score = df_score.rdd.map(row=>row_map("val",row))

    rdd_score.foreach(println)

    val df_score_grade = spark.createDataFrame(rdd_score,StructType(df_score.schema.toArray :+ StructField("grade",StringType,false)))

    df_score_grade.show()

    spark.stop()


  }
}
