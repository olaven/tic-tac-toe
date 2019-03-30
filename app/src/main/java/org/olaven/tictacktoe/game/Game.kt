package org.olaven.tictacktoe.game

import org.olaven.tictacktoe.game.board.Board
import org.olaven.tictacktoe.game.board.Coordinate
import org.olaven.tictacktoe.game.board.SquareMark
import org.olaven.tictacktoe.game.player.Player
import java.io.Serializable

class Game(val board: Board, val player1: Player, val player2: Player): Serializable {

    var activePlayer = player1

    /**
     * Triggered when activePlayer is changed.
     * Accessible from other class
     */
    var onFirstPlayer:  ((player: Player) -> Unit)? = null
    var onSecondPlayer:  ((player: Player) -> Unit)? = null
    /**
     * Triggered when one player has won
     */
    var onGameOver:  ((result: Result) -> Unit)? = null


    fun setNextPlayerActive() {

        activePlayer = playerDependent(player2, player1)
    }

    fun clickAt(coordinate: Coordinate) {

        val mark = playerDependent(SquareMark.CROSS, SquareMark.CIRCLE)
        board.markSquareAt(coordinate, mark)

        if (hasWinner(coordinate, board)) {
            val result = playerDependent(Result.FIRST, Result.SECOND)
            onGameOver?.invoke(result)
            return
        }

        val moveCount = board.grid.filter { it.mark != SquareMark.EMPTY }.count()
        if (moveCount >= board.size) {
            onGameOver?.invoke(Result.DRAW)
            return
        }

        changePlayer()
    }

    private fun changePlayer() {

        activePlayer = playerDependent(player2, player1)

        playerDependent(
            {
                onFirstPlayer?.let { it(activePlayer) }
            },
            {
                onSecondPlayer?.let { it(activePlayer) }
            }
        )()
    }


    /**
     * Returns first argument if activePlayer is user1Name
     * and second argument otherwise
     */
    private fun<T> playerDependent(onFirst: T, onSecond: T): T {
        return if (activePlayer == player1)
            onFirst
        else
            onSecond
    }

}