package com.android.course.pushnotificationtest.pushnotification

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

/**
 * Access the device registration token
 */
class MyFirebaseInstanceIDService: FirebaseInstanceIdService() {

    companion object {
        val TAG: String = MyFirebaseInstanceIDService::class.java.simpleName
    }

    override fun onTokenRefresh() {
        // Get updated InstanceID token. This method returns null if the token has not yet been generated.
        var refreshedToken : String? = FirebaseInstanceId.getInstance().getToken()
        Log.d(TAG, "Refresh token: "+refreshedToken)

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);

    }

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    fun sendRegistrationToServer(token: String?){
        // TODO: Implement this method to send token to your app server.
    }
}