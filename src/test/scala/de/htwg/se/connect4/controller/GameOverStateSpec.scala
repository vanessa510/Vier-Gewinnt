package de.htwg.se.connect4.controller

import de.htwg.se.connect4.aview.Tui
import de.htwg.se.connect4.model.{Board, Color, Player}
import org.scalatest.{Matchers, WordSpec}

class GameOverStateSpec extends WordSpec with Matchers {
  "A Game Over State" should {

    val board = new Board(2, 3, false)
    val players: List[Player] = Player("test1", Color.RED, piecesLeft = 1) :: Player("test2", Color.YELLOW, piecesLeft = 0) :: Nil
    val controller = new Controller(board, players)
    val tui = new Tui(controller)
    controller.state = InGameState(controller)

    "print out game over string and change state to in Game state" in {
      controller.handle("1 2", board)

      controller.getString should startWith("Game over. No pieces left. Press 'n' to start a new game.")

      tui.processInputLine("n", board)

      controller.state shouldBe InGameState(controller)

    }

    "A Game Over State" should {
      val board = new Board(2, 3, false)
      val players: List[Player] = Player("test1", Color.RED, piecesLeft = 0) :: Player("test2", Color.YELLOW, piecesLeft = 0) :: Nil
      val controller = new Controller(board, players)
      val tui = new Tui(controller)

      "change state to inGameState" in {
        controller.state = GameOverState(controller)

        controller.state.nextState() shouldBe InGameState(controller)
      }
    }

  }

}
