package me.moontree.test.appwrite.auth

import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import io.appwrite.Client
import io.appwrite.oauth.OAuthProvider
import io.appwrite.services.Account
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.moontree.test.appwrite.auth.databinding.ActivityMainBinding

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
        binding.btnLogin.setOnClickListener {
            startAppwriteOAuth()
        }
    }

    // ✅ Appwrite SDK를 사용하여 OAuth 로그인 시작
    private fun startAppwriteOAuth() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val session = account.createOAuth2Token(
                    activity = this@MainActivity,  // 🔹 현재 Activity 전달
                    provider = OAuthProvider.Google  // 🔹 OAuthProvider.Google 사용
                )
                Log.d("AppwriteOAuth", "OAuth Token Created Successfully")

                // 🔹 로그인한 사용자 정보 가져오기
                val user = account.get()
                Log.d("AppwriteOAuth", "User Info: ${user.name}, ${user.email}")
            } catch (e: Exception) {
                Log.e("AppwriteOAuth", "Failed to create OAuth2 token: ${e.message}")
            }
        }
    }
}
