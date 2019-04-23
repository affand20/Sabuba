package id.trydev.sabuba.ChatDokter.ChatDetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import id.trydev.sabuba.ChatDokter.Adapter.ChatDetailAdapter
import id.trydev.sabuba.ChatDokter.Model.Chat
import id.trydev.sabuba.R
import id.trydev.sabuba.Utils.AppPreferences
import kotlinx.android.synthetic.main.activity_chat_detail.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class ChatDetailActivity : AppCompatActivity(), ChatDetailView {

    val listChat:MutableList<Chat> = mutableListOf()
    val listViewType:MutableList<Int> = mutableListOf()
    lateinit var prefs:AppPreferences
    lateinit var presenter:ChatDetailPresenter
    lateinit var adapter:ChatDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Chat"

        val roomId = intent.getStringExtra("room_id")

        prefs = AppPreferences(this)
        adapter = ChatDetailAdapter(listChat, listViewType)

        rv_chat.layoutManager = LinearLayoutManager(this)
        rv_chat.adapter = adapter

        presenter = ChatDetailPresenter(this,this)
        presenter.getChats(roomId)

        scrollDown()

        btn_send_chat.onClick {
            if (input_chat.text.isEmpty()){
                toast("Ketikkan pesan terlebih dahulu")
            } else{
                presenter.sendChat(roomId, input_chat.text.toString())
                input_chat.text.clear()
            }
        }

    }

    private fun scrollDown(){
        rv_chat.post {
            rv_chat.scrollToPosition(listChat.size)
        }
    }

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
    }

    override fun showChats(chats: List<Chat>) {
        listChat.clear()
        listChat.addAll(chats)
        listChat.forEach {
            if (it.pengirim==prefs.token){
                listViewType.add(0)     // chatnya sendiri
            } else{
                listViewType.add(1)     // chhatnya orang lain
            }
        }
        adapter.notifyDataSetChanged()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId==android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
