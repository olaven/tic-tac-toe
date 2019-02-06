package org.olaven.tictacktoe.game

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.olaven.tictacktoe.game.board.Grid
import org.olaven.tictacktoe.game.board.Square
import kotlin.random.Random

internal class GridTest {

    private var defaultContent = Square(SquareMark.EMPTY)

    @BeforeEach
    fun init() {
        defaultContent = Square(SquareMark.EMPTY)
    }

    @Test
    fun testGridSize() {
        val grid = Grid(3, 4, defaultContent)

        assertThat(grid.size)
            .isEqualTo(3 * 4)
    }

    @Test
    fun testArbitraryGridSize() {
        for(i in 0 until 20) {

            val rows = Random.nextInt(42)
            val columns = Random.nextInt(42)

            val grid = Grid(rows, columns, defaultContent)

            Assertions.assertThat(grid.size)
                .isEqualTo(rows * columns)
        }
    }

    @Test
    fun testGridSizeMatchesMatrix() {
        val rows = Random.nextInt(42)
        val columns = Random.nextInt(42)

        val grid = Grid(rows, columns, defaultContent)

        val computedSize = grid.matrix.map { it.count() }.sum()

        assertThat(computedSize)
            .isEqualTo(grid.size)
    }

    @Test
    fun testGridIsIterable() {
        val grid = Grid(2, 2, defaultContent)
        assertThat(grid)
            .isInstanceOf(Iterable::class.java)
    }

    @Test
    fun testCountOfIterable() {

        val grid = Grid(2, 2, defaultContent)

        assertThat(grid.count())
            .isEqualTo(4)
    }


    @Test
    fun testDefaultContent() {

        val default = Square(SquareMark.CROSS)
        val grid = Grid(3, 3, default)


        grid.forEach {
            assertThat(it == default)
        }
    }

}
