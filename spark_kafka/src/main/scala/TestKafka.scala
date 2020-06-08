import org.apache.spark.sql.{ForeachWriter, Row, SparkSession}
import org.apache.spark.sql.execution.streaming.FileStreamSource.Timestamp
import org.apache.spark.sql.types.{DoubleType, IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.functions._

object TestKafka {
  def main(args: Array[String]): Unit = {
//    // test 1
//    val spark = SparkSession
//      .builder
//      .appName("Spark-Kafka-Integration")
//      .master("local")
//      .getOrCreate()
//
//    import spark.implicits._
//
//    val df = spark
//      .readStream
//      .format("kafka")
//      .option("kafka.bootstrap.servers", "localhost:9092")
//      .option("subscribe", "connect-test")
//      .load()
//
//    df.selectExpr("CAST(key AS STRING)")
//      .as[(String)]
//
//    val ds = df
//      .selectExpr("CAST(key AS STRING)")
//      .writeStream
//      .format("kafka")
//      .option("checkpointLocation", "/tmp/checkpoint")
//      .option("kafka.bootstrap.servers", "localhost:9092")
//      .option("topic", "connect-test")
//      .start()
//
//    df.show()
//    ds.stop()

//    // test 2
//    val spark = SparkSession
//      .builder
//      .appName("Spark-Kafka-Integration")
//      .master("local")
//      .getOrCreate()
//
//    val mySchema = StructType(Array(
//      StructField("id", IntegerType),
//      StructField("name", StringType),
//      StructField("year", IntegerType),
//      StructField("rating", DoubleType),
//      StructField("duration", IntegerType)
//    ))
//
//    val streamingDataFrame = spark.readStream.schema(mySchema).csv("/home/sheep/workspace/arctern/sheep/kafka/test/")
//
//    streamingDataFrame.selectExpr("CAST(id AS STRING) AS key", "to_json(struct(*)) AS value").
//      writeStream
//      .format("kafka")
//      .option("topic", "topicName")
//      .option("kafka.bootstrap.servers", "localhost:9092")
//      .option("checkpointLocation", "/tmp/checkpoint")
//      .start()
//
//    import spark.implicits._
//
//    val df = spark
//      .readStream
//      .format("kafka")
//      .option("kafka.bootstrap.servers", "localhost:9092")
//      .option("subscribe", "topicName")
//      .load()
//
//    val df1 = df.selectExpr("CAST(value AS STRING)", "CAST(timestamp AS TIMESTAMP)").as[(String, Timestamp)]
//      .select(from_json($"value", mySchema).as("data"), $"timestamp")
//      .select("data.*", "timestamp")
//
//    df1.writeStream
//      .format("console")
//      .option("truncate","false")
//      .start()
//      .awaitTermination()

    // test 3
    val spark = SparkSession
      .builder
      .appName("Spark-Kafka-Integration")
      .master("local")
      .getOrCreate()

    import spark.implicits._

    val df = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "test")
      .option("startingOffsets", "earliest")
      .option("endingOffsets", "latest")
      .load()

    val df1 = df.selectExpr("CAST(key AS STRING)")
      .as[(String)]

    df1.show()

//      // test 4
//      val spark = SparkSession
//        .builder
//        .appName("Spark-Kafka-Integration")
//        .master("local")
//        .getOrCreate()
//
//      val mySchema = StructType(Array(
//        StructField("id", IntegerType),
//        StructField("name", StringType),
//        StructField("year", IntegerType),
//        StructField("rating", DoubleType),
//        StructField("duration", IntegerType)
//      ))
//
//      val streamingDataFrame = spark.readStream.schema(mySchema).csv("/home/sheep/workspace/arctern/sheep/kafka/test/")
//
//      streamingDataFrame.selectExpr("CAST(id AS STRING) AS key", "to_json(struct(*)) AS value").
//        writeStream
//        .format("kafka")
//        .option("topic", "topicName")
//        .option("kafka.bootstrap.servers", "localhost:9092")
//        .option("startingOffsets", "earliest")
//        .option("endingOffsets", "latest")
//        .option("checkpointLocation", "/tmp/checkpoint")
//        .start()
//
//      import spark.implicits._
//
//      val df = spark
//        .read
//        .format("kafka")
//        .option("kafka.bootstrap.servers", "localhost:9092")
//        .option("subscribe", "topicName")
//        .load()
//
//      val df1 = df.selectExpr("CAST(value AS STRING)", "CAST(timestamp AS TIMESTAMP)").as[(String, Timestamp)]
//        .select(from_json($"value", mySchema).as("data"), $"timestamp")
//        .select("data.*", "timestamp")
//
//      df1.show()
  }
}
