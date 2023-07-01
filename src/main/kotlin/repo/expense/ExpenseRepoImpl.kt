package repo.expense

class ExpenseRepoImpl : ExpenseRepo {

    private val balanceSheet: MutableMap<String, MutableMap<String, Double>> = hashMapOf()

    override fun addUser(userId: String) {
        balanceSheet[userId] = mutableMapOf()
    }

    override fun getBalanceSheet(): MutableMap<String, MutableMap<String, Double>> {
        return balanceSheet
    }

    override fun addExpense(paidTo: String, paidBy: String, netAmount: Double) {
        balanceSheet[paidBy]!![paidTo] = netAmount
        balanceSheet[paidTo]!![paidBy] = -netAmount
    }

    override fun getUserBalance(userId: String): Map<String, Double> {
        return balanceSheet[userId]?.toMap() ?: mapOf()
    }
}
