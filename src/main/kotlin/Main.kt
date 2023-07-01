import controllers.expense.ExpenseController
import controllers.user.UserController
import models.ContributionType
import models.InputAction
import repo.expense.ExpenseRepoImpl
import repo.print.PrintRepoImpl
import repo.user.UserRepoImpl
import services.expense.ExpenseServiceImpl
import services.user.UserServiceImpl
import util.toContributionType
import util.toInputAction
import kotlin.system.exitProcess

/*
https://workat.tech/machine-coding/editorial/how-to-design-splitwise-machine-coding-ayvnfo1tfst6
 */

private val userRepo = UserRepoImpl()
private val expenseRepo = ExpenseRepoImpl()
private val printRepo = PrintRepoImpl()
private val userService = UserServiceImpl(userRepo = userRepo, expenseRepo = expenseRepo)
private val userController = UserController(userService)
private val expenseService = ExpenseServiceImpl(expenseRepo = expenseRepo, printRepo = printRepo)
private val expenseController = ExpenseController(expenseService)

fun main() {
    addUsers()

    while (true) {
        val input = readln()
        val list = input.split(" ")
        processInput(list)
    }
}

private fun processInput(input: List<String>) {
    when (input[0].toInputAction()) {
        InputAction.SHOW -> showExpense(input.subList(1, input.size))
        InputAction.QUIT -> exitProcess(1)
        InputAction.ADD -> addExpense(input.subList(1, input.size))
    }
}

private fun addUsers() {
    val u1 = userController.addUser("u1", "user1")
    val u2 = userController.addUser("u2", "user2")
    val u3 = userController.addUser("u3", "user3")
    val u4 = userController.addUser("u4", "user4")
    println(userController.getAllUsers())
}

private fun addExpense(input: List<String>) {
    val paidBy = input[0]
    val amount = input[1].toDouble()
    val totalContributors = input[2].toInt()
    val contributors = input.subList(3, 3 + totalContributors).toList()
    val contributionType = input[3 + totalContributors].toContributionType()
    val contributions: List<Double> = when (contributionType) {
        ContributionType.EQUAL -> listOf()
        ContributionType.PERCENT -> input.subList(4 + totalContributors, input.size).map { it.toDouble() }
        ContributionType.EXACT -> input.subList(4 + totalContributors, input.size).map { it.toDouble() }
    }

    expenseController.addExpense(
        amount = amount,
        paidBy = paidBy,
        contributors = contributors,
        contributionType = contributionType,
        contributions = contributions
    )
}

private fun showExpense(subList: List<String>) {
    if (subList.isEmpty()) {
        expenseController.showUserTransaction()
    } else {
        expenseController.showUserTransaction(subList[0])
    }
}
