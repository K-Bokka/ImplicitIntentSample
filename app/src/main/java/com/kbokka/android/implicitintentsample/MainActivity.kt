package com.kbokka.android.implicitintentsample

import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {

  private var _latitude = 0.0
  private var _longitude = 0.0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  fun onMapSearchButtonClick(view: View) {
    val etSearchWord = findViewById<EditText>(R.id.etSearchWord)
    var searchWord = etSearchWord.text.toString()
    searchWord = URLEncoder.encode(searchWord, "UTF-8")

    val uriStr = "geo:0,0?q=${searchWord}"
    val uri = Uri.parse(uriStr)
    val intent = Intent(Intent.ACTION_VIEW, uri)
    startActivity(intent)
  }

  fun onMapShowCurrentButtonClick(view: View) {
    val uriStr = "geo:${_latitude},${_longitude}?"
    val uri = Uri.parse(uriStr)
    val intent = Intent(Intent.ACTION_VIEW, uri)
    startActivity(intent)
  }

  private inner class GPSLocationListener : LocationListener {
    override fun onLocationChanged(location: Location) {
      _latitude = location.latitude
      _longitude = location.longitude
      findViewById<TextView>(R.id.tvLatitude).text = _latitude.toString()
      findViewById<TextView>(R.id.tvLongitude).text = _longitude.toString()
    }

    override fun onProviderDisabled(provider: String) {}

    override fun onProviderEnabled(provider: String) {}

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
  }
}
