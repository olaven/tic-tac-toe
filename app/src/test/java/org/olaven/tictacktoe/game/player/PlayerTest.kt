package org.olaven.tictacktoe.game.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.olaven.tictacktoe.game.Board
import org.olaven.tictacktoe.game.Move
import org.olaven.tictacktoe.game.SquareMark
import kotlin.random.Random


/**
 * Tests common to all players (human and AI)
 */
internal abstract class PlayerTest {

    private var player = getPlayer() // depends on concrete implementation
    private var board = Board()

    @BeforeEach
    fun init() {
        player = getPlayer()
        board = Board()
    }

    @Test
    fun testPlayerShouldBeAbleToMakeMove() {
        val board = Board()

        val move = Move(
            SquareMark.CIRCLE,
            board,
            0,
            0
        )

        assertThat(board.squareAt(0, 0).mark)
            .isNotEqualTo(SquareMark.CIRCLE)

        player.makeMove(move)

        assertThat(board.squareAt(0, 0).mark)
            .isEqualTo(SquareMark.CIRCLE)

    }

    @Test
    fun testPlayerCanArbitrarySquare() {
        for(i in 0 until 20) {
            val move = getRandomMove()
            player.makeMove(move)

            val square = move.board.squareAt(move.row, move.column)
            assertThat(square.mark)
                .isEqualTo(move.mark)
        }
    }

    private fun getRandomMove(): Move {
        val boardDimension = Random.nextInt(1, 40)
        val board = Board(boardDimension)

        val column = Random.nextInt(boardDimension)
        val row = Random.nextInt(boardDimension)

        val mark = getRandomMoveMark()

        return Move(
            mark, board, row, column
        )

    }

    private fun getRandomMoveMark(): SquareMark {
        val value = Random.nextInt(0, 2)
        return if (value == 0) {
            SquareMark.CROSS
        } else {
            SquareMark.CIRCLE
        }
    }

    protected abstract fun getPlayer(): Player
}