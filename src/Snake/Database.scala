package Snake

import java.sql.{Connection, DriverManager, ResultSet}

object Database {
  val url = "jdbc:mysql://localhost/mysql?serverTimezone=UTC"
  val username = "root"
  val password = "secret123"
  var connection: Connection = DriverManager.getConnection(url, username, password)

  setupTable()

  def setupTable(): Unit = {
    val statement = connection.createStatement()
    statement.execute("CREATE TABLE IF NOT EXISTS players (username TEXT, lastUpdate BIGINT)")
  }

  def playerExists(username: String): Boolean = {
    val statement = connection.prepareStatement("SELECT * FROM players WHERE username=?")
    statement.setString(1, username)
    val result: ResultSet = statement.executeQuery()

    result.next()
  }

  def createPlayer(username: String): Unit = {
    val statement = connection.prepareStatement("INSERT INTO players VALUE (?, ?)")
    statement.setString(1, username)
    statement.setLong(  6, System.nanoTime())
    statement.execute()
  }
}

/*
   * Change the username/password to your own, but do not modify the methods in this file. Your Database.scala
   * file will be deleted and replaced during testing so any changes you make will not be reflected in AutoLab.
   *
   * There are no objectives to complete here. You should read through the methods and determine
   * what they do, then call the methods you need to be able to complete the objectives
  */