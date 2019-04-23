package id.trydev.sabuba.ChatDokter

import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import id.trydev.sabuba.ChatDokter.Adapter.ChatAdapter
import id.trydev.sabuba.ChatDokter.ChatDetail.ChatDetailActivity
import id.trydev.sabuba.ChatDokter.Model.ChatList
import id.trydev.sabuba.ChatDokter.NewChat.NewChatDokterActivity
import id.trydev.sabuba.Login.LoginActivity
import id.trydev.sabuba.R
import id.trydev.sabuba.Utils.AppPreferences
import kotlinx.android.synthetic.main.activity_chat_dokter.*
import kotlinx.android.synthetic.main.dialog_pilih_dokter.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ChatDokterActivity : AppCompatActivity(), ChatView, AnkoLogger {
    lateinit var prefs:AppPreferences

    lateinit var presenter:ChatPresenter
    lateinit var adapter:ChatAdapter
    val listChat:MutableList<ChatList> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_dokter)

        prefs = AppPreferences(this)

        if (prefs.role=="dokter"){
            start_new_chat.hide()
            supportActionBar?.title = "Chat"
        } else{
            start_new_chat.show()
            supportActionBar?.title = "Chat Dokter"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        presenter = ChatPresenter(this,this)
        adapter = ChatAdapter(listChat){
            presenter.updateUnreadChat(it.roomId)
            startActivity<ChatDetailActivity>("room_id" to it.roomId)
        }

        rv_chat.layoutManager = LinearLayoutManager(this)
        rv_chat.adapter = adapter

        presenter.getAllChatList()


        start_new_chat.onClick {
            startActivity<NewChatDokterActivity>()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (prefs.role=="dokter"){
            menuInflater.inflate(R.menu.options_logout, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId==R.id.logout){
            FirebaseAuth.getInstance().signOut()
            prefs.resetPreference()
            startActivity<LoginActivity>()
            finish()
        }
        if (item?.itemId==android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
    }

    override fun showEmptyChat() {
        empty_chat.visibility = View.VISIBLE
    }

    override fun hideEmptyChat() {
        empty_chat.visibility = View.GONE
    }

    override fun showListChat(listChat: List<ChatList>) {
        rv_chat.visibility = View.VISIBLE
        this.listChat.clear()
        this.listChat.addAll(listChat)
        adapter.notifyDataSetChanged()
    }

    override fun hideListChat() {
        rv_chat.visibility = View.GONE
    }
}
