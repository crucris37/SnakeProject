package Snake.ExampleObjects

import Snake.physics.PhysicsVector
import play.api.libs.json.{JsValue, Json}
import towers.model.genetics.genes.Gene

import scala.collection.mutable.ArrayBuffer

class DodgeBallTower(val x: Int, val y: Int) extends GameObject {

  // The height at which projectiles are fired
  val height = 3.0

  // Towers can only fire at players closer than this distance from the tower
  val sightRange = 5.0

  // The magnitude of the velocity at which projectiles are fired
  val projectileVelocity = 5.0


  def dist(x1: Double, y1: Double, x2: Double, y2: Double): Double = {
    Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2))
  }

  def fire(jsonGameState: String): List[Projectile] = {
    // TODO: Objective 2
    val returnBuffer: ArrayBuffer[Projectile] = ArrayBuffer()
    val playerArrayBuffer: ArrayBuffer[Player] = ArrayBuffer()
    val parsed: JsValue = Json.parse(jsonGameState)
    val playerList = (parsed \ "players").as[List[Map[String, JsValue]]]

    for (player <- playerList) {
      var xP, yP, v_x, v_y = 0.0
      for ((key, value) <- player)
        if (key == "x") xP = value.as[Double]
        else if (key == "y") yP = value.as[Double]
        else if (key == "v_x") v_x = value.as[Double]
        else if (key == "v_y") v_y = value.as[Double]
      if(xP !=0 && yP != 0){
        if (dist(xP, yP, x +.5, y + .5) < sightRange) {
          playerArrayBuffer += new Player(new PhysicsVector(xP, yP), new PhysicsVector(v_x, v_y))
        }
      }
    }
    var closestPlayer1: Player = null
    var closestDouble: Double = 1000000
    for (player <- playerArrayBuffer) {
      //if (dist(player.location.x, player.location.y, x, y) <= sightRange) {
      if (dist(player.location.x, player.location.y, x + .5, y + .5) < closestDouble) {
        closestPlayer1 = player
        closestDouble = dist(player.location.x, player.location.y, x + .5, y + .5)
      }
      //}
    }
    if (closestPlayer1 != null) {
      val closestPlayer: Player = closestPlayer1
      val dX: Double = closestPlayer.location.x - (x + .5)
      val dY: Double = closestPlayer.location.y - (y + .5)
      val newVector: PhysicsVector = new PhysicsVector(dX, dY).normal2d()
      val newdX = newVector.x * projectileVelocity
      val newdY = newVector.y * projectileVelocity
      val newerVector: PhysicsVector = new PhysicsVector(newdX, newdY)
      returnBuffer += new Projectile(new PhysicsVector(x + .5, y + .5, height), newerVector)
    }
    returnBuffer.toList
  }


  def aimFire(jsonGameState: String): List[Projectile] = {
    // TODO: Bonus Objective
    List()
  }


  // Suggested Genetic Algorithm setup
  def getFitnessFunction(targetPlayer: Player): PhysicsVector => Double = {
    null
  }

  def vectorIncubator(genes: List[Gene]): PhysicsVector = {
    null
  }

}
