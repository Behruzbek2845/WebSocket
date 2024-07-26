package com.behruzbek0430.websocket18.websocket

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.behruzbek0430.websocket18.models.MyMessageResponse
import com.google.gson.Gson
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI


class MyWebSocketClient(serverUri: URI?) : WebSocketClient(serverUri) {
    override fun onOpen(handshakedata: ServerHandshake) {
        // Websocket ochilganda bajariladigan amallar
        Log.d(TAG, "onOpen: ")
    }

    override fun onMessage(message: String) {
        // Yangi xabar keldi
        Log.d(TAG, "onMessage: $message")
        val myMessageResponse = Gson().fromJson<MyMessageResponse>(message, MyMessageResponse::class.java)
        liveData.postValue(myMessageResponse)
    }

    override fun onClose(code: Int, reason: String, remote: Boolean) {
        // Websocket yopilganda bajariladigan amallar
        Log.d(TAG, "onClose: ")
    }

    override fun onError(ex: Exception) {
        // Xatolik yuz berdi
        Log.d(TAG, "onError: " + ex.message)
    }

    companion object {
        private const val TAG = "MyWebSocketClient"
        val liveData = MutableLiveData<MyMessageResponse>()
    }
}