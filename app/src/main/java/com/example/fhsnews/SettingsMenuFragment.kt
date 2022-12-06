package com.example.fhsnews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.example.fhsnews.databinding.FragmentSettingsMenuBinding

private var _binding: FragmentSettingsMenuBinding? = null
private val binding get() = _binding!!

class SettingsMenuFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsMenuBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_system ->
                    if (checked) {
                        // sex
                    }
                R.id.radio_light ->
                    if (checked) {
                        // sex
                    }
                R.id.radio_dark ->
                    if (checked) {
                        // sex
                    }
            }
        }
    }
}