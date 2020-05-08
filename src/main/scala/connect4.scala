import de.htwg.se.connect4.model.{Board, Player}

import scala.io.StdIn.readLine

object connect4 {

  def main(args: Array[String]): Unit = {

    println("Welcome to connect 4. Please Enter your names.")

    print("Player 1: ")

    val player1 = Player(readLine(), "red")

    print("Player 2: ")

    val player2 = Player(readLine(), "yellow")

    println("Hello " + player1.playerName + " and " + player2.playerName + "!")

    val board = new Board(6, 7, false)

    print(board.getBoardAsString(6, 7))


  }
}