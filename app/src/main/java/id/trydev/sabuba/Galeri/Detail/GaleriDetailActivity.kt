package id.trydev.sabuba.Galeri.Detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import id.trydev.sabuba.R
import net.alhazmy13.mediagallery.library.activity.MediaGallery
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import org.jetbrains.anko.info

class GaleriDetailActivity : FragmentActivity(), AnkoLogger{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galeri_detail)

        val listUrl = intent.getStringArrayListExtra("list-gambar")
        val position = intent.getIntExtra("position", 0)

        info("${listUrl}, ${position}")

        MediaGallery.Builder(this,listUrl).title("Title")
            .backgroundColor(android.R.color.black)
            .selectedImagePosition(position)
            .show()

    }
}
