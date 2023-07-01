package services.user

import models.User
import repo.expense.ExpenseRepo
import repo.user.UserRepo

class UserServiceImpl(
    private val userRepo: UserRepo,
    private val expenseRepo: ExpenseRepo
) : UserService {

    override fun addUser(userId: String, name: String): User {
        val user = User(id = userId, name = name)
        userRepo.users[userId] = user
        expenseRepo.addUser(userId)
        return user
    }

    override fun getAllUsers(): List<User> {
        return userRepo.users.values.toList()
    }
}
