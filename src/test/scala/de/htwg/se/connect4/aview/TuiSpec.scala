package de.htwg.se.connect4.aview

import de.htwg.se.connect4.controller.Controller
import de.htwg.se.connect4.model.{Board, Cell, Color, Matrix, Player}
import org.scalatest.{Matchers, WordSpec}

class TuiSpec extends WordSpec with Matchers {

  "A Tui represents the game with text. A Tui" when {

    "gets input to quit" should {
      val board = new Board(2, 3, false)
      val players: List[Player] = Player("test", Color.RED) :: Nil
      val controller = new Controller(board, players)
      val tui = new Tui(controller)



      val newBoard = tui.processInputLine("q", board)

      "return current board" in {
        newBoard shouldEqual board
      }
    }

    "gets input to create new Board" should {
      val board = new Board(2, 3, false)
      val players: List[Player] = Player("test", Color.RED) :: Nil
      val controller = new Controller(board, players)
      val tui = new Tui(controller)

      tui.processInputLine("n", board)

      "return new Board" in {

      }
    }

    "gets two valid integers as input" should {
      val board = new Board(2, 3, false)
      val players: List[Player] = Player("test", Color.RED) :: Nil
      val controller = new Controller(board, players)
      val tui = new Tui(controller)

      //var newBoard = tui.processInputLine("1 2", board)

      //newBoard = board.set(1,2,Color.RED)

      "mark cell as set with correct color" in {
        //newBoard.cell(1,2).isSet shouldBe true
        //newBoard.cell(1,2).color shouldBe Some(Color.RED)
      }

    }

    "gets two invalid integers as input" should {
      val board = new Board(2, 3, false)
      val players: List[Player] = Player("test", Color.RED) :: Nil
      val controller = new Controller(board, players)
      val tui = new Tui(controller)

      //var newBoard = tui.processInputLine("5 6", board)

      "do nothing" in {
        //newBoard shouldEqual board
      }

    }

    "gets random integer as input" should {
      val board = new Board(2, 3, false)
      val players: List[Player] = Player("test", Color.RED) :: Nil
      val controller = new Controller(board, players)
      val tui = new Tui(controller)

      //var newBoard = tui.processInputLine("1", board, Player("test", Color.RED))

      "do nothing" in {
       // newBoard shouldEqual board
      }

    }

    "gets random input" should {
      val board = new Board(2, 3, false)
      val players: List[Player] = Player("test", Color.RED) :: Nil
      val controller = new Controller(board, players)
      val tui = new Tui(controller)

      //var newBoard = tui.processInputLine("a", board, Player("test", Color.RED))

      "catch exception and do nothing" in {
        //newBoard shouldEqual board
      }
    }
  }

}
