package id.trydev.sabuba.ChatDokter

import id.trydev.sabuba.ChatDokter.Model.ChatList

interface ChatView {

    fun showLoading()
    fun hideLoading()
    fun showEmptyChat()
    fun hideEmptyChat()
    fun showListChat(listChat:List<ChatList>)
    fun hideListChat()
}