package com.lanta.lantamobile.ui.map

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.lanta.lantamobile.databinding.FragmentMapBinding
import androidx.core.content.ContextCompat.getSystemService
class MapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var mapViewModel: MapViewModel
    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, avedInstanceState: Bundle?): View? {
        mapViewModel = ViewModelProvider(this)[MapViewModel::class.java]
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        val supportMapFragment = childFragmentManager.findFragmentById(com.lanta.lantamobile.R.id.map) as SupportMapFragment?
        supportMapFragment!!.getMapAsync { googleMap -> map = googleMap
            onMapReady(map)
        }
        return binding.root
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mapViewModel.setMapStyle(googleMap)
        mapViewModel.updateLocationUI(googleMap)
        mapViewModel.getDeviceLocation(googleMap)
    }
    @SuppressLint("ServiceCast")
    override fun onResume() {
        super.onResume()
        mapViewModel.startLocationUpdates()
    }
    override fun onPause() {
        super.onPause()
        mapViewModel.stopLocationUpdates()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object{
        lateinit var map: GoogleMap
    }
}

