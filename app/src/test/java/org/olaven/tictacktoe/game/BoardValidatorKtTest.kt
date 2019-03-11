package org.olaven.tictacktoe.game

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.olaven.tictacktoe.game.board.Board

internal class BoardValidatorKtTest {

    var board: Board = Board()

    @BeforeEach
    fun init() {
        board = Board()
    }

    @Test
    fun defaultBoardHasNoWinner() {

        val result = hasWinner(board)
        assertThat(result)
            .isFalse()
    }
}