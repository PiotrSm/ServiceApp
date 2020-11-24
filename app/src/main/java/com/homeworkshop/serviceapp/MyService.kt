package com.homeworkshop.serviceapp

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.homeworkshop.serviceapp.App.Companion.CHANNEL_ID

class MyService : Service() {

    private lateinit var mediaPlayer : MediaPlayer

    //zmienna informująca czy muzyka jest odtwarzana, żeby za każdym razem nie otwierać nowego serwisu
    private var isPlay: Boolean = false

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!isPlay) {
            val notificationIntent = Intent(this, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

            val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Examle Service")
                .setContentText("Example Service")
                .setSmallIcon(R.drawable.ic_music_note)
                .setContentIntent(pendingIntent) //ta linijka decyduje o tym że odpali się nasza aktywność
                .build()

            mediaPlayer = MediaPlayer.create(applicationContext, R.raw.bamboleo)
            mediaPlayer.start()
            isPlay = true

            startForeground(1, notification)
        }
        return Service.START_NOT_STICKY //gdyby nasz servise został zniszczony z powodu braku pamięci przez aplikację, to serwis ginie i nie można go odtworzyć
    }

    override fun onBind(intent: Intent?): IBinder? {
        //zwraca nula ponieważ korzystamy z serwis który jest forground
        //servis forground pojawia się na pasku zadań, nie jest ukryty
        return null
    }

    override fun onDestroy() {
        mediaPlayer.stop()
        isPlay = false
        super.onDestroy()
    }
}