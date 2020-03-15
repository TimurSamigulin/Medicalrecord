package com.example.medicalrecord.fragmentadapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.medicalrecord.fragment.DoctorCurrentFragment
import com.example.medicalrecord.fragment.DoctorOldFragment

class DoctorFragmentAdapter(fm: FragmentManager): FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                DoctorCurrentFragment()
            }
            else -> {
                return DoctorOldFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Записи к врачам"
            else -> "Архив походов к врачу"
        }
    }
}