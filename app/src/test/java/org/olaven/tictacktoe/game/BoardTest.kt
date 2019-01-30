package org.olaven.tictacktoe.game

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.random.Random

internal class BoardTest {

    private var board: Board = Board();

    @BeforeEach
    fun init() {
        board = Board()
    }

    @Test
    fun testDefaultGridIs3x3() {

        val count = board.grid.map { it.count() }.sum()
        assertThat(count)
            .isEqualTo(9)
    }

    @Test
    fun testArbitraryGridSize() {
        for(i in 0 until 20) {
            val rows = Random.nextInt(0, 100)
            val columns = Random.nextInt(0, 100);
            val board = Board(rows, columns);

            val count = board.grid.map { it.count() }.sum()

            assertThat(count)
                .isEqualTo(rows * columns)
        }
    }

    @Test
    fun canMarkCircleAtCoordinate() {

        val stateBefore = board.squareAt(0, 0).mark
        assertThat(stateBefore)
            .isEqualTo(SquareMark.EMPTY);

        board.markSquareAt(0, 0, SquareMark.CIRCLE)

        val stateAfter = board.squareAt(0, 0).mark
        assertThat(stateAfter)
            .isEqualTo(SquareMark.CIRCLE)

    }

    @Test
    fun canMarkCrossAtCoordinate() {
        val before = board.squareAt(1, 2).mark
        assertThat(before)
            .isNotEqualTo(SquareMark.CROSS) // am testing change, not a specific start

        board.markSquareAt(1, 2, SquareMark.CROSS)

        val after = board.squareAt(1, 2).mark
        assertThat(after)
            .isEqualTo(SquareMark.CROSS)

    }
}