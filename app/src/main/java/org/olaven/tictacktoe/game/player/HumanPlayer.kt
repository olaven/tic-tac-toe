package org.olaven.tictacktoe.game.player

import org.olaven.tictacktoe.database.User

class HumanPlayer(val user: User) : Player(user.name)