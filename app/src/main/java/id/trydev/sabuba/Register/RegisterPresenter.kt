package id.trydev.sabuba.Register

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import id.trydev.sabuba.Register.Model.DataBalita
import id.trydev.sabuba.Register.Model.User
import id.trydev.sabuba.SplashActivity
import id.trydev.sabuba.Utils.AppPreferences
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class RegisterPresenter(val context:Context, val view:RegisterView):AnkoLogger {

    private val mAuth = FirebaseAuth.getInstance()
    private val mFirestore = FirebaseFirestore.getInstance()
    private val prefs = AppPreferences(context)

    fun doRegister(email:String, password:String, nama:String, role:String){
        view.showRegisterLoading()
        mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            prefs.role = role
            prefs.token = it.user.uid
            val user = User(prefs.token, nama, role)
            info("role => ${prefs.role}, token => ${prefs.token}")
            // add data to firestore
            if (role=="dokter"){
                mFirestore.collection("users")
                    .document("dokter")
                    .collection("dokter")
                    .add(user)
                    .addOnSuccessListener {
                        view.hideRegisterLoading()
                        view.showRegisterSuccess()
                    }
                    .addOnFailureListener{
                        view.hideRegisterLoading()
                        view.showRegisterFailed(it.localizedMessage)
                        info("failed => ${it.localizedMessage}")
                    }
            }

            if (role=="ibu"){
                mFirestore.collection("users")
                    .document("ibu")
                    .collection("ibu")
                    .add(user)
                    .addOnSuccessListener {
                        view.hideRegisterLoading()
                        view.showRegisterSuccess()
                    }
                    .addOnFailureListener{
                        view.hideRegisterLoading()
                        view.showRegisterFailed(it.localizedMessage)
                        info("failed => ${it.localizedMessage}")
                    }
            }

        }
    }

    fun saveDataBalita(data:DataBalita){
        view.showSubmitDataLoading()
        mFirestore.collection("data-balita").add(data)
            .addOnSuccessListener {
                view.hideSubmitDataLoading()
                view.showSubmitDataSuccess()
            }
            .addOnFailureListener {
                view.hideSubmitDataLoading()
                view.showSubmitDataFailed(it.localizedMessage)
                info("failed => ${it.localizedMessage}")
            }
    }
}