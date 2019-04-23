package id.trydev.sabuba.Register

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import id.trydev.sabuba.ChatDokter.ChatDokterActivity
import id.trydev.sabuba.DokterMenu.DokterActivity
import id.trydev.sabuba.Login.LoginActivity
import id.trydev.sabuba.Menu.MenuActivity
import id.trydev.sabuba.R
import id.trydev.sabuba.Register.Model.DataBalita
import id.trydev.sabuba.Utils.AnimSupport
import kotlinx.android.synthetic.main.activity_register.*
import me.adawoud.bottomsheettimepicker.BottomSheetTimeRangePicker
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.sdk27.coroutines.onFocusChange
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.time.Duration
import java.util.*
import kotlin.math.min

class RegisterActivity : AppCompatActivity(), RegisterView, AnkoLogger {

    private lateinit var presenter:RegisterPresenter
    private val anim = AnimSupport()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        presenter = RegisterPresenter(this,this)

        register_ibu.onClick {
            if (validateRegisterAkun()){
                presenter.doRegister(email.text.toString(), password.text.toString(), nama_lengkap.text.toString(), role = "ibu")
            }
        }

        register_dokter.onClick {
            if (validateRegisterAkun()){
                presenter.doRegister(email.text.toString(), password.text.toString(), nama_lengkap.text.toString(), role = "dokter")
            }
        }

        login.onClick {
            startActivity<LoginActivity>()
            finish()
        }

        next_form_ortu_balita.onClick {
            if (validateFormOrtuBalita()){
                showFormBalita()
            }
        }

        next_form_balita.onClick {
            if (validateFormBalita()){
                showFormRiwayatKelahiran()
            }
        }

        submit_data.onClick {
            if (validateFormRiwayatKelahiran()){
                var penolong:String = ""
                var jenisPersalinan = ""
                var tempatPersalinan = ""

                when(radio_group_penolong.checkedRadioButtonId){
                    R.id.radio_bidan -> penolong = radio_bidan.text.toString()
                    R.id.radio_dokter -> penolong = radio_dokter.text.toString()
                }

                when(radio_group_jenis_persalinan.checkedRadioButtonId){
                    R.id.radio_normal -> jenisPersalinan = radio_normal.text.toString()
                    R.id.radio_operasi -> jenisPersalinan = radio_operasi.text.toString()
                }

                when(radio_group_tempat_persalinan.checkedRadioButtonId){
                    R.id.radio_bidan_praktik_mandiri -> tempatPersalinan = radio_bidan_praktik_mandiri.text.toString()
                    R.id.radio_puskesmas -> tempatPersalinan = radio_puskesmas.text.toString()
                    R.id.radio_rumah_sakit -> tempatPersalinan = radio_rumah_sakit.text.toString()
                }

                val dataBalita = DataBalita(
                    nama_ayah.text.toString(),
                    nama_ibu.text.toString(),
                    alamat_rumah.text.toString(),
                    nomor_hp.text.toString(),
                    nama_balita.text.toString(),
                    usia.text.toString(),
                    berat_badan.text.toString(),
                    tinggi_badan.text.toString(),
                    anak_ke.text.toString(),
                    umur_kehamilan.text.toString(),
                    penolong,
                    jenisPersalinan,
                    tempatPersalinan,
                    berat_badan_lahir.text.toString(),
                    tgl_persalinan.text.toString(),
                    waktu_kelahiran.text.toString()
                )

                presenter.saveDataBalita(dataBalita)
            }
        }

        val calendar = Calendar.getInstance()
        val listener = DatePickerDialog.OnDateSetListener { datePicker, year, month, date ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, date)
            tgl_persalinan.setText("${datePicker.dayOfMonth}/${datePicker.month+1}/${datePicker.year}")
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tgl_persalinan.showSoftInputOnFocus = false
        }
        tgl_persalinan.onFocusChange { v, hasFocus ->
            if (hasFocus){
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                DatePickerDialog(this@RegisterActivity, listener,year,month,day).show()
            }
        }
        tgl_persalinan.onClick {

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)


            DatePickerDialog(this@RegisterActivity, listener,year,month,day).show()
        }

        val calendar2 = Calendar.getInstance()
        val listener2 = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            info("hour ${hour}, minute ${minute}")
            var jam = ""
            var menit = ""
            if (hour.toString().length==1){
                jam = "0${hour}"
            }
            if (minute.toString().length==1){
                menit = "0${minute}"
            }
            waktu_kelahiran.setText("$jam:$menit")

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            waktu_kelahiran.showSoftInputOnFocus = false
        }
        waktu_kelahiran.onFocusChange { v, hasFocus ->
            if (hasFocus){

                val hour = calendar2.get(Calendar.HOUR_OF_DAY)
                val minute = calendar2.get(Calendar.MINUTE)

                TimePickerDialog(this@RegisterActivity,listener2,hour,minute,true).show()
            }
        }
        waktu_kelahiran.onClick {

            val hour = calendar2.get(Calendar.HOUR_OF_DAY)
            val minute = calendar2.get(Calendar.MINUTE)

            TimePickerDialog(this@RegisterActivity,listener2,hour,minute,true).show()
        }

    }

    fun validateRegisterAkun():Boolean{
        if (nama_lengkap.text.toString().isEmpty()){
            nama_lengkap.requestFocus()
            nama_lengkap.setError("Wajib diisi !")
            return false
        }

        if (email.text.toString().isEmpty()){
            email.requestFocus()
            email.setError("Wajib diisi !")
            return false
        }

        if (password.text.toString().isEmpty()){
            password.requestFocus()
            password.setError("Wajib diisi !")
            return false
        }

        if (confirm_password.text.toString().isEmpty()){
            confirm_password.requestFocus()
            confirm_password.setError("Wajib diisi !")
            return false
        }

        if (confirm_password.text.toString()!=password.text.toString()){
            confirm_password.requestFocus()
            confirm_password.setError("Password tidak sama !")
            return false
        }
        return true
    }

    fun validateFormOrtuBalita():Boolean{
        if (nama_ayah.text.toString().isEmpty()){
            nama_ayah.requestFocus()
            nama_ayah.setError("Wajib diisi")
            return false
        }
        if (nama_ibu.text.toString().isEmpty()){
            nama_ibu.requestFocus()
            nama_ibu.setError("Wajib diisi")
            return false
        }
        if (alamat_rumah.text.toString().isEmpty()){
            alamat_rumah.requestFocus()
            alamat_rumah.setError("Wajib diisi")
            return false
        }
        if (nomor_hp.text.toString().isEmpty()){
            nomor_hp.requestFocus()
            nomor_hp.setError("Wajib diisi")
            return false
        }

        return true
    }

    fun validateFormBalita():Boolean{
        if (nama_balita.text.toString().isEmpty()){
            nama_balita.requestFocus()
            nama_balita.setError("Wajib diisi")
            return false
        }
        if (usia.text.toString().isEmpty()){
            usia.requestFocus()
            usia.setError("Wajib diisi")
            return false
        }
        if (berat_badan.text.toString().isEmpty()){
            berat_badan.requestFocus()
            berat_badan.setError("Wajib diisi")
            return false
        }
        if (tinggi_badan.text.toString().isEmpty()){
            tinggi_badan.requestFocus()
            tinggi_badan.setError("Wajib diisi")
            return false
        }
        return true
    }

    fun validateFormRiwayatKelahiran():Boolean{
        if (anak_ke.text.toString().isEmpty()){
            anak_ke.requestFocus()
            anak_ke.setError("Wajib diisi")
            return false
        }
        if (umur_kehamilan.text.toString().isEmpty()){
            umur_kehamilan.requestFocus()
            umur_kehamilan.setError("Wajib diisi")
            return false
        }
        if (radio_group_penolong.checkedRadioButtonId==-1){
            toast("Penolong belum dipilih !")
            return false
        }
        if (radio_group_jenis_persalinan.checkedRadioButtonId==-1){
            toast("Jenis persalinan belum dipilih !")
            return false
        }
        if (radio_group_tempat_persalinan.checkedRadioButtonId==-1){
            toast("Tempat persalinan belum dipilih !")
            return false
        }
        if (berat_badan_lahir.text.toString().isEmpty()){
            berat_badan_lahir.requestFocus()
            berat_badan_lahir.setError("Wajib diisi")
            return false
        }
        if (tgl_persalinan.text.toString().isEmpty()){
            tgl_persalinan.requestFocus()
            tgl_persalinan.setError("Wajib diisi")
            return false
        }
        if (waktu_kelahiran.text.toString().isEmpty()){
            waktu_kelahiran.requestFocus()
            waktu_kelahiran.setError("Wajib diisi")
            return false
        }
        return true
    }

    fun showFormRiwayatKelahiran() {
        anim.slideLeftHideAnim(form_balita, object: AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)

                form_balita.visibility = View.GONE
                form_riwayat_kelahiran.visibility = View.VISIBLE
                anim.slideLeftShowAnim(form_riwayat_kelahiran)
            }
        })
    }

    fun showFormBalita(){
        anim.slideLeftHideAnim(form_ortu_balita, object:AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)

                form_ortu_balita.visibility = View.GONE
                form_balita.visibility = View.VISIBLE
                anim.slideLeftShowAnim(form_balita)
            }
        })
    }

    override fun showRegisterSuccess() {
        anim.slideLeftHideAnim(form_register, object: AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)

                form_register.visibility = View.GONE
                form_ortu_balita.visibility = View.VISIBLE
                anim.slideLeftShowAnim(form_ortu_balita)
            }
        })
    }

    override fun onBackPressed() {
        if (form_riwayat_kelahiran.visibility == View.VISIBLE){
            anim.slideRightHideAnim(form_riwayat_kelahiran, object: AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)

                    form_riwayat_kelahiran.visibility = View.GONE
                    form_balita.visibility = View.VISIBLE
                    anim.slideRightShowAnim(form_balita)
                }
            })
        }
        else if (form_balita.visibility==View.VISIBLE){
            anim.slideRightHideAnim(form_balita, object:AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)

                    form_balita.visibility = View.GONE
                    form_ortu_balita.visibility = View.VISIBLE
                    anim.slideRightShowAnim(form_ortu_balita)
                }
            })
        }
        else if (form_ortu_balita.visibility == View.VISIBLE){
//            anim.slideRightHideAnim(form_ortu_balita, object: AnimatorListenerAdapter(){
//                override fun onAnimationEnd(animation: Animator?) {
//                    super.onAnimationEnd(animation)
//
//                    form_ortu_balita.visibility = View.GONE
//                    form_register.visibility = View.VISIBLE
//                    anim.slideRightShowAnim(form_register)
//                }
//            })
            super.onBackPressed()
        }
        else{
            super.onBackPressed()
        }
    }

    override fun showRegisterLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideRegisterLoading() {
        progress_bar.visibility = View.GONE
    }

    override fun showSubmitDataLoading() {
        progress_bar_submit_data.visibility = View.VISIBLE
    }

    override fun hideSubmitDataLoading() {
        progress_bar_submit_data.visibility = View.GONE
    }

    override fun showRegisterFailed(msg:String) {
        toast(msg)
    }

    override fun showSubmitDataSuccess() {
        startActivity<MenuActivity>()
        finish()
    }

    override fun showSubmitDataFailed(msg:String) {
        toast(msg)
    }

    override fun showDokterMenu() {
        startActivity<ChatDokterActivity>()
        finish()
    }
}
