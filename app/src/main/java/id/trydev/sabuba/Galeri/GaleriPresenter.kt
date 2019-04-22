package id.trydev.sabuba.Galeri

import android.content.Context
import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import id.trydev.sabuba.Galeri.Model.GaleriImage
import id.trydev.sabuba.Utils.AppPreferences
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.io.File
import kotlin.coroutines.Continuation

class GaleriPresenter(val ctx: Context, val view:GaleriView):AnkoLogger {

    val mStorage = FirebaseStorage.getInstance()
    val mFirestore = FirebaseFirestore.getInstance()
    val prefs = AppPreferences(ctx)
    val listGambar:MutableList<GaleriImage> = mutableListOf()

    fun uploadGambar(listUri:List<String>){
        view.hideEmptyText()
        info("upload gambar triggered")
        val ref = mStorage.reference.child("galeri/${prefs.token}")
//        val ref = mStorage.getReference("galeri/${prefs.token}")
        val urlImage:MutableList<String> = mutableListOf()
        var isSuccess: Boolean
        view.showLoading()
        view.updateProgressUpload(urlImage.size,listUri.size)
        listUri.forEachIndexed { index, s ->
            info("start uploading")
            isSuccess = false
            val file = Uri.fromFile(File(s))
            val mRef = ref.child(s.substringAfterLast('/'))
            mRef.putFile(file).addOnSuccessListener{
                mRef.downloadUrl.addOnCompleteListener {taskSnapshot ->
                    val url =  taskSnapshot.result
                    info("URL ${url}")
                    urlImage.add(url.toString())
                    mFirestore.collection("galeri")
                        .document(prefs.token)
                        .collection("galeri")
                        .add(hashMapOf("Url" to url.toString()))
                        .addOnFailureListener {
                            info(it.localizedMessage)
                        }
                        .addOnSuccessListener {
                            info("success upload 1 foto")
                            view.updateProgressUpload(urlImage.size, listUri.size)
                            if (urlImage.size==listUri.size){
                                view.hideProgressUpload()
                                getGambar()
                            }
                        }
                }
            }
        }
    }

    fun getGambar(){
        listGambar.clear()
        view.showLoading()
        view.hideEmptyText()
        mFirestore.collection("galeri")
            .document(prefs.token)
            .collection("galeri")
            .get()
            .addOnSuccessListener { foto->
                view.hideLoading()
                for (item in foto){
                    val galeriImage = item.toObject(GaleriImage::class.java)
                    listGambar.add(galeriImage)
                }
                if (listGambar.size>0) view.showFoto(listGambar)
                else view.showEmptyText()
            }
    }
}