package de.htwg.se.connect4.aview


import de.htwg.se.connect4.controller.Controller
import de.htwg.se.connect4.model.Board
import de.htwg.se.connect4.util.Observer

class Tui(controller: Controller) extends Observer {

  val rows: Int = 6
  val cols: Int = 7

  controller.add(this)

  def processInputLine(input: String, board: Board): String = {

    input match {
      case "q" => "exit game"
      case "n" => controller.createNewBoard(rows, cols)
      case "r" => controller.redo
      case "u" => controller.undo

      case _ => controller.handle(input, board)

    }
  }

  override def update: Unit = println(controller.getString)

}
