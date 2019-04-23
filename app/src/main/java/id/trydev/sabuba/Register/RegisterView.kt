package id.trydev.sabuba.Register

interface RegisterView {

    fun showRegisterLoading()
    fun hideRegisterLoading()
    fun showSubmitDataLoading()
    fun hideSubmitDataLoading()
    fun showRegisterSuccess()
    fun showRegisterFailed(msg:String)
    fun showSubmitDataSuccess()
    fun showSubmitDataFailed(msg:String)
    fun showDokterMenu()
}