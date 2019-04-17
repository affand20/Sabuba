package id.trydev.sabuba.Login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import id.trydev.sabuba.Menu.MenuActivity
import id.trydev.sabuba.R
import id.trydev.sabuba.Register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity(), LoginView{

    private lateinit var presenter:LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this, this)

        login.onClick {
            if (validate()){
                presenter.doLogin(email.text.toString(), password.text.toString())
            }
        }

        register.onClick {
            startActivity<RegisterActivity>()
            finish()
        }
    }

    fun validate():Boolean{
        if (email.text.toString().isEmpty()){
            email.requestFocus()
            email.setError("Wajib diisi")
            return false
        }
        if (password.text.toString().isEmpty()){
            password.requestFocus()
            password.setError("Wajib diisi")
            return false
        }
        return true
    }

    override fun showSuccessLogin() {
        startActivity<MenuActivity>()
        finish()
    }

    override fun showFailedLogin(msg: String) {
        toast(msg)
    }

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
    }
}
