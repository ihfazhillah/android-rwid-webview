package com.ihfazh.remoteworkerid

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ihfazh.remoteworkerid.ui.AMAFragment
import com.ihfazh.remoteworkerid.ui.BlogFragment
import com.ihfazh.remoteworkerid.ui.PromoFragment

class ViewPagerAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> BlogFragment()
            1 -> PromoFragment()
            else -> AMAFragment()
        }
    }
}