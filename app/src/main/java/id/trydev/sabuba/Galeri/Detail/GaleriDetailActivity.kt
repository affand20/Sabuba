package id.trydev.sabuba.Galeri.Detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.veinhorn.scrollgalleryview.ScrollGalleryView
import com.veinhorn.scrollgalleryview.builder.GallerySettings
import id.trydev.sabuba.R
import org.jetbrains.anko.find

class GaleriDetailActivity : FragmentActivity() {

    lateinit var scrollGalleryView: ScrollGalleryView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galeri_detail)

        scrollGalleryView = ScrollGalleryView.from(find(R.id.scroll_gallery_view))

            .settings(
                GallerySettings.from(supportFragmentManager)
                    .thumbnailSize(100)
                    .enableZoom(true)
                    .build()
            )
                // add image here
            .build()
    }
}
