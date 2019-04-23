package id.trydev.sabuba.ChatDokter.ChatDetail

import android.content.Context
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import id.trydev.sabuba.ChatDokter.Model.Chat
import id.trydev.sabuba.ChatDokter.Model.ChatList
import id.trydev.sabuba.Utils.AppPreferences
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class ChatDetailPresenter(val context: Context, val view:ChatDetailView):AnkoLogger {

    val prefs = AppPreferences(context)
    val mFirestore = FirebaseFirestore.getInstance()
    val chats:MutableList<Chat> = mutableListOf()
    val mDatabase = FirebaseDatabase.getInstance()

    fun getChats(roomId: String){
        view.showLoading()
        val ref = mDatabase.getReference("chat/${roomId}/conversation")
        ref.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                chats.clear()
                p0.children.forEach {
                    val data = it.getValue(Chat::class.java)
                    if (data != null) {
                        chats.add(data)
                    }
                }

                view.showChats(chats)
                view.hideLoading()
            }

        })
    }

    fun sendChat(roomId:String, msg:String){
        val chat = Chat(false,msg,prefs.token)
        mDatabase.getReference("chat/${roomId}").child("last_chat").setValue(msg)
        mDatabase.getReference("chat/${roomId}/conversation").push().setValue(chat).addOnSuccessListener {
            getChats(roomId)
        }
    }

}