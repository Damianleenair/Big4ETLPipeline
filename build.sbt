//1. I am telling SBT to use Scala Version 2.2.17 for this project
ThisBuild / scalaVersion := "2.12.17"

//Declaring main project in the current folder
lazy val root = (project in file("."))
  // Setting the project metadata which is the project name and version
  .settings(
    name := "Big4ETLPipeline",
    version := "0.1.0",
    //This is external libraries/dependencies and enables the SBT to download spark-sql lib and PostgreSQL JDBC Driver
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-sql" % "3.5.0",
      "org.postgresql" % "postgresql" % "42.7.5"
    )
  )
