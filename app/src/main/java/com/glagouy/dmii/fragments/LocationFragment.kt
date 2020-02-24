package com.glagouy.dmii.fragments

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.glagouy.dmii.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.location_fragment.*

class LocationFragment: Fragment() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.location_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = activity ?: return

        if (ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            println("La permission n'est pas accordée")
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                println("L’utilisateur a déjà refusé la permission, on doit lui expliquer pourquoi on en a besoin; une dialog, une vue dédiée etc…")
                // popup

                val builder: AlertDialog.Builder? = activity.let {
                    AlertDialog.Builder(it)
                }

                builder?.setMessage(R.string.dialog_permission_msg)?.setTitle(R.string.dialog_permission_title)
                builder?.apply {
                    setPositiveButton(R.string.ok
                    ) { dialog, id ->
                        askPermission()
                        dialog.cancel()
                    }
                    setNegativeButton(R.string.cancel) { dialog, id -> dialog.cancel() }
                }
                val dialog: AlertDialog? = builder?.create()
                dialog?.show()
            } else {
                askPermission()
            }
        } else {
            println("La permission est accordée")
            getLocation()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == PERMISSION_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLocation()
            }
        }
    }

    fun getLocation(){
        fusedLocationClient.lastLocation
            .addOnSuccessListener { locationValue : Location? ->
                location.text = "Latitude : ${locationValue?.latitude} - Longitude : ${locationValue?.longitude}"
            }

    }

    fun askPermission(){
        requestPermissions(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_CODE
        )
    }

    companion object val PERMISSION_CODE = 403
}