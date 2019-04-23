package id.trydev.sabuba.ChatDokter.NewChat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import id.trydev.sabuba.ChatDokter.ChatDetail.ChatDetailActivity
import id.trydev.sabuba.ChatDokter.Model.ChatList
import id.trydev.sabuba.R
import id.trydev.sabuba.Register.Model.User
import id.trydev.sabuba.Utils.AppPreferences
import kotlinx.android.synthetic.main.activity_new_chat_dokter.*
import org.jetbrains.anko.startActivity

class NewChatDokterActivity : AppCompatActivity() {

    val listUser:MutableList<User> = mutableListOf()
    val listNamaDokter:MutableList<String> = mutableListOf()
    lateinit var prefs:AppPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_chat_dokter)

        progress_bar.visibility = View.VISIBLE
        prefs= AppPreferences(this)

        supportActionBar?.title = "Pilih Dokter"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val ref = FirebaseFirestore.getInstance()
            .collection("users")
            .whereEqualTo("role", "dokter")
        ref.get()
            .addOnSuccessListener {document ->
                progress_bar.visibility = View.GONE
                for (i in document){
                    val users = i.toObject(User::class.java)
                    listUser.add(users)
                    listNamaDokter.add("Dokter ${users.nama}")
                }
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listNamaDokter)
                lv_kontak_dokter.adapter = adapter
            }

        lv_kontak_dokter.setOnItemClickListener { adapterView, view, position, l ->
            val ref = FirebaseDatabase.getInstance().getReference("chat")
            val key = ref.push().key.toString()
            val chatList = ChatList(listNamaDokter[position], listUser[position].uid, prefs.nama, prefs.token, "", key)
            ref.child(key).setValue(chatList).addOnSuccessListener {
                startActivity<ChatDetailActivity>("room_id" to key)
                finish()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId==android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
