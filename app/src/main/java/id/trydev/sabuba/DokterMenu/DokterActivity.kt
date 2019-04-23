package id.trydev.sabuba.DokterMenu

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import id.trydev.sabuba.ChatDokter.Model.ChatList
import id.trydev.sabuba.R
import kotlinx.android.synthetic.main.activity_dokter.*

class DokterActivity : AppCompatActivity(),DokterView {

    lateinit var presenter:DokterPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dokter)
    }

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
    }

    override fun showChat(listChat:List<ChatList>) {
        rv_list_chat.visibility = View.VISIBLE
    }

    override fun hideChat() {
        rv_list_chat.visibility = View.GONE
    }
}
