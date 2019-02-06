package org.olaven.tictacktoe.game

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.olaven.tictacktoe.game.player.HumanPlayer
import org.olaven.tictacktoe.game.player.Player

internal class GameTest {

    @Test
    fun gameCanChangeActivePlayer() {
        val player1 = HumanPlayer("player1")
        val player2 = HumanPlayer("player1")

        val game = Game(Board(), player1, player2)

        assertThat(game.activePlayer)
            .isEqualTo(player1)

        game.setNextPlayerActive()

        assertThat(game.activePlayer)
            .isEqualTo(player2)
    }

}