package repo.print

interface PrintRepo {

    fun showUserData(userBalance: Map<String, Double>, userId: String)

    fun showUserData(balanceData: MutableMap<String, MutableMap<String, Double>>)
}
