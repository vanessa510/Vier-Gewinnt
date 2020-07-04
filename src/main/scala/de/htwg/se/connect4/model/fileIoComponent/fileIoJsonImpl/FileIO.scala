package de.htwg.se.connect4.model.fileIoComponent.fileIoJsonImpl

import com.google.inject.Guice
import de.htwg.se.connect4.Connect4Module
import de.htwg.se.connect4.controller.controllerComponent.controllerBaseImpl.State
import de.htwg.se.connect4.model.boardComponent.boardBaseImpl.{BoardSizeStrategy, Color}
import de.htwg.se.connect4.model.boardComponent.boardBaseImpl.Color.Color
import de.htwg.se.connect4.model.boardComponent.{BoardInterface, CellInterface}
import de.htwg.se.connect4.model.fileIoComponent.FileIoInterface
import de.htwg.se.connect4.model.playerComponent.Player
import play.api.libs.json.{JsNumber, JsString, JsValue, Json, Writes}

import scala.io.Source

class FileIO extends FileIoInterface {

  /*override def load: BoardInterface = {
    var board: BoardInterface = null
    val source: String = Source.fromFile("board.json").getLines.mkString
    val json: JsValue = Json.parse(source)

    val sizeOfRows = (json \ "board" \ "row").get.toString.toInt
    val sizeOfCols = (json \ "board" \ "col").get.toString.toInt

    board = BoardSizeStrategy.execute(sizeOfRows, sizeOfCols)

    val injector = Guice.createInjector(new Connect4Module)

    for (index <- 0 until sizeOfRows * sizeOfCols) {
      val row = (json \ "board" \ "cells" \\ "row") (index).as[Int]
      val col = (json \ "board" \ "cells" \\ "col") (index).as[Int]
      val cell = (json \\ "cell") (index)
      val isSet = (cell \ "isSet").as[Boolean]
      val color = (cell \ "color" \ "color").as[Color]

      board = board.set(row, col, color, isSet)

    }


    board
  }*/


  override def load: (BoardInterface, State) = {
    var board: BoardInterface = null
    val source: String = Source.fromFile("board.json").getLines.mkString
    val json: JsValue = Json.parse(source)

    val sizeOfRows = (json \ "board" \ "row").get.toString.toInt
    val sizeOfCols = (json \ "board" \ "col").get.toString.toInt

    board = BoardSizeStrategy.execute(sizeOfRows, sizeOfCols)

    val injector = {
      Guice.createInjector(new Connect4Module)
    }

    for (index <- 0 until sizeOfRows * sizeOfCols) {
      val row = (json \ "board" \ "cells" \\ "row") (index).as[Int]
      val col = (json \ "board" \ "cells" \\ "col") (index).as[Int]
      val cell = (json \\ "cell") (index)
      val isSet = (cell \ "isSet").as[Boolean]
      val color = (cell \ "color" \ "color").as[Color]

      board = board.set(row, col, color, isSet)

    }

    val currentPlayerIndex = (json \ "currentPlayerIndex").get.toString().toInt
    var players:List[Player] = Nil

    for (index <- 0 until 2) {
      val name = (json \ "players" \\ "name") (index).as[String]
      val piecesLeft = (json \ "players" \\ "piecesLeft") (index).as[Int]
      val playerColor = (json \ "players") (index)("color")
      val color = (playerColor \ "color" ).as[Color]


      val player = new Player(name, color, piecesLeft)
      players = players ::: player :: Nil

    }



    /*for (index <- 0 until 2) {
     if (index == 0) {
        val name = (json \ "players" \\ "name")(index).toString()
        val piecesLeft = (json \ "players" \\ "piecesLeft")(index).toString().toInt
        val valuePlayer = (json \ "players") (index)
        val colorObject = (valuePlayer \\ "color")(index)
        val color = (colorObject \ "color").get.toString()
        val player = new Player(name, getColor(color), piecesLeft)
        players = players ::: player :: Nil
      } else {
        val name = (json \ "players" \\ "name")(index).toString()
        val piecesLeft = (json \ "players" \\ "piecesLeft")(index).toString().toInt
        val valuePlayer = (json \ "players") (index)
        val colorObject = (valuePlayer \\ "color")(index)
        val color = (colorObject \ "color").get.toString()

        val player = new Player(name, getColor(color), piecesLeft)
        players = players ::: player :: Nil
      }

      val a = 4



    /*  val colorObject = (valuePlayer \\ "color")(index)
      val color = (colorObject \ "color").get.toString()*/




    }*/

    val state = (json \ "state").get.toString()

    val stateToLoad = new State(currentPlayerIndex, players, state)

    (board, stateToLoad)

  }

  override def save(board: BoardInterface, state: State): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("board.json"))
    pw.write(Json.prettyPrint(gridToJson(board, state)))
    pw.close
  }


  def gridToJson(board: BoardInterface, state: State) = {
    Json.obj(
      "currentPlayerIndex" -> JsNumber(state.currentPlayerIndex),
      "state" -> JsString(state.state),
      "players" -> Json.toJson(
        for {
          index <- state.players.indices

        } yield {
          Json.obj(
            "name" -> state.players(index).playerName,
            "color" -> state.players(index).color,
            "piecesLeft" -> state.players(index).piecesLeft,
          )
        }

      ),
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

  def getColor(color: String): Color.Value = {
    color match {
      case "\"red\"" => Color.RED
      case "\"yellow\"" => Color.YELLOW
      case "\"empty\"" => Color.EMPTY
    }
  }


  implicit val cellWrites = new Writes[CellInterface] {
    def writes(cell: CellInterface) = Json.obj(
      "isSet" -> cell.isSet,
      "color" -> cell.color

    )
  }
}


