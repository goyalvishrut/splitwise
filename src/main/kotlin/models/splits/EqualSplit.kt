package models.splits

import models.Expense

class EqualSplit(expense: Expense) : Split(expense) {

    override fun isValid(): Boolean = true

    override val splits: Map<String, Double> = getSplitsMap(expense)

    private fun getSplitsMap(expense: Expense): Map<String, Double> {
        val split: MutableMap<String, Double> = mutableMapOf()
        val eachShare = expense.amount / (expense.contributors.size)
        expense.contributors.forEach {
            split[it] = eachShare
        }
        return split
    }
}
