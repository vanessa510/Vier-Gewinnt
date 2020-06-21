package de.htwg.se.connect4.aview.gui

import de.htwg.se.connect4.controller.Controller
import de.htwg.se.connect4.model.Color

import scala.swing.event.MouseClicked
import scala.swing.{BoxPanel, Color, Dimension, FlowPanel, Font, Label, Orientation, Swing}

class CellPanel(row: Int, col: Int, controller: Controller) extends FlowPanel {


  val cellColor = new Color(255, 250, 250)

  def cellText(row: Int, col: Int) = {
    if (controller.board.cell(row, col).isSet) {
      if (controller.board.cell(row, col).color.get == Color.YELLOW) "yellow"
      else "red"
    } else ""

  }

  val label = new Label {
    text = cellText(row, col)
    font = new Font("Verdana", 1, 36)
  }

  val cell = new BoxPanel(Orientation.Vertical) {
    contents += label
    preferredSize = new Dimension(51, 51)
    border = Swing.BeveledBorder(Swing.Raised)

    reactions += {
      case MouseClicked(src, pt, mod, clicks, pops) => {
        controller.handle(row.toString + col.toString, controller.board)
        repaint
      }
    }
  }

  def redraw = {
    contents.clear()
    label.text = cellText(row, col)

    contents += cell

    repaint
  }

}
