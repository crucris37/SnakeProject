package Demo2.demo

import javafx.scene.input.{KeyCode, KeyEvent, MouseEvent}
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Circle, Rectangle, Shape}
import scalafx.scene.{Group, Scene}


object snake extends JFXApp{
  val windowWidth: Double = 1000
  val windowHeight: Double = 850

  val playerCircleRadius:Double = 20
  val playerSpeed: Double = 40

  val pelletRadius: Double = 10

  var allRectangles: List[Shape] = List()

  var playerList: List[Shape] = List()

  var allPellets: List[Shape] = List()

  var allPlayers: List[Shape] = List()

  var sceneGraphics: Group = new Group {}

  val otherPlayer: Circle = new Circle {
    centerX = 100
    centerY = 100
    radius = playerCircleRadius
    fill = Color.Green
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
      allPellets = newCircle :: allPellets
    }
  }


  def createPlayers(playerNumber: Int): Unit={
    for(x <- 0 to playerNumber){
      val newCirlce: Circle = new Circle {
        centerX = Math.random() * windowWidth
        centerY = Math.random() * windowHeight
        radius = playerCircleRadius
        fill = Color.Orange
      }
      sceneGraphics.children.add(newCirlce)
      //allRectangles = newCirlce :: allRectangles
    }
  }
//  val newRectangle = new Rectangle() {
//    width = rectangleWidth
//    height = rectangleHeight
//    translateX = centerX - rectangleWidth / 2.0
//    translateY = centerY - rectangleHeight / 2.0
//    fill = Color.Blue
//  }

  val player: Circle = new Circle() {
    centerX = 100
    centerY = 100
    radius = playerCircleRadius
    fill = Color.Green
  }


//  val square: Square = new Square(){
//
//  }

//  val player: Rectangle = new Rectangle() {
//    width = 10
//    height = 10
//    translateX = centerX - rectangleWidth / 2.0
//    translateY = centerY - rectangleHeight / 2.0
//    fill = Color.Blue
//  }

  sceneGraphics.children.add(player)


  def inputKey(keyCode: KeyCode): Unit = {
//    sceneGraphics.children.remove()
//
//    for(abs <- sceneGraphics){
//      for(sa <- abs){
//        sa.
//      }
//      if(player.centerX == abs.centerX){
//
//      }
//    }



    keyCode.getName match {
      case "Up"    => player.translateY.value -= playerSpeed
      case "Left"  => player.translateX.value -= playerSpeed
      case "Down"  => player.translateY.value += playerSpeed
      case "Right" => player.translateX.value += playerSpeed

      case _ => println(keyCode.getName + " pressed with no action" + player.translateX.value.toString)
    }
    playerList = player :: playerList

  }


  def drawRectangle(centerX: Double, centerY: Double): Unit = {
    val rectangleWidth = 20
    val rectangleHeight = 30

    val newRectangle = new Rectangle() {
      width = rectangleWidth
      height = rectangleHeight
      translateX = centerX - rectangleWidth / 2.0
      translateY = centerY - rectangleHeight / 2.0
      fill = Color.Blue
    }
    sceneGraphics.children.add(newRectangle)
    allRectangles = newRectangle :: allRectangles
  }




  ////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////////////////////
  this.stage = new PrimaryStage {
    this.title = "Snake Game"
    scene = new Scene(windowWidth, windowHeight) {
      content = List(sceneGraphics)


      addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => inputKey(event.getCode))

      addEventHandler(MouseEvent.MOUSE_CLICKED, (event: MouseEvent) => drawRectangle(event.getX, event.getY))
      createPlayers(10)
      createPellets(windowWidth, windowHeight)
    }

    val update: Long => Unit = (time: Long) => {
      for (shape <- allRectangles) {
        shape.rotate.value += 0.5
      }

//      for(pellets <- allPellets){
//        //if(pellets.)
//      }

      //for()



    }

    AnimationTimer(update).start()

  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////////////////////

  def isthereOnePlayer(playerNumber: Int): Boolean ={
    if(playerNumber == 1){
      return true
    }else{
      return false
    }
  }

  def removePlayer(): Boolean ={
    if(player.radius - otherPlayer.radius == 0){
      true
    }else{
      false
    }
  }


  //  def snakeOverPellet(): Unit ={
  //    for(x <- pelletRadius){
  //      if(player.centerX == x.centerX){
  //        pelletRadius.remove(0)
  //        player.radius += 3
  //      }
  //    }
  //  }

  def isThereOnePlayer(playerNumber: Int): Boolean ={
    if(allPlayers.length == 1){
      return true
    }else{
      return false
    }
  }

  def createsPlayerss(playerNumber: Int): Int={
    var sceneGraphics: Group = new Group {}
    var allCircles: List[Shape] = List()
    for(x <- 0 to playerNumber){
      val newCirlce: Circle = new Circle {
        centerX = Math.random() * 1000
        centerY = Math.random() * 1000
        radius = 20
      }
      sceneGraphics.children.add(newCirlce)
      allCircles = newCirlce :: allCircles
    }
    allCircles.length - 1
  }

  def createPelletss(width: Double, height: Double): Int = {
    var sceneGraphics: Group = new Group {}
    var allCircles: List[Shape] = List()

    val windowWidth: Double = 1000
    val windowHeight: Double = 850

    for(x <- 0 to 100){
      val newCircle = new Circle() {
        centerX = Math.random() * windowWidth
        centerY = Math.random() * windowHeight
        radius = 20
      }
      sceneGraphics.children.add(newCircle)
      //allCircles = newCircle :: allCircles
    }
    allCircles.length
  }

  def createPellet(c: Int): Int = {
    val numberOfPellets: Int = c
    numberOfPellets
  }

}

