package de.htwg.se.connect4.model

case class Player(playerName: String, color: Color.Value, piecesLeft: Int = 21) {

  override def toString: String = playerName

  def setPiece(): Player = copy(piecesLeft = piecesLeft - 1)
}