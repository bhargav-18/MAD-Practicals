package com.example.practical8_19012021093

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.VideoView

class MainActivity : AppCompatActivity() {

    private val youTubeId = "h-ile9tMNM0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val videoView = findViewById<VideoView>(R.id.video_view)
        val webView = findViewById<WebView>(R.id.web_view)

        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true
        webView.loadUrl("https://www.youtube.com/embed/$youTubeId")

        val mediaController = android.widget.MediaController(this)
        mediaController.setAnchorView(videoView)

        val uri: Uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.video)
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()
    }
}