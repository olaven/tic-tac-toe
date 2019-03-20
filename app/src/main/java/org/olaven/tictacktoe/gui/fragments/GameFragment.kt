package org.olaven.tictacktoe.gui.fragments

import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_game.*
import org.olaven.tictacktoe.R
import org.olaven.tictacktoe.game.Game
import org.olaven.tictacktoe.game.board.Board
import org.olaven.tictacktoe.game.player.BotPlayer
import org.olaven.tictacktoe.game.player.HumanPlayer
import org.olaven.tictacktoe.gui.SharedModel
import org.olaven.tictacktoe.gui.BaseActivity
import org.olaven.tictacktoe.gui.adapters.GameGridAdapter


class GameFragment : Fragment() {

    lateinit var game: Game

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initializeGame()
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initializeGame() {

        activity!!.let {

            val sharedData = ViewModelProviders.of(it).get(SharedModel::class.java)

            var player1Name: String? = null
            var player2Name: String? = null

            sharedData.player1Name.observe(it, Observer {

                player1Name = it
                startGameIfReady(player1Name, player2Name)
            })

            sharedData.player2Name.observe(it, Observer {

                player2Name = it
                startGameIfReady(player1Name, player2Name)
            })
        }
    }

    private fun startGameIfReady(player1Name: String?, player2Name: String?) {

        // Start game only if nececary data is recieved
        player1Name?.let {player1Name ->
            player2Name?.let { player2Name ->

                val player1 = HumanPlayer(player1Name)
                val player2 = if (player2Name.startsWith("AI")) {
                    BotPlayer()
                } else {
                    HumanPlayer(player2Name)
                }


                this.game = Game(Board(), player1, player2)

                setupBoardView()
                setupOnGameOver()
                setupOnPlayerActions()
                setupText()
            }
        }

    }

    private fun setupOnGameOver() {

        game.onGameOver = {

            val alert = AlertDialog.Builder(activity)

            alert.apply {

                setTitle(getString(R.string.game_over_message))
                setPositiveButton("New game") { _, _ ->

                    (activity as BaseActivity)
                        .replaceMainFragment(GameFragment())
                }
                setNeutralButton("View leaderboard") { _, _ ->
                    (activity as BaseActivity)
                        .replaceMainFragment(LeaderboardFragment())
                }
            }.show()
        }
    }

    private fun setupBoardView() {

        game.apply {

            fragment_game_grid_view.adapter = GameGridAdapter(context!!, this)
            fragment_game_grid_view.numColumns = board.dimension
        }

    }


    private fun setupText() {

        game.apply {

            fragment_game_text_player1.text = player1.name
            fragment_game_text_player2.text = player2.name
        }
    }

    private fun setupOnPlayerActions() {

        game.apply {

            onFirstPlayer = {

                fragment_game_text_player1.setTextColor(Color.GREEN)
                fragment_game_text_player2.setTextColor(Color.BLACK)
            }

            onSecondPlayer = {

                fragment_game_text_player1.setTextColor(Color.BLACK)
                fragment_game_text_player2.setTextColor(Color.GREEN)

                if (it is BotPlayer) {
                    val coordinate = it.selectCoordinate(board)
                    game.clickAt(coordinate)
                }
            }
        }
    }


}
