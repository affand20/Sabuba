package id.trydev.sabuba

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import id.trydev.sabuba.ChatDokter.ChatDokterActivity
import id.trydev.sabuba.Login.LoginActivity
import id.trydev.sabuba.Menu.MenuActivity
import id.trydev.sabuba.Utils.AppPreferences
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SplashActivity : AppCompatActivity(), AnkoLogger {

    lateinit var prefs:AppPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        prefs = AppPreferences(this)

        Handler().postDelayed({
            info("post delayed")
            if (prefs.token == null || prefs.role == null){
                startActivity<LoginActivity>()
                finish()
            } else if (prefs.role == "dokter"){
                startActivity<ChatDokterActivity>()
                finish()
            } else {
                startActivity<MenuActivity>()
                finish()
            }
        },2500)
    }
}
