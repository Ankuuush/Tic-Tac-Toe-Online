package com.ankush.tictactoeonline

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private companion object {
        private const val TAG = "MainActivity"
    }

    var myEmail: String? = null
    val database = Firebase.database


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth
        var b: Bundle? = intent.extras
        myEmail = b!!.getString("email")
        IncommingCalls()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mILogout) {
            Log.i(TAG, "Logout")
            auth.signOut()
            val logoutIntent = Intent(this, LoginActivity::class.java)
            logoutIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(logoutIntent)
        }
        return super.onOptionsItemSelected(item)
    }
    fun bslct(view:View)
    {
        var buslct=view as Button
        var cellid:Int=0
        when(buslct.id)
        {
            R.id.b1 -> cellid=1
            R.id.b2 -> cellid=2
            R.id.b3 -> cellid=3
            R.id.b4 -> cellid=4
            R.id.b5 -> cellid=5
            R.id.b6 -> cellid=6
            R.id.b7 -> cellid=7
            R.id.b8 -> cellid=8
            R.id.b9 -> cellid=9
            else ->
            {}
        }
        database.getReference().child("PlayerOnline").child(sessionID!!).setValue(cellid.toString() +myEmail)
    }
    val player1:MutableList<Int> = mutableListOf()
    val player2:MutableList<Int> = mutableListOf()
    val loser:MutableList<Int> = mutableListOf()
    var activeplayer=1
    fun play(cellid:Int,buslct:Button)
    {
        if(activeplayer==1)
        {
            buslct.text="X"
            buslct.setBackgroundColor(Color.GREEN)
            player1.add(cellid)
            loser.add(cellid)
            activeplayer=2

        }
        else
        {
            buslct.text="0"
            buslct.setBackgroundColor(Color.YELLOW)
            player2.add(cellid)
            loser.add(cellid)
            activeplayer=1

        }
        buslct.isEnabled=false
        winner()
    }
    fun winner()
    {
        if(player1.contains(1) && player1.contains(2) && player1.contains(3))
        {
            stop()
            Toast.makeText(this, "Player X won", Toast.LENGTH_LONG).show()
        }
        else if(player2.contains(1) && player2.contains(2) && player2.contains(3))
        {
            stop()
            Toast.makeText(this, "Player O won", Toast.LENGTH_LONG).show()
        }
        else if(player1.contains(4) && player1.contains(5) && player1.contains(6))
        {
            stop()
            Toast.makeText(this, "Player X won", Toast.LENGTH_LONG).show()
        }
        else if(player2.contains(4) && player2.contains(5) && player2.contains(6))
        {
            stop()
            Toast.makeText(this, "Player O won", Toast.LENGTH_LONG).show()
        }
        else if(player1.contains(7) && player1.contains(8) && player1.contains(9))
        {
            stop()
            Toast.makeText(this, "Player X won", Toast.LENGTH_LONG).show()
        }
        else if(player2.contains(7) && player2.contains(8) && player2.contains(9))
        {
            stop()
            Toast.makeText(this, "Player O won", Toast.LENGTH_LONG).show()
        }
        else if(player1.contains(1) && player1.contains(4) && player1.contains(7))
        {
            stop()
            Toast.makeText(this, "Player X won", Toast.LENGTH_LONG).show()
        }
        else if(player2.contains(1) && player2.contains(4) && player2.contains(7))
        {
            stop()
            Toast.makeText(this, "Player O won", Toast.LENGTH_LONG).show()
        }
        else if(player1.contains(2) && player1.contains(5) && player1.contains(8))
        {
            stop()
            Toast.makeText(this, "Player X won", Toast.LENGTH_LONG).show()
        }
        else if(player2.contains(2) && player2.contains(5) && player2.contains(8))
        {
            stop()
            Toast.makeText(this, "Player O won", Toast.LENGTH_LONG).show()
        }
        else if(player1.contains(3) && player1.contains(9) && player1.contains(6))
        {
            stop()
            Toast.makeText(this, "Player X won", Toast.LENGTH_LONG).show()
        }
        else if(player2.contains(3) && player2.contains(9) && player2.contains(6))
        {
            stop()
            Toast.makeText(this, "Player O won", Toast.LENGTH_LONG).show()
        }
        else if(player1.contains(1) && player1.contains(5) && player1.contains(9))
        {
            stop()
            Toast.makeText(this, "Player X won", Toast.LENGTH_LONG).show()
        }
        else if(player2.contains(1) && player2.contains(5) && player2.contains(9))
        {
            stop()
            Toast.makeText(this, "Player O won", Toast.LENGTH_LONG).show()
        }
        else if(player1.contains(3) && player1.contains(5) && player1.contains(7))
        {
            stop()
            Toast.makeText(this, "Player X won", Toast.LENGTH_LONG).show()
        }
        else if(player2.contains(3) && player2.contains(5) && player2.contains(7))
        {
            stop()
            Toast.makeText(this, "Player O won", Toast.LENGTH_LONG).show()
        }
        else if (loser.contains(1) && loser.contains(2) && loser.contains(3) && loser.contains(4) && loser.contains(5) && loser.contains(6) && loser.contains(7) && loser.contains(8) && loser.contains(9))
        {
            Toast.makeText(this, "MATCH DRAW", Toast.LENGTH_LONG).show()
        }
    }
    fun stop()
    {
        for (i in 1..9)
        {
            if(!loser.contains(i))
            {
                when(i)
                {
                    1 -> b1.isEnabled=false
                    2 -> b2.isEnabled=false
                    3 -> b3.isEnabled=false
                    4 -> b4.isEnabled=false
                    5 -> b5.isEnabled=false
                    6 -> b6.isEnabled=false
                    7 -> b7.isEnabled=false
                    8 -> b8.isEnabled=false
                    9 -> b9.isEnabled=false
                }
            }
        }
    }
    fun auto(cellid: Int)
    {
        var buslct:Button
        when(cellid)
        {
            1 -> buslct=b1
            2 -> buslct=b2
            3 -> buslct=b3
            4 -> buslct=b4
            5 -> buslct=b5
            6 -> buslct=b6
            7 -> buslct=b7
            8 -> buslct=b8
            9 -> buslct=b9
            else ->
            {buslct=b1}
        }
        play(cellid, buslct)
    }
    fun buRequestEvent(view:android.view.View){
        player1.clear()
        player2.clear()
        loser.clear()
        var userDemail=etEmail.text.toString()

        database.getReference().child("Users").child(splt(userDemail)).child("Request").push().setValue(myEmail)
        PlayerSymbol="X"
        PlayerOnline(splt(myEmail!!)+splt(userDemail))
        game()

    }

     fun buAcceptEvent(view:android.view.View){
         player1.clear()
         player2.clear()
         loser.clear()
        var userDemail=etEmail.text.toString()
         database.getReference().child("Users").child( splt(userDemail)).child("Request").push().setValue(myEmail!!)
         PlayerSymbol="O"
         PlayerOnline(splt(userDemail)+splt(myEmail!!))
         game()
    }
    var sessionID:String?=null
    var PlayerSymbol:String?=null
    fun PlayerOnline(sessionID:String) {
        this.sessionID = sessionID
    }
    fun game(){
        database.getReference().child("PlayerOnline").child(sessionID!!)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    try {
                        var td=snapshot.value!! as String
                        if (td != null) {
                            var key=td.substring(0,1)
                            var value=td.substring(1)
                                if (value != myEmail) {
                                    activeplayer = if (PlayerSymbol == "X") 2 else 1
                                } else {
                                    activeplayer = if (PlayerSymbol == "X") 1 else 2
                                }

                                auto(key.toInt())
                                database.getReference().child("PlayerOnline").child(sessionID!!).setValue(true)
                            }
                    } catch (ex: Exception) {
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
    }


        fun IncommingCalls() {
            database.getReference().child("Users").child(splt(myEmail!!)).child("Request")
                .addValueEventListener(object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {


                        try {
                            val td = snapshot!!.value as HashMap<String, Any>
                            if (td != null) {

                                var value: String
                                for (key in td.keys) {
                                    value = td[key] as String
                                    etEmail.setText(value)
                                    database.getReference().child("Users").child(splt(myEmail!!))
                                        .child("Request").setValue(true)

                                    break

                                }

                            }

                        } catch (ex: Exception) {
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }

                })
        }

    fun splt(email: String): String {
        var b = email.split("@")
        var c = b[0]
        for (i in "!#$%&*.") {
            c = c.replace(i, '1')
        }
        return c
    }
}