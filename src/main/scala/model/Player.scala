package model

case class Player(playerName:String, color:String, var piecesLeft:Int = 21) {

  def setPiece(): Player = copy(piecesLeft = piecesLeft - 1)
}
