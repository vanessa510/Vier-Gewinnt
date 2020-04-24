case class Cell( isSet:Boolean)

val cell = Cell(false)
cell.isSet

case class Matrix[Cell](rows:Vector[Vector[Cell]]) {

  def this(sizeOfRows:Int, sizeOfCol:Int, cell: Cell) = this(Vector.tabulate(sizeOfRows, sizeOfCol){(row, col) => cell})

  def size:Int = rows.size

  def cell(row:Int, col:Int):Cell = rows(row)(col)

}

case class Field(cells: Matrix[Cell]) {
  def this(sizeOfRows:Int, sizeOfCol:Int, isSet:Boolean) = this(new Matrix[Cell](sizeOfRows, sizeOfCol, Cell(isSet)))
  def size: Int = cells.size

}
val field = new Field(6,7, false)
field.size
field.cells.cell(2,2)

val m = new Matrix(Vector(Vector(Cell(true))))

m.rows(0)(0).isSet

val m2 = new Matrix(2, 2, Cell(false))



