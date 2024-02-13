package com.ebookfrenzy.viewmodeldemo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ebookfrenzy.viewmodeldemo.databinding.FragmentFirstBinding
import androidx.lifecycle.ViewModelProvider

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private lateinit var viewModel: MainViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.resultText.text = getString(R.string.tip_amounts_will_display_here)

        binding.convertButton.setOnClickListener {
            if (binding.dollarText.text.isNotEmpty()) {
                val billAmount = binding.dollarText.text.toString().toDouble()

                val tip10 = billAmount * 0.1
                val tip15 = billAmount * 0.15
                val tip20 = billAmount * 0.2

                val resultString = "The tips are as follows:\n\n" +
                        "10% = ${String.format("%.2f", billAmount + tip10)}\n" +
                        "15% = ${String.format("%.2f", billAmount + tip15)}\n" +
                        "20% = ${String.format("%.2f", billAmount + tip20)}"

                binding.resultText.text = resultString
            } else {
                binding.resultText.text = "YOU MUST ENTER A BILL AMOUNT"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

}