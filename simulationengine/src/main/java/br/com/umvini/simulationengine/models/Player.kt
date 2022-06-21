package br.com.umvini.simulationengine.models

import br.com.umvini.simulationengine.models.enums.PlayerCharacteristic
import br.com.umvini.simulationengine.models.enums.Position
import br.com.umvini.simulationengine.models.enums.Status

class Player (
    var name: String,
    var position: Position,
    var statAtt: Int = 0,
    var statDef: Int = 0,
    var statMed: Int = 0,
    var status: Status,
    var nationality: String,
    var age: String,
    var fullName: String,
    var firstFeature: PlayerCharacteristic,
    var secondFeature: PlayerCharacteristic,
) {

   constructor(player: ArrayList<String>): this(
       name = player[NAME],
       position = Position.valueOf(player[POSITION]),
       statAtt = Integer.valueOf(player[STAT_ATT]),
       statDef = Integer.valueOf(player[STAT_DEF]),
       statMed = (Integer.valueOf(player[STAT_ATT]) + Integer.valueOf(player[STAT_DEF])) / 2,
       status = Status.valueOf(player[STATUS]),
       nationality = player[NATIONALITY],
       age = player[AGE],
       fullName = player[FULL_NAME],
       firstFeature = PlayerCharacteristic.valueOf(player[FIRST_FEATURE]),
       secondFeature = PlayerCharacteristic.valueOf(player[SECOND_FEATURE]),
   )

    companion object {
        const val NAME = 0
        const val POSITION = 1
        const val STAT_ATT = 2
        const val STAT_DEF = 3
        const val STATUS = 4
        const val NATIONALITY = 5
        const val AGE = 6
        const val FULL_NAME = 7
        const val FIRST_FEATURE = 8
        const val SECOND_FEATURE = 9
    }
}