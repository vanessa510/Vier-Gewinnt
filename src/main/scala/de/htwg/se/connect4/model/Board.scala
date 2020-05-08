package de.htwg.se.connect4.model

case class Board(cells: Matrix[Cell]) {

  def this(sizeOfRows:Int, sizeOfCol:Int, isSet:Boolean) = this(new Matrix[Cell](sizeOfRows, sizeOfCol, Cell(isSet)))

  def size: Int = cells.size

}
