package repo.expense

interface ExpenseRepo {

    fun addUser(userId: String)

    fun getBalanceSheet(): MutableMap<String, MutableMap<String, Double>>

    fun addExpense(paidTo: String, paidBy: String, netAmount: Double)

    fun getUserBalance(userId: String): Map<String, Double>
}
