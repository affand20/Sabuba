package id.trydev.sabuba.ChatDokter.Adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.matrixxun.starry.badgetextview.MaterialBadgeTextView
import id.trydev.sabuba.ChatDokter.Model.ChatList
import id.trydev.sabuba.R
import id.trydev.sabuba.Utils.AppPreferences
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick

class ChatAdapter(val listChat:List<ChatList>, val listener:(ChatList)->Unit):RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.layout_chat_dokter, p0, false))
    }

    override fun getItemCount(): Int = listChat.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindItem(listChat[p1], listener)
    }

    class ViewHolder(val view: View):RecyclerView.ViewHolder(view) {

        val namaDokter = view.find<TextView>(R.id.nama_pengirim)
        val isiChat = view.find<TextView>(R.id.isi_chat)
        val waktuChat = view.find<TextView>(R.id.waktu_chat)
        val badgeUnread = view.find<MaterialBadgeTextView>(R.id.badge_unread_chat)

        val prefs = AppPreferences(view.context)

        fun bindItem(item:ChatList, listener:(ChatList)->Unit){
            if (prefs.role=="dokter"){
                namaDokter.text = "Ibu ${item.nama_ibu}"
                isiChat.text = item.last_chat
            } else{
                namaDokter.text = "Dokter ${item.nama_dokter}"
                isiChat.text = item.last_chat
            }

            itemView.onClick {
                listener(item)
            }
        }
    }
}