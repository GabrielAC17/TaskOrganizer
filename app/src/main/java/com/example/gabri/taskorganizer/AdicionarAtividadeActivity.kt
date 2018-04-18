package com.example.gabri.taskorganizer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.gabri.taskorganizer.Models.ProgramDataStore

class AdicionarAtividadeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_ativ_activity)

        val addTaskButton = findViewById<Button>(R.id.addTask)
        val addTaskText = findViewById<EditText>(R.id.editText)

        addTaskButton.setOnClickListener{
            if (!addTaskText.text.isEmpty()){
                ProgramDataStore.atividades.add(addTaskText.text.toString())
                finish()
            }
            else{
                addTaskText.requestFocus()
                addTaskText.error = "Nome da atividade não pode ser vazio!"
            }
        }
    }
}
