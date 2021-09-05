package ru.sber.oop

import kotlin.random.Random

interface Fightable {
    val powerType: String
    var healthPoints: Int
    val damageRoll: Int
    get() = Random.nextInt()

    fun attack(opponent: Fightable): Int
}
abstract class Monster: Fightable{
    abstract val name: String
    abstract val desctiption: String
    override fun attack(opponent: Fightable): Int {
        opponent.healthPoints -= damageRoll
        return damageRoll
    }
}
//TODO: create class Player, Monster, Goblin here...

class Player(val name:String,
             val isBlessed: Boolean,
             override val powerType: String,
             override var healthPoints: Int,
             override val damageRoll: Int) : Fightable{


    override fun attack(opponent: Fightable): Int {
        if(isBlessed) {
            opponent.healthPoints -= damageRoll*2
            return damageRoll*2
        }
        else {
            opponent.healthPoints -= damageRoll
            return damageRoll
        }
    }

}

class Goblin(
    override val powerType: String,
    override var healthPoints: Int,
    override val name: String,
    override val desctiption: String) : Monster() {
    override val damageRoll: Int
        get() = super.damageRoll / 2



}

