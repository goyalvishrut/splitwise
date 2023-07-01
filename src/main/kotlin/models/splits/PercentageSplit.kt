package models.splits

import models.Expense

class PercentageSplit(expense: Expense) : Split(expense) {

    override fun isValid(): Boolean {
        return expense.contributions.sum() == 100.00
    }

    override val splits: Map<String, Double> = getSplitsMap(expense)

    private fun getSplitsMap(expense: Expense): Map<String, Double> {
        val split: MutableMap<String, Double> = mutableMapOf()
        expense.contributors.forEachIndexed { ind, value ->
            split[value] = (expense.contributions[ind] * expense.amount) / 100
        }
        return split.toMap()
    }
}
