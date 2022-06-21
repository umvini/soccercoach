package br.com.umvini.simulationengine.engine

import br.com.umvini.simulationengine.mock.CrearteMockReferee
import br.com.umvini.simulationengine.mock.CreateMockPlayers
import br.com.umvini.simulationengine.mock.CreateMockTeams
import br.com.umvini.simulationengine.models.Referee
import br.com.umvini.simulationengine.models.Team
import kotlin.collections.ArrayList

class LoadInfo {
    lateinit var homeTeam: Team
    lateinit var awayTeam: Team
    lateinit var ref: Referee

    fun newLoad(): LoadInfo {

        //Create MockTeams
        homeTeam = CreateMockTeams().getMockTeams(1)
        awayTeam = CreateMockTeams().getMockTeams(1)

        //Create MockPlayers and set players in Team
        homeTeam.buildTeam(CreateMockPlayers().getMockPlayers(11))
        awayTeam.buildTeam(CreateMockPlayers().getMockPlayers(11))

        //Input info about Referee
        ref = CrearteMockReferee().getMockReferee(1)
        return this
    }
}