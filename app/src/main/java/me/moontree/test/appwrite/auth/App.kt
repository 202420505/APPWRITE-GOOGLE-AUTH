package me.moontree.test.appwrite.auth

import android.app.Application
import io.appwrite.Client

class App : Application() {
    companion object {
        lateinit var client: Client
    }

    override fun onCreate() {
        super.onCreate()

        // Appwrite 클라이언트 초기화
        client = Client(this)
            .setEndpoint("https://cloud.appwrite.io/v1") // Appwrite 엔드포인트
            .setProject("YOUR_PROJECT_ID") // 프로젝트 ID 입력
    }
}
