package id.trydev.sabuba.Menu

import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import id.trydev.sabuba.BalitaSakit.BalitaSakitActivity
import id.trydev.sabuba.ChatDokter.ChatDokterActivity
import id.trydev.sabuba.DeteksiDini.DeteksiDiniActivity
import id.trydev.sabuba.Galeri.GaleriActivity
import id.trydev.sabuba.Imunisasi.ImunisasiActivity
import id.trydev.sabuba.Login.LoginActivity
import id.trydev.sabuba.Menu.ArtikelTips.Adapter.ArtikelTipsAdapter
import id.trydev.sabuba.Menu.ArtikelTips.ArtikelTipsPresenter
import id.trydev.sabuba.Menu.ArtikelTips.ArtikelTipsView
import id.trydev.sabuba.Menu.ArtikelTips.Model.ArtikelTips
import id.trydev.sabuba.R
import id.trydev.sabuba.Utils.AppPreferences
import kotlinx.android.synthetic.main.activity_menu.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MenuActivity : AppCompatActivity(), ArtikelTipsView, AnkoLogger {

    lateinit var presenter:ArtikelTipsPresenter
    lateinit var adapter:ArtikelTipsAdapter
    val listArtikel:MutableList<ArtikelTips> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)


        val prefs = AppPreferences(this)

        presenter = ArtikelTipsPresenter(this)
        adapter = ArtikelTipsAdapter(listArtikel){
            val uri = Uri.parse(it.url)
            val intentBuilder = CustomTabsIntent.Builder()
            intentBuilder.build()
                .launchUrl(this, uri)
            intentBuilder.setCloseButtonIcon(BitmapFactory.decodeResource(resources,R.drawable.ic_close_black_24dp))
        }

        rv_artikel_tips.layoutManager = LinearLayoutManager(this)
        rv_artikel_tips.adapter = adapter
        presenter.getAllArtikelTips()

        deteksi_dini.onClick {
            startActivity<DeteksiDiniActivity>()
        }

        galeri.onClick {
            startActivity<GaleriActivity>()
        }

        balita_sakit.onClick {
            startActivity<BalitaSakitActivity>()
        }

        keluar.onClick {
            FirebaseAuth.getInstance().signOut()
            prefs.resetPreference()
            startActivity<LoginActivity>()
            finish()
        }

        chat_dokter.onClick {
            startActivity<ChatDokterActivity>()
        }

        imunisasi.onClick {
            startActivity<ImunisasiActivity>()
        }

        bantuan.onClick {
            val uri = Uri.parse("https://sites.google.com/view/pusat-bantuan-aplikasi-sabuba/beranda")
            val intentBuilder = CustomTabsIntent.Builder()
            intentBuilder.build()
                .launchUrl(this@MenuActivity, uri)
            intentBuilder.setCloseButtonIcon(BitmapFactory.decodeResource(resources,R.drawable.ic_close_black_24dp))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.chat){
            startActivity<ChatDokterActivity>()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showLoading() {
        cv_artikel.visibility = View.GONE
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        cv_artikel.visibility = View.VISIBLE
        progress_bar.visibility = View.GONE
    }

    override fun showArtikelDanTips(listArtikel: List<ArtikelTips>) {
        this.listArtikel.clear()
        this.listArtikel.addAll(listArtikel)
        info("list size ${listArtikel.size}")
        this.adapter.notifyDataSetChanged()
    }

    override fun sendToast(msg: String) {
        toast(msg)
    }
}
