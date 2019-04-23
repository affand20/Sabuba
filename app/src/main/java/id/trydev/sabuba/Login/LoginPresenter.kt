package id.trydev.sabuba.Login

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import id.trydev.sabuba.Register.Model.User
import id.trydev.sabuba.SplashActivity
import id.trydev.sabuba.Utils.AppPreferences
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class LoginPresenter(val context: Context, val view:LoginView):AnkoLogger{

    private val mAuth = FirebaseAuth.getInstance()
    private val prefs = AppPreferences(context)
    private val mFirestore = FirebaseFirestore.getInstance()

    fun doLogin(email:String, password:String){
        view.showLoading()
        mAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
            prefs.token = it.user.uid
            info("token => ${prefs.token}")
            mFirestore.collection("users")
                .document("${prefs.token}")
                .get()
                .addOnSuccessListener {snapshot ->
                    val user = snapshot.toObject(User::class.java)
                    prefs.role = user?.role
                    prefs.nama = user?.nama
                    view.hideLoading()
                    if (prefs.role=="dokter"){
                        view.showDokterMenu()
                    } else if (prefs.role == "ibu"){
                        view.showSuccessLogin()
                    }
                }
                .addOnFailureListener {
                    prefs.resetPreference()
                    view.hideLoading()
                    view.showFailedLogin(it.localizedMessage)
                }

        }.addOnFailureListener {
            prefs.resetPreference()
            info("token => ${it.localizedMessage}")
            view.hideLoading()
            view.showFailedLogin(it.localizedMessage)
        }
    }
}