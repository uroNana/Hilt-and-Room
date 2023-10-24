package com.example.networkcalls.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.networkcalls.MyApplication
import com.example.networkcalls.databinding.FragmentSecondBinding
import com.example.networkcalls.network.Data
import com.example.networkcalls.MyViewState
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
            viewModel.state.collect { state ->
                when (state) {
                    is MyViewState.IsLoading -> {
                        binding.networkLoading.visibility = View.VISIBLE
                    }
                    is MyViewState.Result -> {
                        setText(state.data)
                        binding.networkLoading.visibility = View.GONE
                    }
                    is MyViewState.NetworkError -> {
                        Log.e("NETWORK ERROR", "Couldn't achieve network call")
                    }
                    is MyViewState.IsFirstTimeUser -> {
                        Toast.makeText(requireContext(), "That's your first joke!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setText(it: Data?){
        lifecycleScope.launch {
            if (it != null) {
                binding.textviewQuote.text = it.joke
            }
            viewModel.checkFirstTimeUser()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}