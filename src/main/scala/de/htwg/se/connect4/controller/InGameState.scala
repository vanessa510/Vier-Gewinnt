package de.htwg.se.connect4.controller

import de.htwg.se.connect4.model.Board

import scala.util.{Failure, Success, Try}

case class InGameState(controller: Controller) extends ControllerState {

  override def handle(input: String, board: Board): String = evaluateInput(input, board)

  def evaluateInput(input: String, board: Board): String = {
    val list:Try[List[Int]] = Try(input.toList.filter(c => c != ' ').map(c => c.toString.toInt))

    list match {
      case Success(v) =>

        if (list.get.size == 2) {
          val row:Int = list.get.head
          val column:Int = list.get(1)
          if (row < board.sizeOfRows && column < board.sizeOfCols) controller.set(row, column)
          else controller.getIncorrectInputMessage
        }

        else controller.getIncorrectInputMessage

      case Failure(e) => controller.getIncorrectInputMessage
    }
  }

  override def nextState(): ControllerState =
    if (controller.playersHaveNoPiecesLeft) GameOverState(controller)
    else PlayerWinState(controller, controller.players(controller.currentPlayerIndex).playerName)

  override def getString(): String = controller.getPlayerDemandString + "\n" + controller.boardToString
}
