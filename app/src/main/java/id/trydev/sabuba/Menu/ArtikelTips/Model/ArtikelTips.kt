package id.trydev.sabuba.Menu.ArtikelTips.Model

//data class ArtikelTips(val judul:String, val url:String, val gambar:String )

class ArtikelTips{

    lateinit var judul:String
    lateinit var url:String
    lateinit var gambar:String

    constructor()

    constructor(judul:String, url:String, gambar:String ){
        this.judul = judul
        this.url = url
        this.gambar = gambar
    }
}