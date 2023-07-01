package controllers.user

import models.User
import services.user.UserService

class UserController(private val userService: UserService) {

    fun addUser(id: String, name: String): User {
        return userService.addUser(userId = id, name = name)
    }

    fun getAllUsers() = userService.getAllUsers()
}
