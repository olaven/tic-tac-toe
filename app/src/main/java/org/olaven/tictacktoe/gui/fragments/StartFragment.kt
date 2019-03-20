package org.olaven.tictacktoe.gui.fragments

import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_start.*
import org.olaven.tictacktoe.R
import org.olaven.tictacktoe.database.User
import org.olaven.tictacktoe.database.UserModel
import org.olaven.tictacktoe.gui.SharedModel
import org.olaven.tictacktoe.gui.BaseActivity


class StartFragment : Fragment() {

    private var users = emptyList<User>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setupUserObserver()
        setupSpinners()
        setupButton()
        setupFab()
    }

    private fun setupUserObserver() {
        UserModel(activity!!.application).allUsers.observe(this, Observer {
            it?.let { users ->
                this.users = users
            }
        })
    }

    private fun setupSpinners() {
        
        UserModel(activity!!.application).allUsers.observe(this, Observer {
            it?.let { users ->

                if (users.count() <= 0) {
                    val alert = AlertDialog.Builder(activity)
                    alert.apply {
                        setTitle("Please add some users (◕ᴥ◕)")
                        setPositiveButton("Will do:") { _, _ ->
                            showAddUserDialog()
                        }
                    }.show()
                } else {
                    val names = users.map { user -> user.name }

                    fragment_start_spinner_player1.adapter = getAdapter(names)
                    fragment_start_spinner_player2.adapter = getAdapter(names + "AI")
                }
            }
        })
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
