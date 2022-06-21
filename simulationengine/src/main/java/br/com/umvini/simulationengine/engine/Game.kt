package br.com.umvini.simulationengine.engine

import br.com.umvini.simulationengine.models.Referee
import br.com.umvini.simulationengine.models.Result
import br.com.umvini.simulationengine.models.Team

class Game(
    var homeTeam: Team,
    var awayTeam: Team,
    var ref: Referee
) {
    var finalResult: Result? = null

    // Calculate what team starts an attack
    private fun startsAttack(minute: Int): Team? {

        // Value of attack of each team
        val homeTeamStartAttack = homeTeam.calculateAttack(minute, finalResult!!.redCardsHomeTeam)
        val awayTeamStartAttack = awayTeam.calculateAttack(minute, finalResult!!.redCardsAwayTeam)

        // Random Factoring
        val probHome = homeTeamStartAttack / (Math.random() * 100)
        val probAway = awayTeamStartAttack / (Math.random() * 100)
        if (probHome > probAway && probHome > Variables.SCORE_FACTOR) {
            return homeTeam
        }
        return if (probAway > probHome && probAway > Variables.SCORE_FACTOR) {
            awayTeam
        } else null
    }

    // Calculate if defending team is able to stop goal
    private fun defendAttack(att: Team, def: Team, minute: Int): Boolean {
        val attSuspensions: Int
        val defSuspensions: Int
        if (att == awayTeam) {
            attSuspensions = finalResult!!.redCardsAwayTeam
            defSuspensions = finalResult!!.redCardsHomeTeam
        } else {
            attSuspensions = finalResult!!.redCardsHomeTeam
            defSuspensions = finalResult!!.redCardsAwayTeam
        }

        // Value of team defending attack
        val defendAttack = def.calculateDefend(minute, defSuspensions)

        // Value of team scoring goal
        val score = att.calculateGoal(minute, attSuspensions)

        // Random factoring and Luck factor
        val scoreProb = score / (Math.random() * 100)
        val defendProb = defendAttack / (Math.random() * 100) + Variables.LUCK_FACTOR
        return scoreProb <= defendProb || scoreProb <= Variables.SCORE_FACTOR
    }

    fun faultEvent(minute: Int) {
        val redCard = ref.personality *
                Math.random() *
                Variables.REFEREE_PERSONALITY_FACTOR

        // If is likely to send off
        if (redCard > Variables.REFEREE_RED_CARD) {
            // Randomly Pick Team
            if (Math.random() > 0.5) {
                finalResult!!.addSuspension("home")
                println(minute.toString() + "'' [RED CARD] " + homeTeam.name)
            } else {
                finalResult!!.addSuspension("away")
                println(minute.toString() + "'' [RED CARD] " + awayTeam.name)
            }
        }
    }

    fun attackEvent(minute: Int) {

        //Get team that starts an attack
        val attack = startsAttack(minute)
        val def: Team = if (attack == awayTeam) homeTeam else awayTeam

        //If there will start an attack
        if (attack != null) {
            if (!defendAttack(attack, def, minute)) {
                println(minute.toString() + "'' [GOAL] " + attack.name)

                //Add goal to result
                if (attack == awayTeam) finalResult!!.addGoal("away") else finalResult!!.addGoal("home")
            } else {
                when (Math.random()) {

                }
                println(minute.toString() + "'' [ATTACK-FAILED] " + attack.name)
            }
        }
    }

    private fun printStarting11(t: Team) {
        println("[" + t.name + "]")
        for (pl in t.startingEleven) {
            println(pl.position.toString() + ": " + pl.name)
        }
        println("########\n")
    }

    // Game Simulation
    fun startSimulation(): Result? {
        finalResult = Result()

        //Starting 11
        printStarting11(homeTeam)
        printStarting11(awayTeam)
        println("[GAME STARTED]")
        //90 minutes Game
        for (minute in 0..89) {

            //What happens in this minute?
            val action = Math.random()

            // Fault
            if (action < Variables.FAULT_FACTOR) {
                faultEvent(minute)
            }

            // Attack
            if (action > Variables.ATTACK_FACTOR) {
                attackEvent(minute)
            }

            // Otherwise nothing happens
            if (minute == 45) println("[BREAK]")
        }
        return finalResult
    }
}