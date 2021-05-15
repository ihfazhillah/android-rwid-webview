package com.ihfazh.remoteworkerid.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import com.ihfazh.remoteworkerid.RWIDWebClient
import com.ihfazh.remoteworkerid.databinding.FragmentBlogBinding

class AMAFragment : Fragment() {
    private lateinit var binding: FragmentBlogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBlogBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.webView) {
            loadUrl("https://weworkremotelyasone.id/")
            webViewClient =
                RWIDWebClient(view.context.applicationContext, binding.progressBar, binding.errorAnimation)
            settings.javaScriptEnabled = true
            webChromeClient = WebChromeClient()
        }
    }
}