package Snake

import Snake.Game_Objects.{AbstractSnake, Pellet}
import scalafx.scene.Group
import scala.collection.mutable.ArrayBuffer


class SnakeGame {
  val windowWidth:  Int = 800
  val windowHeight: Int = 600

  val allPellets: ArrayBuffer[Pellet] = ArrayBuffer()
  var allPlayers: Map[String, AbstractSnake] = Map()
  var sceneGraphics: Group = new Group {}

  //  def addPlayer(id: String): Unit = {
  //    val player = new Player(startingVector(), new PhysicsVector(0, 0))
  //    players += (id -> player)
  //    world.objects = player :: world.objects
  //  }


  def addPlayers(userName: String): Unit ={
    val snake = new AbstractSnake(windowWidth, windowHeight)
    allPlayers += userName -> snake
  }







//  var players: Map[String, Player] = Map()

}
