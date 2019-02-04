package org.olaven.tictacktoe.gui.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_game.*
import org.olaven.tictacktoe.R
import org.olaven.tictacktoe.game.Board
import org.olaven.tictacktoe.game.SquareMark

class GameActivity : AppCompatActivity() {

    val board = Board()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }


    // TODO: something like this should perhaps run on different thread
    private fun updateBoard() {


        activity_game_grid_view.columnWidth = board.grid.columns


        board.grid.forEach {
            val textView = TextView(applicationContext)
            textView.text = when (it.mark) {
                SquareMark.CROSS -> "X"
                SquareMark.CIRCLE -> "O"
                SquareMark.EMPTY -> " "
            }

           activity_game_grid_view.adapter = GameGridAdapter(this, board)
        }
    }

}

/*

class GameGridAdapter(val context: Context, val board: Board): BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val textView = TextView(context)
        textView.text =
    }

    override fun getItem(position: Int): Any {
        board.grid.iterator
    }

    override fun getItemId(position: Int): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
*/