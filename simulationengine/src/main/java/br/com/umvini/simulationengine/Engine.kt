package br.com.umvini.simulationengine

import br.com.umvini.simulationengine.engine.Game
import br.com.umvini.simulationengine.engine.LoadInfo
import br.com.umvini.simulationengine.models.Result


object Engine {
    @Throws(Exception::class)
    @JvmStatic
    fun main() {

        val loadInfo = LoadInfo().newLoad()

        val game = Game(loadInfo.homeTeam, loadInfo.awayTeam, loadInfo.ref)
        val res: Result = game.startSimulation()!!

        //Show results
        println("\n")
        println(loadInfo.homeTeam.name + ": " + res.goalsHomeTeam)
        println(loadInfo.awayTeam.name + ": " + res.goalsAwayTeam)

        println("Red Cards " + loadInfo.homeTeam.name + ": " + res.redCardsHomeTeam)
        println("Red Cards " + loadInfo.awayTeam.name + ": " + res.redCardsAwayTeam)
    }
}