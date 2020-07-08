import de.htwg.se.connect4.model.boardComponent.BoardInterface
import de.htwg.se.connect4.model.boardComponent.boardBaseImpl.Color.Color
import de.htwg.se.connect4.model.boardComponent.boardBaseImpl.{Board, Color}

def loadBoard(board: BoardInterface): Unit = {

  val xml = <cell row="0" col="0" isSet="true">color=red</cell>

  val cellNodes = println(xml \ "cell")
  val colorAttr: String = (xml \ "@color").text
  val color: Color = Color.toEnum(colorAttr)
  val isSet: Boolean = (xml \ "@isSet").text.toBoolean
  val a = 3
}




val board = new Board(5, 4, false)
loadBoard(board)






