import org.apache.spark.sql.{ForeachWriter, Row, SparkSession}
import org.apache.spark.sql.execution.streaming.FileStreamSource.Timestamp
import org.apache.spark.sql.types.{DoubleType, IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.functions._

object PerfTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .appName("Spark-Kafka-Integration")
      .master("local")
      .getOrCreate()

    import spark.implicits._
    val t1 = System.currentTimeMillis

    val df = spark
      .read
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "test")
      .load()
      .selectExpr("CAST(value AS STRING)")

    df.createOrReplaceTempView("df")
    spark.sql("cache table df")

    val t2 = System.currentTimeMillis
    println("****************************************************************")
    println((t2 - t1) / 1000.0 + " secs")

    val res = spark.sql("select count(*) from df")
    res.show()

//    result
//    16.986 secs
//    +--------+
//    |count(1)|
//    +--------+
//    |10000000|
//    +--------+
  }
}
