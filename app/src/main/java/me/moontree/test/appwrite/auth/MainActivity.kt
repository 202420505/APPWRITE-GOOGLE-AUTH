package me.moontree.test.appwrite.auth

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var authManager: AuthManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        authManager = AuthManager(this)

        // Google 로그인 버튼
        findViewById<Button>(R.id.btn_login).setOnClickListener {
            authManager.signInWithGoogle()
        }
    }
}
