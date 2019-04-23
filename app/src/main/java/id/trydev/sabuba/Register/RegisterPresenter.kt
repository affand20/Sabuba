package id.trydev.sabuba.Register

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import id.trydev.sabuba.Register.Model.DataBalita
import id.trydev.sabuba.Register.Model.User
import id.trydev.sabuba.SplashActivity
import id.trydev.sabuba.Utils.AppPreferences
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class RegisterPresenter(val context:Context, val view:RegisterView):AnkoLogger {

    private val mAuth = FirebaseAuth.getInstance()
    private val mFirestore = FirebaseFirestore.getInstance()
    private val prefs = AppPreferences(context)

    fun doRegister(email:String, password:String, nama:String, role:String){
        view.showRegisterLoading()
        mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            prefs.role = role
            prefs.token = it.user.uid
            prefs.nama = nama
            val user = User(prefs.token, nama, role)
            info("role => ${prefs.role}, token => ${prefs.token}")
            // add data to firestore
            if (role=="dokter"){
                mFirestore.collection("users")
                    .document(prefs.token)
                    .set(user)
                    .addOnSuccessListener {
                        view.hideRegisterLoading()
                        view.showDokterMenu()
                    }
                    .addOnFailureListener{
                        view.hideRegisterLoading()
                        view.showRegisterFailed(it.localizedMessage)
                        info("failed => ${it.localizedMessage}")
                    }
            }

            if (role=="ibu"){
                mFirestore.collection("users")
                    .document(prefs.token)
                    .set(user)
                    .addOnSuccessListener {
                        view.hideRegisterLoading()
                        view.showRegisterSuccess()
                    }
                    .addOnFailureListener{
                        view.hideRegisterLoading()
                        view.showRegisterFailed(it.localizedMessage)
                        info("failed => ${it.localizedMessage}")
                    }
            }

        }
    }

    fun saveDataBalita(data:DataBalita){

        view.showSubmitDataLoading()
        mFirestore.collection("data-balita")
            .document("${prefs.token}")
            .set(data)
            .addOnSuccessListener {
                generateDataImunisasiHb0(data.tgl_salin)
                generateDataImunisasiBCG(data.tgl_salin)
                generateDataImunisasiPolio(data.tgl_salin)
                generateDataImunisasiDPTHBHib1(data.tgl_salin)
                generateDataImunisasiPolio2(data.tgl_salin)
                generateDataImunisasiDPTHBHib2(data.tgl_salin)
                generateDataImunisasiPolio3(data.tgl_salin)
                generateDataImunisasiDPTHBHib3(data.tgl_salin)
                generateDataImunisasiPolio4(data.tgl_salin)
                generateDataImunisasiIPV(data.tgl_salin)
                generateDataImunisasiCampak(data.tgl_salin)
                generateDataImunisasiCampakLanjutan(data.tgl_salin)
                generateDataImunisasiDPTHBHibLanjutan(data.tgl_salin)
                view.hideSubmitDataLoading()
                view.showSubmitDataSuccess()
            }
            .addOnFailureListener {
                view.hideSubmitDataLoading()
                view.showSubmitDataFailed(it.localizedMessage)
                info("failed => ${it.localizedMessage}")
            }
    }

    fun generateDataImunisasiHb0(tgl:String){
        val bln = tgl.split('/')

        var tanggal = bln[0].toInt()
        var bulan = bln[1].toInt()
        var tahun = bln[2].toInt()

        info("BULAN = ${bln}")

        if (bulan+1>12){
            bulan = (bulan+1)-12
            tahun+= 1
        } else{
            bulan += 1
        }

        val rentang_wajar = "$tgl - $tanggal/$bulan/$tahun"
        info("RENTANG_WAJAR => ${rentang_wajar}")

        mFirestore.collection("imunisasi")
            .document("HB-0")
            .set(hashMapOf("rentang_wajar" to rentang_wajar, "selesai" to ""))
    }

    fun generateDataImunisasiBCG(tgl:String){
        val bln = tgl.split('/')

        var tanggal = bln[0].toInt()
        var bulan = bln[1].toInt()
        var tahun = bln[2].toInt()

        info("BULAN = ${bln}")

        if (bulan+2>12){
            bulan = (bulan+2)-12
            tahun+= 1
        } else{
            bulan += 2
        }

        val rentang_wajar = "$tgl - $tanggal/$bulan/$tahun"
        info("RENTANG_WAJAR => ${rentang_wajar}")

        mFirestore.collection("imunisasi")
            .document("BCG")
            .set(hashMapOf("rentang_wajar" to rentang_wajar, "selesai" to ""))
    }

    fun generateDataImunisasiPolio(tgl:String){
        val bln = tgl.split('/')

        var tanggal = bln[0].toInt()
        var bulan = bln[1].toInt()
        var tahun = bln[2].toInt()

        info("BULAN = ${bln}")

        if (bulan+2>12){
            bulan = (bulan+2)-12
            tahun+= 1
        } else{
            bulan += 2
        }

        val rentang_wajar = "$tgl - $tanggal/$bulan/$tahun"
        info("RENTANG_WAJAR => ${rentang_wajar}")

        mFirestore.collection("imunisasi")
            .document("Polio")
            .set(hashMapOf("rentang_wajar" to rentang_wajar, "selesai" to ""))
    }

    fun generateDataImunisasiDPTHBHib1(tgl:String){
        val bln = tgl.split('/')

        var tanggal = bln[0].toInt()
        var bulan = bln[1].toInt()
        var tahun = bln[2].toInt()

        var bulanMulai = bulan
        var tahunMulai = tahun

        if (bulanMulai+2>12){
            bulanMulai = (bulanMulai+2)-12
            tahunMulai+=1
        } else{
            bulanMulai+=2
        }

        if (bulanMulai+1>12){
            bulan = (bulanMulai+1)-12
            tahun = tahunMulai+1
        } else{
            bulan = bulanMulai+1
            tahun = tahunMulai
        }

        val rentang_wajar = "$tanggal/$bulanMulai/$tahunMulai - $tanggal/$bulan/$tahun"
        info("RENTANG_WAJAR => ${rentang_wajar}")

        mFirestore.collection("imunisasi")
            .document("DPT-HB-Hib1")
            .set(hashMapOf("rentang_wajar" to rentang_wajar, "selesai" to ""))
    }

    fun generateDataImunisasiPolio2(tgl:String){
        val bln = tgl.split('/')

        var tanggal = bln[0].toInt()
        var bulan = bln[1].toInt()
        var tahun = bln[2].toInt()

        var bulanMulai = bulan
        var tahunMulai = tahun

        if (bulanMulai+2>12){
            bulanMulai = (bulanMulai+2)-12
            tahunMulai+=1
        } else{
            bulanMulai+=2
        }

        if (bulanMulai+1>12){
            bulan = (bulanMulai+1)-12
            tahun = tahunMulai+1
        } else{
            bulan = bulanMulai+1
            tahun = tahunMulai
        }

        val rentang_wajar = "$tanggal/$bulanMulai/$tahunMulai - $tanggal/$bulan/$tahun"
        info("RENTANG_WAJAR => ${rentang_wajar}")

        mFirestore.collection("imunisasi")
            .document("Polio2")
            .set(hashMapOf("rentang_wajar" to rentang_wajar, "selesai" to ""))
    }

    fun generateDataImunisasiDPTHBHib2(tgl:String){
        val bln = tgl.split('/')

        var tanggal = bln[0].toInt()
        var bulan = bln[1].toInt()
        var tahun = bln[2].toInt()

        var bulanMulai = bulan
        var tahunMulai = tahun

        if (bulanMulai+3>12){
            bulanMulai = (bulanMulai+3)-12
            tahunMulai+=1
        } else{
            bulanMulai+=3
        }

        if (bulanMulai+1>12){
            bulan = (bulanMulai+1)-12
            tahun = tahunMulai+1
        } else{
            bulan = bulanMulai+1
            tahun = tahunMulai
        }

        val rentang_wajar = "$tanggal/$bulanMulai/$tahunMulai - $tanggal/$bulan/$tahun"
        info("RENTANG_WAJAR => ${rentang_wajar}")

        mFirestore.collection("imunisasi")
            .document("DPT-HB-Hib2")
            .set(hashMapOf("rentang_wajar" to rentang_wajar, "selesai" to ""))
    }

    fun generateDataImunisasiPolio3(tgl:String){
        val bln = tgl.split('/')

        var tanggal = bln[0].toInt()
        var bulan = bln[1].toInt()
        var tahun = bln[2].toInt()

        var bulanMulai = bulan
        var tahunMulai = tahun

        if (bulanMulai+3>12){
            bulanMulai = (bulanMulai+3)-12
            tahunMulai+=1
        } else{
            bulanMulai+=3
        }

        if (bulanMulai+1>12){
            bulan = (bulanMulai+1)-12
            tahun = tahunMulai+1
        } else{
            bulan = bulanMulai+1
            tahun = tahunMulai
        }

        val rentang_wajar = "$tanggal/$bulanMulai/$tahunMulai - $tanggal/$bulan/$tahun"
        info("RENTANG_WAJAR => ${rentang_wajar}")

        mFirestore.collection("imunisasi")
            .document("Polio3")
            .set(hashMapOf("rentang_wajar" to rentang_wajar, "selesai" to ""))
    }

    fun generateDataImunisasiDPTHBHib3(tgl:String){
        val bln = tgl.split('/')

        var tanggal = bln[0].toInt()
        var bulan = bln[1].toInt()
        var tahun = bln[2].toInt()

        var bulanMulai = bulan
        var tahunMulai = tahun

        if (bulanMulai+4>12){
            bulanMulai = (bulanMulai+4)-12
            tahunMulai+=1
        } else{
            bulanMulai+=4
        }

        if (bulanMulai+1>12){
            bulan = (bulanMulai+1)-12
            tahun = tahunMulai+1
        } else{
            bulan = bulanMulai+1
            tahun = tahunMulai
        }

        val rentang_wajar = "$tanggal/$bulanMulai/$tahunMulai - $tanggal/$bulan/$tahun"
        info("RENTANG_WAJAR => ${rentang_wajar}")

        mFirestore.collection("imunisasi")
            .document("DPT-HB-Hib3")
            .set(hashMapOf("rentang_wajar" to rentang_wajar, "selesai" to ""))
    }

    fun generateDataImunisasiPolio4(tgl:String){
        val bln = tgl.split('/')

        var tanggal = bln[0].toInt()
        var bulan = bln[1].toInt()
        var tahun = bln[2].toInt()

        var bulanMulai = bulan
        var tahunMulai = tahun

        if (bulanMulai+4>12){
            bulanMulai = (bulanMulai+4)-12
            tahunMulai+=1
        } else{
            bulanMulai+=4
        }

        if (bulanMulai+1>12){
            bulan = (bulanMulai+1)-12
            tahun = tahunMulai+1
        } else{
            bulan = bulanMulai+1
            tahun = tahunMulai
        }

        val rentang_wajar = "$tanggal/$bulanMulai/$tahunMulai - $tanggal/$bulan/$tahun"
        info("RENTANG_WAJAR => ${rentang_wajar}")

        mFirestore.collection("imunisasi")
            .document("Polio4")
            .set(hashMapOf("rentang_wajar" to rentang_wajar, "selesai" to ""))
    }

    fun generateDataImunisasiIPV(tgl:String){
        val bln = tgl.split('/')

        var tanggal = bln[0].toInt()
        var bulan = bln[1].toInt()
        var tahun = bln[2].toInt()

        var bulanMulai = bulan
        var tahunMulai = tahun

        if (bulanMulai+4>12){
            bulanMulai = (bulanMulai+4)-12
            tahunMulai+=1
        } else{
            bulanMulai+=4
        }

        if (bulanMulai+1>12){
            bulan = (bulanMulai+1)-12
            tahun = tahunMulai+1
        } else{
            bulan = bulanMulai+1
            tahun = tahunMulai
        }

        val rentang_wajar = "$tanggal/$bulanMulai/$tahunMulai - $tanggal/$bulan/$tahun"
        info("RENTANG_WAJAR => ${rentang_wajar}")

        mFirestore.collection("imunisasi")
            .document("IPV")
            .set(hashMapOf("rentang_wajar" to rentang_wajar, "selesai" to ""))
    }

    fun generateDataImunisasiCampak(tgl:String){
        val bln = tgl.split('/')

        var tanggal = bln[0].toInt()
        var bulan = bln[1].toInt()
        var tahun = bln[2].toInt()

        var bulanMulai = bulan
        var tahunMulai = tahun

        if (bulanMulai+9>12){
            bulanMulai = (bulanMulai+9)-12
            tahunMulai+=1
        } else{
            bulanMulai+=9
        }

        if (bulanMulai+1>12){
            bulan = (bulanMulai+1)-12
            tahun = tahunMulai+1
        } else{
            bulan = bulanMulai+1
            tahun = tahunMulai
        }

        val rentang_wajar = "$tanggal/$bulanMulai/$tahunMulai - $tanggal/$bulan/$tahun"
        info("RENTANG_WAJAR => ${rentang_wajar}")

        mFirestore.collection("imunisasi")
            .document("Campak")
            .set(hashMapOf("rentang_wajar" to rentang_wajar, "selesai" to ""))
    }

    fun generateDataImunisasiCampakLanjutan(tgl:String){
        val bln = tgl.split('/')

        var tanggal = bln[0].toInt()
        var bulan = bln[1].toInt()
        var tahun = bln[2].toInt()

        var bulanMulai = bulan
        var tahunMulai = tahun

        var mulai = 18
        while (mulai>12){
            mulai-=12
            tahunMulai+=1
        }

        if (bulanMulai+mulai>12){
            bulanMulai = (bulanMulai+mulai)-12
            tahunMulai+=1
        } else{
            bulanMulai+=mulai
        }

        if (bulanMulai+1>12){
            bulan = (bulanMulai+1)-12
            tahun = tahunMulai+1
        } else{
            bulan = bulanMulai+1
            tahun = tahunMulai
        }

        val rentang_wajar = "$tanggal/$bulanMulai/$tahunMulai - $tanggal/$bulan/$tahun"
        info("RENTANG_WAJAR => ${rentang_wajar}")

        mFirestore.collection("imunisasi")
            .document("CampakLanjutan")
            .set(hashMapOf("rentang_wajar" to rentang_wajar, "selesai" to ""))
    }

    fun generateDataImunisasiDPTHBHibLanjutan(tgl:String){
        val bln = tgl.split('/')

        var tanggal = bln[0].toInt()
        var bulan = bln[1].toInt()
        var tahun = bln[2].toInt()

        var bulanMulai = bulan
        var tahunMulai = tahun

        var mulai = 18
        while (mulai>12){
            mulai-=12
            tahunMulai+=1
        }

        if (bulanMulai+mulai>12){
            bulanMulai = (bulanMulai+mulai)-12
            tahunMulai+=1
        } else{
            bulanMulai+=mulai
        }

        if (bulanMulai+1>12){
            bulan = (bulanMulai+1)-12
            tahun = tahunMulai+1
        } else{
            bulan = bulanMulai+1
            tahun = tahunMulai
        }

        val rentang_wajar = "$tanggal/$bulanMulai/$tahunMulai - $tanggal/$bulan/$tahun"
        info("RENTANG_WAJAR => ${rentang_wajar}")

        mFirestore.collection("imunisasi")
            .document("DPT-HB-HibLanjutan")
            .set(hashMapOf("rentang_wajar" to rentang_wajar, "selesai" to ""))
    }
}