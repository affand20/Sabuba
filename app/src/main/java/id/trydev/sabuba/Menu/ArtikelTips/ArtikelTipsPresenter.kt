package id.trydev.sabuba.Menu.ArtikelTips

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import id.trydev.sabuba.Menu.ArtikelTips.Model.ArtikelTips
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class ArtikelTipsPresenter(val view:ArtikelTipsView):AnkoLogger {

    val mFirestore = FirebaseFirestore.getInstance()
    val mStorage = FirebaseStorage.getInstance()
    val listArtikel:MutableList<ArtikelTips> = mutableListOf()

    fun getAllArtikelTips(){
        view.showLoading()
        mFirestore.collection("artikel-tips")
            .get()
            .addOnSuccessListener { documents ->
                view.hideLoading()
                for (document in documents){
                    val artikel = document.toObject(ArtikelTips::class.java)
                    listArtikel.add(artikel)
                }
                if (listArtikel.size>0){
                    view.showArtikelDanTips(listArtikel)
                }
            }
            .addOnFailureListener {
                view.hideLoading()
                info("Error => ${it.localizedMessage}")
                view.sendToast(it.localizedMessage)
            }
    }
}