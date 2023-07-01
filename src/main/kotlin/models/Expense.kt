package models

data class Expense(
    val paidBy: String,
    val contributors: List<String>,
    val amount: Double,
    val contributionType: ContributionType,
    val contributions: List<Double>,
)
