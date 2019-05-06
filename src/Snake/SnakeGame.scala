package Snake

import Snake.Game_Objects.{AbstractSnake, Pellet}
import play.api.libs.json.{JsValue, Json}
import scalafx.scene.Group

import scala.collection.mutable.ArrayBuffer


class SnakeGame {
  val windowWidth: Int = 1800
  val windowHeight: Int = 1000

  val allPellets: ArrayBuffer[Pellet] = ArrayBuffer()
  var allPlayers: Map[String, AbstractSnake] = Map()
  var sceneGraphics: Group = new Group {}
  var lastUpdateTime: Long = System.nanoTime()

  def addPlayers(userName: String, color: String): Unit = {
    val snake = new AbstractSnake(windowWidth, windowHeight)
    snake.color = color
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
        "v_x" -> Json.toJson(v.xSum),
        "v_y" -> Json.toJson(v.ySum),
        "id" -> Json.toJson(k),
        "color" -> Json.toJson(v.color),
        "body" -> Json.toJson(v.bodyOfPLayer.map({ case z => Json.toJson(Map(
          "x" -> z.centerX,
          "y" -> z.centerY
        ))
        }
        ))
      ))

      })),

    )
    Json.stringify(Json.toJson(gameState))
  }

  def update(): Unit = {
    // Moves the snake accordingly
    for((k, v) <- allPlayers){
      v.snakeMoving()
//      println("center x = " + v.player.centerX)
//      println("center y = " + v.player.centerY)
//      println("//////////")
    }
//    println("Update Reached")
    // Checks if Players intersect with one another
    // if true then the loser get the position 0,0
    for ((outerKey, outerPlayer) <- allPlayers) {
      for((innerKey, innerPlayer) <- allPlayers){
        if(innerKey != outerKey){
          for(bodyParts <- innerPlayer.bodyOfPLayer){
            if(outerPlayer.distanceFormula(bodyParts.centerX, bodyParts.centerY) < outerPlayer.Size){
              val randomInt = scala.util.Random
              val width = randomInt.nextInt(1800)
              val height = randomInt.nextInt(1000)
              outerPlayer.player.centerX = width
              outerPlayer.player.centerY = height
            }
          }
        }
      }
    }

    val time: Long = System.nanoTime()
    val dt = (time - this.lastUpdateTime) / 1000000000.0
    //      Physics.updateWorld(this.world, dt)
    //      checkForPlayerHits()
    //      checkForBaseDamage()
    //      projectiles = projectiles.filter(po => !po.destroyed)
    this.lastUpdateTime = time
    //println("Game Update Reached")
  }
}

//val colorPallet: List[String] = List("red", "blue", "green", "black", "orange", "purple", "grey")
//    val randomInt = scala.util.Random
//    val color: String = colorPallet(randomInt.nextInt(7))
