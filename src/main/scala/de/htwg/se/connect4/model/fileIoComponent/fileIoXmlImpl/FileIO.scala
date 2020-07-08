package de.htwg.se.connect4.model.fileIoComponent.fileIoXmlImpl

import de.htwg.se.connect4.controller.controllerComponent.controllerBaseImpl.State
import de.htwg.se.connect4.model.boardComponent.BoardInterface
import de.htwg.se.connect4.model.boardComponent.boardBaseImpl.Color
import de.htwg.se.connect4.model.boardComponent.boardBaseImpl.Color.Color
import de.htwg.se.connect4.model.fileIoComponent.FileIoInterface
import de.htwg.se.connect4.model.playerComponent.Player

import scala.xml.PrettyPrinter

class FileIO extends FileIoInterface {


  override def load: (BoardInterface, State) = {
    var board: BoardInterface = null
    val file = scala.xml.XML.loadFile("board.xml")
    val rowAttr = file \\ "board" \ "@row"
    val colAttr = file \\ "board" \ "@col"

    val rows = rowAttr.text.toInt
    val cols = colAttr.text.toInt


    val cellNodes = file \\ "cell"
    for (cell <- cellNodes) {
      val row: Int = (cell \ "@row").text.toInt
      val col: Int = (cell \ "@col").text.toInt
      val colorAttr: String = (cell \ "@color").text
      val color: Color = Color.toEnum(colorAttr)
      val isSet: Boolean = (cell \ "@isSet").text.toBoolean
      board = board.set(row, col, color, isSet)
    }

    val currentPlayerIndex: Int = (file \\ "currentPlayerIndex").text.toInt
    val stateString: String = (file \\ "state").text
    var players: List[Player] = Nil

    val playerNodes = file \\ "players"
    for (player <- playerNodes) {
      val name: String = (player \ "@name").text
      val colorAttr: String = (player \\ "@color").text
      val color: Color = Color.toEnum(colorAttr)
      val piecesLeft: Int = (player \ "@piecesLeft").text.toInt

      val playerObject = new Player(name, color, piecesLeft)
      players = players ::: playerObject :: Nil

    }

    val state = new State(currentPlayerIndex, players, stateString)

    (board, state)

  }

  override def save(board: BoardInterface, state: State): Unit = saveString(board)

  def saveString(board: BoardInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("grid.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(boardToXml(board))
    pw.write(xml)
    pw.close

  }

  def boardToXml(board: BoardInterface) = {

    <board>
      {
      for {
        row <- 0 until board.sizeOfRows
        col <- 0 until board.sizeOfCols
      } yield cellToXml(board, row, col)
      }
    </board>
  }

  def cellToXml(board: BoardInterface, row: Int, col: Int): Unit = {
    <cell row={ row.toString } col={ col.toString }  isSet={ board.cell(row, col).isSet.toString }>
     color= { board.cell(row, col).color.toString }
    </cell>
  }
}
