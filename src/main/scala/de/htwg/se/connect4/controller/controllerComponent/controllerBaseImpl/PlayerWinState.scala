package de.htwg.se.connect4.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.connect4.controller.controllerComponent.ControllerInterface
import de.htwg.se.connect4.model.boardComponent.boardBaseImpl.Board

case class PlayerWinState(controller: ControllerInterface, name: String) extends ControllerState {

  override def handle(input: String, board: Board): String = ""

  override def nextState(): ControllerState = InGameState(controller)

  override def getString(): String = "Congratulations! Player " + name + " You win."
}
