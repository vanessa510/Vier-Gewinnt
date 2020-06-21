package de.htwg.se.connect4.controller
import de.htwg.se.connect4.model.Board

case class PlayerWinState(controller: Controller, name: String) extends ControllerState {

  override def handle(input: String, board: Board): Unit = ""

  override def nextState(): ControllerState = GameOverState(controller)

  override def getString(): String = controller.boardToString; nextState(); "Congratulations! Player " + name + " You win.";
}
