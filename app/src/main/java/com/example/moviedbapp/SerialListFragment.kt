package com.example.moviedbapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedbapp.apis.RetrofitBuilder
import com.example.moviedbapp.databinding.LayoutSerialItemsBinding
import com.example.moviedbapp.databinding.LeyautSerialListFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SerialListFragment: Fragment() {
    private var _binding:LeyautSerialListFragmentBinding? = null
    private val binding get() = _binding!!
    private val tvAdapter = TvAdapter ()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =LeyautSerialListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvSeries.adapter = tvAdapter
        binding.tvSeries.layoutManager =
            LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        CoroutineScope(IO).launch {
            val tvPopularsResponse = RetrofitBuilder.tvApi.getPopularTv(API_KEY)
            withContext(Main){
                binding.progressBar.isVisible = false
                tvAdapter.updateAll(tvPopularsResponse.tvItems)
                }
            }
        tvAdapter.setOnItemClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.tvContent,SerialDetailsFragment.newInstance(it.id))
                addToBackStack(SerialDetailsFragment::class.java.name)
                commit()
            }
        }
        }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
    companion object {
        const val API_KEY = "843c612d1207fdec63f0e6a5fd426d68"
    }
}