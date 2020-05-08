package model

import java.util.Optional

case class Cell (isSet:Boolean, color:Optional[String] = Optional.empty()) {
  def setColor():Unit = {}
}
