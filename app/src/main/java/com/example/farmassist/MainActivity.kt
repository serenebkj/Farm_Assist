package com.example.farmassist
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    //val button_second:Button=findViewById(R.id.button_second)
    var value:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        button_second.setOnClickListener {
            val intent=Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
        button_third.setOnClickListener {
            val intent=Intent(this,PlantActivity::class.java)
            startActivity(intent)
        }
        textview_2nd.setOnClickListener {
            val intent=Intent(this,weather::class.java)
            startActivity(intent)
        }
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference()
        val prediction: DatabaseReference= myRef.child("FarmAssist").child("prediction")


        prediction.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                var value2 = dataSnapshot.getValue(String::class.java)
                var pVal=value2.toString()
                textview_prediction.setText(pVal!!)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value

            }
        })

        val moistVal: DatabaseReference= myRef.child("FarmAssist").child("moisture")


        moistVal.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                var value = dataSnapshot.getValue(Int::class.java)!!
                var mVal=value.toString()
                textview_moistval.setText(mVal!!)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value

            }
        })
        if ( value.toString() <= 10.toString())
        { showAlertDialog() }
        if ( value.toString() >= 50.toString())
        { showAlertDialog2() }
    }
    private fun showAlertDialog() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("Water Alert")
        alertDialog.setMessage("Your plant need water")
        alertDialog.setPositiveButton(
            "yes"
        ) { _, _ ->
            Toast.makeText(this@MainActivity, "Alert dialog closed.", Toast.LENGTH_LONG).show()
        }
        alertDialog.setNegativeButton(
            "No"
        ) { _, _ -> }
        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }
    private fun showAlertDialog2() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("Water Alert")
        alertDialog.setMessage("Excess water detected")
        alertDialog.setPositiveButton(
            "yes"
        ) { _, _ ->
            Toast.makeText(this@MainActivity, "Alert dialog closed.", Toast.LENGTH_LONG).show()
        }
        alertDialog.setNegativeButton(
            "No"
        ) { _, _ -> }
        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }
    }


