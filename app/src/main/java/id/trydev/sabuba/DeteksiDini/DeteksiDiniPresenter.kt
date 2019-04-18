package id.trydev.sabuba.DeteksiDini

import android.content.Context
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class DeteksiDiniPresenter(val ctx: Context, val view: DeteksiDiniView):AnkoLogger {

    var soalKuesioner:MutableList<String> = mutableListOf()
    var arrayKuesioner:MutableList<Int> = mutableListOf()

    fun setSoalKuesioner(idArray:Int){
        val array = ctx.resources.getStringArray(idArray)
        for (i in array.indices){
            soalKuesioner.add(array[i])
        }
        info("Kuesioner => ${soalKuesioner}")
    }

    fun loadKuesioner(position:Int){
        if (position<soalKuesioner.size){
            view.showSoalKuesioner(soalKuesioner[position])
        } else{
            info("Hasil Kuesioner => ${arrayKuesioner}, total => ${arrayKuesioner.sumBy { it }}")
            view.showHasil(arrayKuesioner.sumBy { it })
        }
    }

    fun pushJawabanKuesioner(value:Int){
        arrayKuesioner.add(value)
    }
}