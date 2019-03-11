package org.olaven.tictacktoe.game

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.olaven.tictacktoe.game.board.Board
import org.olaven.tictacktoe.game.board.Coordinate
import org.olaven.tictacktoe.game.player.HumanPlayer
import org.olaven.tictacktoe.game.player.Player

internal class GameTest {

    var player1 = HumanPlayer("player1")
    var player2 = HumanPlayer("player2")
    var game = Game(Board(), player1, player2)

    @BeforeEach
    fun init() {

        player1 = HumanPlayer("player1")
        player2 = HumanPlayer("player2")
        game = Game(Board(), player1, player2)
    }

    @Test
    fun gameCanChangeActivePlayer() {

        assertThat(game.activePlayer)
            .isEqualTo(player1)

        game.setNextPlayerActive()

        assertThat(game.activePlayer)
            .isEqualTo(player2)
    }

    @Test
    fun clickCountIncrements() {

        game.clickAt(Coordinate(0, 0))
        game.clickAt(Coordinate(0, 2))

        assertThat(game.clickCount)
            .isEqualTo(2)
    }

    @Test
    fun noIncrementOnSameSquare() {

        val coordinate = Coordinate(0, 0)
        game.clickAt(coordinate)
        game.clickAt(coordinate)

        assertThat(game.clickCount)
            .isEqualTo(1)
    }

    @Test
    fun gameOverWhenAllSquaresAreMarked() {

        var run = false
        var runCount = 0

        game.onGameOver = {
            run = true
            runCount++
        }

        for(i in 0 until game.board.dimension) {
            for (j in 0 until game.board.dimension) {

                game.clickAt(Coordinate(i, j))
            }
        }

        assertThat(run)
            .isTrue()
        assertThat(runCount)
            .isEqualTo(1)
    }
}