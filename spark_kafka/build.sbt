name := "spark_kafka"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq("org.apache.spark" % "spark-sql_2.11" % "2.4.5",
  "org.apache.spark" % "spark-sql-kafka-0-10_2.11" % "2.4.5",
  "org.apache.kafka" % "kafka-clients" % "0.11.0.1")
