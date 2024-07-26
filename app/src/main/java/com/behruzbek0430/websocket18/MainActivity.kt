package com.behruzbek0430.websocket18

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.behruzbek0430.websocket18.adapters.RvAdapter
import com.behruzbek0430.websocket18.databinding.ActivityMainBinding
import com.behruzbek0430.websocket18.models.MyMessage
import com.behruzbek0430.websocket18.websocket.MyWebSocketClient
import com.google.gson.Gson
import java.net.URI

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var rvAdapter: RvAdapter
    lateinit var myWebSocketClient: MyWebSocketClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        myWebSocketClient = MyWebSocketClient(URI("ws://89.223.65.48/ws/chat/"))

        try {
            myWebSocketClient.connect()
            Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show()
        }catch (e:Exception){
            Toast.makeText(this, "Xatolik", Toast.LENGTH_SHORT).show()
        }

        binding.btnSend.setOnClickListener {
            val text = binding.etMessage.text.toString()
            val myMessage = MyMessage(text)
            val gsonString = Gson().toJson(myMessage)
            myWebSocketClient.send(gsonString)
            Toast.makeText(this, "Send message", Toast.LENGTH_SHORT).show()
        }



        MyWebSocketClient.liveData.observe(this){
            rvAdapter = RvAdapter(it.messages!!)
            binding.rv.adapter = rvAdapter
        }
    }
}