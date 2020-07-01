package de.htwg.se.connect4.model

import de.htwg.se.connect4.model.boardComponent.boardBaseImpl.Color
import org.scalatest.{Matchers, WordSpec}

class PlayerSpec extends WordSpec with Matchers {
  "A player represents a participant. A player" when {

    "is initialized" should {
      val player = Player("Player1", Color.RED)

      "has 21 pieces at beginning" in {
        player.piecesLeft should be(21)
      }
    }

    "has a name and it" should {
      val player = Player("Player1", Color.RED)

      "be printed" in {
        player.toString
      }
    }

    "is playing" should {
      val player = Player("Player1", Color.RED)

      "set pieces and reduce amount of left pieces" in {
        val newPlayer = player.setPiece()

        newPlayer.piecesLeft should be(20)

        val secondRoundPlayer = newPlayer.setPiece()
        secondRoundPlayer.piecesLeft should be(19)

      }
    }
  }

}
