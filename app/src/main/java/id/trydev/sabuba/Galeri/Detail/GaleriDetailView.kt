package id.trydev.sabuba.Galeri.Detail

import id.trydev.sabuba.Galeri.Model.GaleriImage

interface GaleriDetailView {

    fun showLoading()
    fun hideLoading()
    fun showGaleriImage(listGaleri:List<GaleriImage>)
}