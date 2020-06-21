package de.htwg.se.connect4.controller
import de.htwg.se.connect4.model.Board

case class GameOverState(controller: Controller) extends ControllerState {

  override def handle(input: String, board: Board): String =  "Game over. No pieces left. Press 'n' to start a new game."

  override def nextState(): ControllerState = InGameState(controller)

  override def welcomeString(): String = ""
}
