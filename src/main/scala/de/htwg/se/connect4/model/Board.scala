package de.htwg.se.connect4.model

case class Board(cells: Matrix[Cell]) {

  def this(sizeOfRows:Int, sizeOfCol:Int, isSet:Boolean) = this(new Matrix[Cell](sizeOfRows, sizeOfCol, Cell(isSet)))

  def size: Int = cells.size

  def cell(row:Int, col:Int): Cell = cells.cell(row, col)

  def col(col:Int): Set = Set(cells.rows.map(row => row(col)))

  def row(row:Int): Set = Set(cells.rows(row))

  def getBoardAsString(rows:Int, cols: Int): String = {
    var returnString = "\n"
    for (row <- 0 until rows) {
      returnString += "__|" * cols + "\n"
    }

    returnString
  }

}
