name := """activator-cassandra-scala"""

version := "1.0"

scalaVersion := "2.11.7"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

val cassandraDriverVersion = "3.0.0"
libraryDependencies ++= Seq(
  "com.datastax.cassandra" % "cassandra-driver-core" % cassandraDriverVersion,
  "com.typesafe" % "config" % "1.3.1"
)
// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"

