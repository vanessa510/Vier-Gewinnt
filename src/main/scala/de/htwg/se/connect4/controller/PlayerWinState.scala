package de.htwg.se.connect4.controller
import de.htwg.se.connect4.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.connect4.model.Board

case class PlayerWinState(controller: Controller, name: String) extends ControllerState {

  override def handle(input: String, board: Board): String = ""

  override def nextState(): ControllerState = InGameState(controller)

  override def getString(): String = "Congratulations! Player " + name + " You win."
}
