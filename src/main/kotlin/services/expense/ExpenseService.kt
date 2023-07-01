package services.expense

import models.ContributionType
import models.Expense
import models.splits.Split

interface ExpenseService {

    fun addExpense(expense: Expense, contributionType: ContributionType, split: Split)

    fun getBalance(): MutableMap<String, MutableMap<String, Double>>

    fun showUserBalance(userId: String)

    fun showUserBalance()
}
