package br.com.umvini.simulationengine.models

import br.com.umvini.simulationengine.engine.Variables

class Team(
    var name: String,
    var form: Int,
    var supporters: Int,
    var playingStyle: Int
) {
    var players: ArrayList<Player> = ArrayList()
    var startingEleven: ArrayList<Player> = ArrayList()

    fun buildTeam(list: ArrayList<ArrayList<String>>) {
        for (player in list) {
            val pl = Player(player)
            if (pl.status.name == "T") addTitularPlayer(pl) else addPlayer(pl)
        }
    }

    private fun addTitularPlayer(pl: Player) {
        startingEleven.add(pl)
    }

    private fun addPlayer(pl: Player) {
        players.add(pl)
    }

    val meanAge: Float
        get() {
            var sumAge = 0f
            for (pl in startingEleven) {
                sumAge += pl.age.toFloat()
            }
            return sumAge / 11
        }

    val attack: Float
        get() {
            var sumAttack = 0f
            var count = 0
            for (pl in startingEleven) {
                if (pl.position.name == "A") {
                    sumAttack += pl.statAtt.toFloat()
                    count++
                }
            }
            return sumAttack / count
        }

    private val meanAttackingValue: Double
        get() {
            var sumAtt = 0f
            for (pl in startingEleven) {
                sumAtt += pl.statAtt.toFloat()
            }
            return (sumAtt / 11).toDouble()
        }

    val defence: Float
        get() {
            var sumDefence = 0f
            var count = 0
            for (pl in startingEleven) {
                if (pl.position.name == "D" || pl.position.name == "G") {
                    sumDefence += pl.statDef.toFloat()
                    count++
                }
            }
            return sumDefence / count
        }

    val meanValue: Float
        get() {
            var sumMed = 0f
            for (pl in startingEleven) {
                sumMed += pl.statMed.toFloat()
            }
            return sumMed / 11
        }

    fun calculateAttack(minute: Int, redCards: Int): Float {
        var att = 0f

        //Value of Attack
        att += (attack * 0.75 + meanValue * 0.25).toFloat()

        //Time Effect on Age
        att -= ageEffect(minute).toFloat()

        //Time Effect on Suspensions
        att -= redCardsEffect(redCards).toFloat()

        //Supporters Rate Effect
        att += formEffect().toFloat()

        //Form Rate Effect - High Form increases chances of goal
        //If form good it increases chances of scoring/starting attack
        if (form > Variables.MINIMAL_FORM) att += formEffect().toFloat() else att -= formEffect().toFloat()

        //Playing Style Effect
        // 0: Super Defensive
        // 100: Super Attacking
        att += playingStyleEffect().toFloat()
        return att
    }

    fun calculateGoal(minute: Int, redCards: Int): Float {
        var goal = 0f

        //Value of Scoring
        goal += (attack * 0.75 + meanAttackingValue * 0.25).toFloat()

        //Time Effect on Age with time
        goal -= ageEffect(minute).toFloat()

        //Time Effect on Suspensions
        goal -= redCardsEffect(redCards).toFloat()

        //Form Rate Effect - High Form increases chances of goal
        //If form good it increases chances of scoring/starting attack
        if (form > Variables.MINIMAL_FORM) goal += formEffect().toFloat() else goal -= formEffect().toFloat()
        return goal
    }

    fun calculateDefend(minute: Int, redCards: Int): Float {
        var defence = 0f

        //Value of Defence
        defence += (this.defence * 0.75 + meanValue * 0.25).toFloat()

        //Time Effect on Age with time
        defence -= ageEffect(minute).toFloat()

        //Time Effect on Suspensions
        defence -= redCardsEffect(redCards).toFloat()

        //Supporters Rate Effect
        defence += supportersEffect().toFloat()

        //Form Rate Effect
        defence += formEffect().toFloat()

        //Playing Style Effect
        // 0: Super Defensive
        // 100: Super Attacking
        // More attack less defence
        defence += playingStyleEffect().toFloat()
        return defence
    }

    private fun redCardsEffect(redCards: Int): Double {
        return redCards * Variables.RED_CARD_EFFECT
    }

    private fun formEffect(): Double {
        return form * Variables.FORM_EFFECT
    }

    private fun playingStyleEffect(): Double {
        return (-playingStyle * Variables.PLAYING_STYLE_EFFECT //Attack
                + (100 - playingStyle * Variables.PLAYING_STYLE_EFFECT)) //Defence
    }

    private fun supportersEffect(): Double {
        val rivalsSupporters = 100 - supporters
        return (supporters - rivalsSupporters) * Variables.SUPPORTERS_EFFECT
    }

    private fun ageEffect(minute: Int): Double {
        return meanAge * Variables.AGE_EFFECT * (minute * Variables.TIME_EFFECT)
    }

    init {
        this.form = form
        this.supporters = supporters
        this.playingStyle = playingStyle
    }
}
