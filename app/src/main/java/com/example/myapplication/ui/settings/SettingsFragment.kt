package com.example.myapplication.ui.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSettingsBinding
import com.example.myapplication.util.toast


class SettingsFragment() : Fragment()  {
    private lateinit var _binding : FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSettingsBinding.inflate(inflater , container , false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_dropdown_item, arrayListOf(
                "Size:18sp",
                "Size:22sp",
                "Size:24sp",
            )
        )
        _binding.typesFilter.setAdapter(adapter)


        _binding.typesFilter.setDropDownBackgroundDrawable(
            ResourcesCompat.getDrawable(
                resources,
                R.drawable.filter_spinner_dropdown_bg,
                null
            )
        )

        _binding.typesFilter.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id->
                val fontSize: Float = resources.getDimension(R.dimen.title_size)
                Log.v("test" , fontSize.toString())
                when (position) {
                    0 -> {

                    }
                    1 -> {
                        requireActivity().toast("Size:22sp")
                        R.dimen.title_size.minus(4f)
                    }
                    2 -> {
                        requireActivity().toast("Size:24sp")
                    }
                }

            }




        // set the switch to listen on checked change
        _binding.switch1.setOnCheckedChangeListener { _, isChecked ->

            // if the button is checked, i.e., towards the right or enabled
            // enable dark mode, change the text to disable dark mode
            // else keep the switch text to enable dark mode
            if (_binding.switch1.isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                _binding.switch1.text = "Disable dark mode"
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                _binding.switch1.text = "Enable dark mode"
            }
        }
    }
}