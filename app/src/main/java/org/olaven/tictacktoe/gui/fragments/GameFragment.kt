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
import kotlinx.android.synthetic.main.fragment_game.*
import org.olaven.tictacktoe.R
import org.olaven.tictacktoe.database.UserModel
import org.olaven.tictacktoe.game.Game
import org.olaven.tictacktoe.game.Result
import org.olaven.tictacktoe.game.board.Board
import org.olaven.tictacktoe.game.player.BotPlayer
import org.olaven.tictacktoe.game.player.HumanPlayer
import org.olaven.tictacktoe.game.player.Player
import org.olaven.tictacktoe.gui.SharedModel
import org.olaven.tictacktoe.gui.BaseActivity
import org.olaven.tictacktoe.gui.adapters.GameGridAdapter

//TODO: Timer for spillet
//TODO: stats for gjennomsnitlig spilletid
//TODO: setting for antall ruter

class GameFragment : Fragment() {

    lateinit var game: Game
    private var player1: Player? = null
    private var player2: Player? = null

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


            sharedData.user1Name.observe(it, Observer {

                it?.let {name ->

                    UserModel(activity!!.application).getByName(name).observe(this, Observer {
                        it?.let {user ->
                            player1 = HumanPlayer(user)
                            startGameIfReady()
                        }
                    })

                }
            })

            sharedData.user2Name.observe(it, Observer {

                it?.let {name ->

                    val aiName = getString(R.string.AI_name)

                    if (name.startsWith(aiName)) {
                        player2 = BotPlayer(aiName)
                        startGameIfReady()
                    } else {
                        UserModel(activity!!.application).getByName(name).observe(this, Observer {
                            it?.let { user ->
                                player2 = HumanPlayer(user)
                                startGameIfReady()
                            }
                        })
                    }
                }
            })
        }
    }

    private fun startGameIfReady() {

        // Start game only if nececary data is recieved
        player1?.let { player1 ->
            player2?.let { player2 ->

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

            registerResult(it)

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

    private fun registerResult(result: Result) {

        when(result) {
            Result.FIRST -> {

                val user = (player1 as HumanPlayer).user
                user.wins
            }
            Result.SECOND -> {

            }
            Result.DRAW -> {

            }
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
