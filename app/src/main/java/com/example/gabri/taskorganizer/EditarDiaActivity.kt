package com.example.gabri.taskorganizer

import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.gabri.taskorganizer.Models.AtividadeSemanal
import com.example.gabri.taskorganizer.Models.ProgramDataStore
import com.example.gabri.taskorganizer.Models.Time
import kotlinx.android.synthetic.main.activity_editar_dia.*
import java.text.SimpleDateFormat
import java.util.*

class EditarDiaActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {
    var dia:Int = 0
    var prioritySelected:Int = 0
    var tempoInicio:Time = Time(0,0)
    var tempoFim:Time = Time(0,0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_dia)

        val spin = findViewById<Spinner>(R.id.spinner)
        spin.adapter  = ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, ProgramDataStore.atividades)
        spin.setSelection(0)

        dia = intent.getIntExtra("dayOfWeek",-1)
        if (dia != -1){
            val diaTexto = findViewById(R.id.diaTexto) as TextView
            diaTexto.text = "Adicionar Atividade na " + ProgramDataStore.diasNaSemana[dia].nome
        }

        fabInside.setOnClickListener { view ->
            val intent = Intent(this@EditarDiaActivity, AdicionarAtividadeActivity::class.java)
            startActivity(intent)
        }

        getTime(horaInicialText,this,horaInicialButton, tempoInicio)
        getTime(horaFinalText, this, horaFinalButton, tempoFim)

        this.seekBar!!.setOnSeekBarChangeListener(this)

        confirm.setOnClickListener{view ->
            var timeRange:Time = tempoFim - tempoInicio
            if (dia in 0..4 && prioritySelected != 0 && !timeRange.isEmpty()){
                var timeRange:Time = tempoFim - tempoInicio
                if (tempoFim.hour > tempoInicio.hour || (tempoFim.hour == tempoInicio.hour && tempoFim.minute > tempoInicio.minute)){
                    var ativ = AtividadeSemanal()
                    ativ.dia = dia
                    ativ.prioritySelected = prioritySelected
                    ativ.time = tempoFim - tempoInicio
                    ativ.atividade = spin.selectedItem.toString()

                    when(dia){
                        0 ->  ProgramDataStore.atividadesSeg.add(ativ)
                        1 ->  ProgramDataStore.atividadesTer.add(ativ)
                        2 ->  ProgramDataStore.atividadesQua.add(ativ)
                        3 ->  ProgramDataStore.atividadesQui.add(ativ)
                        4 ->  ProgramDataStore.atividadesSex.add(ativ)
                    }

                    ProgramDataStore.saldo[dia] += (tempoFim - tempoInicio)

                    ProgramDataStore.diasNaSemana[0].descricao = "Saldo: " + (ProgramDataStore.saldo[0] - ProgramDataStore.jornadaSeg).toStringFull()
                    ProgramDataStore.diasNaSemana[1].descricao = "Saldo: " + (ProgramDataStore.saldo[1] - ProgramDataStore.jornadaTer).toStringFull()
                    ProgramDataStore.diasNaSemana[2].descricao = "Saldo: " + (ProgramDataStore.saldo[2] - ProgramDataStore.jornadaQua).toStringFull()
                    ProgramDataStore.diasNaSemana[3].descricao = "Saldo: " + (ProgramDataStore.saldo[3] - ProgramDataStore.jornadaQui).toStringFull()
                    ProgramDataStore.diasNaSemana[4].descricao = "Saldo: " + (ProgramDataStore.saldo[4] - ProgramDataStore.jornadaSex).toStringFull()

                    MainActivity.RealoadListData()

                    finish()
                }
                else{
                    horaFinalText.error = "Hora inválida: o horário de fim deve ser maior do que o início!"
                }
            }
            else{
                confirm.error = "Preencha todas as informações"
            }
        }
    }

    fun getTime(text: TextView, context: Context, but:Button, time:Time){

        val cal = Calendar.getInstance()

        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)

            time.hour = hour
            time.minute = minute

            text.text = SimpleDateFormat("HH:mm").format(cal.time)
        }

        but.setOnClickListener {
            TimePickerDialog(context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        colorPriority.setBackgroundColor(ProgramDataStore.GetColorPriority(progress))
        prioritySelected = progress
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        Log.d("SEEK","Started tracking SeekBar")
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        Log.d("SEEK","Stopped tracking SeekBar")
    }
}
