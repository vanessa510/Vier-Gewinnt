package de.htwg.se.connect4.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.connect4.model.boardComponent.boardBaseImpl.Board

abstract class ControllerState {

  def handle(input: String, board: Board): String

  def nextState(): ControllerState

  def getString(): String

}
