package br.com.umvini.simulationengine.mock

import br.com.umvini.simulationengine.Utils
import br.com.umvini.simulationengine.models.Team

class CreateMockTeams {

    companion object {
        const val MIN_NUM_TEAMS = 1
        const val MAX_NUM_TEAMS = 20
    }

    fun getMockTeams(numMinTeam: Int = MIN_NUM_TEAMS, numMaxTeam: Int = MAX_NUM_TEAMS): Team {
        val _numTeam = if (numMinTeam in MIN_NUM_TEAMS..MAX_NUM_TEAMS) numMinTeam else (MIN_NUM_TEAMS..MAX_NUM_TEAMS).random()

        return createMockTeams(_numTeam)[0]
    }

    private fun createMockTeams(numberTeams: Int = 1): ArrayList<Team>{
        val mockTeams: ArrayList<Team> = ArrayList()

        val nameMock = NameMock()
        for (i in 1..numberTeams) {
            mockTeams.add(
                Team(
                    name = nameMock.getTeamName(),
                    form = Utils().getIntRandomNumber(minNum = 50),
                    supporters = Utils().getIntRandomNumber(minNum = 50),
                    playingStyle = Utils().getIntRandomNumber(minNum = 50)
                   )
            )
        }

        return mockTeams
    }
}