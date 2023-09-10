package com.example.networkcalls.placeholder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.networkcalls.databinding.FragmentSecondBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class SecondFragment : Fragment() {

    private val viewModel: MyViewModel by activityViewModels()

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

        binding.buttonSecond.setOnClickListener {
            viewModel.networkCall()
        }



        viewModel.result.observe(viewLifecycleOwner){
            setText(it)
        }

        viewModel.isLoading.observe(viewLifecycleOwner){
            if(it){
                binding.networkLoading.visibility = View.VISIBLE
            } else {
                binding.networkLoading.visibility = View.GONE
            }
        }
    }



    private fun setText(it: Data){
        binding.textviewQuote.text = it.joke

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}