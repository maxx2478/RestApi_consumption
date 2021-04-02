package com.trade.restapiconsumption

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.trade.restapiconsumption.data.UserPreferences
import com.trade.restapiconsumption.ui.auth.home.HomeActivity
import com.trade.restapiconsumption.ui.auth.startNewActivity

class LauncherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        val userPreferences = UserPreferences(this)
        userPreferences.authToken.asLiveData().observe(this, Observer {

            val activity = if(it == null) MainActivity::class.java else HomeActivity::class.java
            startNewActivity(activity)
        })

    }
}