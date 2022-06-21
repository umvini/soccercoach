package br.com.umvini.simulationengine

import java.lang.Math.abs

class Utils {
    fun getStringRandomNumber(minNum: Int = 0, maxNum: Int = 100): String {
        return (minNum..maxNum).random().toString()
    }

    fun getIntRandomNumber(minNum: Int = 0, maxNum: Int = 100): Int {
        return (minNum..maxNum).random()
    }

    fun chancePercent(chance: Int, universe: Int = 100): Boolean {
        val _chance = kotlin.math.abs(chance)
        val _universe = kotlin.math.abs(universe)

        return (1.._universe).random() <= _chance
    }
}