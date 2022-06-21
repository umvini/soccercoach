package br.com.umvini.simulationengine.models

class Result {
    var goalsHomeTeam = 0
    var goalsAwayTeam = 0
    var redCardsHomeTeam = 0
    var redCardsAwayTeam = 0

    fun addGoal(team: String) {
        if (team == "away") goalsAwayTeam++ else goalsHomeTeam++
    }

    fun addSuspension(team: String) {
        if (team == "away") redCardsAwayTeam++ else redCardsHomeTeam++
    }
}
