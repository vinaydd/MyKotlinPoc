package com.doctoremr.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.doctoremr.data.preferences.PreferenceProvider
import com.doctoremr.utils.Constants.NO_INTERNET
import com.doctoremr.utils.viewutils.logout
import com.doctoremr.utils.viewutils.toast
import com.wipro.pocapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import kotlin.coroutines.CoroutineContext


abstract class BaseFragment : Fragment(), CoroutineScope, KodeinAware {

    override val kodein by kodein()
    private lateinit var job: Job
    protected val prefs: PreferenceProvider by instance()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    protected fun setViewModel(viewModel: BaseViewModel) {
        viewModel.error.observe(viewLifecycleOwner, Observer {
            when (it.code) {
                401 -> {
                    prefs.clearPreferences()
                    requireContext().logout()
                }
                NO_INTERNET -> {
                    findNavController().navigate(R.id.fragmentNoInternet)
                }
                else -> {
                    requireContext().toast(it.message)
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}