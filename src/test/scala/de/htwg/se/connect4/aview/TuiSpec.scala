package de.htwg.se.connect4.aview

import de.htwg.se.connect4.controller.Controller
import de.htwg.se.connect4.model.{Board, Color, Player}
import org.scalatest.{Matchers, WordSpec}

class TuiSpec extends WordSpec with Matchers {


  "A Tui represents the game with text. A Tui" when {


    "gets input to create new Board with 'n' " should {

      val board = new Board(2, 3, false)
      val players: List[Player] = Player("test", Color.RED) :: Nil
      val controller = new Controller(board, players)
      val tui = new Tui(controller)

      tui.processInputLine("n", board)

      "return new Board" in {
        controller.board should be(new Board(6, 7, false))
      }
    }

    "gets integer input" should {
      val board = new Board(2, 3, false)
      val players: List[Player] = Player("test1", Color.RED) :: Player("test2", Color.YELLOW) :: Nil
      val controller = new Controller(board, players)
      val tui = new Tui(controller)


      tui.processInputLine("1 2", board)

      "mark cell and set to true" in {
        controller.board.cell(1, 2).isSet shouldBe true
      }


    }

    "gets no integer input " should {
      val board = new Board(2, 3, false)
      val players: List[Player] = Player("test", Color.RED) :: Nil
      val controller = new Controller(board, players)
      val tui = new Tui(controller)



      "print error message" in {
        tui.processInputLine("a", board) should startWith ("Please Enter two numbers separated by a whitespace.")

      }
    }

    "gets to many arguments" should {
      val board = new Board(2, 3, false)
      val players: List[Player] = Player("test", Color.RED) :: Nil
      val controller = new Controller(board, players)
      val tui = new Tui(controller)



      "print error message" in {
        tui.processInputLine("1,2,3", board) should startWith ("Please Enter two numbers separated by a whitespace.")
      }
    }

    "gets input out of board" should {
      val board = new Board(2, 3, false)
      val players: List[Player] = Player("test", Color.RED) :: Nil
      val controller = new Controller(board, players)
      val tui = new Tui(controller)

      "print error message" in {
        tui.processInputLine("5,6", board) should startWith ("Please Enter two numbers separated by a whitespace.")
      }
    }




  }

}
