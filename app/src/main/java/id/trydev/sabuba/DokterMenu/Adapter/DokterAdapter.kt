package id.trydev.sabuba.DokterMenu.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.trydev.sabuba.ChatDokter.Model.ChatList
import id.trydev.sabuba.R

class DokterAdapter(val listChat:List<ChatList>, val listener:(ChatList)->Unit):RecyclerView.Adapter<DokterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.layout_chat_dokter,p0, false))
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ViewHolder(val view: View):RecyclerView.ViewHolder(view) {

    }
}