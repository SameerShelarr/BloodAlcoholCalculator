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
import java.lang.StringBuilder
import java.net.URLEncoder

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
                val builder = StringBuilder(
                    "mailto:" +
                            Uri.encode(getString(R.string.email_id_of_developer))
                )
                builder.append(
                    "?subject=" + getString(R.string.feedback_email_subject)
                )
                val uri = builder.toString()
                val intent = Intent(Intent.ACTION_SENDTO, Uri.parse(uri))
                startActivity(intent)
            }

        }

        return binding.root
    }
}