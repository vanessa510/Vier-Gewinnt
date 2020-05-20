package de.htwg.se.connect4.aview

import de.htwg.se.connect4.model.{Board, Color, Player}

class Tui {

  def welcomePlayers(): List[Player] = {
    println("Welcome to connect 4. Please Enter your names.")

    print("Player 1: ")
    val player1 = Player(readLine(),Color.RED)

    print("Player 2: ")

    val player2 = Player(readLine(), Color.YELLOW)

    println("Hello " + player1.playerName + " and " + player2.playerName + "!")

    player1 :: player2 :: Nil
  }

  def processInputLine(input: String, board: Board, player: Player): Board = {

    input match {
      case "q" => board
      case "n" => new Board(6, 7, false)

      case _ => {
        input.toList.filter(c => c != ' ').map(c => c.toString.toInt) match {
          case row :: column :: Nil =>
            if (row <= board.sizeOfRows && column <= board.sizeOfCols) board.set(row, column, player.color)
            else board
          case _ => board
        }
      }
    }
  }
}
