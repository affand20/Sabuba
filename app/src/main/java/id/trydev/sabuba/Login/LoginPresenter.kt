package id.trydev.sabuba.Login

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import id.trydev.sabuba.SplashActivity
import id.trydev.sabuba.Utils.AppPreferences
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class LoginPresenter(val context: Context, val view:LoginView):AnkoLogger{

    private val mAuth = FirebaseAuth.getInstance()
    private val prefs = AppPreferences(context)

    fun doLogin(email:String, password:String){
        view.showLoading()
        mAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
            prefs.token = it.user.uid
            info("token => ${prefs.token}")
            view.hideLoading()
            view.showSuccessLogin()
        }.addOnFailureListener {
            info("token => ${it.localizedMessage}")
            view.hideLoading()
            view.showFailedLogin(it.localizedMessage)
        }
    }
}