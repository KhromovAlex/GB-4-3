package com.example.gb_4_3.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gb_4_3.R
import com.example.gb_4_3.data.entity.MarkerEntity
import com.example.gb_4_3.databinding.FragmentMarkerDetailsBinding
import com.example.gb_4_3.presentation.viewmodel.MarkerDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MarkerDetailsFragment : Fragment() {
    private var _binding: FragmentMarkerDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MarkerDetailsViewModel by viewModel()
    private val args by navArgs<MarkerDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarkerDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabSaveMarkers.setOnClickListener {
            viewModel.updateMarker(
                name = binding.name.editText?.text?.toString() ?: "",
                description = binding.description.editText?.text?.toString() ?: ""
            )
            findNavController().popBackStack()
        }

        viewModel.getMarkerById(args.markerId)
        viewModel.liveData.observe(viewLifecycleOwner, ::renderData)
    }

    private fun renderData(markerEntity: MarkerEntity?) {
        binding.name.editText?.setText(markerEntity?.name ?: "")
        binding.name.setHint(R.string.name)
        binding.description.editText?.setText(markerEntity?.description ?: "")
        binding.description.setHint(R.string.description)
        binding.latitude.text = markerEntity?.position?.latitude.toString()
        binding.longitude.text = markerEntity?.position?.longitude.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
