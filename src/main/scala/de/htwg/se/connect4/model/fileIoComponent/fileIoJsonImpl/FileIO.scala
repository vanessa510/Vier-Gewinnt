package de.htwg.se.connect4.model.fileIoComponent.fileIoJsonImpl

import com.google.inject.Guice
import de.htwg.se.connect4.Connect4Module
import de.htwg.se.connect4.model.boardComponent.{BoardInterface, CellInterface}
import de.htwg.se.connect4.model.boardComponent.boardBaseImpl.BoardSizeStrategy
import de.htwg.se.connect4.model.boardComponent.boardBaseImpl.Color.Color
import de.htwg.se.connect4.model.fileIoComponent.FileIoInterface
import play.api.libs.json.{JsNumber, JsValue, Json, Writes}

import scala.io.Source

class FileIO extends FileIoInterface {

  override def load: BoardInterface = {
    var board: BoardInterface = null
    val source: String = Source.fromFile("board.json").getLines.mkString
    val json: JsValue = Json.parse(source)

    val sizeOfRows = (json \ "board" \ "row").get.toString.toInt
    val sizeOfCols = (json \ "board" \ "col").get.toString.toInt

    board = BoardSizeStrategy.execute(sizeOfRows, sizeOfCols)

    val injector = Guice.createInjector(new Connect4Module)

    for (index <- 0 until sizeOfRows * sizeOfCols) {
      val row = (json \ "board" \ "cells" \\ "row")(index).as[Int]
      val col = (json \ "board" \ "cells" \\ "col")(index).as[Int]
      val cell = (json \\ "cell")(index)
      val isSet = (cell \ "isSet").as[Boolean]
      val color = (cell \ "color" \ "color").as[Color]

      board = board.set(row, col, color, isSet)

    }


    board
  }

  override def save(board: BoardInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("board.json"))
    pw.write(Json.prettyPrint(gridToJson(board)))
    pw.close
  }


  def gridToJson(board: BoardInterface) = {
    Json.obj(
      "board" -> Json.obj(
        "row" -> JsNumber(board.sizeOfRows),
                "col" -> JsNumber(board.sizeOfCols),
        "cells" -> Json.toJson(
          for {
            row <- 0 until board.sizeOfRows
            col <- 0 until board.sizeOfCols
          } yield {
            Json.obj(
              "row" -> row,
              "col" -> col,
              "cell" -> Json.toJson(board.cell(row, col))
            )
          }
        )
      )
    )
  }

  implicit val cellWrites = new Writes[CellInterface] {
    def writes(cell: CellInterface) = Json.obj(
      "isSet" -> cell.isSet,
              "color" -> cell.color

    )
  }
}


