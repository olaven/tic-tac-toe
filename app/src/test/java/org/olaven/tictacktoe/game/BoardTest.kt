package org.olaven.tictacktoe.game

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.olaven.tictacktoe.game.board.Board
import org.olaven.tictacktoe.game.board.Coordinate
import org.olaven.tictacktoe.game.board.SquareMark
import org.olaven.tictacktoe.positionToCoordinates

internal class BoardTest {

    private var board: Board = Board();

    @BeforeEach
    fun init() {
        board = Board()
    }

    @Test
    fun defaultGridIs3x3() {

        val count = board.grid.matrix.map { it.count() }.sum()
        Assertions.assertThat(count)
            .isEqualTo(9)
    }

    @Test
    fun sizeIsDimensionSquared() {

        val dimension = 12
        board = Board(dimension)

        assertThat(board.size)
            .isEqualTo(dimension * dimension)
    }

    @Test
    fun canMarkCircleAtCoordinate() {
        val coordinate = Coordinate(0, 0)

        val stateBefore = board.squareAt(coordinate).mark
        assertThat(stateBefore)
            .isEqualTo(SquareMark.EMPTY)

        board.markSquareAt(coordinate, SquareMark.CIRCLE)

        val stateAfter = board.squareAt(coordinate).mark
        assertThat(stateAfter)
            .isEqualTo(SquareMark.CIRCLE)

    }

    @Test
    fun canMarkCrossAtCoordinate() {
        val coordinate = Coordinate(1, 2)

        val before = board.squareAt(coordinate).mark

        assertThat(before)
            .isNotEqualTo(SquareMark.CROSS) // am testing change, not a specific start

        board.markSquareAt(coordinate, SquareMark.CROSS)

        val after = board.squareAt(coordinate).mark
        assertThat(after)
            .isEqualTo(SquareMark.CROSS)

    }


    @Test
    fun markingOnlyAppliesToOneSquare() {
        val coordinate = Coordinate(1, 1)

        val countBefore = board.grid.filter {it.mark == SquareMark.EMPTY}.count()
        assertThat(board.squareAt(coordinate).mark)
            .isEqualTo(SquareMark.EMPTY)


        board.markSquareAt(coordinate, SquareMark.CROSS)

        val countAfter = board.grid.filter {it.mark == SquareMark.EMPTY}.count()
        assertThat(countAfter)
            .isEqualTo(countBefore - 1)
    }

    @Test
    fun copyingWorks() {

        val board = Board()
        board.markSquareAt(Coordinate(2, 2), SquareMark.CROSS)
        board.markSquareAt(Coordinate(2, 1), SquareMark.CROSS)
        board.markSquareAt(Coordinate(1, 2), SquareMark.CIRCLE)

        val copy = board.copy()
        board.grid.mapIndexed { index, square ->
            val coordinate = positionToCoordinates(index, board.grid)
            val mark = copy.squareAt(coordinate).mark

            assertThat(mark)
                .isEqualTo(square.mark)
        }
    }

}