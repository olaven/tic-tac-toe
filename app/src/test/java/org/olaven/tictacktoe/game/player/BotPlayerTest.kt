package org.olaven.tictacktoe.game.player

import org.junit.jupiter.api.Assertions.*
import org.olaven.tictacktoe.database.User

internal class BotPlayerTest : PlayerTest() {
    override fun getPlayer(): Player {

        val botUser = User("TTTBot")
        return BotPlayer(botUser)
    }
}