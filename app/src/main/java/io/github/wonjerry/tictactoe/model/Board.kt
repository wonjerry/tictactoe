package io.github.wonjerry.tictactoe.model

class Board {

  companion object {
    const val ROW = 3
    const val COL = 3
  }

  val cells = createCells()
  var currentPlayer = Player.O
  var winner: Player? = null

  private fun createCells(): List<Cell> {
    var cells = mutableListOf<Cell>()
    for (i in 0..(ROW - 1)) {
      for (j in 0..(COL - 1)) {
        cells.add(Cell(i, j))
      }
    }

    return cells
  }

  private fun getCell(row: Int, col: Int): Cell {
    return cells[row * ROW + col]
  }

  private fun clear() {
    cells.map { it.player = null }
  }

  fun restart() {
    clear()
    currentPlayer = Player.O
    winner = null
  }

  fun markCell(row: Int, col: Int) {
    getCell(row, col).player = currentPlayer
  }

  fun isValid(row: Int, col: Int): Boolean {
    return getCell(row, col).player == null && winner == null
  }

  fun checkWinner(): Player? {
    // Check row
    for (i in 1..ROW) {
      if (cells
              .filterIndexed { index, _ -> index < i * COL }
              .all { it.player == currentPlayer }) {
        winner = currentPlayer
        return winner
      }
    }

    // Check column
    for (i in 0..(COL - 1)) {
      if (cells
              .filterIndexed { index, _ -> index % ROW == i }
              .all { it.player == currentPlayer }) {
        winner = currentPlayer
        return winner
      }
    }

    // Check right down diagonal
    if (
        getCell(0, 0).player == currentPlayer &&
        getCell(1, 1).player == currentPlayer &&
        getCell(2, 2).player == currentPlayer
    ) {
      winner = currentPlayer
      return winner
    }

    // Check left down diagonal
    if (
        getCell(0, 2).player == currentPlayer &&
        getCell(1, 1).player == currentPlayer &&
        getCell(2, 0).player == currentPlayer
    ) {
      winner = currentPlayer
      return winner
    }

    // Check draw
    if (cells.all { it.player != null }) {
      winner = Player.DRAW
    }

    return winner
  }

  fun flipPlayer() {
    currentPlayer = when (currentPlayer) {
      Player.O -> Player.X
      Player.X -> Player.O
      else -> Player.O
    }
  }

}