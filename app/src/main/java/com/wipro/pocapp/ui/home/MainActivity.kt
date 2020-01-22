package com.wipro.pocapp.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.doctoremr.data.preferences.PreferenceProvider
import com.wipro.pocapp.R
import com.wipro.pocapp.exception.NoInternetException
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val preferences: PreferenceProvider by instance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {

            setContentView(R.layout.activity_main)

        }catch (e: NoInternetException) {
            Navigation.findNavController(this@MainActivity, R.id.fragment).navigate(R.id.fragmentNoInternet)
        }
    }
}
