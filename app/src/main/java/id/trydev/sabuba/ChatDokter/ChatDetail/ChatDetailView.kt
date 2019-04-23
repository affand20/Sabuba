package id.trydev.sabuba.ChatDokter.ChatDetail

import id.trydev.sabuba.ChatDokter.Model.Chat

interface ChatDetailView {

    fun showLoading()
    fun hideLoading()
    fun showChats(chats:List<Chat>)

}