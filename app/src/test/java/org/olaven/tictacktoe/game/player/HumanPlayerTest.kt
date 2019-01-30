package org.olaven.tictacktoe.game.player

import org.junit.jupiter.api.Assertions.*

internal class HumanPlayerTest : PlayerTest() {
    override fun getPlayer(): Player {
        return HumanPlayer("Human Player")
    }
}