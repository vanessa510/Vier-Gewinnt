package de.htwg.se.connect4.controller

import de.htwg.se.connect4.aview.Tui
import de.htwg.se.connect4.model.{Board, Color, Player}
import org.scalatest.{Matchers, WordSpec}

class PlayerWinStateSpec extends WordSpec with Matchers {

  "A Player Win State" when {
    val board = new Board(2, 3, false)
    val players: List[Player] = Player("test1", Color.RED, piecesLeft = 0) :: Player("test2", Color.YELLOW, piecesLeft = 0) :: Nil
    val controller = new Controller(board, players)
    val tui = new Tui(controller)

    controller.state = PlayerWinState(controller, "test1")

    "print correct string" in {
      controller.getString should startWith ("Congratulations! Player test1 You win.")

    }

    "trigger next state" in {
      controller.state.nextState() shouldBe InGameState(controller)
    }


  }

}
