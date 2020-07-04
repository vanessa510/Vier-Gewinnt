package de.htwg.se.connect4.model.fileIoComponent.fileIoJsonImpl

import de.htwg.se.connect4.model.boardComponent.BoardInterface
import de.htwg.se.connect4.model.fileIoComponent.FileIoInterface
import play.api.libs.json.{JsValue, Json}

import scala.io.Source

class FileIO extends FileIoInterface {

  override def load: BoardInterface = {
    var grid: BoardInterface = null
    val source: String = Source.fromFile("grid.json").getLines.mkString
    val json: JsValue = Json.parse(source)

    grid
  }

  override def save(grid: BoardInterface): Unit = ???
}
