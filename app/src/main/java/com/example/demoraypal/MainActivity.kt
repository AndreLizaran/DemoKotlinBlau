package com.example.demoraypal

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter:TodoAdapter
    private lateinit var lastLocation:Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        btnGetLocation.setOnClickListener {
            checkPermissions()
        }
        btnCleanText.setOnClickListener {
            cleanTexts()
        }
        btnGetRequest.setOnClickListener {
            myFirstGetRequest()
        }
    }

    private fun checkPermissions () {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 1)
        } else getLocation()
    }

    @SuppressLint("MissingPermission")
    private fun getLocation () {
        fusedLocationProviderClient.lastLocation?.addOnSuccessListener {
            if (it == null) Toast.makeText(this, "Sorry, can´t get location", Toast.LENGTH_SHORT).show()
            else {
                it.apply {
                  val latitude = it.latitude
                  val longitude = it.longitude
                    tvLatitude.text = "Latitude: $latitude"
                    tvLongitude.text = "Longitude: $longitude"
                }
            }
        }
    }

    override fun onRequestPermissionsResult (requestCode: Int, permissions:Array<out String>, grantResults:IntArray) {
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun cleanTexts () {
        tvLatitude.text = "Latitude: "
        tvLongitude.text = "Longitude: "
    }

    private fun myFirstGetRequest () {

    }

}