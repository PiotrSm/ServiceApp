package com.homeworkshop.serviceapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class App:Application() {

    companion object{
        const val CHANNEL_ID = "exampleService"
    }

    override fun onCreate() {
        super.onCreate()
        //jeżeli wersja systemu jest większa niż 26 czyli Oreo to dodajemy channel
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            val serviceChannel = NotificationChannel(CHANNEL_ID,"ExampleServiceChannel",NotificationManager.IMPORTANCE_DEFAULT)

            //Tworzymy notification manager i dodajemy servicechannel
            val mng = getSystemService(NotificationManager::class.java)
            mng.createNotificationChannel(serviceChannel)
        }
    }
}