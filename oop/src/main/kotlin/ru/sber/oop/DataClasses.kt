package ru.sber.oop

data class User(val name: String, val age: Long) {
     lateinit var city: String

    @Override
    override fun equals(other: Any?):Boolean {
        if (other is User) {
            return this.city == other.city && this.name == other.name && this.age == other.age
        }
        return false
    }

}

fun main() {
    val user1 = User("Alex", 13)
    val user2 = user1.copy(age = 21)
    user1.city = "Omsk"
    val user3 = user1.copy()
    user3.city = "Tomsk"
    println(user1.equals(user3))

}