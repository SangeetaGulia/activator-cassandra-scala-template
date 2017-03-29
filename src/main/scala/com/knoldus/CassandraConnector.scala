package com.knoldus

import com.datastax.driver.core.{Cluster, ResultSet, Session}
import com.knoldus.utils.ConfigReader

class CassandraConnector {

  def getCasssandraBuilder: Cluster = {
    Cluster.builder()
      .addContactPoint(ConfigReader.getCassandraHost)
      .withPort(ConfigReader.getCassandraPort.toInt)
      .build()
  }

  def getSession(keySpaceName: String, cluster: Cluster): Session = {
    cluster.connect(keySpaceName)
  }

  def executeQuery(session: Session, query: String): ResultSet = {
    session.execute(query)
  }

}
