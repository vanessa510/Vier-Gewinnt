package de.htwg.se.connect4.controller

import de.htwg.se.connect4.model.Board

case class InitializationState(controller: Controller) extends ControllerState {

  override def handle(input: String, board: Board): String = controller.addPlayer(input)


  override def nextState(): ControllerState = InGameState(controller)

  override def getString(): String = controller.getWelcomeString
}
