package de.htwg.se.connect4.aview.gui

import de.htwg.se.connect4.controller.{Controller, InGameState}
import de.htwg.se.connect4.util.Observer

import scala.swing.Swing.LineBorder
import scala.swing._
import scala.swing.event.{ButtonClicked, Key, KeyPressed}


class SwingGui(controller: Controller) extends Frame with Observer {

  controller.add(this)
  title = "Connect 4"

  var cells = Array.ofDim[CellPanel](controller.sizeOfRows, controller.sizeOfCols)
  val statusLine = new Label(controller.getString)


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
    centerOnScreen()
    preferredSize = new Dimension(300, 100)

    contents += new BoxPanel(Orientation.Vertical) {
      contents += nameTextField
      contents += statusLine
    }

    contents += new BoxPanel(Orientation.Horizontal) {
      contents += nextPlayerButton
    }

    reactions += {
      case ButtonClicked(`nextPlayerButton`) => controller.handle(nameTextField.text, controller.board)
        contents.clear
        contents += nameTextField
        contents += nextPanelButton
        repaint()
      case ButtonClicked(`nextPanelButton`) => controller.handle(nameTextField.text, controller.board); changePanel
    }
  }

  def changePanel = {
    contents = new BorderPanel {
      add(gridPanel, BorderPanel.Position.Center)
      add(statusLine, BorderPanel.Position.South)
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
      centerOnScreen()
      preferredSize = new Dimension(900, 700)

    }
  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("New") {
        controller.createNewBoard(controller.sizeOfRows, controller.sizeOfCols)
        redraw
      })
      contents += new MenuItem(Action("Quit") {
        System.exit(0)
      })
    }
    contents += new Menu("Edit") {
      mnemonic = Key.E
      contents += new MenuItem(Action("Undo") {
        controller.undo
        redraw
      })
      contents += new MenuItem(Action("Redo") {
        controller.redo
        redraw
      })
    }
  }


  def redraw = {
    for {
      row <- 0 until controller.sizeOfRows
      column <- 0 until controller.sizeOfCols
    } cells(row)(column).redraw


    updateStatusLine
    repaint
  }

  def updatePanel: Unit = {
    if (controller.state.equals(InGameState(controller))) changePanel else contents = welcomePanel
  }

  def updateStatusLine: Unit  =
    if (controller.state.equals(InGameState(controller))) statusLine.text = controller.getPlayerDemandString
    else statusLine.text = controller.getString

  repaint()


  contents = welcomePanel

  visible = true

  override def update: Unit = {updateStatusLine; updatePanel}

}
