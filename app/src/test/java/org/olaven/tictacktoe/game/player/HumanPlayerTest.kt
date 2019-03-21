package org.olaven.tictacktoe.game.player

import org.junit.jupiter.api.Assertions.*
import org.olaven.tictacktoe.database.User

internal class HumanPlayerTest : PlayerTest() {

    override fun getPlayer(): Player {

        val user = User("Human Player")
        return HumanPlayer(user)
    }
}