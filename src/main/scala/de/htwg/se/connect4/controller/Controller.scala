package de.htwg.se.connect4.controller

import de.htwg.se.connect4.model.{Board, Color, Player}
import de.htwg.se.connect4.util.Observable

class Controller(var board: Board, var players: List[Player]) extends Observable {

  var currentPlayerIndex: Int = 0

  def getWelcomeString: String = "Welcome to connect 4. Please Enter your names."

  def set(row: Int, col: Int): String = {
    if (board.cell(row, col).isSet) {
      notifyObservers
      "Cell is already set. Please chose different one."

    } else {

      board = board.set(row, col, players(currentPlayerIndex).color)
      players = players.updated(currentPlayerIndex, players(currentPlayerIndex).setPiece())
      currentPlayerIndex = getNextPlayerIndex
      notifyObservers
      "It's your turn Player " + players(currentPlayerIndex).playerName

    }
  }

  def getNextPlayerIndex: Int = if (currentPlayerIndex == 0) 1 else 0


  def boardToString: String = board.getBoardAsString(board.cells)


  def addPlayer(input: String): Unit = {
    if (players.isEmpty) players = players ::: Player(input, Color.RED) :: Nil
    else players = players ::: Player(input, Color.YELLOW) :: Nil
  }

  def createNewBoard(rows: Int, cols: Int): String = {
    board = new Board(rows, cols, false)
    notifyObservers
    "created new Board"
  }


}
