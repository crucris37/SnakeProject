package Snake.Game_Objects

import scalafx.scene.shape.Rectangle

import scala.collection.mutable.{ArrayBuffer, Map}


class AbstractSnake(windowWidth: Int, windowHeight: Int) {

  var GodSpeed: Int = 2
  var zoomTranslate: Int = 0
  var translate: Int = 0
  val grid: Int = 2
  var executed: Boolean = true

  var Size: Int  = 20
  var score: Int = 1

  var previousKey: Int = -1

  var xSum: Int = 0
  var ySum: Int = 0

  val player = new Body((Math.random() * windowWidth).toInt + Size , (Math.random() * windowHeight).toInt + Size)

  val AllPellets: ArrayBuffer[Pellet] = ArrayBuffer()
  
  val testPellets: Map[Pellet, Any] = Map()

  var SnakeBodyLength: ArrayBuffer[Body] = ArrayBuffer(player)

  var SnakeBodyLengthMutable: ArrayBuffer[Rectangle] = ArrayBuffer()

  def createPellets(numberOfPellets: Int): ArrayBuffer[Pellet] = {
    for (x <- 1 to numberOfPellets) {
      val pellet = new Pellet((Math.random() * windowWidth).toInt, (Math.random() * windowHeight).toInt)
      AllPellets += pellet
    }
    AllPellets
  }

  def calculateDistance(x1: Double, y1: Double, x2: Double, y2: Double): Double ={
    Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2))
  }

  def ScorePlus1(): Unit = {
    val pellet = new Pellet((Math.random() * windowWidth).toInt, (Math.random() * windowHeight).toInt)
    AllPellets += pellet
  }

  def removePellets(): Unit ={
    for(pellet <- AllPellets){
      val centerX : Double = player.centerX
      val centerY : Double = player.centerY
      val distance: Double = calculateDistance(centerX, centerY, pellet.centerX, pellet.centerY)
      val HeadAndPellet = Size / 2 + pellet.radius
      if(HeadAndPellet > distance){
        score += 1
        AllPellets -= pellet
        ScorePlus1()
    }
  }
}

  def snakeMoving(): Unit ={

    if(executed){
      println("Create Pellets Reached")
      println("All Pellets Length Before = " + AllPellets.length)
      createPellets(20)
      println("All Pellets Length After  = " + AllPellets.length)
      executed = false
    }
    def update(): Unit ={
      for(x <- SnakeBodyLength.length-1 until 0 by -1){
        if(x > 0){
          SnakeBodyLength(x).centerX = SnakeBodyLength(x-1).centerX
          SnakeBodyLength(x).centerY = SnakeBodyLength(x-1).centerY
        }
      }
    }
    def DIED(): Unit ={
      for(bodyPart <- SnakeBodyLength.reverse){
        if(SnakeBodyLength.length != 1){
          score -= 1
          SnakeBodyLength -= bodyPart
        }
      }
    }

    if(translate == 1 && previousKey != 3){
      previousKey = translate
      ySum = GodSpeed * -grid
      xSum = 0
    }else if(translate == 2 && previousKey != 4){
      previousKey = translate
      xSum = GodSpeed * -grid
      ySum = 0
    }else if(translate == 3 && previousKey != 1){
      previousKey = translate
      ySum = GodSpeed * grid
      xSum = 0
    }else if(translate == 4 && previousKey != 2){
      previousKey = translate
      xSum = GodSpeed * grid
      ySum = 0
    }

    if(zoomTranslate % 2 == 0){
      GodSpeed = 2
    }else if(zoomTranslate % 2 != 0){
      GodSpeed = 3
    }

    if(SnakeBodyLength.length != score){
      val body: Body = new Body(-Size * 2, -Size * 2)
      SnakeBodyLength += body
    }
    if(player.centerX > windowWidth - Size){
      player.centerX = windowWidth - Size
      translate = 0
      DIED()
    }else if(player.centerY > windowHeight - Size ){
      player.centerY = windowHeight - Size
      translate = 0
      DIED()
    } else if(0 > player.centerX){
      player.centerX = 0
      translate = 0
      DIED()
    }else if(0 > player.centerY){
      player.centerY = 0
      translate = 0
      DIED()
    }
    player.centerX += xSum
    player.centerY += ySum
    update()
    removePellets()
  }
}















//  def gameState(): String = {
//    val gameState: Map[String, JsValue] = Map(
//      "players" -> Json.toJson(this.players.map({ case (k, v) => Json.toJson(Map(
//        "x" -> Json.toJson(v.location.x),
//        "y" -> Json.toJson(v.location.y),
//        "v_x" -> Json.toJson(v.velocity.x),
//        "v_y" -> Json.toJson(v.velocity.y),
//        "id" -> Json.toJson(k))) }))
//    )
//
//    Json.stringify(Json.toJson(gameState))
//  }

//"gridSize" -> Json.toJson(Map("x" -> level.gridWidth, "y" -> level.gridHeight)),
//"start" -> Json.toJson(Map("x" -> level.startingLocation.x, "y" -> level.startingLocation.y)),
//"base" -> Json.toJson(Map("x" -> level.base.x, "y" -> level.base.y)),
//      "baseHealth" -> Json.toJson(baseHealth),
//      "maxBaseHealth" -> Json.toJson(level.maxBaseHealth),
//      "walls" -> Json.toJson(this.walls.map({ w => Json.toJson(Map("x" -> w.x, "y" -> w.y)) })),
//      "towers" -> Json.toJson(this.towers.map({ t => Json.toJson(Map("x" -> t.x, "y" -> t.y)) })),
//,
//      "projectiles" -> Json.toJson(this.projectiles.map({ po => Json.toJson(Map("x" -> po.location.x, "y" -> po.location.y)) }))

























//  def inputKey(keyCode: KeyCode): Unit = {
//    keyCode.getName match {
//      case "Up"    => translate = 1
//      case "Left"  => translate = 2
//      case "Down"  => translate = 3
//      case "Right" => translate = 4
//      case "Space" => zoomTranslate += 1
//      case _ => println(keyCode.getName + " pressed with no action")
//    }
//  }

























//                     .-'`     `'.
//              __    /  .-. .-.   \
//           .'`__`'.| /  ()|  ()\  \
//          / /`   `\\ |_ .-.-. _|  ;  __
//          ||     .-'`  (/`|`\) `-./'`__`'.
//          \ \. .'                 `.`  `\ \
//           `-./  _______            \    ||
//              | |\      ''''---.__   |_./ /
//              ' \ `'---..________/|  /.-'`
//               `.`._            _/  /
//                 `-._'-._____.-' _.`
//                  _,-''.__...--'`
//              _.-'_.    ,-. _ `'-._
//           .-' ,-' /   /   \\`'-._ `'.
//         <`  ,'   /   /     \\    / /
//          `.  \  ;   ;       ;'  / /_
//    __   (`\`. \ |   |       ||.' // )
// .'`_ `\(`'.`.\_\|   |    o  |/_,'/.' )
/// .' `; |`-._ ` /;    \     / \   _.-'
//| |  (_/  (_..-' _\    `'--' | `-.._)
//; \        _.'_.' / /'.___.; \
// \ '-.__.-'_.'   ; '        \ \
//  `-.,__.-'      | ;         ; '
//                 | |         | |
//                 | |         / /
//               .-' '.      ,' `-._
//             /`    _ `.   /  _    `.
//            '-/ / / `\_) (_/` \  .`,)
//             | || |            | | |
//             `-'\_'            (_/-'


//      _             _             _             _    |  See No Evil +
//     c -.          { ".          c "}          c ".  |  Hear No Evil +
//\_   / \   +  \_   /\\   +  \_   / \/  =  \_   / \^  |  Speak No Evil =
//  \_| ||        \_|  |        \_|  |        \_| ||   |  One Bored Monkey
