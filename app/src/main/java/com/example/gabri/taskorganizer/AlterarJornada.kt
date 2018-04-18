package com.example.gabri.taskorganizer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import com.example.gabri.taskorganizer.Models.ProgramDataStore
import com.example.gabri.taskorganizer.Models.Time
import kotlinx.android.synthetic.main.activity_alterar_jornada.*

class AlterarJornada : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {
    var segValue:Int = 0
    var terValue:Int = 0
    var quaValue:Int = 0
    var quiValue:Int = 0
    var sexValue:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alterar_jornada)

        this.segSeekBar!!.setOnSeekBarChangeListener(this)
        this.terSeekBar!!.setOnSeekBarChangeListener(this)
        this.quaSeekBar!!.setOnSeekBarChangeListener(this)
        this.quiSeekBar!!.setOnSeekBarChangeListener(this)
        this.sexSeekBar!!.setOnSeekBarChangeListener(this)

        segSeekBar.progress = ProgramDataStore.jornadaSeg.timeToMinutes()
        terSeekBar.progress = ProgramDataStore.jornadaTer.timeToMinutes()
        quaSeekBar.progress = ProgramDataStore.jornadaQua.timeToMinutes()
        quiSeekBar.progress = ProgramDataStore.jornadaQui.timeToMinutes()
        sexSeekBar.progress = ProgramDataStore.jornadaSex.timeToMinutes()

        saveJornada.setOnClickListener { view ->
            if (segValue != 0 && terValue != 0 && quaValue != 0 && quiValue != 0 && sexValue != 0){
                ProgramDataStore.jornadaSeg.minutesToTime(segValue)
                ProgramDataStore.jornadaTer.minutesToTime(terValue)
                ProgramDataStore.jornadaQua.minutesToTime(quaValue)
                ProgramDataStore.jornadaQui.minutesToTime(quiValue)
                ProgramDataStore.jornadaSex.minutesToTime(sexValue)

                ProgramDataStore.diasNaSemana[0].descricao = "Saldo: " + (ProgramDataStore.saldo[0] - ProgramDataStore.jornadaSeg).toStringFull()
                ProgramDataStore.diasNaSemana[1].descricao = "Saldo: " + (ProgramDataStore.saldo[1] - ProgramDataStore.jornadaTer).toStringFull()
                ProgramDataStore.diasNaSemana[2].descricao = "Saldo: " + (ProgramDataStore.saldo[2] - ProgramDataStore.jornadaQua).toStringFull()
                ProgramDataStore.diasNaSemana[3].descricao = "Saldo: " + (ProgramDataStore.saldo[3] - ProgramDataStore.jornadaQui).toStringFull()
                ProgramDataStore.diasNaSemana[4].descricao = "Saldo: " + (ProgramDataStore.saldo[4] - ProgramDataStore.jornadaSex).toStringFull()

                MainActivity.RealoadListData()

                finish()
            }
            else{
                saveJornada.error = "Favor defina todas as jornadas da semana!"
            }
        }
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        when (seekBar!!.id){
            R.id.segSeekBar -> {
                segTime.text = Time.minutesToTimeString(progress)
                segValue = progress
            }
            R.id.terSeekBar -> {
                terTime.text = Time.minutesToTimeString(progress)
                terValue = progress
            }
            R.id.quaSeekBar -> {
                quaTime.text = Time.minutesToTimeString(progress)
                quaValue = progress
            }
            R.id.quiSeekBar -> {
                quiTime.text = Time.minutesToTimeString(progress)
                quiValue = progress
            }
            R.id.sexSeekBar -> {
                sexTime.text = Time.minutesToTimeString(progress)
                sexValue = progress
            } //( ͡° ͜ʖ ͡°)
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        Log.d("SEEK","Started tracking SeekBar")
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        Log.d("SEEK","Stopped tracking SeekBar")
    }
}
