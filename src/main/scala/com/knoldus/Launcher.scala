package com.knoldus

import com.datastax.driver.core.{Cluster, ResultSet, Session}
import com.knoldus.utils.ConfigReader

import scala.collection.JavaConversions
import scala.util.{Failure, Success, Try}

object Launcher extends App {

  val cassandraConnector = new CassandraConnector
  Try {
    cassandraConnector.getCasssandraBuilder
  } match {
    case Success(cluster) =>
      val session = getCassandraSession(ConfigReader.getKeyspaceName, cluster)
      runQuery(session)
      session.close()
      cluster.close()
    case Failure(exception) => println("Unable to Connect to Cassandra" + exception)
  }

  def runQuery(session: Session): Unit ={
    val keyspace = ConfigReader.getKeyspaceName
    val tableName = ConfigReader.getTableName
    val selectQuery = s"select * from $tableName"
    val insertQuery = s"insert into $keyspace.$tableName(id,age,name) values(2,24,'geetu')"
    val updateQuery = s"update $keyspace.$tableName set age=23 where id=2"
    val deleteQuery = s"delete from $keyspace.$tableName where id=2"

    println("\n\nRecords in table Initially...")
    displayResult(selectQuery, session)

    // Inserting into table
    println("\n\nInserting one Record ...")
    cassandraConnector.executeQuery(session, insertQuery)
    displayResult(selectQuery, session)

    println("\n\nUpdating Inserted Record ...")
    cassandraConnector.executeQuery(session, updateQuery)
    displayResult(selectQuery, session)

    println("\n\nDeleting Inserted Record ...")
    cassandraConnector.executeQuery(session, deleteQuery)
    displayResult(selectQuery, session)
  }

  private def getCassandraSession(keyspace: String, cluster: Cluster): Session = {
    Try{
    cassandraConnector.getSession(keyspace, cluster)
  } match {
      case Success(session) => session
      case Failure(exception) => throw new Exception("Unable to connect to keyspace" + exception)
    }
  }

  private def displayResult(selectQuery: String, session: Session): Unit = {
    val resultSet: ResultSet = cassandraConnector.executeQuery(session, selectQuery)
    val iterator = JavaConversions.asScalaIterator(resultSet.iterator)
    iterator foreach { row =>
      println(s"[ id: ${row.getInt("id")}, name : ${row.getString("name")}, age : ${row.getInt("age")} ]")
    }
  }

}
