package id.trydev.sabuba.Galeri

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.engine.impl.GlideEngine
import id.trydev.sabuba.Galeri.Adapter.GaleriAdapter
import id.trydev.sabuba.Galeri.Model.GaleriImage
import id.trydev.sabuba.R
import kotlinx.android.synthetic.main.activity_galeri.*
import kotlinx.android.synthetic.main.activity_galeri.progress_bar
import net.alhazmy13.mediagallery.library.activity.MediaGallery
import net.alhazmy13.mediagallery.library.views.MediaGalleryView
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

class GaleriActivity : AppCompatActivity(), GaleriView, EasyPermissions.PermissionCallbacks, AnkoLogger, MediaGalleryView.OnImageClicked {
    lateinit var presenter:GaleriPresenter

    lateinit var adapter:GaleriAdapter
    val perms = arrayOf(Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
    val listGaleri:MutableList<GaleriImage> = mutableListOf()
    var listUrl:ArrayList<String> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galeri)

        supportActionBar?.title = "Galeri"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        info(FirebaseAuth.getInstance().currentUser?.uid)

//        galeri_view.layoutManager = GridLayoutManager(this,2)
//        galeri_view.layoutManager = LinearLayoutManager(this)
//        adapter = GaleriAdapter(listGaleri){
//            startActivity<GaleriDetailActivity>("list-gambar" to listUrl, "position" to listUrl.indexOf(it.Url))
//        }



        presenter = GaleriPresenter(this,this)
//        galeri_view.adapter = adapter
        presenter.getGambar()

        fab_add_foto.onClick {
            getPermissionStorage()
        }


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    @AfterPermissionGranted(rcCamera)
    private fun getPermissionCamera() {
        if (hasPermissionCamera()){
            toast("Granted!")
        } else{
            EasyPermissions.requestPermissions(
                this,
                resources.getString(R.string.rationale_camera),
                rcCamera,
                Manifest.permission.CAMERA
            )
        }
    }


    @AfterPermissionGranted(rcStorage)
    private fun getPermissionStorage(){
        if (hasPermissionStorage()){
            Matisse.from(this)
                .choose(MimeType.ofImage(), false)
                .theme(R.style.Matisse_Zhihu)
                .countable(false)
                .maxSelectable(9)
                .gridExpectedSize(resources.getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(GlideEngine())
                .forResult(rcChoose)
        } else{
            EasyPermissions.requestPermissions(
                this,
                resources.getString(R.string.rationale_external_storage),
                rcStorage,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    }

    private fun hasPermissionCamera():Boolean = EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)

    private fun hasPermissionStorage():Boolean = EasyPermissions.hasPermissions(this,Manifest.permission.READ_EXTERNAL_STORAGE)
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)){
            AppSettingsDialog.Builder(this).build().show()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        info("requestCode ${requestCode}, perms ${perms.size}")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == rcChoose && resultCode == Activity.RESULT_OK){
            info("DATA ${Matisse.obtainResult(data)}")
            info("PATH ${Matisse.obtainPathResult(data)}")
            val listUri = Matisse.obtainPathResult(data)
            info(listUri[0].substringAfterLast('/'))
            presenter.uploadGambar(listUri)
        }
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE){
            toast("Granted permission")
        }
    }

    override fun showEmptyText() {
        galeri_view.visibility = View.GONE
        empty_text.visibility = View.VISIBLE
    }

    override fun hideEmptyText() {
        galeri_view.visibility = View.VISIBLE
        empty_text.visibility = View.GONE
    }

    override fun showLoading() {
        galeri_view.visibility = View.GONE
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        galeri_view.visibility = View.VISIBLE
        progress_bar.visibility = View.GONE
    }

    override fun hideProgressUpload() {
        progress_upload.visibility = View.GONE
    }

    override fun updateProgressUpload(index: Int, total:Int) {
        progress_upload.visibility = View.VISIBLE
        progress_upload.text = String.format(resources.getString(R.string.upload_progress),index, total)
    }

    override fun showFoto(listGaleri: List<GaleriImage>) {
        this.listGaleri.clear()
        this.listUrl.clear()
        this.listGaleri.addAll(listGaleri)
//        adapter.notifyDataSetChanged()
        listGaleri.forEach {
            listUrl.add(it.Url)
        }
        info(listUrl)

        galeri_view.setImages(listUrl)
        galeri_view.setOnImageClickListener(this)
        galeri_view.setOrientation(MediaGalleryView.VERTICAL)
        galeri_view.notifyDataSetChanged()
    }

    override fun onImageClicked(pos: Int) {
        MediaGallery.Builder(this, listUrl)
            .title("Galeri")
            .backgroundColor(android.R.color.black)
            .selectedImagePosition(pos)
            .show()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val rcCamera = 101
        const val rcStorage = 102
        const val rcChoose = 202
    }

}
