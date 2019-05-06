package Snake

import java.sql.{Connection, DriverManager, ResultSet}

object Database {
  val url = "jdbc:mysql://localhost/mysql?serverTimezone=UTC"
  val username = "root"
  val password = "secret123"
  var connection: Connection = DriverManager.getConnection(url, username, password)
// BUG

  setupTable()

  def setupTable(): Unit = {
    val statement = connection.createStatement()
    statement.execute("CREATE TABLE IF NOT EXISTS players (username TEXT, color TEXT)")
  }

  def playerExists(username: String): Boolean = {
    val statement = connection.prepareStatement("SELECT * FROM players WHERE username=?")
    statement.setString(1, username)
    val result: ResultSet = statement.executeQuery()

    result.next()
  }

  def createPlayer(username: String, color: String): Unit = {
    val statement = connection.prepareStatement("INSERT INTO players VALUE (?, ?)")
    statement.setString(1, username)
    statement.setString(2, color)
    statement.execute()
  }


  def loadGame(username: String, game: SnakeGame): Unit = {
    val statement = connection.prepareStatement("SELECT * FROM players WHERE username=?")
    statement.setString(1, username)
    val result: ResultSet = statement.executeQuery()
    result.next()
    game.addPlayers(result.getString("username"), result.getString("color"))
  }

  //def loadGame(username: String, game: Game): Unit = {
  //
  //    val statement = connection.prepareStatement("SELECT * FROM players WHERE username=?")
  //    statement.setString(1, username)
  //    val result: ResultSet = statement.executeQuery()
  //
  //    result.next()
  //    game.gold = result.getDouble("gold")
  //    game.equipment("shovel").numberOwned = result.getInt("shovels")
  //    game.equipment("excavator").numberOwned = result.getInt("excavators")
  //    game.equipment("mine").numberOwned = result.getInt("mines")
  //    game.lastUpdateTime = result.getLong("lastUpdate")
  //  }


}

/*
   * Change the username/password to your own, but do not modify the methods in this file. Your Database.scala
   * file will be deleted and replaced during testing so any changes you make will not be reflected in AutoLab.
   *
   * There are no objectives to complete here. You should read through the methods and determine
   * what they do, then call the methods you need to be able to complete the objectives
  */