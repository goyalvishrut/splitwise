package repo.print

import util.isZero

class PrintRepoImpl : PrintRepo {

    override fun showUserData(userBalance: Map<String, Double>, userId: String) {
        val results: MutableSet<String> = mutableSetOf()
        userBalance.forEach { (key, value) ->
            if (value.isZero().not()) {
                val str = if (value > 0) {
                    "$userId owes $key: $value"
                } else {
                    "$key owes $userId: ${-value}"
                }
                results.add(str)
            }
        }
        printStrings(results)
    }

    override fun showUserData(balanceData: MutableMap<String, MutableMap<String, Double>>) {
        val results: MutableSet<String> = mutableSetOf()
        balanceData.forEach { (keyUser, userBalance) ->
            userBalance.forEach { (innerUser, value) ->
                if (value.isZero().not()) {
                    val str = if (value > 0) {
                        "$keyUser owes $innerUser: $value"
                    } else {
                        "$innerUser owes $keyUser: ${-value}"
                    }
                    results.add(str)
                }
            }
        }
        printStrings(results)
    }

    private fun printStrings(data: MutableSet<String>) {
        data.forEach {
            println(it)
        }
    }
}
