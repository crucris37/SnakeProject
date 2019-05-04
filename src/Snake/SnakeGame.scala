package Snake

import Snake.Game_Objects.{AbstractSnake, Pellet}
import play.api.libs.json.{JsValue, Json}
import scalafx.scene.Group

import scala.collection.mutable.ArrayBuffer


class SnakeGame {
  val windowWidth:  Int = 800
  val windowHeight: Int = 600

  val allPellets: ArrayBuffer[Pellet] = ArrayBuffer()
  var allPlayers: Map[String, AbstractSnake] = Map()
  var sceneGraphics: Group = new Group {}

  def addPlayers(userName: String): Unit ={
    val snake = new AbstractSnake(windowWidth, windowHeight)
    allPlayers += userName -> snake
  }

  def removeSnake(userName: String): Unit = {
    allPlayers -= userName
  }

  def gameState(): String = {
    val gameState: Map[String, JsValue] = Map(
      "players" -> Json.toJson(this.allPlayers.map({ case (k, v) => Json.toJson(Map(
        "x" -> Json.toJson(v.player.centerX),
        "y" -> Json.toJson(v.player.centerY),
        "v_x" -> Json.toJson(v.GodSpeed),
        "v_y" -> Json.toJson(v.GodSpeed),
        "id" -> Json.toJson(k))) }))
    )
    Json.stringify(Json.toJson(gameState))
  }
}
