package io.github.wonjerry.tictactoe.presenter

import io.github.wonjerry.tictactoe.model.Board
import io.github.wonjerry.tictactoe.view.TicTacToeView

class TicTacToePresenter(view: TicTacToeView) {
  private enum class GameState {
    IN_PROGRESS, FINISHED
  }

  private val model = Board()
  private val view = view
  private var gameState: GameState = GameState.IN_PROGRESS

  private fun restartGame() {
    gameState = GameState.IN_PROGRESS
    model.clear()
    view.showWinner("")
    view.clearButtons(Board.ROW, Board.COL)
  }

  private fun isClickable(row: Int, col: Int): Boolean {
    return !model.isAlreadyMarked(row, col) && gameState == GameState.IN_PROGRESS
  }

  fun onTicTacToeButtonClicked(row: Int, col: Int) {
    if (isClickable(col, row)) {
      return
    }

    model.markCell(row, col)
    view.setButtonText(row, col, model.getCurrentPlayer())

    val winner = model.getWinner()

    when {
      winner != null -> {
        gameState = GameState.FINISHED
        view.showWinner(winner.toString())
      }
      model.isAllCellsFilled() -> {
        gameState = GameState.FINISHED
        view.showWinner("Draw")
      }
      else -> model.flipPlayer()
    }
  }

  fun onResetClicked(): Boolean {
    restartGame()
    return true
  }
}
