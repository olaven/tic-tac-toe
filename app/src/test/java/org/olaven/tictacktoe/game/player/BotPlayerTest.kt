package org.olaven.tictacktoe.game.player

import org.junit.jupiter.api.Assertions.*

internal class BotPlayerTest : PlayerTest() {
    override fun getPlayer(): Player {
        return BotPlayer()
    }
}