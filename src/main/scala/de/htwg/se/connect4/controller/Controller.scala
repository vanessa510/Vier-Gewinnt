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

      if (playerWin(row, col)) return "Congratulations " + players(currentPlayerIndex).playerName + "You win."

      if (playersHaveNoPiecesLeft) return "Game over. No pieces left. Press 'n' to start a new game."

      currentPlayerIndex = getNextPlayerIndex
      notifyObservers
      getPlayerDemandString

    }
  }

  def playerWin(row: Int, col: Int): Boolean = {
    val matchInCols =  board.checkCols(col, players(currentPlayerIndex).color)
    val matchInRows = board.checkRow(row, players(currentPlayerIndex).color)

    matchInCols || matchInRows
  }

  def playersHaveNoPiecesLeft: Boolean = {
    if (players.head.piecesLeft == 0 && players(1).piecesLeft == 0) return true

    false

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

  def getPlayerDemandString: String = {
    "It's your turn Player " + players(currentPlayerIndex).playerName
  }

  def getIncorrectInputMessage: String = {
    "Please Enter two numbers separated by a whitespace."
  }

}
