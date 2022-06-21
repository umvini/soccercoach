package br.com.umvini.simulationengine.mock

import br.com.umvini.simulationengine.Utils
import br.com.umvini.simulationengine.models.Referee
import br.com.umvini.simulationengine.models.enums.Country

class CrearteMockReferee {

    companion object {
        const val MIN_REFEREE_PERSONALITY = 30
        const val MAX_REFEREE_PERSONALITY = 80
    }

    fun getMockReferee(numReferee: Int = 1): Referee{
        return createMockReferee(1)[0]
    }

    private fun createMockReferee(numReferee: Int): ArrayList<Referee> {
        val listMockReferee: ArrayList<Referee> = ArrayList()
        val nameMock = NameMock()

        for (i in 1..numReferee) {
            listMockReferee.add(
                Referee(
                    name = nameMock.getCompleteName(),
                    nationality = Country.values()[(0 until Country.values().size).random()].name,
                    Utils().getIntRandomNumber(minNum = MIN_REFEREE_PERSONALITY, maxNum = MAX_REFEREE_PERSONALITY)
                )
            )
        }

        return listMockReferee
    }
}