package id.trydev.sabuba.Menu

import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.View
import id.trydev.sabuba.DeteksiDini.DeteksiDiniActivity
import id.trydev.sabuba.Menu.ArtikelTips.Adapter.ArtikelTipsAdapter
import id.trydev.sabuba.Menu.ArtikelTips.ArtikelTipsPresenter
import id.trydev.sabuba.Menu.ArtikelTips.ArtikelTipsView
import id.trydev.sabuba.Menu.ArtikelTips.Model.ArtikelTips
import id.trydev.sabuba.R
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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
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
