package de.htwg.se.connect4.aview

import de.htwg.se.connect4.model.{Board, Color, Player}

class Tui {

  def processInputLine(input: String, board: Board, player: Player): Board = {

    input match {
      case "q" => board
      case "n" => new Board(6, 7, false)

      case _ => {
        try {
          input.toList.filter(c => c != ' ').map(c => c.toString.toInt) match {
            case row :: column :: Nil =>
              if (row <= board.sizeOfRows && column <= board.sizeOfCols) board.set(row, column, player.color)
              else board
            case _ => board
          }
        }
        catch {
         case e: NumberFormatException => board
        }

      }
    }
  }

}
