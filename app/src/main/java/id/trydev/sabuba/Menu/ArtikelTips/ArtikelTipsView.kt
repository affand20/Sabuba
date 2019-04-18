package id.trydev.sabuba.Menu.ArtikelTips

import id.trydev.sabuba.Menu.ArtikelTips.Model.ArtikelTips

interface ArtikelTipsView {

    fun showLoading()
    fun hideLoading()
    fun showArtikelDanTips(listArtikel:List<ArtikelTips>)
    fun sendToast(msg:String)
}