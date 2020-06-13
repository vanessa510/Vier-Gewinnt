package de.htwg.se.connect4.controller


import de.htwg.se.connect4.model.{Color, Player}
import de.htwg.se.connect4.util.Command

class SetCommand(row:Int, col: Int, currentPlayer: Player, controller: Controller, isSet: Boolean) extends Command {

  override def doStep: Unit =   controller.board = controller.board.set(row, col, currentPlayer.color, isSet)

  override def undoStep: Unit = controller.board = controller.board.set(row, col, Color.EMPTY , isSet)

  override def redoStep: Unit = controller.board = controller.board.set(row, col, currentPlayer.color, isSet)
}
