package id.trydev.sabuba.ChatDokter.Model

class Chat {

    var isRead:Boolean = false
    var message:String = ""
    var pengirim:String = ""

    constructor()

    constructor(isRead: Boolean, message: String, pengirim: String) {
        this.isRead = isRead
        this.message = message
        this.pengirim = pengirim
    }


}