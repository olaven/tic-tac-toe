package org.olaven.tictacktoe.game

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.random.Random

internal class GridTest {



    @Test
    fun testGridSize() {
        val grid = Grid(3, 4, 0)

        assertThat(grid.size)
            .isEqualTo(3 * 4)
    }

    @Test
    fun testArbitraryGridSize() {
        for(i in 0 until 20) {

            val rows = Random.nextInt(42)
            val columns = Random.nextInt(42)

            val grid = Grid(rows, columns, 0)

            Assertions.assertThat(grid.size)
                .isEqualTo(rows * columns)
        }
    }

    @Test
    fun testGridSizeMatchesMatrix() {
        val rows = Random.nextInt(42)
        val columns = Random.nextInt(42)

        val grid = Grid(rows, columns, 0)

        val computedSize = grid.matrix.map { it.count() }.sum()

        assertThat(computedSize)
            .isEqualTo(grid.size)
    }

    @Test
    fun testGridIsIterable() {
        val grid = Grid(2, 2, "default")
        assertThat(grid)
            .isInstanceOf(Iterable::class.java)
    }

    @Test
    fun testCountOfIterable() {

        val grid = Grid(2, 2, 1)

        grid.forEach {
            println(it);
        }

        assertThat(grid.count())
            .isEqualTo(4)
    }

    @Test
    fun testSumOfContent() {
        val grid = Grid(2, 2, 2)

        assertThat(grid.sum())
            .isEqualTo(8) // 2 * 2 * 2
    }

    @Test
    fun testDefaultContent() {

        val default = "I AM DEFAULT"
        val grid = Grid(3, 3, default)


        grid.forEach {
            assertThat(it == default)
        }
    }

}
