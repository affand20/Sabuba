package id.trydev.sabuba.Login

interface LoginView{

    fun showSuccessLogin()
    fun showFailedLogin(msg:String)
    fun showLoading()
    fun hideLoading()
}