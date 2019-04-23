package id.trydev.sabuba.DeteksiDini

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import id.trydev.sabuba.ChatDokter.ChatDokterActivity
import id.trydev.sabuba.Menu.MenuActivity
import id.trydev.sabuba.R
import id.trydev.sabuba.Utils.AnimSupport
import kotlinx.android.synthetic.main.activity_deteksi_dini.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class DeteksiDiniActivity : AppCompatActivity(), DeteksiDiniView, AnkoLogger {

    val anim = AnimSupport()
    lateinit var presenter:DeteksiDiniPresenter
    var step = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deteksi_dini)

        Handler().postDelayed({
            anim.slideLeftShowAnim(card_usia_balita, object: AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    card_usia_balita.visibility = View.VISIBLE
                }
            })
        },500)

        presenter = DeteksiDiniPresenter(this,this)

        lv_umur_balita.setOnItemClickListener { _, _, position, _ ->
            when (position){
                0 -> {
                    presenter.setSoalKuesioner(R.array.kuesioner_3_bulan)
                    showCardKuesioner()
                }
                1 -> {
                    presenter.setSoalKuesioner(R.array.kuesioner_6_bulan)
                    showCardKuesioner()
                }
                2 -> {
                    presenter.setSoalKuesioner(R.array.kuesioner_9_bulan)
                    showCardKuesioner()
                }
                3 -> {
                    presenter.setSoalKuesioner(R.array.kuesioner_12_bulan)
                    showCardKuesioner()
                }
                4 -> {
                    presenter.setSoalKuesioner(R.array.kuesioner_15_bulan)
                    showCardKuesioner()
                }
                5 -> {
                    presenter.setSoalKuesioner(R.array.kuesioner_18_bulan)
                    showCardKuesioner()
                }
                6 -> {
                    presenter.setSoalKuesioner(R.array.kuesioner_21_bulan)
                    showCardKuesioner()
                }
                7 -> {
                    presenter.setSoalKuesioner(R.array.kuesioner_24_bulan)
                    showCardKuesioner()
                }
                8 -> {
                    presenter.setSoalKuesioner(R.array.kuesioner_30_bulan)
                    showCardKuesioner()
                }
                9 -> {
                    presenter.setSoalKuesioner(R.array.kuesioner_36_bulan)
                    showCardKuesioner()
                }
                10 -> {
                    presenter.setSoalKuesioner(R.array.kuesioner_42_bulan)
                    showCardKuesioner()
                }
                11 -> {
                    presenter.setSoalKuesioner(R.array.kuesioner_48_bulan)
                    showCardKuesioner()
                }
                12 -> {
                    presenter.setSoalKuesioner(R.array.kuesioner_54_bulan)
                    showCardKuesioner()
                }
                13 -> {
                    presenter.setSoalKuesioner(R.array.kuesioner_60_bulan)
                    showCardKuesioner()
                }
                14 -> {
                    presenter.setSoalKuesioner(R.array.kuesioner_66_bulan)
                    showCardKuesioner()
                }
                15 -> {
                    presenter.setSoalKuesioner(R.array.kuesioner_72_bulan)
                    showCardKuesioner()
                }
            }
        }

        no_button.onClick {
            presenter.pushJawabanKuesioner(0)
            anim.slideLeftHideAnim(tv_kuesioner, object: AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    tv_kuesioner.translationX = 500f
                    presenter.loadKuesioner(step++)
                }
            })
        }

        yes_button.onClick {
            presenter.pushJawabanKuesioner(1)
            anim.slideLeftHideAnim(tv_kuesioner, object: AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    tv_kuesioner.translationX = 500f
                    presenter.loadKuesioner(step++)
                }
            })
        }
    }

    override fun showCardKuesioner() {
        info("Masuk")
        anim.slideLeftHideAnim(card_usia_balita, object: AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)

                card_usia_balita.visibility = View.GONE
                card_kuesioner.visibility = View.VISIBLE
                presenter.loadKuesioner(step++)
                anim.slideLeftShowAnim(card_kuesioner)
            }
        })
    }

    override fun showSoalKuesioner(soal:String) {
        tv_kuesioner.text = soal
        anim.slideLeftShowAnim(tv_kuesioner)
    }

    override fun showHasil(totalYa:Int) {
        anim.slideLeftHideAnim(card_kuesioner, object: AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)

                card_kuesioner.visibility = View.GONE
                card_hasil_kuesioner.visibility = View.VISIBLE
                anim.slideLeftShowAnim(card_hasil_kuesioner)
            }
        })
        if (totalYa<=6){
            arahan_hasil_kuesioner.text = "Berdasarkan jawaban kuesioner anda, anak anda dicurigai mengalami gangguan perkembangan. Silakan mulai konsultasi ke dokter dengan menekan tombol dibawah ini."
            emotion.text =  ":("
            btn_arahan_kuesioner.text = "Chat Dokter Sekarang"
            btn_arahan_kuesioner.onClick {
            startActivity<ChatDokterActivity>()
            }
        } else if (totalYa>=7 && totalYa<=8){
            arahan_hasil_kuesioner.text = "Berdasarkan jawaban kuesioner anda, anak anda perlu diperiksa ulang 1 minggu kemudian karena hasil meragukan."
            emotion.text =  ":/"
            btn_arahan_kuesioner.text = "Kembali ke Menu Utama"
            btn_arahan_kuesioner.onClick {
                startActivity<MenuActivity>()
            }
        } else if (totalYa>=9 && totalYa<=10){
            arahan_hasil_kuesioner.text = "Berdasarkan jawaban kuesioner anda, anak anda dianggap tidak memiliki gangguan perkembangan."
            emotion.text =  ":)"
            btn_arahan_kuesioner.text = "Kembali ke Menu Utama"
            btn_arahan_kuesioner.onClick {
                startActivity<MenuActivity>()
            }
        }
    }

    override fun onBackPressed() {
        if (card_kuesioner.visibility==View.VISIBLE){

            alert("Segala kuesioner yang telah anda jawab akan hilang. Apakah anda yakin ingin kembali ke Menu Utama ?"){
                yesButton { finish() }
                noButton { }
            }.show()
        } else{
            super.onBackPressed()
        }
    }
}
