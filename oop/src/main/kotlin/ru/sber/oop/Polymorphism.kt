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
        val attackPoints = damageRoll
        opponent.healthPoints -= attackPoints
        return attackPoints
    }
}
//TODO: create class Player, Monster, Goblin here...

class Player(val name:String,
             val isBlessed: Boolean,
             override val powerType: String,
             override var healthPoints: Int,
             override val damageRoll: Int) : Fightable{


    override fun attack(opponent: Fightable): Int {
        val attackPoints = damageRoll
        if(isBlessed) {
            opponent.healthPoints -= attackPoints*2
            return attackPoints*2
        }
        else {
            opponent.healthPoints -= attackPoints
            return attackPoints
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
fun main() {
    val p1 = Player("name1", false, "powerType1", 100, 5)
    val e1 = Goblin("powerType2", 100, "name2", "desc2")
    println(e1.attack(p1))
    println(100 - p1.healthPoints)
}
