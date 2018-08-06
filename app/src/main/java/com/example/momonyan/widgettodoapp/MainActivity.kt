package com.example.momonyan.widgettodoapp

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.appwidget.AppWidgetManager
import android.support.v4.app.NotificationCompat.getExtras
import android.content.Intent
import android.graphics.Color
import android.widget.*


class MainActivity : AppCompatActivity() {
    private lateinit var editText0: EditText
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText

    //文字設定
    private lateinit var button: Button
    //りーどみー
    private lateinit var button2: Button

    //色変更(Text)
    private lateinit var colorButton1 :Button
    private lateinit var colorButton2 :Button
    private lateinit var colorButton3 :Button
    private lateinit var colorButton4 :Button

    private lateinit var sharedPreferences:SharedPreferences
    private lateinit var prefsPrivateEditor:SharedPreferences.Editor

    private var memoString:Array<String> = Array<String>(4,{""})
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText0 = findViewById(R.id.editText)
        editText1 = findViewById(R.id.editText2)
        editText2 = findViewById(R.id.editText3)
        editText3 = findViewById(R.id.editText4)

        button = findViewById(R.id.button)
        button2 = findViewById(R.id.button2)

        colorButton1 = findViewById(R.id.colorChangeButton1)
        colorButton2 = findViewById(R.id.colorChangeButton2)
        colorButton3 = findViewById(R.id.colorChangeButton3)
        colorButton4 = findViewById(R.id.colorChangeButton4)

        sharedPreferences = getSharedPreferences("Widget", Context.MODE_MULTI_PROCESS);

        memoString[0] = sharedPreferences.getString("list1", "")
        memoString[1] = sharedPreferences.getString("list2", "")
        memoString[2] = sharedPreferences.getString("list3", "")
        memoString[3] = sharedPreferences.getString("list4", "")

        editText0.setText(memoString[0], TextView.BufferType.NORMAL)
        editText0.setTextColor(sharedPreferences.getInt("list1_Color", Color.rgb(0,0,0)))

        editText1.setText(memoString[1], TextView.BufferType.NORMAL)
        editText1.setTextColor(sharedPreferences.getInt("list2_Color",Color.rgb(0,0,0)))

        editText2.setText(memoString[2], TextView.BufferType.NORMAL)
        editText2.setTextColor(sharedPreferences.getInt("list3_Color",Color.rgb(0,0,0)))

        editText3.setText(memoString[3], TextView.BufferType.NORMAL)
        editText3.setTextColor(sharedPreferences.getInt("list4_Color",Color.rgb(0,0,0)))

        prefsPrivateEditor = sharedPreferences.edit()

        button.setOnClickListener {
            prefsPrivateEditor.putString("list1", editText0.text.toString())
            prefsPrivateEditor.putString("list2", editText1.text.toString())
            prefsPrivateEditor.putString("list3", editText2.text.toString())
            prefsPrivateEditor.putString("list4", editText3.text.toString())
            prefsPrivateEditor.apply()
            Toast.makeText(this,"更新しました", Toast.LENGTH_LONG).show()

        }
        button2.setOnClickListener {
            val intent = Intent(this, ReadMeActivity::class.java)
            startActivity(intent)
        }
        colorButton1.setOnClickListener{
            val intent = Intent(this,SetColorActivity::class.java)
            intent.putExtra("name","list1")
            startActivity(intent)
        }
        colorButton2.setOnClickListener{
            val intent = Intent(this,SetColorActivity::class.java)
            intent.putExtra("name","list2")
            startActivity(intent)
        }
        colorButton3.setOnClickListener{
            val intent = Intent(this,SetColorActivity::class.java)
            intent.putExtra("name","list3")
            startActivity(intent)
        }
        colorButton4.setOnClickListener{
            val intent = Intent(this,SetColorActivity::class.java)
            intent.putExtra("name","list4")
            startActivity(intent)
        }
    }
}
