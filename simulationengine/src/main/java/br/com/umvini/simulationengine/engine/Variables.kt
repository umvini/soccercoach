package br.com.umvini.simulationengine.engine

object Variables {
    const val SCORE_FACTOR = 0.75
    const val ATTACK_FACTOR = 0.75
    const val FAULT_FACTOR = 0.01
    @JvmField
    var LUCK_FACTOR = Math.random()
    const val AGE_EFFECT = 0.5
    const val TIME_EFFECT = 0.1
    const val SUPPORTERS_EFFECT = 0.01
    const val FORM_EFFECT = 0.1
    const val PLAYING_STYLE_EFFECT = 0.2
    const val MINIMAL_FORM = 75.0
    const val REFEREE_PERSONALITY_FACTOR = 0.02
    const val REFEREE_RED_CARD = 0.1
    const val RED_CARD_EFFECT = 50.0
}