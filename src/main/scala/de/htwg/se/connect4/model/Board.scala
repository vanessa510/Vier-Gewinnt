package de.htwg.se.connect4.model


case class Board(cells: Matrix[Cell]) {

  def this(sizeOfRows: Int, sizeOfCol: Int, isSet: Boolean) = this(new Matrix[Cell](sizeOfRows, sizeOfCol, Cell(isSet)))

  def sizeOfRows: Int = cells.sizeOfRows

  def sizeOfCols: Int = cells.rows(0).length

  def cell(row: Int, col: Int): Cell = cells.cell(row, col)

  def col(col: Int): Set = Set(cells.rows.map(row => row(col)))

  def row(row: Int): Set = Set(cells.rows(row))

  def getBoardAsString(matrix: Matrix[Cell]): String = {
    val rows = matrix.sizeOfRows
    val cols = matrix.sizeOfCols
    var returnString = ""
    val oneLine = " __ " * cols

    for {
      row <- 0 until rows
      col <- 0 until cols

    } {

      if (col == 0) returnString += "\n" + oneLine + "\n"
      if (matrix.cell(row, col).isSet) {
        matrix.cell(row, col).color match {
          case Some("red") => returnString += " r |"
          case Some("yellow") => returnString += " y |"
          case _ => returnString +=  " ? |"

        }
      } else {
        returnString += " - |"
      }
    }

    returnString
  }
}
