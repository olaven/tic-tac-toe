package org.olaven.tictacktoe.game.board

import org.olaven.tictacktoe.game.CanCopy

data class Square(var mark: SquareMark):
    CanCopy<Square> {
    override fun copy(): Square {
        return Square(mark)
    }
}