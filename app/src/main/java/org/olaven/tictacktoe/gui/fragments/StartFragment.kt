package org.olaven.tictacktoe.gui.fragments

import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_start.*
import org.olaven.tictacktoe.R
import org.olaven.tictacktoe.database.User
import org.olaven.tictacktoe.database.UserModel
import org.olaven.tictacktoe.gui.SharedModel
import org.olaven.tictacktoe.gui.BaseActivity


class StartFragment : Fragment() {

    private var users = emptyList<User>()
    private lateinit var sharedModel: SharedModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        sharedModel = (activity as BaseActivity).getSharedModel()
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setupDatabaseObserver()
        setupSeekbar()
        setupFab()
        setupDimension()
        setupDimensionTextListener()
    }


    private fun setupDimension() {

        val dimension = fragment_start_seekbar_dimension.progress

        sharedModel.dimension
            .postValue(dimension)
    }

    private fun setupDatabaseObserver() {
        UserModel(activity!!.application).allUsers.observe(this, Observer {
            it?.let { users ->

                this.users = users
                addBotifNotPresent()
                displayAddUsersMessage()
                updateSpinners()
                updateButton()
            }
        })
    }

    private fun setupSeekbar() {

        fragment_start_seekbar_dimension.thumbOffset = 0
        fragment_start_seekbar_dimension.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                sharedModel.dimension.postValue(progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun setupDimensionTextListener() {

        sharedModel.dimension.observe(this, Observer {
            it?.let { dimension ->
                fragment_start_text_dimension.text = "Board: ${dimension}x${dimension}"
            }
        })
    }


    private fun addBotifNotPresent() {

        val present = users.
            map { it.name }
            .filter { it == getString(R.string.AI_name) }
            .count() > 0
        if (!present) {
            UserModel(activity!!.application)
                .insert(User(getString(R.string.AI_name)))
        }
    }

    private fun displayAddUsersMessage() {
        if (users.count() <= 0) {
            val alert = AlertDialog.Builder(activity)
            alert.apply {
                setTitle("Please add some users (◕ᴥ◕)")
                setPositiveButton("Will do:") { _, _ ->
                    showAddUserDialog()
                }
            }.show()
        }
    }


    private fun updateSpinners() {

        val names = users.map { user -> user.name }

        fragment_start_spinner_player1.adapter = getAdapter(names.filter { !it.startsWith(getString(R.string.AI_name)) })
        fragment_start_spinner_player2.adapter = getAdapter((names))
    }

    private fun updateButton() {

        fragment_start_button_start.setOnClickListener {

            val player1Name = fragment_start_spinner_player1.selectedItem.toString()
            val player2Name = fragment_start_spinner_player2.selectedItem.toString()



            if (player1Name == player2Name) {
                fragment_start_spinner_player2.performClick()
                Toast.makeText(context, getString(R.string.same_user_message), Toast.LENGTH_SHORT).show()
            } else {

                sharedModel.user1Name.postValue(player1Name)
                sharedModel.user2Name.postValue(player2Name)

                (activity as BaseActivity)
                    .replaceMainFragment(GameFragment())
            }
        }
        fragment_start_button_start.isEnabled = users.count() >= 1
    }


    private fun setupFab() {

        fragment_start_fab_add_user.setOnClickListener {
            showAddUserDialog()
        }
    }



    private fun showAddUserDialog() {

        val editText = EditText(context)

        val dialog = AlertDialog.Builder(activity)

        dialog.apply {
            setView(editText)
            setPositiveButton("Add user") { _, _ ->

                val name = editText.text.toString()
                val names = users.map { user -> user.name }
                val invalid = names.contains(name) || name.isEmpty()

                if (!invalid) {
                    UserModel(activity!!.application)
                        .insert(User(name))
                } else {
                    Toast.makeText(context, "user not valid", Toast.LENGTH_SHORT).show()
                }
            }

            setNegativeButton("Cancel") { _, _ -> }
        }
        dialog.show()
    }


    private fun getAdapter(array: List<String>) =
        ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, array)
}
