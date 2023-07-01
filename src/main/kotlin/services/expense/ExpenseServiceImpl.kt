package services.expense

import models.ContributionType
import models.Expense
import models.splits.EqualSplit
import models.splits.ExactSplit
import models.splits.PercentageSplit
import models.splits.Split
import repo.expense.ExpenseRepo
import repo.print.PrintRepo

class ExpenseServiceImpl(
    private val expenseRepo: ExpenseRepo,
    private val printRepo: PrintRepo
) : ExpenseService {

    override fun addExpense(expense: Expense, contributionType: ContributionType, split: Split) {
        if (split.isValid().not()) return
        val paidBy = expense.paidBy

        val splitType = when (contributionType) {
            ContributionType.EQUAL -> split as EqualSplit
            ContributionType.PERCENT -> split as PercentageSplit
            ContributionType.EXACT -> split as ExactSplit
        }
        splitType.splits.forEach { (paidTo, amount) ->
            if (paidBy != paidTo) {
                try {
                    val prevAmountToPay = expenseRepo.getBalanceSheet()[paidBy]!![paidTo] ?: 0.0
                    val newAmount = prevAmountToPay - amount
                    expenseRepo.addExpense(paidTo = paidTo, paidBy = paidBy, netAmount = newAmount)
                } catch (e: Exception) {
                    println("This is exception $e")
                }
            }
        }
    }

    override fun getBalance(): MutableMap<String, MutableMap<String, Double>> {
        return expenseRepo.getBalanceSheet()
    }

    override fun showUserBalance(userId: String) {
        printRepo.showUserData(expenseRepo.getUserBalance(userId), userId)
    }

    override fun showUserBalance() {
        printRepo.showUserData(expenseRepo.getBalanceSheet())
    }
}
