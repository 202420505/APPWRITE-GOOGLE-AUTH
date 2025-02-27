package me.moontree.test.appwrite.auth

import android.app.Activity
import android.content.Intent
import io.appwrite.Client
import io.appwrite.services.Account

class AuthManager(private val activity: Activity) {
    private val account = Account(App.client)

    // Google OAuth 로그인 시작
    fun signInWithGoogle() {
        account.createOAuth2Session(
            activity,
            provider = "google",
            success = { result ->
                activity.runOnUiThread {
                    activity.startActivity(Intent(activity, MainActivity::class.java))
                }
            },
            failure = { e ->
                e.printStackTrace()
            }
        )
    }

    // 로그아웃
    fun logout() {
        account.deleteSession("current")
    }
}
