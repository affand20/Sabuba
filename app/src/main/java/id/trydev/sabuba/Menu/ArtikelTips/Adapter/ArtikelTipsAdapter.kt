package id.trydev.sabuba.Menu.ArtikelTips.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import id.trydev.sabuba.Menu.ArtikelTips.Model.ArtikelTips
import id.trydev.sabuba.R
import id.trydev.sabuba.Utils.GlideApp
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick

class ArtikelTipsAdapter(val listArtikel:List<ArtikelTips>, val listener:(ArtikelTips)->Unit):RecyclerView.Adapter<ArtikelTipsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.artikel_dan_tips_layout, p0, false))
    }

    override fun getItemCount(): Int = listArtikel.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(listArtikel[position], listener)
    }

    class ViewHolder(val view: View):RecyclerView.ViewHolder(view) {
        val judul = view.find<TextView>(R.id.judul_artikel_tips)
        val image = view.find<ImageView>(R.id.gambar_artikel_tips)

        fun bindItem(item:ArtikelTips, listener: (ArtikelTips)->Unit){
            judul.text = item.judul
            GlideApp.with(view)
                .asBitmap()
                .centerCrop()
                .thumbnail(0.25f)
                .load(item.gambar)
                .fallback(R.color.grey)
                .into(image)
            itemView.onClick {
                listener(item)
            }
        }
    }
}