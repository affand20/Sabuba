package id.trydev.sabuba.Imunisasi

import android.app.DatePickerDialog
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.google.firebase.firestore.FirebaseFirestore
import id.trydev.sabuba.Imunisasi.Model.Imunisasi
import id.trydev.sabuba.R
import kotlinx.android.synthetic.main.activity_imunisasi.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast
import java.util.*

class ImunisasiActivity : AppCompatActivity() {

    lateinit var presenter: ImunisasiPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imunisasi)

        supportActionBar?.title = "Imunisasi"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getdata()

        btn_done_hb0.onClick {
            var selesai =""
            val calendar = Calendar.getInstance()
            val listener = DatePickerDialog.OnDateSetListener { datePicker, year, month, date ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, date)
                selesai = "$date/${month+1}/$year"
                FirebaseFirestore.getInstance().collection("imunisasi").document("HB-0").update("selesai", selesai).addOnSuccessListener {
                    getdata()
                }
            }

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(this@ImunisasiActivity, listener,year,month,day).show()

        }
        btn_done_bcg.onClick {
            var selesai =""
            val calendar = Calendar.getInstance()
            val listener = DatePickerDialog.OnDateSetListener { datePicker, year, month, date ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, date)
                selesai = "$date/${month+1}/$year"
                FirebaseFirestore.getInstance().collection("imunisasi").document("BCG").update("selesai", selesai).addOnSuccessListener {
                    getdata()
                }
            }

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(this@ImunisasiActivity, listener,year,month,day).show()
        }
        btn_done_campak.onClick {
            var selesai =""
            val calendar = Calendar.getInstance()
            val listener = DatePickerDialog.OnDateSetListener { datePicker, year, month, date ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, date)
                selesai = "$date/${month+1}/$year"
                FirebaseFirestore.getInstance().collection("imunisasi").document("Campak").update("selesai", selesai).addOnSuccessListener {
                    getdata()
                }
            }

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(this@ImunisasiActivity, listener,year,month,day).show()


        }
        btn_done_campak_lanjutan.onClick {
            var selesai =""
            val calendar = Calendar.getInstance()
            val listener = DatePickerDialog.OnDateSetListener { datePicker, year, month, date ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, date)
                selesai = "$date/${month+1}/$year"
                FirebaseFirestore.getInstance().collection("imunisasi").document("CampakLanjutan").update("selesai", selesai).addOnSuccessListener {
                    getdata()
                }
            }

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(this@ImunisasiActivity, listener,year,month,day).show()
        }
        btn_done_dpt_hb_hib_1.onClick {
            var selesai =""
            val calendar = Calendar.getInstance()
            val listener = DatePickerDialog.OnDateSetListener { datePicker, year, month, date ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, date)
                selesai = "$date/${month+1}/$year"
                FirebaseFirestore.getInstance().collection("imunisasi").document("DPT-HB-Hib1").update("selesai", selesai).addOnSuccessListener {
                    getdata()
                }
            }

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(this@ImunisasiActivity, listener,year,month,day).show()
        }
        btn_done_dpt_hb_hib_2.onClick {
            var selesai =""
            val calendar = Calendar.getInstance()
            val listener = DatePickerDialog.OnDateSetListener { datePicker, year, month, date ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, date)
                selesai = "$date/${month+1}/$year"

                FirebaseFirestore.getInstance().collection("imunisasi").document("DPT-HB-Hib2").update("selesai", selesai).addOnSuccessListener {
                    getdata()
                }
            }

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(this@ImunisasiActivity, listener,year,month,day).show()
        }
        btn_done_dpt_hb_hib_3.onClick {
            var selesai =""
            val calendar = Calendar.getInstance()
            val listener = DatePickerDialog.OnDateSetListener { datePicker, year, month, date ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, date)
                selesai = "$date/${month+1}/$year"

                FirebaseFirestore.getInstance().collection("imunisasi").document("DPT-HB-Hib3").update("selesai", selesai).addOnSuccessListener { getdata() }
            }

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(this@ImunisasiActivity, listener,year,month,day).show()
        }
        btn_done_dpt_hb_hib_lanjutan.onClick {
            var selesai =""
            val calendar = Calendar.getInstance()
            val listener = DatePickerDialog.OnDateSetListener { datePicker, year, month, date ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, date)
                selesai = "$date/${month+1}/$year"

                FirebaseFirestore.getInstance().collection("imunisasi").document("DPT-HB-HibLanjutan").update("selesai", selesai).addOnSuccessListener {
                    getdata()
                }
            }

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(this@ImunisasiActivity, listener,year,month,day).show()
        }
        btn_done_ipv.onClick {
            var selesai =""
            val calendar = Calendar.getInstance()
            val listener = DatePickerDialog.OnDateSetListener { datePicker, year, month, date ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, date)
                selesai = "$date/${month+1}/$year"

                FirebaseFirestore.getInstance().collection("imunisasi").document("IPV").update("selesai", selesai).addOnSuccessListener {
                    getdata()
                }
            }

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(this@ImunisasiActivity, listener,year,month,day).show()
        }
        btn_done_polio.onClick {
            var selesai =""
            val calendar = Calendar.getInstance()
            val listener = DatePickerDialog.OnDateSetListener { datePicker, year, month, date ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, date)
                selesai = "$date/${month+1}/$year"

                FirebaseFirestore.getInstance().collection("imunisasi").document("Polio").update("selesai", selesai).addOnSuccessListener {
                    getdata()
                }
            }

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(this@ImunisasiActivity, listener,year,month,day).show()
        }
        btn_done_polio_2.onClick {
            var selesai =""
            val calendar = Calendar.getInstance()
            val listener = DatePickerDialog.OnDateSetListener { datePicker, year, month, date ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, date)
                selesai = "$date/${month+1}/$year"

                FirebaseFirestore.getInstance().collection("imunisasi").document("Polio2").update("selesai", selesai).addOnSuccessListener { getdata() }
            }

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(this@ImunisasiActivity, listener,year,month,day).show()
        }
        btn_done_polio_3.onClick {
            var selesai =""
            val calendar = Calendar.getInstance()
            val listener = DatePickerDialog.OnDateSetListener { datePicker, year, month, date ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, date)
                selesai = "$date/${month+1}/$year"

                FirebaseFirestore.getInstance().collection("imunisasi").document("Polio3").update("selesai", selesai).addOnSuccessListener {
                    getdata()
                }
            }

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(this@ImunisasiActivity, listener,year,month,day).show()
        }
        btn_done_polio_4.onClick {
            var selesai =""
            val calendar = Calendar.getInstance()
            val listener = DatePickerDialog.OnDateSetListener { datePicker, year, month, date ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, date)
                selesai = "$date/${month+1}/$year"

                FirebaseFirestore.getInstance().collection("imunisasi").document("Polio4").update("selesai", selesai).addOnSuccessListener {
                    getdata()
                }
            }

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(this@ImunisasiActivity, listener,year,month,day).show()
        }

    }

    private fun getdata() {
        val ref = FirebaseFirestore.getInstance().collection("imunisasi")
        ref.document("BCG").get().addOnSuccessListener {
            rentang_wajar_bcg.text = it.getString("rentang_wajar")
            tgl_selesai_bcg.text = it.getString("selesai")
        }
        ref.document("Campak").get().addOnSuccessListener {
            rentang_wajar_campak.text = it.getString("rentang_wajar")
            tgl_selesai_campak.text = it.getString("selesai")
        }
        ref.document("CampakLanjutan").get().addOnSuccessListener {
            rentang_wajar_campak_lanjutan.text = it.getString("rentang_wajar")
            tgl_selesai_campak_lanjutan.text = it.getString("selesai")
        }
        ref.document("DPT-HB-Hib1").get().addOnSuccessListener {
            rentang_wajar_dpt_hb_hib_1.text = it.getString("rentang_wajar")
            tgl_selesai_dpt_hb_hib_1.text = it.getString("selesai")
        }
        ref.document("DPT-HB-Hib2").get().addOnSuccessListener {
            rentang_wajar_dpt_hb_hib_2.text = it.getString("rentang_wajar")
            tgl_selesai_dpt_hb_hib_2.text = it.getString("selesai")
        }
        ref.document("DPT-HB-Hib3").get().addOnSuccessListener {
            rentang_wajar_dpt_hb_hib_3.text = it.getString("rentang_wajar")
            tgl_selesai_dpt_hb_hib_3.text = it.getString("selesai")
        }
        ref.document("DPT-HB-HibLanjutan").get().addOnSuccessListener {
            rentang_wajar_dpt_hb_hib_lanjutan.text = it.getString("rentang_wajar")
            tgl_selesai_dpt_hb_hib_lanjutan.text = it.getString("selesai")
        }
        ref.document("HB-0").get().addOnSuccessListener {
            rentang_wajar_hb0.text = it.getString("rentang_wajar")
            tgl_selesai_hb0.text = it.getString("selesai")
        }
        ref.document("IPV").get().addOnSuccessListener {
            rentang_wajar_ipv.text = it.getString("rentang_wajar")
            tgl_selesai_ipv.text = it.getString("selesai")
        }
        ref.document("Polio").get().addOnSuccessListener {
            rentang_wajar_polio.text = it.getString("rentang_wajar")
            tgl_selesai_polio.text = it.getString("selesai")
        }
        ref.document("Polio2").get().addOnSuccessListener {
            rentang_wajar_polio_2.text = it.getString("rentang_wajar")
            tgl_selesai_polio_2.text = it.getString("selesai")
        }
        ref.document("Polio3").get().addOnSuccessListener {
            rentang_wajar_polio_3.text = it.getString("rentang_wajar")
            tgl_selesai_polio_3.text = it.getString("selesai")
        }
        ref.document("Polio4").get().addOnSuccessListener {
            rentang_wajar_polio_4.text = it.getString("rentang_wajar")
            tgl_selesai_polio_4.text = it.getString("selesai")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId==android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}
