import java.util.Optional


case class Cell (isSet:Boolean, color:Optional[String] = Optional.empty()) {
  def setColor():Unit = {}
}

val cell = Cell(false)
cell.isSet

case class Matrix[Cell](rows:Vector[Vector[Cell]]) {

  def this(sizeOfRows:Int, sizeOfCol:Int, cell: Cell) = this(Vector.tabulate(sizeOfRows, sizeOfCol){(row, col) => cell})

  def size:Int = rows.size

  def cell(row:Int, col:Int):Cell = rows(row)(col)

}

case class Board(cells: Matrix[Cell]) {
  def this(sizeOfRows:Int, sizeOfCol:Int, isSet:Boolean) = this(new Matrix[Cell](sizeOfRows, sizeOfCol, Cell(isSet)))

  def size: Int = cells.size

  def getBoardAsString(rows:Int, cols: Int): String = {
    var returnString = "\n"
   for (row <- 0 until rows) {
     returnString += "__|" * cols + "\n"
   }

    returnString
  }
}

val field = new Board(6,7, false)
field.size
field.getBoardAsString(4,5)
field.cells.cell(2,2)

val m = new Matrix(Vector(Vector(Cell(true))))

m.rows(0)(0).isSet

val m2 = new Matrix(2, 2, Cell(false))

m2.rows

case class Player(playerName:String, color:String, var piecesLeft:Int = 21) {

  def setPiece(): Player = copy(piecesLeft = piecesLeft - 1)
}

val player = Player("lisa", "rot")
player.piecesLeft
val updatedPlayer = player.setPiece()
updatedPlayer.piecesLeft
updatedPlayer.playerName
updatedPlayer.color

