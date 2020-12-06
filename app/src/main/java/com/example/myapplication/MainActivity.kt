package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var listView: ListView
    public val VOICE_RECOGNITION_REQUEST_CODE = 1234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        listView = findViewById(R.id.listView)


    }

    fun onClick(view: View) {
        startSpeak()
    }

    public fun startSpeak() {
        val intent: Intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "what can you tell me")
        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE)
    }

    val array: ArrayList<String> = ArrayList()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val commandList: ArrayList<String>? =
                data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)



            array.add(commandList!!.get(0))

            val adapter: ArrayAdapter<String> =
                ArrayAdapter(this, android.R.layout.simple_list_item_1, array)

            listView.adapter = adapter
            
                if (commandList.contains("красный")) {
                    button.setText("Красный")
                    button.setBackgroundColor(Color.RED)
                }
                if (commandList.contains("синий")) {
                    button.setText("Синий")
                    button.setBackgroundColor(Color.BLUE)
                }
                if (commandList.contains("жёлтый")) {
                    button.setText("Желтый")
                    button.setBackgroundColor(Color.YELLOW)
                }
                if (commandList.contains("зелёный")) {
                    button.setText("Зеленый")
                    button.setBackgroundColor(Color.GREEN)
                }
                if (commandList.contains("белый")) {
                    button.setText("Белый")
                    button.setBackgroundColor(Color.WHITE)
                }



        }

        super.onActivityResult(requestCode, resultCode, data)
    }


}