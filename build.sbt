name := "sample-spark-project"
version := "1.0"
scalaVersion := "2.11.8"

val sparkVersion = "2.1.1"

// Note the dependencies are provided
libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion % "provided"
libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion % "provided"

// Do not include Scala in the assembled JAR
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

// META-INF discarding for the FAT JAR
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

lazy val spark_run = taskKey[Unit]("Builds the assembly and ships it to the Spark Cluster")
spark_run := {
  ("D:/dev-tools/spark-2.2.0-bin-hadoop2.7/ " + assembly.value) !
}