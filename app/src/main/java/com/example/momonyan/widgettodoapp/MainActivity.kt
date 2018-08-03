package com.example.momonyan.widgettodoapp

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var editText0: EditText
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText
    private lateinit var button: Button
    private  lateinit var sharedPreferences:SharedPreferences
    private  lateinit var   prefsPrivateEditor:SharedPreferences.Editor
    private  var memoString:Array<String> = Array<String>(4,{""})
    private  var memoBool:Array<Boolean> = Array<Boolean>(4,{false})
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText0= findViewById(R.id.editText)
        editText1= findViewById(R.id.editText2)
        editText2= findViewById(R.id.editText3)
        editText3= findViewById(R.id.editText4)
        button = findViewById(R.id.button)
        sharedPreferences = getSharedPreferences("list", Context.MODE_PRIVATE);

        memoString[0] = sharedPreferences.getString("list1", "")
        memoString[1] = sharedPreferences.getString("list2", "")
        memoString[2] = sharedPreferences.getString("list3", "")
        memoString[3] = sharedPreferences.getString("list4", "")

        editText0.setText(memoString[0],TextView.BufferType.NORMAL)
        editText1.setText(memoString[1],TextView.BufferType.NORMAL)
        editText2.setText(memoString[2],TextView.BufferType.NORMAL)
        editText3.setText(memoString[3],TextView.BufferType.NORMAL)


        prefsPrivateEditor = sharedPreferences.edit()
        button.setOnClickListener {
            prefsPrivateEditor.putString("list1",editText0.text.toString())
            prefsPrivateEditor.putString("list2",editText1.text.toString())
            prefsPrivateEditor.putString("list3",editText2.text.toString())
            prefsPrivateEditor.putString("list4",editText3.text.toString())
            prefsPrivateEditor.commit()
        }
    }
}
