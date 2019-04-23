package id.trydev.sabuba.Galeri.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import id.trydev.sabuba.Galeri.Model.GaleriImage
import id.trydev.sabuba.R
import kotlinx.android.synthetic.main.activity_galeri.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import org.jetbrains.anko.info
import org.jetbrains.anko.sdk27.coroutines.onClick

class GaleriAdapter(val listGaleri:List<GaleriImage>, val listener:(GaleriImage)->Unit):RecyclerView.Adapter<GaleriAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.layout_galeri, p0, false))
    }

    override fun getItemCount(): Int = listGaleri.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindItem(listGaleri[p1], listener)
    }

    class ViewHolder(val view: View):RecyclerView.ViewHolder(view),AnkoLogger {

        val image = view.find<ImageView>(R.id.img_galeri)

        fun bindItem(item:GaleriImage, listener: (GaleriImage) -> Unit){
            info("URL ${item.Url}")
//            GlideApp.with(view)
//                .asBitmap()
//                .centerCrop()
//                .thumbnail(0.25f)
//                .load(item.Url)
//                .placeholder(R.color.colorPrimary)
//                .fallback(R.color.grey)
//                .into(image)
            Glide.with(view.context)
                .load(item.Url)
                .asBitmap()
                .thumbnail(0.25f)
                .placeholder(android.R.color.white)
                .fallback(R.color.grey)
                .centerCrop()
                .into(image)

            itemView.onClick {
                listener(item)
            }
        }
    }
}