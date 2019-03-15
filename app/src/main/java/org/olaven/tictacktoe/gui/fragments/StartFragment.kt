package org.olaven.tictacktoe.gui.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_start.*
import org.olaven.tictacktoe.R
import org.olaven.tictacktoe.gui.SharedModel
import org.olaven.tictacktoe.gui.BaseActivity


class StartFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setupSpinners()
        setupButton()
    }

    private fun setupSpinners() {

        val humanPlayers = resources.getStringArray(R.array.humanPlayers)
        val aiPlayers = resources.getStringArray(R.array.aiPlayers)

        fragment_start_spinner_player1.adapter = getAdapter(humanPlayers)
        fragment_start_spinner_player2.adapter = getAdapter(humanPlayers + aiPlayers)
    }

    private fun setupButton() {
        fragment_start_button_start.setOnClickListener {

            val player1 = fragment_start_spinner_player1.selectedItem.toString()
            val player2 = fragment_start_spinner_player2.selectedItem.toString()

            if (player1 == player2) {
                fragment_start_spinner_player2.performClick()
                Toast.makeText(context, getString(R.string.same_user_message), Toast.LENGTH_SHORT).show()
            } else {

                activity?.let {

                    val sharedData = ViewModelProviders.of(it).get(SharedModel::class.java)
                    sharedData.player1Name.postValue(player1)
                    sharedData.player2Name.postValue(player2)

                }
                (activity as BaseActivity)
                    .replaceMainFragment(GameFragment())

            }
        }
    }

    private fun getAdapter(array: Array<String>) =
        ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, array)
}
