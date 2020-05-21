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

      controller.set(1,2)

      controller.board.cell(1,2).isSet shouldBe true
    }

    "move to next player" in {
      val index = controller.currentPlayerIndex

      var newIndex = controller.getNextPlayerIndex

      newIndex should not equal index
      controller.currentPlayerIndex = newIndex

      newIndex = controller.getNextPlayerIndex

      newIndex should equal(index)

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



}
