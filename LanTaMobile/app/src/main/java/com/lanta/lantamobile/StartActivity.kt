package com.lanta.lantamobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.lanta.lantamobile.databinding.ActivityStartBinding
import com.lanta.lantamobile.model.RegisterDatabaseDao
import com.lanta.lantamobile.viewmodel.StartActivityViewModel


class StartActivity : AppCompatActivity() {
    private lateinit var binding : ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_start)
        binding.startActivityViewModel = ViewModelProvider(this)[StartActivityViewModel::class.java]
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),101)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==101){
            ActivityCompat.startActivityForResult(this,Intent(this, MainActivity::class.java),requestCode,null)
        }
        finish()
    }
}