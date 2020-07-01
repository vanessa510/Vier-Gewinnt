package de.htwg.se.connect4.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.connect4.controller.controllerComponent.ControllerInterface
import de.htwg.se.connect4.model.boardComponent.boardBaseImpl.Board

case class GameOverState(controller: ControllerInterface) extends ControllerState {

  override def handle(input: String, board: Board): String =  ""

  override def nextState(): ControllerState = InGameState(controller)

  override def getString(): String = "Game over. No pieces left. Press 'n' to start a new game."
}
