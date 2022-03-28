package com.example.gb_4_3.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gb_4_3.R
import com.example.gb_4_3.data.entity.MarkerEntity
import com.example.gb_4_3.databinding.FragmentMarkersListBinding
import com.example.gb_4_3.presentation.adapter.MarkersListAdapter
import com.example.gb_4_3.presentation.viewmodel.MarkersListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MarkersListFragment : Fragment(R.layout.fragment_markers_list) {
    private var _binding: FragmentMarkersListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MarkersListViewModel by viewModel()
    private val markersListAdapter = MarkersListAdapter(::markerOnClick)

    private fun markerOnClick(id: String) {
        val directions = MarkersListFragmentDirections.navigateToMarkerDetail(id)
        findNavController().navigate(directions)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarkersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.markersList.layoutManager = LinearLayoutManager(requireContext())
        binding.markersList.adapter = markersListAdapter

        viewModel.watchMarkers()
        viewModel.liveData.observe(viewLifecycleOwner, ::renderData)
    }

    private fun renderData(list: List<MarkerEntity>) {
        markersListAdapter.submitList(list)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
