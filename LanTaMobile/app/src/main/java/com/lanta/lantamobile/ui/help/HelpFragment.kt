package com.lanta.lantamobile.ui.help

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lanta.lantamobile.databinding.HelpFragmentBinding

class HelpFragment : Fragment() {

    private lateinit var viewModel: HelpViewModel
    private var _binding: HelpFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this)[HelpViewModel::class.java]
        _binding = HelpFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.agreement.movementMethod = LinkMovementMethod.getInstance();
        binding.privacy.movementMethod = LinkMovementMethod.getInstance();
        binding.button.setOnClickListener {
            Toast.makeText(context, "Opened", Toast.LENGTH_SHORT).show()
        }
        binding.button2.setOnClickListener {
            Toast.makeText(context, "Calling", Toast.LENGTH_SHORT).show()
        }
        binding.button3.setOnClickListener {
            Toast.makeText(context, "FAQ text", Toast.LENGTH_SHORT).show()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

