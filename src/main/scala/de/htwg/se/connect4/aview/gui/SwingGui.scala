package de.htwg.se.connect4.aview.gui

import scala.swing._
import de.htwg.se.connect4.controller.Controller

import scala.swing.Swing.LineBorder
import scala.swing.event.{ButtonClicked, Key, KeyPressed}


class SwingGui(controller: Controller) extends Frame {

  title = "Connect 4"

  var cells = Array.ofDim[CellPanel](controller.sizeOfRows, controller.sizeOfCols)

  def welcomePanel = new BoxPanel(Orientation.Vertical) {
    val nameTextField = new TextField() {
      listenTo(keys)
      reactions += {
        case KeyPressed(_, Key.Enter, _, _) => controller.addPlayer(text)
      }
    }

    val nextPlayerButton = new Button("next Player")
    val nextPanelButton = new Button("start game")

    listenTo(nextPlayerButton)
    listenTo(nextPanelButton)

    contents += new BoxPanel(Orientation.Vertical) {
      contents += nameTextField
    }

    contents += new BoxPanel(Orientation.Horizontal) {
      contents += nextPlayerButton
    }

    reactions += {
      case ButtonClicked(`nextPlayerButton`) => controller.handle(nameTextField.text, controller.board);
        contents.clear; contents += nameTextField; contents += nextPanelButton; repaint()
      case ButtonClicked(`nextPanelButton`) => controller.handle(nameTextField.text, controller.board); changePanel
    }
  }

  def changePanel = {
    contents = new BorderPanel {
      add(gridPanel, BorderPanel.Position.Center)
    }

    redraw
  }



  def gridPanel = new GridPanel(controller.sizeOfRows, controller.sizeOfCols) {
    border = LineBorder(java.awt.Color.BLACK, 2)
    background = java.awt.Color.BLACK

    for {
      row <- 0 until controller.sizeOfRows
      col <- 0 until controller.sizeOfCols
    } {
      val cellPanel = new CellPanel(row, col, controller)
      cells(row)(col) = cellPanel
      contents += cellPanel
      listenTo(cellPanel)

    }
  }

  def redraw = {
    for {
      row <- 0 until controller.sizeOfRows
      column <- 0 until controller.sizeOfCols
    } cells(row)(column).redraw

    repaint
  }

  contents = welcomePanel

  visible = true


}
