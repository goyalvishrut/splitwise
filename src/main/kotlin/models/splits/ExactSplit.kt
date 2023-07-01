package models.splits

import models.Expense

class ExactSplit(expense: Expense) : Split(expense) {

    override fun isValid(): Boolean {
        return expense.contributions.sum() == expense.amount
    }

    override val splits: Map<String, Double> = getSplitsMap(expense)

    private fun getSplitsMap(expense: Expense): Map<String, Double> {
        val split: MutableMap<String, Double> = mutableMapOf()
        expense.contributors.forEachIndexed { ind, value ->
            split[value] = expense.contributions[ind]
        }
        return split.toMap()
    }
}
