package me.moontree.test.appwrite.auth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import me.moontree.test.appwrite.auth.databinding.ActivityMainBinding
import io.appwrite.Client
import io.appwrite.services.Account
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var client: Client
    private lateinit var account: Account
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View Binding 설정
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 🔹 Appwrite 클라이언트 초기화
        client = Client(this)
            .setEndpoint("https://cloud.appwrite.io/v1")  // Appwrite 서버 주소
            .setProject("moontree-test")  // Appwrite 프로젝트 ID

        account = Account(client)

        // ✅ 로그인 버튼 클릭 시 OAuth 실행
        binding.btn_login.setOnClickListener  {
            startAppwriteOAuth()
        }

        // ✅ OAuth 인증 후 Deep Link에서 결과 처리
        handleDeepLink(intent)
    }

    // ✅ Appwrite OAuth 로그인 시작
    private fun startAppwriteOAuth() {
        val authUrl = "https://cloud.appwrite.io/v1/account/sessions/oauth2/google?project=YOUR_PROJECT_ID&success=appwrite://auth/oauth&failure=appwrite://auth/oauth"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(authUrl))
        startActivity(intent)
    }

    // ✅ OAuth 로그인 완료 후 Deep Link 처리 (Coroutine 사용)
    private fun handleDeepLink(intent: Intent?) {
        intent?.data?.let { uri ->
            Log.d("AppwriteOAuth", "Deep Link received: $uri")

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val user = account.get()
                    Log.d("AppwriteOAuth", "User Info: ${user.name}, ${user.email}")
                } catch (e: Exception) {
                    Log.e("AppwriteOAuth", "Failed to get user info: ${e.message}")
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleDeepLink(intent)
    }
}
