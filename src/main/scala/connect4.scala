import de.htwg.se.connect4.aview.Tui
import de.htwg.se.connect4.aview.gui.SwingGui
import de.htwg.se.connect4.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.connect4.model.boardComponent.boardBaseImpl.{Board, BoardSizeStrategy}
import de.htwg.se.connect4.model.playerComponent.Player

import scala.io.StdIn.readLine

object connect4 {

  var board: Board = BoardSizeStrategy.execute((6, 7))
  var players: List[Player] = Nil
  val controller = new Controller(board, players)
  val tui = new Tui(controller)
  val gui = new SwingGui(controller)

  controller.notifyObservers


  def main(args: Array[String]): Unit = {

    var input: String = ""
    do {

      input = readLine()
      println(tui.processInputLine(input, board))
    } while (input != "q")

  }
}