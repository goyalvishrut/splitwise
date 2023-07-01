package controllers.expense

import models.ContributionType
import models.Expense
import models.splits.EqualSplit
import models.splits.ExactSplit
import models.splits.PercentageSplit
import models.splits.Split
import services.expense.ExpenseService

class ExpenseController(private val expenseService: ExpenseService) {

    fun addExpense(
        amount: Double,
        paidBy: String,
        contributors: List<String>,
        contributionType: ContributionType,
        contributions: List<Double>
    ) {
        val expense = Expense(
            amount = amount,
            paidBy = paidBy,
            contributors = contributors,
            contributionType = contributionType,
            contributions = contributions
        )
        expenseService.addExpense(
            expense = expense,
            contributionType = contributionType,
            split = getSplit(contributionType, expense)
        )
    }

    private fun getSplit(contributionType: ContributionType, expense: Expense): Split {
        return when (contributionType) {
            ContributionType.EQUAL -> EqualSplit(expense)
            ContributionType.PERCENT -> PercentageSplit(expense)
            ContributionType.EXACT -> ExactSplit(expense)
        }
    }

    fun showUserTransaction(userId: String) {
        expenseService.showUserBalance(userId)
    }

    fun showUserTransaction() {
        expenseService.showUserBalance()
    }
}
