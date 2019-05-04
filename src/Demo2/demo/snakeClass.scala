package Demo2.demo

import javafx.scene.input.KeyCode
import scalafx.application.JFXApp
import scalafx.scene.Group
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Circle, Rectangle, Shape}


class snakeClass extends JFXApp{
  val windowWidth: Double = 800
  val windowHeight: Double = 600

  val playerCircleRadius:Double = 20
  val playerSpeed: Double = 10

  val rectangleWidth: Double = 60
  val rectangleHeight: Double = 40

  val pelletRadius: Double = 10

  var allRectangles: List[Shape] = List()
  var allCircles: List[Shape] = List()
  var allPlayers: List[Shape] = List()


  var sceneGraphics: Group = new Group {}


  def numberOfPlayers(playerNumber: Int): Boolean ={
    if(allPlayers.length == 1){
      return true
    }else{
      return false
    }
  }

  def createPellets(width: Double, height: Double): Unit = {
    for(x <- 0 to 100){
      val newCircle = new Circle() {
        centerX = Math.random() * windowWidth
        centerY = Math.random() * windowHeight
        radius = pelletRadius
        fill = Color.Violet
      }
      sceneGraphics.children.add(newCircle)
      allCircles = newCircle :: allCircles
    }

  }

  def createPlayers(playerNumber: Int): Unit={
    for(x <- 0 to playerNumber){
      val newCirlce: Circle = new Circle {
        centerX = Math.random() * windowWidth
        centerY = Math.random() * windowHeight
        radius = playerCircleRadius
        fill = Color.Green
      }
      sceneGraphics.children.add(newCirlce)
      allRectangles = newCirlce :: allRectangles
    }
  }

  def numbersOfPlayers(playerNumber: Int): Boolean ={
    if(playerNumber == 1){
      return true
    }else{
      return false
    }
  }


  val player: Circle = new Circle {
    centerX = 10
    centerY = 10
    radius = playerCircleRadius
    fill = Color.Green
  }
  sceneGraphics.children.add(player)


  def drawRectangle(centerX: Double, centerY: Double): Unit = {
    val newRectangle = new Rectangle() {
      width = rectangleWidth
      height = rectangleHeight
      translateX = centerX - rectangleWidth / 2.0
      translateY = centerY - rectangleHeight / 2.0
      fill = Color.Blue
    }
    sceneGraphics.children.add(newRectangle)
    allCircles = newRectangle :: allCircles
  }




  def createPellet(width: Double, height: Double): Unit = {
    for(x <- 0 to 100){
      val newCircle = new Circle() {
        centerX = Math.random() * windowWidth
        centerY = Math.random() * windowHeight
        radius = pelletRadius
        fill = Color.Violet
      }
      sceneGraphics.children.add(newCircle)
      allCircles = newCircle :: allCircles
    }

  }



  def keyPressed(keyCode: KeyCode): Unit = {
    keyCode.getName match {
      case "W" => player.translateY.value -= playerSpeed
      case "A" => player.translateX.value -= playerSpeed
      case "S" => player.translateY.value += playerSpeed
      case "D" => player.translateX.value += playerSpeed
      case _ => println(keyCode.getName + " pressed with no action" + player.translateX.value.toString)
    }
  }

  def detectCollision(radius: Int): Unit ={


  }


//  this.stage = new PrimaryStage {
//    this.title = "2D Graphics"
//    scene = new Scene(windowWidth, windowHeight) {
//      content = List(sceneGraphics)
//
//      addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed(event.getCode))
//
//      createPlayers(10)
//
//
//      createPellets(windowWidth, windowHeight)
//    }
//
//
//  }


}
