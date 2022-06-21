package br.com.umvini.simulationengine.models.enums

enum class PlayerCharacteristic(val value: String) {
    PL("placement"), DP("penalty_save"), RF("reflex"), GE("goal_exit"), //GOAL-KEEPER
    F("frame"), H("header"), C("Crossing"), D("disarm"),
    DR("dribble"), FNZ("finalization"), M("marking"), P("pass"),
    R("resistance"), S("speed")
}