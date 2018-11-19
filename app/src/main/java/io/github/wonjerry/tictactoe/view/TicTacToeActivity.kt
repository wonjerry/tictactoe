package io.github.wonjerry.tictactoe.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import io.github.wonjerry.tictactoe.R
import io.github.wonjerry.tictactoe.presenter.TicTacToePresenter
import kotlinx.android.synthetic.main.tictactoe_view.*
import android.widget.Button


class TicTacToeActivity : AppCompatActivity(), TicTacToeView {

  private val presenter = TicTacToePresenter(this)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.tictactoe_view)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.tictactoe_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
    R.id.action_reset -> presenter.onResetClicked()
    else -> super.onOptionsItemSelected(item)
  }

  override fun showWinner(winner: String) {
    winnerPlayer.text = winner
    when (winner) {
      "", "Draw" -> winnerText.visibility = View.INVISIBLE
      else -> winnerText.visibility = View.VISIBLE
    }
  }

  override fun clearWinnerDisplay() {
    winnerPlayer.text = ""
  }

  override fun clearButtons(rows: Int, cols: Int) {
    for (i in 0..rows) {
      for (j in 0..cols) {
        setButtonText(i, j)
      }
    }
  }

  override fun setButtonText(row: Int, col: Int, mark: String) {
    buttonGrid.findViewWithTag<Button>("$row$col").run {
      text = mark
    }
  }

  fun onCellClicked(view: View) {
    val button = view as Button
    val position = button.tag.toString()
    val row = Integer.valueOf(position.substring(0, 1))
    val col = Integer.valueOf(position.substring(1, 2))
    Log.i("On cell clicked", "Click Row: [$row, $col]")

    presenter.onTicTacToeButtonClicked(row, col)
  }
}
