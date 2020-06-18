package de.htwg.se.connect4.controller

import de.htwg.se.connect4.model.{Board, Color, Player}
import org.scalatest.{Matchers, WordSpec}

class ControllerSpec extends WordSpec with Matchers {

  "A Controller is managing the game. A controller" should {

    val board = new Board(2, 3, false)
    val players: List[Player] = Player("test1", Color.RED) :: Player("test2", Color.YELLOW) :: Nil
    val controller = new Controller(board, players)


    "print welcome String" in {
      val welcomeString = controller.getWelcomeString

      welcomeString should startWith("Welcome to connect 4. Please Enter your names.")
    }

    "set cells correctly" in {

      controller.set(1, 2)

      controller.board.cell(1, 2).isSet shouldBe true
    }

    "prevent cetting cell when already set" in {
      controller.set(1, 2)

      controller.board.cell(1, 2).color shouldBe Some(Color.RED)
    }

    "move to next player" in {
      val index = controller.currentPlayerIndex

      var newIndex = controller.getNextPlayerIndex

      newIndex should not equal index
      controller.currentPlayerIndex = newIndex

      newIndex = controller.getNextPlayerIndex

      newIndex should equal(index)

    }

    "create new Board" in {
      controller.createNewBoard(1, 1)

      controller.players.head.piecesLeft shouldBe 21
      controller.players(1).piecesLeft shouldBe 21
      controller.board.sizeOfCols shouldBe 7
      controller.board.sizeOfRows shouldBe 6

    }

  }

  "Controller" should {

    val board = new Board(2, 3, false)
    val noPlayers: List[Player] = Nil

    val controller = new Controller(board, noPlayers)

    "add players correctly" in {
      controller.addPlayer("test1")
      controller.addPlayer("test2")

      val players: List[Player] = Player("test1", Color.RED) :: Player("test2", Color.YELLOW) :: Nil

      controller.players shouldBe players

    }
  }

  "Controller" should {
    val board = new Board(2, 3, false)
    val players: List[Player] = Player("test1", Color.RED, 0) :: Player("test2", Color.YELLOW, 0) :: Nil
    val controller = new Controller(board, players)

    "detect no pieces left" in {
      controller.playersHaveNoPiecesLeft shouldBe true
    }
  }

  "Controller" should {
    val board = new Board(2, 3, false)
    val players: List[Player] = Player("test1", Color.RED, 1) :: Player("test2", Color.YELLOW, 0) :: Nil
    val controller = new Controller(board, players)

    "return false if one player has stil some pieces left" in {
      controller.playersHaveNoPiecesLeft shouldBe false
    }
  }

  "Controller" should {
    var board = new Board(2, 4, false)
    board = board.set(0, 0, Color.RED, true)
    board = board.set(0, 1, Color.RED, true)
    board = board.set(0, 2, Color.RED, true)
    board = board.set(0, 3, Color.RED, true)
    val players: List[Player] = Player("test1", Color.RED, 1) :: Player("test2", Color.YELLOW, 0) :: Nil
    val controller = new Controller(board, players)

    "detect win" in {
      controller.playerWin(0, 3) shouldBe true
    }
  }

  "Controller" should {
    var board = new Board(2, 4, false)
    board = board.set(0, 0, Color.RED, true)
    board = board.set(0, 1, Color.RED, true)
    board = board.set(0, 2, Color.RED, true)
    val players: List[Player] = Player("test1", Color.RED, 1) :: Player("test2", Color.YELLOW, 0) :: Nil
    val controller = new Controller(board, players)

    "detect no win" in {
      controller.playerWin(0, 2) shouldBe false
    }
  }

  "Controller " should {
    var board = new Board(2, 4, false)
    val players: List[Player] = Player("test1", Color.RED, 1) :: Player("test2", Color.YELLOW, 0) :: Nil
    val controller = new Controller(board, players)

    "undo step" in {
      controller.undo() should startWith("Undo Step.")
    }
  }

  "Controller " should {
    var board = new Board(2, 4, false)
    val players: List[Player] = Player("test1", Color.RED, 1) :: Player("test2", Color.YELLOW, 0) :: Nil
    val controller = new Controller(board, players)

    "redo step" in {
      controller.redo should startWith("Redo Step.")
    }
  }

}
