package io.github.wonjerry.tictactoe.view

interface TicTacToeView {
  fun showWinner(winner: String)
  fun clearWinnerDisplay()
  fun clearButtons(rows: Int, cols: Int)
  fun setButtonText(row: Int, col: Int, text: String = "")
}
