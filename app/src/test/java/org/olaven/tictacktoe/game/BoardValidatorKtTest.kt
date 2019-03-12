package org.olaven.tictacktoe.game

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.olaven.tictacktoe.game.board.Board
import org.olaven.tictacktoe.game.board.Coordinate
import org.olaven.tictacktoe.game.board.SquareMark

internal class BoardValidatorKtTest {

    var board: Board = Board()

    @BeforeEach
    fun init() {
        board = Board()
    }

    @Test
    fun defaultBoardHasNoWinner() {

        val result = hasWinner(Coordinate(0, 0), board)
        assertThat(result)
            .isFalse()
    }

    @Test
    fun winningHorizontally() {

        lateinit var coordinate: Coordinate
        for (i in 0 until board.dimension) {
            coordinate = Coordinate(i, 0)
            board.markSquareAt(coordinate, SquareMark.CROSS)
        }

        assertThat(hasWinner(coordinate, board))
            .isTrue()
    }

    @Test
    fun winningVertically() {

        lateinit var coordinate: Coordinate
        for (i in 0 until board.dimension) {
            coordinate = Coordinate(0, i)
            board.markSquareAt(coordinate, SquareMark.CROSS)
        }

        assertThat(hasWinner(coordinate, board))
            .isTrue()
    }
}