package com.glagouy.dmii

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.glagouy.dmii.fragments.ChoiceFragment
import com.glagouy.dmii.fragments.LocationFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            println("La permission n'est pas accordée")
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            ) {
                println("L’utilisateur a déjà refusé la permission, on doit lui expliquer pourquoi on en a besoin; une dialog, une vue dédiée etc…")
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    PERMISSION_CODE
                )
            }
        } else {
            println("La permission est accordée")
        }*/

        // Associer le layout à l'activité

        setContentView(R.layout.activity_main)
        if(savedInstanceState == null){
            val locationFragment = LocationFragment()
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.locationFragmentContainer, locationFragment)
                addToBackStack(null)
            }.commit()

            val fragment = ChoiceFragment()
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, fragment)
                addToBackStack(null)
            }.commit()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

    companion object val PERMISSION_CODE = 404
}
