package Snake

import Snake.Game_Objects.SnakeWithAbstract
import javafx.scene.input.KeyEvent
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.shape.{Circle, Shape}
import scalafx.scene.{Group, Scene}

import scala.collection.mutable.ArrayBuffer

object Desktop extends JFXApp{
  val windowWidth: Double = 800
  val windowHeight: Double = 600

  val allPellets: ArrayBuffer[Circle] = ArrayBuffer()
  var allPlayers: List[Shape] = List()
  var sceneGraphics: Group = new Group {}


  this.stage = new PrimaryStage {
    this.title = "Snake Game!"
    val abstractSnake = new SnakeWithAbstract()
//    val snake = new Snake
    scene = new Scene(windowWidth, windowHeight) {
      content = List(sceneGraphics)
      addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => abstractSnake.inputKey(event.getCode))
//      addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => snake.inputKey(event.getCode))
//      abstractSnake.createPelletsClass()
    }

    val update: Long => Unit = (time: Long) => {
      abstractSnake.snakeMoving()
//      snake.snakeMoving()
    }
    AnimationTimer(update).start()
  }
}





//addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => Snake.inputKey(event.getCode))
//createPellets(windowWidth, windowHeight)
//Snake.snakeMoving()
//abstractSnake.ClassPlayer.snakeMoving()