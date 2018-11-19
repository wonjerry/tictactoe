package io.github.wonjerry.tictactoe.model

class Board {

  companion object {
    const val ROW = 3
    const val COL = 3
  }

  private val cells = createCells()
  private var currentPlayer = Player.O


  private fun createCells(): List<Cell> {
    var cells = mutableListOf<Cell>()
    for (i in 0..(ROW * COL - 1)) {
        cells.add(Cell())
    }
    return cells
  }

  private fun getCell(row: Int, col: Int): Cell {
    return cells[row * ROW + col]
  }

  fun getCurrentPlayer(): String {
    return currentPlayer.toString()
  }

  fun clear() {
    currentPlayer = Player.O
    cells.forEach { it.player = null }
  }

  fun markCell(row: Int, col: Int) {
    getCell(row, col).player = currentPlayer
  }

  fun isAlreadyMarked(row: Int, col: Int): Boolean {
    return getCell(row, col).player != null
  }

  fun isAllCellsFilled(): Boolean {
    return cells.all { it.player != null }
  }

  fun getWinner(): Player? {
    // Check row
    for (i in 1..ROW) {
      if (cells
              .filterIndexed { index, _ -> index < i * COL }
              .all { it.player == currentPlayer }) {
        return currentPlayer
      }
    }

    // Check column
    for (i in 0..(COL - 1)) {
      if (cells
              .filterIndexed { index, _ -> index % ROW == i }
              .all { it.player == currentPlayer }) {
        return currentPlayer
      }
    }

    // Check right down diagonal
    if (
        getCell(0, 0).player == currentPlayer &&
        getCell(1, 1).player == currentPlayer &&
        getCell(2, 2).player == currentPlayer
    ) {
      return currentPlayer
    }

    // Check left down diagonal
    if (
        getCell(0, 2).player == currentPlayer &&
        getCell(1, 1).player == currentPlayer &&
        getCell(2, 0).player == currentPlayer
    ) {
      return currentPlayer
    }

    return null
  }

  fun flipPlayer() {
    currentPlayer = when (currentPlayer) {
      Player.O -> Player.X
      Player.X -> Player.O
    }
  }

}