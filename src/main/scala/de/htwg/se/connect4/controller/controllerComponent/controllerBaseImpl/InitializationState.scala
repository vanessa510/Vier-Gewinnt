package de.htwg.se.connect4.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.connect4.controller.controllerComponent.ControllerInterface
import de.htwg.se.connect4.model.boardComponent.boardBaseImpl.Board

case class InitializationState(controller: ControllerInterface) extends ControllerState {

  override def handle(input: String, board: Board): String = controller.addPlayer(input)


  override def nextState(): ControllerState = InGameState(controller)

  override def getString(): String = controller.getWelcomeString
}
