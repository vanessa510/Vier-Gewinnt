package de.htwg.se.connect4.model.fileIoComponent

import de.htwg.se.connect4.model.boardComponent.BoardInterface

trait FileIoInterface {

  def load: BoardInterface
  def save(grid: BoardInterface): Unit

}
