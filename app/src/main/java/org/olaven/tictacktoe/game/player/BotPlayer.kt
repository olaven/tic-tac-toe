package org.olaven.tictacktoe.game.player

class BotPlayer: Player("TTTBot") {

    fun makeMove() {
        // TODO: Find the next best move based on the board and make it.
        // Finding of move on different thread.
        // making the move with inherited makemove(move) has to be done on UI Thread again
        Runnable { print("find move here and access main thread afterwards") }.run()
    }
}