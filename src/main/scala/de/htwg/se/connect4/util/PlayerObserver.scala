package de.htwg.se.connect4.util

trait PlayerObserver {

  def updatePlayers: Unit

}

class PlayerObservable {

  var subscribers: Vector[Observer] = Vector()

  def add(s: Observer): Unit = subscribers = subscribers :+ s

  def remove(s: Observer): Unit = subscribers = subscribers.filterNot(o => o == s)

  def notifyUpdatePlayers: Unit = subscribers.foreach(o => o.update)
}
