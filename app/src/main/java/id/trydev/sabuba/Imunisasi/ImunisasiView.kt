package id.trydev.sabuba.Imunisasi

import id.trydev.sabuba.Imunisasi.Model.Imunisasi

interface ImunisasiView {

    fun showLoading()
    fun hideLoading()
    fun showListImunisasi(listImunisasi:List<Imunisasi>)

}