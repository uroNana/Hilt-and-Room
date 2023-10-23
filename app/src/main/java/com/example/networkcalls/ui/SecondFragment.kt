package com.example.networkcalls.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.networkcalls.MyApplication
import com.example.networkcalls.databinding.FragmentSecondBinding
import com.example.networkcalls.network.Data
import kotlinx.coroutines.launch

class SecondFragment : Fragment() {

    private lateinit var viewModel: MyViewModel

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application = (requireActivity().application as MyApplication)
        viewModel = application.factory.create(MyViewModel::class.java)
        binding.buttonSecond.setOnClickListener {
            viewModel.getJoke()
        }


        lifecycleScope.launch {
            viewModel.result.collect{
            setText(it)
            }
        }

        lifecycleScope.launch {
            viewModel.isLoading.collect {
                if (it) {
                    binding.networkLoading.visibility = View.VISIBLE
                } else {
                    binding.networkLoading.visibility = View.GONE
                }
            }
        }
    }



    private fun setText(it: Data?){
        if (it != null) {
            binding.textviewQuote.text = it.joke
        }
        if(viewModel.isFirstTimeUser.value == true && viewModel.isFirstTimeUser.value != null){
            Toast.makeText(requireContext(), "That's your first joke!", Toast.LENGTH_SHORT).show()
            viewModel.isFirstTimeUser.value = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}