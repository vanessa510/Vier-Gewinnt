import de.htwg.se.connect4.aview.Tui
import de.htwg.se.connect4.controller.Controller
import de.htwg.se.connect4.model.{Board, Color, Player}

import scala.io.StdIn.readLine

object connect4 {

  var board = new Board(6, 7, false)
  var players: List[Player] = Nil
  val controller = new Controller(board, players)
  val tui = new Tui(controller)


  def main(args: Array[String]): Unit = {

    println(controller.getWelcomeString)

    while(controller.players.size != 2) {
      controller.addPlayer(readLine())
    }


    var input: String = ""
    do {

      input = readLine()
      tui.processInputLine(input, board)
    } while (input != "q")

  }
}