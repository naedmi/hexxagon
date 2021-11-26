package controller

import GameStatus._
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class GameStatusSpec extends AnyWordSpec {
    "A GameStatus" when {
        "map descriptions for each value" in {
            IDLE.message() should be ("")
            GAMEOVER.message() should be ("GAME OVER")
            TURNPLAYER1.message() should be ("Player 1 to place X")
            TURNPLAYER2.message() should be ("Player 2 to place O")
        }
        "have a type with a value of the given ENUMS" in {
            GameStatus.values should be(Array( IDLE, GAMEOVER, TURNPLAYER1, TURNPLAYER2 ))
        }
    }
}