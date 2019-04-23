package id.trydev.sabuba.DokterMenu

import id.trydev.sabuba.ChatDokter.Model.ChatList

interface DokterView {

    fun showLoading()
    fun hideLoading()
    fun showChat(listChat:List<ChatList>)
    fun hideChat()
}