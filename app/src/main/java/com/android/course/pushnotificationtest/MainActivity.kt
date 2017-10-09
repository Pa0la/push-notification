package com.android.course.pushnotificationtest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.common.GoogleApiAvailability
import com.google.firebase.messaging.FirebaseMessaging








class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = getTag()

        protected fun getTag(): String {
            return MainActivity::class.java.simpleName
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()

        checkForGooglePlayService()
    }

    fun checkForGooglePlayService() {
        GoogleApiAvailability.getInstance()
                .makeGooglePlayServicesAvailable(this)
                .addOnSuccessListener {
                    preparePushNotification()
                    subscribeTopic()
                }
                .addOnFailureListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("market://details?id=com.google.android.gms")
                    startActivity(intent)
                }
    }

//    abstract fun getLayoutId():Int

    fun preparePushNotification(){

        // With Android 8.0, notification channels allow you to create
        // a user-customizable channel for each type of notification you want to display
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Construct a notification channel object with an ID that's unique within your package.
            val channelId = getString(R.string.default_notification_channel_id)
            // The user-visible name of the channel.
            val channelName = getString(R.string.default_notification_channel_name)
            // The user-visible description of the channel.
            val description = getString(R.string.default_notification_channel_description)
            val important = NotificationManager.IMPORTANCE_LOW
            // Notification channel
            val mChannel=NotificationChannel(channelId, channelName, important)
            // Configure the notification channel.
            mChannel.setDescription(description)
            mChannel.enableLights(true)
            // Sets the notification light color for notifications posted to this
            // channel, if the device supports this feature.
            mChannel.lightColor = Color.RED
            mChannel.enableVibration(true)
            mChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager!!.createNotificationChannel(mChannel)
        }

        // If a notification message is tapped, any data accompanying the notification
        // message is available in the intent extras. In this sample the launcher
        // intent is fired when the notification is tapped, so any accompanying data would
        // be handled here. If you want a different intent fired, set the click_action
        // field of the notification message to the desired intent. The launcher intent
        // is used when no click_action is specified.
        //
        // Handle possible data accompanying notification message.
        // [START handle_data_extras]
        if (intent.extras != null) {
            for (key in intent.extras!!.keySet()) {
                val value = intent.extras!!.get(key)
                Log.d(TAG, "Key: $key Value: $value")
            }
        }




    }

    fun subscribeTopic(){
        // [START subscribe_topics]
        FirebaseMessaging.getInstance().subscribeToTopic("/topics/my_little_topic")
        // [END subscribe_topics]

        // Log and toast
        val msg = getString(R.string.msg_subscribed)
        Log.d(TAG, msg)
        //Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()

        // [START unsubscribe_topics]
        // FirebaseMessaging.getInstance().unsubscribeFromTopic("/topics/my_little_topic")
        // [END unsubscribe_topics]
    }

}
