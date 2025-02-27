package me.moontree.test.appwrite.auth

import androidx.activity.ComponentActivity
import io.appwrite.Client
import io.appwrite.services.Account
import io.appwrite.exceptions.AppwriteException
import io.appwrite.models.Session
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthManager(private val client: Client, private val scope: CoroutineScope) {
    private val account = Account(client)

    fun createOAuth2Session(activity: ComponentActivity, provider: OAuthProvider, callback: (String?) -> Unit) {
        scope.launch {
            try {
                account.createOAuth2Session(activity, provider)
                withContext(Dispatchers.Main) {
                    callback(null)
                }
            } catch (e: AppwriteException) {
                withContext(Dispatchers.Main) {
                    callback(e.message)
                }
            }
        }
    }

    fun deleteSession(sessionId: String, callback: (String?) -> Unit) {
        scope.launch {
            try {
                account.deleteSession(sessionId)
                withContext(Dispatchers.Main) {
                    callback(null)
                }
            } catch (e: AppwriteException) {
                withContext(Dispatchers.Main) {
                    callback(e.message)
                }
            }
        }
    }
}
