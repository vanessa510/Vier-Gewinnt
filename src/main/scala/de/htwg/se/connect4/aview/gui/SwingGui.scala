package de.htwg.se.connect4.aview.gui

import de.htwg.se.connect4.controller.Controller

import scala.swing._
import scala.swing.event.{ButtonClicked, Key, KeyPressed}

class SwingGui(controller: Controller) extends Frame {

  title = "Connect 4"

  var cells = Array.ofDim[CellPanel](controller.board.sizeOfRows, controller.board.sizeOfCols)


  def welcomePanel: BoxPanel = new BoxPanel(Orientation.Vertical) {

    centerOnScreen()

    val playerNameTextField: TextField = new TextField() {
      listenTo(keys)
      reactions += {
        case KeyPressed(_, Key.Enter, _, _) => controller.addPlayer(text)
      }
    }

    val nextPlayerButton: Button = new Button("next Player")
    listenTo(nextPlayerButton)

    val nextPanelButton: Button = new Button("next")
    listenTo(nextPanelButton)

    contents += new BoxPanel(Orientation.Vertical) {
      contents += playerNameTextField
    }

    contents += new BoxPanel(Orientation.Horizontal) {
      contents += nextPlayerButton
    }

    reactions += {
      case ButtonClicked(`nextPanelButton`) =>  switchPanel
      case ButtonClicked(`nextPlayerButton`) => contents.clear; contents += playerNameTextField; contents += nextPanelButton; repaint()
    }
  }

  def boardPanel = new GridPanel(controller.board.sizeOfCols, controller.board.sizeOfRows) {
    background = java.awt.Color.BLACK
    centerOnScreen()

    for {
      row <- 0 until controller.board.sizeOfRows
      col <- 0 until controller.board.sizeOfCols
    } {
      val cellPanel = new CellPanel(row, col, controller)
      cells(row)(col) = cellPanel
      contents += cellPanel
      listenTo(cellPanel)
    }
  }

  def switchPanel = {
      contents = new BorderPanel {
        add(boardPanel, BorderPanel.Position.Center)
      }

      redraw
      repaint
  }

   def redraw = {
    for {
      row <- 0 until controller.board.sizeOfRows
      column <- 0 until controller.board.sizeOfCols
    } cells(row)(column).redraw

     repaint()

  }

  contents = welcomePanel


  visible = true

}
