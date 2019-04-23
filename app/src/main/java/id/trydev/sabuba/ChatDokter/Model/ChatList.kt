package id.trydev.sabuba.ChatDokter.Model

class ChatList {

    var nama_dokter:String = ""
    var id_dokter:String = ""
    var nama_ibu:String = ""
    var id_ibu:String = ""
    var last_chat:String = ""
    var roomId:String = ""

    constructor()
    constructor(
        nama_dokter: String,
        id_dokter: String,
        nama_ibu: String,
        id_ibu: String,
        last_chat:String,
        roomId: String
    ) {
        this.nama_dokter = nama_dokter
        this.id_dokter = id_dokter
        this.nama_ibu = nama_ibu
        this.id_ibu = id_ibu
        this.roomId = roomId
        this.last_chat = last_chat
    }


}