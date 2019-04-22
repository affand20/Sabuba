package id.trydev.sabuba.Galeri

import id.trydev.sabuba.Galeri.Model.GaleriImage

interface GaleriView {

    fun showEmptyText()
    fun hideEmptyText()
    fun updateProgressUpload(index:Int, total:Int)
    fun hideProgressUpload()
    fun showLoading()
    fun hideLoading()
    fun showFoto(listGaleri:List<GaleriImage>)
}