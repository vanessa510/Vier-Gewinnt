package de.htwg.se.connect4.model.boardComponent.boardBaseImpl

import de.htwg.se.connect4.model.boardComponent.CellInterface

case class Cell(isSet: Boolean, color: Option[Color.Value] = Option.empty) extends CellInterface
