package com.example.notaryapp.feature_date.presentation.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.core.app.NotificationCompat
import com.example.notaryapp.R


fun showNotification(context: Context, title: String, content: String) {
    val channelId = "sms_call_channel"
    val notificationManager = context.getSystemService(NotificationManager::class.java)

    // Crear el canal de notificación si es necesario (Android 8+)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(channelId, "Mensajes y Llamadas", NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(channel)
    }

    // Construir la notificación
    val notification = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.ic_launcher_background) // Icono de la notificación
        .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_launcher_foreground)) // Imagen personalizada
        .setContentTitle(title)
        .setContentText(content)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setAutoCancel(true) // Desaparece al tocarla
        .build()

    // Mostrar la notificación
    notificationManager.notify(1, notification)
}