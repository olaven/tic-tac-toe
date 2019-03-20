package org.olaven.tictacktoe.gui.fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_leaderboard.*
import org.olaven.tictacktoe.R
import org.olaven.tictacktoe.database.User
import org.olaven.tictacktoe.database.UserModel

class LeaderboardFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leaderboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        UserModel(activity!!.application).allUsers.observe(this, Observer {

            it?.let {users ->

                updateTable(users)
            }
        })
    }

    private fun updateTable(users: List<User>) {

        users.forEach {user ->

            val row = customRow(
                user.name,
                user.wins.toString(),
                user.losses.toString(),
                user.draws.toString()
            )

            fragment_leaderboard_table.addView(row)
        }
    }

    private fun customRow(vararg strings: String): TableRow =
        TableRow(context).apply {

            strings
                .map { customTextView(it) }
                .forEach { addView(it) }
        }

    private fun customTextView(text: String): TextView =
        TextView(context)
            .apply {

                val width = TableRow.LayoutParams.MATCH_PARENT
                val height = TableRow.LayoutParams.WRAP_CONTENT

                this.text = text
                layoutParams = TableRow.LayoutParams(width, height, 1f)

                gravity = Gravity.CENTER
                gravity = Gravity.CENTER
            }
}
