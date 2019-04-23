package id.trydev.sabuba.BalitaSakit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import id.trydev.sabuba.R
import kotlinx.android.synthetic.main.activity_detail_balita_sakit.*

class DetailBalitaSakitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_balita_sakit)

        val sakit = intent.getStringExtra("sakit")
        val solusiSakit = intent.getStringArrayExtra("listSolusi")

        supportActionBar?.title = "Solusi ${sakit}"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        for (i in solusiSakit.indices){
            val cb = CheckBox(this)
            cb.setText(solusiSakit[i])
            val layout = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            layout.setMargins(0,0,0,32)
            cb.layoutParams = layout
            ll_checkbox.addView(cb)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
