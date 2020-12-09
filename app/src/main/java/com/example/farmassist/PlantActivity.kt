package com.example.farmassist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_plant.*


class PlantActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant)
        buttonOn.setOnClickListener {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference().child("FarmAssist").child("motor")
                //

            myRef.setValue("1")
        }
        buttonOff.setOnClickListener {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference().child("FarmAssist").child("motor")

            myRef.setValue("0")
        }
    }
}