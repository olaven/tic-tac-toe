package org.olaven.tictacktoe.game

import org.olaven.tictacktoe.game.player.Player

class Game(val board: Board, private val player1: Player, private val player2: Player){

    var activePlayer = player1
    var gameOver: Boolean = false
        // TODO: getter calculating state if not calculated for this turn (or something similar)

    fun setNextPlayerActive() {

        activePlayer = playerDependent(player2, player1)
    }

    fun clickAt(coordinate: Coordinate) {

        val mark = playerDependent(SquareMark.CROSS, SquareMark.CIRCLE)
        board.markSquareAt(coordinate, mark)
    }

    fun playerWinning() {

    }

    /**
     * Returns first argument if activePlayer is player1
     * and second argument otherwise
     */
    private fun<T> playerDependent(onFirst: T, onSecond: T): T {
        return if (activePlayer == player1)
            onFirst
        else
            onSecond
    }
}