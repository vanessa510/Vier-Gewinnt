package de.htwg.se.connect4.model

import org.scalatest.{Matchers, WordSpec}

class CellSpec extends WordSpec with Matchers {

  "A Cell represents the place for a gaming piece" when {

    "is created" should {
      val cell = Cell(false)

      "be initialized with false and no color" in {
        cell.isSet should be(false)
        cell.color should be(None)
      }

    }

    "a piece is set" should {
      val redCell = Cell(true, Some(Color.RED))

      "be red if red player set a piece" in {
        redCell.isSet should be(true)
        redCell.color should be(Some("red"))
      }

      val yellowCell = Cell(true, Some(Color.YELLOW))

      "be initialized if yellow player set a piece" in {
        yellowCell.isSet should be(true)
        yellowCell.color should be(Some("yellow"))
      }

    }

  }

}
