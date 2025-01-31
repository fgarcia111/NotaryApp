package com.example.notaryapp.feature_date.presentation.util

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.provider.Telephony
import android.telephony.TelephonyManager
import androidx.compose.runtime.Composable
import androidx.core.app.NotificationCompat
import com.example.notaryapp.R



class CallSmsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Telephony.Sms.Intents.SMS_RECEIVED_ACTION -> {
                showNotification(context, "Nuevo SMS recibido", "Has recibido un nuevo mensaje.")
            }
            TelephonyManager.ACTION_PHONE_STATE_CHANGED -> {
                val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
                if (state == TelephonyManager.EXTRA_STATE_RINGING) {
                    showNotification(context, "Llamada entrante", "Te están llamando...")
                }
            }
        }
    }
}


