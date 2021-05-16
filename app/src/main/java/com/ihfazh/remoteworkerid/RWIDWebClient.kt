package com.ihfazh.remoteworkerid

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.airbnb.lottie.LottieAnimationView

class RWIDWebClient(
    private val context: Context,
    private val progressBar: ProgressBar,
    private val errorAnimation: LottieAnimationView
): WebViewClient(){
    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        progressBar.visibility = View.VISIBLE
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        progressBar.visibility = View.GONE
        super.onPageFinished(view, url)
    }

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        if (if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                error?.errorCode == ERROR_CONNECT
            } else {
                TODO("VERSION.SDK_INT < M")
            }
        ) {
            view?.loadUrl("about:blank")
            errorAnimation.visibility = View.VISIBLE
        }
    }


    // TODO: Add Linkedin, instagram
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        if (url != null){
            if (url.contains("intent://")){
                val intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
                return true
            }

            if (url.contains("twitter.com/intent")){
                return false
            }

            if (url.contains("facebook.com/sharer")) {
                return false
            }

            if (url.contains("linkedin.com/share")) {
                return false
            }

            if (url.contains("google.com/share")) {
                return false
            }
        }

        when(Uri.parse(url).host){
            "www.facebook.com" -> {
                openFacebook()
                return true
            }
            "twitter.com" -> {
                openTwitter()
                return true
            }
        }
        return false
    }

    private fun openTwitter() {
        val userId = "VXNlcjoxMjI4OTI2NDE2NzQ3NzQ1Mjgy"

        val intent = try {
            context.packageManager.getPackageInfo("com.twitter.android", 0)
            Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=$userId"))
        } catch (e: Exception) {
            println("In exception")
            Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/remoteworkerid"))
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    private fun openFacebook() {
        val pageId = "796963000474809"

        val intent = try {
            println("Hello world")
            Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/$pageId"))
        } catch (e: Exception) {
            println("In exception")
            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/$pageId"))
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}