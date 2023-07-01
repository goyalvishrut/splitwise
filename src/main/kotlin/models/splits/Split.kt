package models.splits

import models.Expense

abstract class Split(val expense: Expense) {

    abstract fun isValid(): Boolean

    abstract val splits: Map<String, Double>
}
