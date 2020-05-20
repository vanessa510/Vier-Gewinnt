package de.htwg.se.connect4.aview

import de.htwg.se.connect4.model.Color.Color
import de.htwg.se.connect4.model.{Board, Color, Player}

class Tui {

  def processInputLine(input: String, board: Board, player: Player):Board = {
    input match {
      case "q" => board
      case "n"=> new Board(6, 7, false)

      case _ => {
        val rowAndCol = input.toList.filter(c => c != ' ').slice(0,2).map(c => c.toString.toInt)

          rowAndCol match {
          case row :: column :: Nil => board.set(row, column, player.color)
          case _ => board
        }
      }
    }
  }

}
