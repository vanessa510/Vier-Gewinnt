package de.htwg.se.connect4.controller

import de.htwg.se.connect4.model.{Board, BoardSizeStrategy, Color, Player}
import de.htwg.se.connect4.util.{Observable, UndoManager}

class Controller(var board: Board, var players: List[Player]) extends Observable {

  var state: ControllerState = InGameState(this)
  var currentPlayerIndex: Int = 0
  private val undoManager = new UndoManager


  def handle(input: String, board: Board): String = {
    state.handle(input, board)
  }

  def getWelcomeString: String = "Welcome to connect 4. Please Enter your names."

  def set(row: Int, col: Int): String = {
    if (board.cell(row, col).isSet) {
      notifyObservers
      "Cell is already set. Please chose different one."

    } else {

      undoManager.doStep(new SetCommand(row, col, players(currentPlayerIndex), this, true))
      players = players.updated(currentPlayerIndex, players(currentPlayerIndex).setPiece())

      if (playerWin(row, col)) return triggerNextStateAndEvaluateInput

      if (playersHaveNoPiecesLeft) return triggerNextStateAndEvaluateInput


      currentPlayerIndex = getNextPlayerIndex
      notifyObservers
      getPlayerDemandString

    }
  }

  private def triggerNextStateAndEvaluateInput: String = {
    state = state.nextState()
    notifyObservers
    state.handle("", board)
  }

  def playerWin(row: Int, col: Int): Boolean = {
    val matchInCols = board.checkCols(col, players(currentPlayerIndex).color)
    val matchInRows = board.checkRow(row, players(currentPlayerIndex).color)

    matchInCols || matchInRows
  }

  def playersHaveNoPiecesLeft: Boolean = if (players.head.piecesLeft == 0 && players(1).piecesLeft == 0) true else false


  def getNextPlayerIndex: Int = if (currentPlayerIndex == 0) 1 else 0


  def boardToString: String = board.getBoardAsString(board.cells)


  def addPlayer(input: String): Unit = {
    if (players.isEmpty) players = players ::: Player(input, Color.RED) :: Nil
    else players = players ::: Player(input, Color.YELLOW) :: Nil
  }

  def createNewBoard(rows: Int, cols: Int): String = {
    board = BoardSizeStrategy.execute((rows, cols))
    currentPlayerIndex = 0
    var newPlayers: List[Player] = Nil
    newPlayers = newPlayers ::: players.head.copy(piecesLeft = 21) :: Nil
    newPlayers = newPlayers ::: players(1).copy(piecesLeft = 21) :: Nil
    players = newPlayers
    notifyObservers
    state = InGameState(this)
    "created new Board \n" + getPlayerDemandString
  }

  def getPlayerDemandString: String = "It's your turn Player " + players(currentPlayerIndex).playerName


  def getIncorrectInputMessage: String = "Please Enter two numbers separated by a whitespace."

  def undo(): String = {
    undoManager.undoStep()
    notifyObservers
    "Undo Step."
  }

  def redo: String = {
    undoManager.redoStep()
    notifyObservers
    "Redo Step."
  }

}
