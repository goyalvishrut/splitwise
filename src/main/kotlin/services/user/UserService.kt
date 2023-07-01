package services.user

import models.User

interface UserService {

    fun addUser(userId: String, name: String): User

    fun getAllUsers(): List<User>
}
