package id.trydev.sabuba.Register.Model

class DataBalita{

    var nama_ayah:String = ""
    var nama_ibu:String = ""
    var alamat:String = ""
    var nomor_handphone:String = ""
    var nama_balita:String = ""
    var usia:String = ""
    var berat_badan:String = ""
    var tinggi_badan:String = ""
    var anak_ke:String = ""
    var umur_kehamilan:String = ""
    var penolong:String = ""
    var jenis_persalinan:String = ""
    var tempat_persalinan:String = ""
    var berat_badan_lahir:String = ""
    var tgl_salin:String = ""
    var wkt_lahir:String = ""

    constructor()

    constructor(
        nama_ayah: String,
        nama_ibu: String,
        alamat: String,
        nomor_handphone: String,
        nama_balita: String,
        usia: String,
        berat_badan: String,
        tinggi_badan: String,
        anak_ke: String,
        umur_kehamilan: String,
        penolong: String,
        jenis_persalinan: String,
        tempat_persalinan: String,
        berat_badan_lahir: String,
        tgl_salin: String,
        wkt_lahir: String
    ) {
        this.nama_ayah = nama_ayah
        this.nama_ibu = nama_ibu
        this.alamat = alamat
        this.nomor_handphone = nomor_handphone
        this.nama_balita = nama_balita
        this.usia = usia
        this.berat_badan = berat_badan
        this.tinggi_badan = tinggi_badan
        this.anak_ke = anak_ke
        this.umur_kehamilan = umur_kehamilan
        this.penolong = penolong
        this.jenis_persalinan = jenis_persalinan
        this.tempat_persalinan = tempat_persalinan
        this.berat_badan_lahir = berat_badan_lahir
        this.tgl_salin = tgl_salin
        this.wkt_lahir = wkt_lahir
    }
}