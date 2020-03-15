package com.example.medicalrecord.fragmentadapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.medicalrecord.fragment.DiseaseCurrentFragment
import com.example.medicalrecord.fragment.DiseaseOldFragment

class DiseaseFragmentAdapter(fm: FragmentManager): FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                DiseaseCurrentFragment()
            }
            else -> {
                return DiseaseOldFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Текущие Болезни"
            else -> "Архив болезней"
        }
    }

}