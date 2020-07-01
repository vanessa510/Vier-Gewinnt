package de.htwg.se.connect4.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.connect4.controller.controllerComponent.ControllerInterface
import de.htwg.se.connect4.model._
import de.htwg.se.connect4.util.{Observable, UndoManager}

class Controller(var board: Board, var players: List[Player]) extends Observable with ControllerInterface {

  var state: ControllerState = InitializationState(this)
  var currentPlayerIndex: Int = 0
  private val undoManager = new UndoManager

  def getString: String = state.getString()


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

      if (playerWin(row, col)) {
        triggerNextStateAndEvaluateInput
        return ""
      }

      if (playersHaveNoPiecesLeft) {
        triggerNextStateAndEvaluateInput
        return ""
      }

      currentPlayerIndex = getNextPlayerIndex
      notifyObservers
      ""
    }
  }

   def triggerNextStateAndEvaluateInput: Unit = {
    state = state.nextState()
    notifyObservers

  }

  def playerWin(row: Int, col: Int): Boolean = {
    val matchInCols = board.checkCols(col, players(currentPlayerIndex).color)
    val matchInRows = board.checkRow(row, players(currentPlayerIndex).color)
    val matchDiagonal = board.checkDiagonal(row, col, players(currentPlayerIndex).color)

    matchInCols || matchInRows || matchDiagonal
  }

  def playersHaveNoPiecesLeft: Boolean = if (players.head.piecesLeft == 0 && players(1).piecesLeft == 0) true else false


  def getNextPlayerIndex: Int = if (currentPlayerIndex == 0) 1 else 0


  def boardToString: String = board.getBoardAsString(board.cells)


  def addPlayer(input: String): String = {
    if (players.isEmpty) {
      players = players ::: Player(input, Color.RED) :: Nil
    }
    else if (players.size < 2) {
      players = players ::: Player(input, Color.YELLOW) :: Nil
      triggerNextStateAndEvaluateInput
    }
    else triggerNextStateAndEvaluateInput
    ""
  }

  def createNewBoard(rows: Int, cols: Int): String = {
    board = BoardSizeStrategy.execute((rows, cols))
    currentPlayerIndex = 0
    var newPlayers: List[Player] = Nil
    newPlayers = newPlayers ::: players.head.copy(piecesLeft = 21) :: Nil
    newPlayers = newPlayers ::: players(1).copy(piecesLeft = 21) :: Nil
    players = newPlayers
    state = InGameState(this)
    notifyObservers
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

  def sizeOfRows: Int = board.sizeOfRows

  def sizeOfCols: Int = board.sizeOfCols

  def isSet(row: Int, col: Int): Boolean = board.cell(row, col).isSet

  def cell(row: Int, col: Int): Cell = board.cell(row, col)

  def getCell(row: Int, col: Int): Cell = board.cell(row, col)

  def getBoard: Board = board

}
