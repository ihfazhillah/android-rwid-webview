package com.ihfazh.remoteworkerid

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import com.ihfazh.remoteworkerid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.webView) {
            loadUrl("https://remoteworker.id/blog/blog-1")
            webViewClient =
                RWIDWebClient(applicationContext, binding.progressBar, binding.errorAnimation)
            settings.javaScriptEnabled = true
        }
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()){
            binding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

}