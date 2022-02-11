package com.lanta.lantamobile.ui.map

import android.annotation.SuppressLint
import android.app.Application
import android.content.res.Resources
import android.os.Looper
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.lanta.lantamobile.MainActivity

class MapViewModel(application: Application) : AndroidViewModel(application){

    private val context = application.applicationContext
    private var fusedLocationClient : FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    private var locationCallback: LocationCallback = createLocationCallback()

    private fun createLocationCallback(): LocationCallback {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
            }
        }
        return locationCallback
    }

    @SuppressLint("MissingPermission")
    fun updateLocationUI(map: GoogleMap) {
        map?.isMyLocationEnabled = true
        map?.uiSettings?.isMyLocationButtonEnabled = true
        map?.uiSettings?.isZoomControlsEnabled = true
    }
    @SuppressLint("MissingPermission")
    fun getDeviceLocation(map: GoogleMap) {
        fusedLocationClient.lastLocation.addOnSuccessListener { location->
            if (location != null) {
                map?.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(
                    LatLng(location.latitude, location.longitude), DEFAULT_ZOOM.toFloat()))
            }
            else {
                map?.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(
                    defaultLocation, DEFAULT_ZOOM.toFloat()))
            }
        }
    }
    fun setMapStyle(map: GoogleMap) {
        try {
            val success =
                map.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                        context,
                        com.lanta.lantamobile.R.raw.map_style)
                )
            if (!success) {
                Log.e(TAG, "Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            Log.e(TAG, "Can't find style. Error: ", e)
        }
    }
    fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    @SuppressLint("MissingPermission")
    fun startLocationUpdates() {
        fusedLocationClient.requestLocationUpdates(
            createLocationRequest(),
            locationCallback,
            Looper.getMainLooper())
    }
    private fun createLocationRequest(): LocationRequest {
        val locationRequest = LocationRequest.create()?.apply {
            interval = 1
            fastestInterval = 0
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        return locationRequest
    }
    companion object {
        private val TAG = MainActivity::class.java.simpleName
        private const val DEFAULT_ZOOM = 15
        private val defaultLocation = LatLng(52.7371, 41.4396)

    }
}