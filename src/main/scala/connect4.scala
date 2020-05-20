import de.htwg.se.connect4.aview.Tui
import de.htwg.se.connect4.model.{Board, Color, Player}

import scala.io.StdIn.readLine

object connect4 {

  var board = new Board(6, 7, false)
  val tui = new Tui

  def main(args: Array[String]): Unit = {

    println("Welcome to connect 4. Please Enter your names.")

    print("Player 1: ")

    val player1 = Player(readLine(),Color.RED)

    print("Player 2: ")

    val player2 = Player(readLine(), Color.YELLOW)

    println("Hello " + player1.playerName + " and " + player2.playerName + "!")




    var input: String = ""

    val players = player1 :: player2 :: Nil
    val previousPlayer = players.indexOf(player2)

    do {
      println("Board : " + board.getBoardAsString(board.cells))
      val currentPlayer = getNextPlayer(players, previousPlayer)
      input = readLine()
      board = tui.processInputLine(input, board, players(currentPlayer))
    } while (input != "q")

  }

  def getNextPlayer(players: List[Player], previousPlayer: Int): Int =
    if (previousPlayer == 1) players.indexOf(players.head)
    else players.indexOf(players(1))
}