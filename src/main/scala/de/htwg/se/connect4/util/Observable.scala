package de.htwg.se.connect4.util


  trait Observer {
    def update: Unit

    def receiveError(message: String): Unit
  }

  class Observable {
    var subscribers: Vector[Observer] = Vector()

    def add(s: Observer): Unit = subscribers = subscribers :+ s

    def remove(s: Observer): Unit = subscribers = subscribers.filterNot(o => o == s)

    def notifyObservers: Unit = subscribers.foreach(o => o.update)

    def notifyError(message: String): Unit = subscribers.foreach(o => o.receiveError(message))
  }


