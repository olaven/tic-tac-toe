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

    @Test
    fun winningDiagonallyLeftDown() {

        // Checking first direction -> left-down
        lateinit var coordinate: Coordinate
        for (i in 0 until board.dimension) {
            coordinate = Coordinate(i, i)
            board.markSquareAt(coordinate, SquareMark.CROSS)
        }

        assertThat(hasWinner(coordinate, board))
            .isTrue()
    }

    @Test
    fun winningDiagonallyLeftUp() {

        // Checking other direction -> left-bottom up
        lateinit var coordinate: Coordinate
        for (x in 0 until board.dimension) {

            val limit = board.dimension - 1
            val y = (limit - x)

            coordinate = Coordinate(x, y)
            board.markSquareAt(coordinate, SquareMark.CROSS)
        }

        assertThat(hasWinner(coordinate, board))
            .isTrue()
    }
}