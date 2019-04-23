package id.trydev.sabuba.BalitaSakit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import id.trydev.sabuba.R
import kotlinx.android.synthetic.main.activity_balita_sakit.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class BalitaSakitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_balita_sakit)

        supportActionBar?.title = "Manajemen Balita Sakit"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val listSakit = resources.getStringArray(R.array.list_sakit)

        pilihan_sakit.setOnItemClickListener { adapterView, view, position, l ->
//            toast(listSakit[position])
            when (listSakit[position]){
                "Demam" -> {
                    val listSolusi = resources.getStringArray(R.array.solusi_demam)
                    startActivity<DetailBalitaSakitActivity>("listSolusi" to listSolusi, "sakit" to listSakit[position])
                }
                "Diare/Mencret" ->{
                    val listSolusi = resources.getStringArray(R.array.solusi_diare)
                    startActivity<DetailBalitaSakitActivity>("listSolusi" to listSolusi, "sakit" to listSakit[position])
                }
                "Batuk" -> {
                    val listSolusi = resources.getStringArray(R.array.solusi_batuk)
                    startActivity<DetailBalitaSakitActivity>("listSolusi" to listSolusi, "sakit" to listSakit[position])
                }
                "Luka dan Koreng" ->{
                    val listSolusi = resources.getStringArray(R.array.solusi_luka_dan_koreng)
                    startActivity<DetailBalitaSakitActivity>("listSolusi" to listSolusi, "sakit" to listSakit[position])
                }
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
