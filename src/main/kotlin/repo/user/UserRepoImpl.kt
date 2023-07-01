package repo.user

import models.User

class UserRepoImpl : UserRepo {

    override val users: HashMap<String, User> = hashMapOf()
}
