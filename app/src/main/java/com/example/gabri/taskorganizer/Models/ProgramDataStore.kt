package com.example.gabri.taskorganizer.Models

import android.graphics.Color
import com.example.gabri.taskorganizer.R

class ProgramDataStore {
    companion object
    {
        var diasNaSemana = arrayOf(DiaDaSemana("Segunda", "Saldo: -8h0min", R.drawable.segunda),
                                   DiaDaSemana("Terça", "Saldo: -8h0min", R.drawable.terca),
                                   DiaDaSemana("Quarta", "Saldo: -8h0min", R.drawable.quarta),
                                   DiaDaSemana("Quinta", "Saldo: -8h0min", R.drawable.quinta),
                                   DiaDaSemana("Sexta", "Saldo: -8h0min", R.drawable.sexta))

        var saldo = Array(5,{ Time(0,0)} )

        var atividades:ArrayList<String> = ArrayList(arrayListOf("Finalizar telas", "Fazer backend", "Fazer Banco", "Dormir (não recomendado)"))

        var atividadesSeg:ArrayList<AtividadeSemanal> = ArrayList()
        var atividadesTer:ArrayList<AtividadeSemanal> = ArrayList()
        var atividadesQua:ArrayList<AtividadeSemanal> = ArrayList()
        var atividadesQui:ArrayList<AtividadeSemanal> = ArrayList()
        var atividadesSex:ArrayList<AtividadeSemanal> = ArrayList()

        var jornadaSeg:Time = Time(8,0)
        var jornadaTer:Time = Time(8,0)
        var jornadaQua:Time = Time(8,0)
        var jornadaQui:Time = Time(8,0)
        var jornadaSex:Time = Time(8,0)

        fun GetColorPriority(percentage:Int):Int{
            when (percentage){
                in 1..5 -> return Color.rgb(91,192,235) //V. Low
                in 6..10 -> return Color.rgb(107,193,192)
                in 11..15 -> return Color.rgb(123,195,148)
                in 15..20 -> return Color.rgb(139,196,105)
                in 21..25 -> return Color.rgb(155,197,61) //Low
                in 26..30 -> return Color.rgb(180,206,65)
                in 31..35 -> return Color.rgb(204,214,69)
                in 36..40 -> return Color.rgb(229,223,72)
                in 41..45 -> return Color.rgb(253,231,76) //Medium
                in 46..50 -> return Color.rgb(252,204,65)
                in 51..55 -> return Color.rgb(252,176,55)
                in 56..60 -> return Color.rgb(251,149,44)
                in 61..65 -> return Color.rgb(250,121,33) //High
                in 66..70 -> return Color.rgb(245,113,38)
                in 71..75 -> return Color.rgb(240,105,43)
                in 76..80 -> return Color.rgb(234,97,47)
                in 81..85 -> return Color.rgb(229,89,52) //Fuck
                in 86..90 -> return Color.rgb(153,59,35)
                in 91..95 -> return Color.rgb(76,30,17)
                in 96..100 -> return Color.rgb(0,0,0)
            }
            return 0
        }
    }

}
