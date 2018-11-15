package io.github.wonjerry.tictactoe.model

data class Cell(val row: Int, val col: Int, var player: Player? = null)
