package de.htwg.se.connect4.controller.controllerComponent

import de.htwg.se.connect4.model.{Board, Cell}

trait ControllerInterface {

  def getString: String

  def getWelcomeString: String

  def handle(input: String, board: Board): String

  def set(row: Int, col: Int): String

  def triggerNextStateAndEvaluateInput: Unit

  def playerWin(row: Int, col: Int): Boolean

  def playersHaveNoPiecesLeft: Boolean

  def getNextPlayerIndex: Int

  def boardToString: String

  def addPlayer(input: String): String

  def createNewBoard(rows: Int, cols: Int): String

  def getPlayerDemandString: String

  def getIncorrectInputMessage: String

  def undo(): String

  def redo: String

  def sizeOfRows: Int

  def sizeOfCols: Int

  def isSet(row: Int, col: Int): Boolean

  def cell(row: Int, col: Int): Cell


}
