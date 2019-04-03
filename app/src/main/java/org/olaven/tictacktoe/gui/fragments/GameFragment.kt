package org.olaven.tictacktoe.gui.fragments

import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
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


class GameFragment : Fragment() {

    lateinit var game: Game
    private lateinit var sharedModel: SharedModel
    private lateinit var aiName: String

    private var player1: Player? = null
    private var player2: Player? = null
    private var dimension: Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //name is stored in a temp var to avoid crashes when reading getString in other parts of lifecycle
        aiName = getString(R.string.AI_name)
        sharedModel = (activity as BaseActivity).getSharedModel()
        setupDataObservers()
        super.onViewCreated(view, savedInstanceState)
    }


    // TODO: Refactor this function
    private fun setupDataObservers() {

        activity?.let { activity ->

            sharedModel.dimension.observe(activity, Observer {

                it?.let { dimension ->

                    this.dimension = dimension
                    startGameIfReady()
                }
            })

            sharedModel.user1Name.observe(activity, Observer {

                it?.let {name ->

                    UserModel(activity.application).getByName(name).observe(this, Observer {

                        it?.let {user ->
                            player1 = HumanPlayer(user)
                            startGameIfReady()
                        }
                    })

                }
            })

            sharedModel.user2Name.observe(activity, Observer {

                it?.let {name ->

                    if (name.startsWith(aiName)) {
                        UserModel(activity.application).getByName(name).observe(this, Observer {

                            it?.let { user ->
                                player2 = BotPlayer(user)
                                startGameIfReady()
                            }
                        })
                    } else {
                        UserModel(activity.application).getByName(name).observe(this, Observer {

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

        // Start game only if necessary data is received
        player1?.let { player1 ->
            player2?.let { player2 ->
                dimension?.let { dimension ->

                    // prevents setting up when user replaces fragment just after starting it, which resulted in crashes.
                    val fragment = activity?.supportFragmentManager?.findFragmentById(R.id.activity_base_frame_layout)
                    if (fragment is GameFragment) {

                        this.game = Game(Board(dimension), player1, player2)
                        setupBoardView()
                        setupOnGameOver()
                        setupOnPlayerActions()
                        setupText()
                        fragment_game_chronometer.start()
                    }
                }
            }
        }
    }


    private fun setupOnGameOver() {

        game.onGameOver = {

            registerResult(it)

            fragment_game_chronometer.base = SystemClock.elapsedRealtime()
            fragment_game_chronometer.stop()

            val alert = AlertDialog.Builder(activity)

            alert.apply {

                setTitle(getString(R.string.game_over_message))
                setPositiveButton("Start over") { _, _ ->

                    (activity as BaseActivity)
                        .replaceMainFragment(StartFragment())
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
                player1!!.user.wins += 1
                player2!!.user.losses += 1
            }
            Result.SECOND -> {
                player2!!.user.wins += 1
                player1!!.user.losses += 1;
            }
            Result.DRAW -> {
                player1!!.user.draws += 1
                player2!!.user.draws += 1
            }
        }

        UserModel(activity!!.application)
            .update(player1!!.user)
        UserModel(activity!!.application)
            .update(player2!!.user)
    }


    private fun setupBoardView() {

        game.apply {

            fragment_game_grid_view.adapter = GameGridAdapter(context!!, this)
            fragment_game_grid_view.numColumns = board.dimension
        }

    }


    private fun setupText() {

        game.apply {

            fragment_game_text_player1.text = player1.user.name
            fragment_game_text_player2.text = player2.user.name
        }
    }

    private fun setupOnPlayerActions() {

        val activePlayerColor = Color.BLACK
        val waitingPlayerColor = Color.GRAY

        game.apply {

            //fragment_start_text_player1.setTextColor(activePlayerColor)
            //fragment_start_text_player2.setTextColor(waitingPlayerColor)

            onFirstPlayer = {

                fragment_game_text_player1.setTextColor(activePlayerColor)
                fragment_game_text_player2.setTextColor(waitingPlayerColor)
            }

            onSecondPlayer = {

                fragment_game_text_player1.setTextColor(waitingPlayerColor)
                fragment_game_text_player2.setTextColor(activePlayerColor)

                if (it is BotPlayer) {
                    val coordinate = it.selectCoordinate(board)
                    game.clickAt(coordinate)
                }
            }
        }
    }
}
