package org.olaven.tictacktoe.game.player

import org.olaven.tictacktoe.database.User
import org.olaven.tictacktoe.game.Move


abstract class Player(val user: User) {

    fun makeMove(move: Move) {
        move.board.markSquareAt(
            move.coordinate,
            newMark = move.mark
        )
    }

}
