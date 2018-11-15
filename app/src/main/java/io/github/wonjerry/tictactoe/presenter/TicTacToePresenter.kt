package io.github.wonjerry.tictactoe.presenter

import io.github.wonjerry.tictactoe.model.Board
import io.github.wonjerry.tictactoe.view.TicTacToeView

class TicTacToePresenter(view: TicTacToeView) {
  private val model = Board()
  private val view = view

  fun onTicTacToeButtonClicked(row: Int, col: Int) {
    if (!model.isValid(row, col)) {
      return
    }

    model.markCell(row, col)
    view.setButtonText(row, col, model.currentPlayer.toString())
    model.checkWinner()?.let {
      with(model) {
        view.showWinner(winner.toString())
      }
    }
    model.flipPlayer()
  }

  fun onResetClicked() {
    model.restart()
    view.showWinner("")
    view.clearButtons(Board.ROW, Board.COL)
  }
}
