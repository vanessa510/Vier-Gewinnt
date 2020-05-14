package de.htwg.se.connect4.model


import org.scalatest.{Matchers, WordSpec}


class BoardSpec extends WordSpec with Matchers {
  "A Board is the Gamefield of Connect4. A Board" when {

    "to be constructed" should {
      "be created with the size 6x7 and Cells should be initialized with false" in {
        val board = new Board(6, 7, false)
        board.sizeOfRows should be(6)
        board.sizeOfCols should be(7)

      }

      "for test purpose created with a Matrix of Cells" in {
        val board = new Board(Matrix[Cell](Vector(Vector(Cell(false), Cell(false)), Vector(Cell(false), Cell(false)))))

      }

      "created should be empty. For test purpose only a small board with 2x3 is created." should {
        val board = new Board(2, 3, false)

        "give access to its Cells" in {
          board.cell(0, 0) should be(Cell(false))
          board.cell(0, 1) should be(Cell(false))
          board.cell(0, 2) should be(Cell(false))
          board.cell(1, 0) should be(Cell(false))
          board.cell(1, 1) should be(Cell(false))
          board.cell(1, 2) should be(Cell(false))
        }

      }
      "get access to cols and rows" should {
        val board = new Board(2, 3, false)

        "return requested cols and rows" in {
          board.row(0) should be(Set(Vector.fill(3)(Cell(false))))
          board.col(2) should be(Set(Vector.fill(2)(Cell(false))))

        }

      }

      "empty" should {
        val board = new Board(2, 3, false)

        "be printed out empty" in {
          val boardAsString = board.getBoardAsString(board.cells)

          boardAsString should startWith(
            "\n" +
              " __  __  __ \n" +
              " - | - | - |\n" +
              " __  __  __ \n" +
              " - | - | - |")

        }

      }
      "first piece from red player is set" should {
        val board = new Board(2, 3, false)
        val newBoard = Board(board.cells.replaceCell(1, 1, Cell(true, Some("red"))))
        "print one cell set at second row and second col with red piece" in {
          val boardAsString = newBoard.getBoardAsString(newBoard.cells)

          boardAsString should startWith(
            "\n" +
              " __  __  __ \n" +
              " - | - | - |\n" +
              " __  __  __ \n" +
              " - | r | - |")
        }
      }

      "first piece from yellow player is set" should {
        val board = new Board(2, 3, false)
        val newBoard = Board(board.cells.replaceCell(1, 1, Cell(true, Some("yellow"))))
        "print one cell set at second row and second col with yellow piece" in {
          val boardAsString = newBoard.getBoardAsString(newBoard.cells)

          boardAsString should startWith(
            "\n" +
              " __  __  __ \n" +
              " - | - | - |\n" +
              " __  __  __ \n" +
              " - | y | - |")
        }
      }

      "piece with unspecified color is set" should {
        val board = new Board(2, 3, false)
        val newBoard = Board(board.cells.replaceCell(1, 1, Cell(true, Some("grün"))))
        "print one cell set at second row and second col with unspecified color" in {
          val boardAsString = newBoard.getBoardAsString(newBoard.cells)

          boardAsString should startWith(
            "\n" +
              " __  __  __ \n" +
              " - | - | - |\n" +
              " __  __  __ \n" +
              " - | ? | - |")
        }
      }
    }
  }

}
