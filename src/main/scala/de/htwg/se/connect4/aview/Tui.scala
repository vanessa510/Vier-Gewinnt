package de.htwg.se.connect4.aview


import de.htwg.se.connect4.controller.Controller
import de.htwg.se.connect4.model.Board
import de.htwg.se.connect4.util.Observer

class Tui(controller: Controller) extends Observer {

  val rows: Int = 6
  val cols: Int = 7

  controller.add(this)
  def processInputLine(input: String, board: Board): Unit = {

    input match {
      case "q" =>
      case "n" => controller.createNewBoard(rows, cols)

      case _ => {
        try {
          input.toList.filter(c => c != ' ').map(c => c.toString.toInt) match {
            case row :: column :: Nil =>
              if (row <= board.sizeOfRows && column <= board.sizeOfCols) controller.set(row, column)
            case _ =>
          }
        }
        catch {
         case e: NumberFormatException =>
        }

      }
    }
  }

  override def update: Unit = println(controller.boardToString)

  override def receiveError(message: String): Unit = println(message)

}
