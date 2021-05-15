package com.ihfazh.remoteworkerid

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ihfazh.remoteworkerid.ui.WebViewFragment

class ViewPagerAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> WebViewFragment.create("https://remoteworker.id/blog/blog-1?affid=47ZY8rGf")
            else -> WebViewFragment.create("https://remoteworker.id/promo?affid=47ZY8rGf")
        }
    }
}