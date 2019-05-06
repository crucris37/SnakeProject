package Snake

import Snake.Database.{createPlayer, loadGame, playerExists}
//import akka.actor.AbstractActor.Receive
import akka.actor.{Actor, ActorRef}

////////// Bug
class GameSnakeActor extends Actor {
  var players: Map[String, ActorRef] = Map()
  var towers: List[ActorRef] = List()

  val game: SnakeGame = new SnakeGame

  override def receive: Receive = {

    //// BUG
    case message: AddPlayer =>
      if(playerExists(message.username)){
        loadGame(message.username, game)
      }else{
        val colorPallet: List[String] = List("red", "blue", "green", "black", "orange", "purple", "grey")
        val randomInt = scala.util.Random
        val color: String = colorPallet(randomInt.nextInt(7))
        createPlayer(username = message.username, color)
        game.addPlayers(message.username, color)
      }


    case message: RemovePlayer => game.removeSnake(message.username)

    case message: MovePlayer =>
      if (message.x != 0 || message.y != 0) {
        game.allPlayers(message.username).translate = translate(message.x, message.y)
        //game.allPlayers(message.username).snakeMoving()
//        println(message.x)
//        println(message.y)
//        println(game.allPlayers(message.username).translate)
      }

    case SendGameState => sender() ! GameState(game.gameState())

    case UpdateGame => game.update()
  }

  def translate(x: Double, y: Double): Int = {
    var translate = 0
    if (x == 1) {
      translate = 4
    } else if (x == -1) {
      translate = 2
    } else if (y == 1) {
      translate = 3
    } else {
      translate = 1
    }
    translate
  }

}
