package com.example.gabri.taskorganizer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.gabri.taskorganizer.Models.AtividadeSemanal
import com.example.gabri.taskorganizer.Models.ProgramDataStore
import kotlinx.android.synthetic.main.activity_relatorio.*
import kotlin.math.absoluteValue

class Relatorio : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relatorio)

        segSaldo.text = ProgramDataStore.diasNaSemana[0].descricao
        terSaldo.text = ProgramDataStore.diasNaSemana[1].descricao
        quaSaldo.text = ProgramDataStore.diasNaSemana[2].descricao
        quiSaldo.text = ProgramDataStore.diasNaSemana[3].descricao
        sexSaldo.text = ProgramDataStore.diasNaSemana[4].descricao

        var text:String = ""
        ProgramDataStore.atividadesSeg.forEach{ativ ->
            text += ativ.atividade + " (" + ativ.time.toStringFull() + ")\n"
        }
        segActitivitiesText.text = text
        text = ""

        ProgramDataStore.atividadesTer.forEach{ativ ->
            text += ativ.atividade + " (" + ativ.time.toStringFull() + ")\n"
        }
        terActitivitiesText.text = text
        text = ""

        ProgramDataStore.atividadesQua.forEach{ativ ->
            text += ativ.atividade + " (" + ativ.time.toStringFull() + ")\n"
        }
        quaActitivitiesText.text = text
        text = ""

        ProgramDataStore.atividadesQui.forEach{ativ ->
            text += ativ.atividade + " (" + ativ.time.toStringFull() + ")\n"
        }
        quiActitivitiesText.text = text
        text = ""

        ProgramDataStore.atividadesSex.forEach{ativ ->
            text += ativ.atividade + " (" + ativ.time.toStringFull() + ")\n"
        }
        sexActitivitiesText.text = text

        selectColor(segCor, ProgramDataStore.atividadesSeg)
        selectColor(terCor, ProgramDataStore.atividadesTer)
        selectColor(quaCor, ProgramDataStore.atividadesQua)
        selectColor(quiCor, ProgramDataStore.atividadesQui)
        selectColor(sexCor, ProgramDataStore.atividadesSex)
    }

    fun selectColor(caixa: TextView, list:ArrayList<AtividadeSemanal>){
        var listNumbers = ArrayList<Int>()
        list.forEach { ativ ->
            when (ativ.prioritySelected){
                in 1..20 ->  listNumbers.add(1)
                in 21..40 -> listNumbers.add(21)
                in 41..60 -> listNumbers.add(41)
                in 61..80 -> listNumbers.add(61)
                in 81..100 -> listNumbers.add(81)
            }
        }

        var numbers = moda(listNumbers.toIntArray())

        if (numbers.count() == 0)
            caixa.setBackgroundColor(0)
        else if (numbers.count() == 1)
            caixa.setBackgroundColor(ProgramDataStore.GetColorPriority(numbers[0]))
        else if (numbers.count() > 1){
            var sum = 0
            numbers.forEach {number ->
                sum += number
            }
            caixa.setBackgroundColor(ProgramDataStore.GetColorPriority(sum/numbers.count()))
        }
    }

    fun moda(numbers: IntArray): List<Int> {
        val modas = ArrayList<Int>()
        val countMap = HashMap<Int, Int>()

        var max = -1

        for (n in numbers) {
            var count = 0

            if (countMap.containsKey(n)) {
                count = countMap[n]!!.absoluteValue + 1
            } else {
                count = 1
            }

            countMap[n] = count

            if (count > max) {
                max = count
            }
        }

        for ((key, value) in countMap) {
            if (value == max) {
                modas.add(key)
            }
        }

        return modas
    }
}