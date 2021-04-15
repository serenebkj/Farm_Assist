package com.example.farmassist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_plant.*
import android.net.Uri;
import android.webkit.WebView
import android.widget.MediaController;
import android.widget.VideoView;

class PlantActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val video_url ="http://192.168.43.88:8081/"
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
        val video = findViewById<VideoView>(R.id.plantVideo) as WebView
        video.loadUrl(video_url);
        val uri = Uri.parse(video_url)



    }
}