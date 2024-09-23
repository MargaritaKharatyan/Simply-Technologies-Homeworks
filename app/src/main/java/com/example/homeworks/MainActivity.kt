package com.example.homeworks

data class User(
    val name:String,
    val age:Int,
    var balance:Double = 0.0,
    var accountState:AccountState,
)

sealed class AccountState{
    data object Active:AccountState()
    data object Inactive:AccountState()
    data class Suspended(val message:String):AccountState()
}

fun printUsersInfo(items:List<User>){
    items.forEach {
        print("User: ${it.name}\n${it.age} years old\nuser balance: ${it.balance}\n")
        if(it.accountState is AccountState.Suspended){
            println("account state is Suspended, suspension reason is ${(it.accountState as AccountState.Suspended).message}")
        } else{
            println("account state is ${it.accountState}")
        }
        println()
    }
}

fun User.hasHighBalance():Boolean {
    return this.balance > 1000
}

fun filterActiveUsersWithHighBalance(items:List<User>){
    println("Active users with high balance")
    items.forEach{
        if(it.accountState is AccountState.Active && it.hasHighBalance()){
            println(it.name)
        }
    }
}

fun main(){
    val users = listOf(
        User("Margarita",22,300000.0,AccountState.Active),
        User("Armen",28,1000000.0,AccountState.Active),
        User("Anahit",7,1000.0,AccountState.Inactive),
        User("Ruzanna",22,259010.7,AccountState.Suspended("hasn't used the account for a long time")),
        User("Argishti",45,413200.3,AccountState.Active),
        User("Anna",51,94046.4,AccountState.Active),
        User("Hayk",24,200000.0,AccountState.Suspended("didn't pay taxes")),
        User("Lusine",19,6300.0,AccountState.Active),
        User("Ani",29,300000.0,AccountState.Suspended("violated the terms")),
        User("Aram",31,17023.1,AccountState.Inactive),
    )

    printUsersInfo(users)
    filterActiveUsersWithHighBalance(users)
}