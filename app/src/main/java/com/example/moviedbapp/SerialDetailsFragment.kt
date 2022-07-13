package com.example.moviedbapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.moviedbapp.apis.RetrofitBuilder
import com.example.moviedbapp.databinding.LeyoutSerialDetailsFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SerialDetailsFragment: Fragment() {
    private var _binding: LeyoutSerialDetailsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LeyoutSerialDetailsFragmentBinding.inflate(inflater,container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = requireArguments().getInt(KEY_ID_PARAM)
        CoroutineScope(IO). launch {
            val serialDetails = RetrofitBuilder.tvApi.getSerialDetail(id, SerialListFragment.API_KEY)
            withContext(Main){
               binding.movAbout.text = binding.root.context.getString(R.string.overviews,serialDetails.overview)
                binding.movDetailTitle.text =serialDetails.originalName
                binding.movDetailRelease.text =binding.root.context.getString(R.string.Release_date1,serialDetails.firstAirDate)
                binding.movDetailRating.text =binding.root.context.getString(R.string.Average_rating1,serialDetails.voteAverage)
                binding.movDetailPopularity.text = binding.root.context.getString(R.string.Popularity,serialDetails.popularity)
                binding.movDetailRateCount.text = binding.root.context.getString(R.string.rate_count,serialDetails.voteCount)

              val postUrl="$IMAGE_PATH${serialDetails.posterPath}"
                Glide.with(binding.root.context).load(postUrl).into(binding.movDetailPoster)

                val backPostUrl="$IMAGE_PATH${serialDetails.backdropPath}"
                Glide.with(binding.root.context).load(backPostUrl).into(binding.movHeadPoster)
              }
        }
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
   companion object {
       const val KEY_ID_PARAM = "KEY_ID_PARAM"
       const val IMAGE_PATH = "https://image.tmdb.org/t/p/w500"
       fun newInstance (id: Int):SerialDetailsFragment {
           return SerialDetailsFragment().apply {
               arguments = bundleOf(KEY_ID_PARAM to id)
           }
       }
   }
}