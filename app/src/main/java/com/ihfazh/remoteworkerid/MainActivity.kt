package com.ihfazh.remoteworkerid

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.ihfazh.remoteworkerid.databinding.ActivityMainBinding
import com.ihfazh.remoteworkerid.ui.WebViewFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ViewPagerAdapter
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetJavaScriptEnabled", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = adapter

        binding.viewPager.reduceDragSensitivity()
        TabLayoutMediator(
            binding.tabLayout, binding.viewPager, false,  true
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Blog"
                    tab.icon = getDrawable(R.drawable.blog)
                }
                else -> {
                    tab.text = "Promo"
                    tab.icon = getDrawable(R.drawable.promo)

                }
            }
        }.attach()

    }

    override fun onBackPressed() {
        binding.viewPager.currentItem

        val currentFragment: WebViewFragment = supportFragmentManager.fragments[binding.viewPager.currentItem] as WebViewFragment
        if (currentFragment.canGoBack()){
            currentFragment.goBack()
        } else {
            super.onBackPressed()
        }
    }


}

private fun ViewPager2.reduceDragSensitivity() {
    val recyclerViewField = ViewPager2::class.java.getDeclaredField("mRecyclerView")
    recyclerViewField.isAccessible = true
    val recyclerView = recyclerViewField.get(this) as RecyclerView

    val touchSlopField = RecyclerView::class.java.getDeclaredField("mTouchSlop")
    touchSlopField.isAccessible = true
    val touchSlop = touchSlopField.get(recyclerView) as Int
    touchSlopField.set(recyclerView, touchSlop*8)       // "8" was obtained experimentally
}

