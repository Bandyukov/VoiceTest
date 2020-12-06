package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


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
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "what do you want?")
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
                /*if (commandList.contains("Открой карту") || commandList.contains("открой карту")) {
                var i = Intent()
                val manager = packageManager
                i = manager.getLaunchIntentForPackage("com.google.android.apps.maps")!!
                i.addCategory(Intent.CATEGORY_LAUNCHER)
                startActivity(i)
            }*/
            if (commandList.contains("google")||commandList.contains("Google")) {
                finish()
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
                startActivity(browserIntent)
            }
            if (commandList.contains("Facebook")) {
                finish()
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com"))
                startActivity(browserIntent)
            }
            if (commandList.contains("ВК")) {
                finish()
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.vk.com"))
                startActivity(browserIntent)
            }

        }

        super.onActivityResult(requestCode, resultCode, data)
    }


}