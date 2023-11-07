package com.example.networkcalls.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.networkcalls.MyApplication
import com.example.networkcalls.databinding.FragmentSecondBinding
import com.example.networkcalls.network.Data
import com.example.networkcalls.usecase.state.MyViewState
import com.example.networkcalls.usecase.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class SecondFragment : Fragment() {

    private val viewModel: MyViewModel by viewModels()
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
        binding.errorMessage.visibility = View.GONE
        binding.networkLoading.visibility = View.GONE
        binding.buttonSecond.setOnClickListener {
            viewModel.getJoke()
        }

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is MyViewState.IsLoading -> {
                        binding.networkLoading.visibility = View.VISIBLE
                    }
                    is MyViewState.Result -> {
                        setText(state.data)
                    }
                    is MyViewState.NetworkError -> {
                        Log.e("NETWORK ERROR", "Couldn't achieve network call")
                        binding.errorMessage.visibility = View.VISIBLE
                    }
                    is MyViewState.IsFirstTimeUser -> {
                        Toast.makeText(requireContext(), "That's your first joke!", Toast.LENGTH_SHORT).show()
                    }
                }
                if (state is MyViewState.Result && state.data == null) {
                    binding.errorMessage.visibility = View.VISIBLE
                } else {
                    binding.errorMessage.visibility = View.GONE
                }
            }
        }
    }


    private fun setText(it: Data?){
        lifecycleScope.launch {
            if (it != null) {
                binding.textviewQuote.text = it.joke
                viewModel.checkFirstTimeUser()
            }

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}