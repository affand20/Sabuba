package id.trydev.sabuba.Register.Model

class User {
    var uid:String = ""
    var nama:String = ""
    var role:String = ""

    constructor()

    constructor(uid: String, nama: String, role: String) {
        this.uid = uid
        this.nama = nama
        this.role = role
    }


}