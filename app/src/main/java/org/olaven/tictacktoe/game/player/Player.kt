package org.olaven.tictacktoe.game.player

import org.olaven.tictacktoe.game.Move

abstract class Player(val name: String) {
    fun makeMove(move: Move) {
        move.board.markSquareAt(
            row = move.row,
            column = move.column,
            newMark = move.mark
        )
    }

}
