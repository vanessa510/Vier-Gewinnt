package de.htwg.se.connect4.aview.gui

import scala.swing._
import de.htwg.se.connect4.controller.Controller


class SwingGui(controller: Controller) extends Frame {

  title = "Connect 4"

  var cells = Array.ofDim[CellPanel](controller.sizeOfRows, controller.sizeOfCols)

}
