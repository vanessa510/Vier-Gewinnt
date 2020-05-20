import de.htwg.se.connect4.aview.Tui
import de.htwg.se.connect4.model.{Board, Color, Player}

import scala.io.StdIn.readLine

object connect4 {

  var board = new Board(6, 7, false)
  val tui = new Tui

  def main(args: Array[String]): Unit = {

    val players = tui.welcomePlayers()
    var previousPlayer = 1

    var input: String = ""
    do {
      println("Board : " + board.getBoardAsString(board.cells))
      val currentPlayer = getNextPlayer(players, previousPlayer)
      previousPlayer = currentPlayer
      input = readLine()
      board = tui.processInputLine(input, board, players(currentPlayer))
    } while (input != "q")

  }

  def getNextPlayer(players: List[Player], previousPlayer: Int): Int =
    if (previousPlayer == 1) players.indexOf(players.head)
    else players.indexOf(players(1))
}