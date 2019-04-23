package id.trydev.sabuba.ChatDokter

import android.content.Context
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import id.trydev.sabuba.ChatDokter.Model.Chat
import id.trydev.sabuba.ChatDokter.Model.ChatList
import id.trydev.sabuba.Register.Model.User
import id.trydev.sabuba.Utils.AppPreferences
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class ChatPresenter(val view:ChatView, val context: Context):AnkoLogger {

    val mFirestore = FirebaseFirestore.getInstance()
    val prefs = AppPreferences(context)
    val listChat:MutableList<ChatList> = mutableListOf()
    val listDokter:MutableList<User> = mutableListOf()
    val mDatabase =FirebaseDatabase.getInstance()

    fun getAllChatList(){
        listChat.clear()
        view.showLoading()
        val ref = mDatabase.getReference("chat")
        ref.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach{
                    val chatList = it.getValue(ChatList::class.java)
                    if (chatList != null) {
                        if (prefs.role=="dokter"){
                            if (prefs.token==chatList.id_dokter){
                                listChat.add(chatList)
                            }
                        } else if (prefs.role=="ibu"){
                            if (prefs.token==chatList.id_ibu){
                                listChat.add(chatList)
                            }
                        }

                    }
                }
                view.showListChat(listChat)
                view.hideLoading()
            }
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    fun updateUnreadChat(roomId:String){
        val rootRef = mFirestore.collection("chat-dokter").document(roomId).collection("chats")
        rootRef.get()
            .addOnSuccessListener {
                it.forEach {doc ->
                    val chat = doc.toObject(Chat::class.java)
                    if (chat.pengirim!=prefs.token){
                        rootRef.document(doc.id)
                            .update("isRead", true)
                    }

                }
            }
        if (prefs.role=="dokter"){
            mFirestore.collection("chat-dokter").document(roomId).update("unread_dokter", 0)
        } else{
            mFirestore.collection("chat-dokter").document(roomId).update("unread_ibu", 0)
        }

    }

}