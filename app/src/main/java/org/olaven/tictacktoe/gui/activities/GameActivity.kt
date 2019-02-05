package org.olaven.tictacktoe.gui.activities

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*
import org.olaven.tictacktoe.R
import org.olaven.tictacktoe.game.Board
import org.olaven.tictacktoe.game.SquareMark

class GameActivity : AppCompatActivity() {

    private val board = Board() // TODO: game logic in separate class

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        activity_game_grid_view.adapter = GameGridAdapter(this, board)
    }

    override fun onStart() {

        super.onStart()
    }

    // TODO: something like this should perhaps run on different thread
    private fun updateBoard() {


    }

}




class GameGridAdapter(private val context: Context, val board: Board) : BaseAdapter() {

    override fun getCount(): Int = 9

    override fun getItem(position: Int): Any? = null

    override fun getItemId(position: Int): Long = 0L

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val coordinates = positionToCoordinates(position)

        val square = board.squareAt(coordinates.first, coordinates.second)
        val textView = TextView(context)


        textView.text = when(square.mark) {
            SquareMark.EMPTY -> "_"
            SquareMark.CIRCLE -> "O"
            SquareMark.CROSS -> "X"
        }

        textView.setOnClickListener {
            Toast.makeText(context, "Clicked Square (${coordinates.first}, ${coordinates.second}", Toast.LENGTH_LONG).show()
        }
        return textView
    }

    private fun positionToCoordinates(position: Int): Pair<Int, Int> {
        val row = position / board.grid.rows
        val column = position % board.grid.columns

        return Pair(row, column)
    }
}