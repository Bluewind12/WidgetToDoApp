package com.example.momonyan.widgettodoapp

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast

class SetColorActivity : AppCompatActivity() {
    //入れ子
    private lateinit var textView: TextView
    private lateinit var seekBarR: SeekBar
    private lateinit var seekBarG: SeekBar
    private lateinit var seekBarB: SeekBar
    private lateinit var changeButton: Button
    private lateinit var resetButton: Button

    //色データ管理
    private var red = 0
    private var green = 0
    private var blue = 0
    private var totalColor = 0x000000
    //変更元
    private var setNames = ""
    //データ保持
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var prefsPrivateEditor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.set_color)
        init()

        //数値
        red = sharedPreferences.getInt(setNames + "_ColorRed", 0)
        green = sharedPreferences.getInt(setNames + "_ColorGreen", 0)
        blue = sharedPreferences.getInt(setNames + "_ColorBlue", 0)


        //表示
        textView.text = getString(R.string.TotalColor, red, green, blue)
        textView.setTextColor(sharedPreferences.getInt(setNames + "_Color", 0))

        //シークバー
        seekBarR.progress = sharedPreferences.getInt(setNames + "_ColorRed", 0)
        seekBarR.max = 255
        seekBarG.progress = sharedPreferences.getInt(setNames + "_ColorGreen", 0)
        seekBarG.max = 255
        seekBarB.progress = sharedPreferences.getInt(setNames + "_ColorBlue", 0)
        seekBarB.max = 255

        //動作
        seekBarR.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            //変更時
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                red = p1
                totalColor = Color.rgb(red, green, blue)
                textView.setTextColor(totalColor)
                textView.text = getString(R.string.TotalColor, red, green, blue)
            }

            //タッチ時
            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            //リリース時
            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
        seekBarG.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            //変更時
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                green = p1
                totalColor = Color.rgb(red, green, blue)
                textView.setTextColor(totalColor)
                textView.text = getString(R.string.TotalColor, red, green, blue)

            }

            //タッチ時
            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            //リリース時
            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
        seekBarB.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            //変更時
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                blue = p1
                totalColor = Color.rgb(red, green, blue)
                textView.setTextColor(totalColor)
                textView.text = getString(R.string.TotalColor, red, green, blue)
            }

            //タッチ時
            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            //リリース時
            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
        changeButton.setOnClickListener {
            prefsPrivateEditor.putInt(setNames + "_Color", totalColor)
            prefsPrivateEditor.putInt(setNames + "_ColorRed", red)
            prefsPrivateEditor.putInt(setNames + "_ColorGreen", green)
            prefsPrivateEditor.putInt(setNames + "_ColorBlue", blue)
            prefsPrivateEditor.apply()

            Toast.makeText(this, setNames + "のカラーを変更しました", Toast.LENGTH_LONG).show()

            val intent = Intent(this, MainActivity::class.java)
            intent.setFlags(FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
        resetButton.setOnClickListener{
            prefsPrivateEditor.putInt(setNames + "_Color", 0)
            prefsPrivateEditor.putInt(setNames + "_ColorRed", 0)
            prefsPrivateEditor.putInt(setNames + "_ColorGreen", 0)
            prefsPrivateEditor.putInt(setNames + "_ColorBlue", 0)
            prefsPrivateEditor.apply()
        }
    }

    private fun init() {
        //データ保持用
        sharedPreferences = getSharedPreferences("Widget", Context.MODE_PRIVATE);
        prefsPrivateEditor = sharedPreferences.edit()

        //変更元
        setNames = intent.getStringExtra("name")

        //表示
        textView = findViewById(R.id.nameText)

        //シークバー
        seekBarR = findViewById(R.id.seekBar)
        seekBarG = findViewById(R.id.seekBar2)
        seekBarB = findViewById(R.id.seekBar3)

        //変更ボタン
        changeButton = findViewById(R.id.changeButton)
        resetButton = findViewById(R.id.resetButton)

    }
}