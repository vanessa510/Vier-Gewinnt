package de.htwg.se.connect4.model


case class Cell(isSet: Boolean, color: Option[Color.Value] = Option.empty)
