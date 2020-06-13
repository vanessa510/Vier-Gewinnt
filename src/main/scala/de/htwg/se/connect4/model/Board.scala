package de.htwg.se.connect4.model

import de.htwg.se.connect4.model.Color.Color

import scala.collection.mutable.ListBuffer


case class Board(cells: Matrix[Cell]) {

  def this(sizeOfRows: Int, sizeOfCol: Int, isSet: Boolean) = this(new Matrix[Cell](sizeOfRows, sizeOfCol, Cell(isSet)))

  def sizeOfRows: Int = cells.sizeOfRows

  def sizeOfCols: Int = cells.rows(0).length

  def cell(row: Int, col: Int): Cell = cells.cell(row, col)

  def col(col: Int): Set = Set(cells.rows.map(row => row(col)))

  def row(row: Int): Set = Set(cells.rows(row))

  def set(row: Int, col: Int, color: Color.Value, isSet: Boolean): Board = copy(cells.replaceCell(row, col, Cell(isSet, Some(color))))

  def checkRow(row: Int, color: Color): Boolean = {
    var pieces = new ListBuffer[Option[Color]]()
    for (col <- 0 until sizeOfCols) {
      pieces += cell(row, col).color
  }
    var counter = 0
    for (Some(elem) <- pieces) {if (elem.equals(color)) counter += 1 else counter = 0}

    if (counter >= 4) true else false
  }

  def checkCols(col: Int, color: Color): Boolean = {
    var pieces = new ListBuffer[Option[Color]]()
    for (row <- 0 until sizeOfRows) {
      pieces += cell(row, col).color
    }
    var counter = 0
    for (Some(elem) <- pieces) {if (elem.equals(color)) counter += 1 else counter = 0}

    if (counter >= 4) true else false

  }

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
          case Some(Color.RED) => returnString += " r |"
          case Some(Color.YELLOW) => returnString += " y |"
          case Some(Color.EMPTY) => returnString += " - |"


        }
      } else {
        returnString += " - |"
      }
    }

    returnString
  }
}
