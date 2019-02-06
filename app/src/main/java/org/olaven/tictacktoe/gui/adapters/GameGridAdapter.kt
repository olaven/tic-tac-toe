package org.olaven.tictacktoe.gui.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import org.olaven.tictacktoe.game.Game
import org.olaven.tictacktoe.game.board.Coordinate
import org.olaven.tictacktoe.game.board.SquareMark


class GameGridAdapter(private val context: Context, val game: Game) : BaseAdapter() {

    override fun getCount(): Int = 9

    override fun getItem(position: Int): Any? = null

    override fun getItemId(position: Int): Long = 0L

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val coordinate = positionToCoordinates(position)
        val square = game.board.squareAt(coordinate)

        val textView = TextView(context)
        textView.text = imageFromMark(square.mark)

        //TODO: Test asserting that only empty squares have listeners
        if (square.mark == SquareMark.EMPTY) {
            textView.setOnClickListener {
                game.clickAt(coordinate)
            }
        }

        return textView
    }

    private fun imageFromMark(mark: SquareMark): CharSequence {
        return when (mark) {
            SquareMark.CIRCLE -> "O"
            SquareMark.CROSS -> "X"
            SquareMark.EMPTY -> "_"
        }
    }

    private fun positionToCoordinates(position: Int): Coordinate {
        val row = position / game.board.grid.rows
        val column = position % game.board.grid.columns

        return Coordinate(row, column)
    }

}