name := "Basketball"

version := "1.0"

lazy val `basketball` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies += "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.7.6"

libraryDependencies ++= Seq( jdbc , cache , ws , specs2 % Test )

libraryDependencies += "org.apache.spark" % "spark-core_2.11" % "2.0.0"

libraryDependencies += "org.apache.spark" % "spark-sql_2.11" % "2.0.0"

libraryDependencies += "org.apache.spark" % "spark-mllib_2.11" % "2.0.0"

libraryDependencies += "com.databricks" % "spark-csv_2.11" % "1.4.0"

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"  