package com.example.tictactoe_java

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowInsets
import android.view.WindowManager

class LodingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loding)

        //위에 작업줄을 삭제
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        //시작하면 잠깐나왔다가 다시 메인으로 돌아가는 행동
        @Suppress("DEPRECATION")
        Handler().postDelayed(
                {
                    startActivity(Intent(this@LodingActivity, new_setting::class.java))
                    finish()
                },
                2500
        )


    }
}