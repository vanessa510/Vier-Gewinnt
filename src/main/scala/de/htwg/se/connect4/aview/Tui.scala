package de.htwg.se.connect4.aview


import de.htwg.se.connect4.controller.Controller
import de.htwg.se.connect4.model.Board
import de.htwg.se.connect4.util.Observer

import scala.util.{Failure, Success, Try}

class Tui(controller: Controller) extends Observer {

  val rows: Int = 6
  val cols: Int = 7

  controller.add(this)

  def processInputLine(input: String, board: Board): String = {

    input match {
      case "q" => "exit game"
      case "n" => controller.createNewBoard(rows, cols)

      case _ => { evaluateInput(input, board)

      }
    }
  }

  private def evaluateInput(input: String, board: Board): String = {
    val list:Try[List[Int]] = Try(input.toList.filter(c => c != ' ').map(c => c.toString.toInt))

      list match {
        case Success(v) =>

          if (list.get.size == 2) {
            val row:Int = list.get.head
            val column:Int = list.get(1)
            if (row <= board.sizeOfRows && column <= board.sizeOfCols) controller.set(row, column)
            else "Please Enter two numbers separated by a whitespace."
          }
          else {

            "Please Enter two numbers separated by a whitespace."
          }


        case Failure(e) => "Please Enter two numbers separated by a whitespace."
      }

  }

  override def update: Unit = println(controller.boardToString)

  override def receiveError(message: String): Unit = println(message)

}
