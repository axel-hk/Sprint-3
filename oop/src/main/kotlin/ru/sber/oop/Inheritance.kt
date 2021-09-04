package ru.sber.oop

open class Room(val name: String, val size: Int) {

    protected open val dangerLevel = 5
    val goblin: Monster = Goblin("Hidness",
                        150,
                          "Goblin Gob",
                    "Green goblin, pathetic")

    fun description() = "Room: $name"

    fun Monster.getSalutation():String {
        return "Hello, my name is ${this.name} and I'm gonna stole your money"

    }
    open fun load() = goblin.getSalutation()


    constructor() : this(name = "Room", size = 100){

    }

}

class TownSquare( name: String = "Town Square",  size: Int = 1000): Room(name, size){
    override val dangerLevel: Int = 3
    @Override
    final override fun load(): String {
        return "This room is like Town Square"
    }
}


