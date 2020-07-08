package de.htwg.se.connect4.model

import de.htwg.se.connect4.model.boardComponent.boardBaseImpl.Color
import org.scalatest.{Matchers, WordSpec}

class ColorSpec extends WordSpec with Matchers {

  "A Color" when {

    "gets string" should {
      val stringRed = "red"
      val stringYellow = "yellow"
      val stringEmpty = "empty"

      "return correct enum" in {
        Color.toEnum(stringRed) shouldBe Color.RED
        Color.toEnum(stringYellow) shouldBe Color.YELLOW
        Color.toEnum(stringEmpty) shouldBe Color.EMPTY

      }
    }
  }

}
