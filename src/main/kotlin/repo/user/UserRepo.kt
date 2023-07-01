package repo.user

import models.User

interface UserRepo {

    val users: HashMap<String, User>
}
