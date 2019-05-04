//package Snake
//
//import akka.actor.AbstractActor.Receive
//import akka.actor.ActorRef
//import towers.model.SendGameState
//
//class GameSnakeActor {
//  var players: Map[String, ActorRef] = Map()
//  var towers: List[ActorRef] = List()
//
//  val game: SnakeGame = new SnakeGame
//
//  def receive: Receive ={
//   case message: AddPlayer => game.addPlayers(message.username)
//   //case SendGameState => sender !
// }
//
//
//
//}
