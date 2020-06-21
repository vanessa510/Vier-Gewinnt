package de.htwg.se.connect4.controller

import de.htwg.se.connect4.model.Board

abstract class ControllerState {

  def handle(input: String, board: Board): String

  def welcomeString(): String

  def nextState(): ControllerState

}
