package com.sameershelar.bloodalcoholcalculator.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sameershelar.bloodalcoholcalculator.R
import com.sameershelar.bloodalcoholcalculator.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    companion object {
        private const val TAG = "AboutFragment"
    }

    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater)

        binding.apply {
            emailImageButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    data = Uri.parse("mailto:")
                    type = "text/plain"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.email_id_of_developer)))
                    putExtra(Intent.EXTRA_SUBJECT, "Feedback for Blood Alcohol Calculator app")
                }
                if (intent.resolveActivity(requireActivity().packageManager) != null) {
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "No suitable application found, Cannot send email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }

        return binding.root
    }
}