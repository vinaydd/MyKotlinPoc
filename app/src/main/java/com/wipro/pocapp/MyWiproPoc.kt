package com.wipro.pocapp

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.doctoremr.data.network.NetworkConnectionInterceptor
import com.doctoremr.data.network.NetworkApi
import com.doctoremr.data.preferences.PreferenceProvider
import com.doctoremr.data.repositories.PocRepository

import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.wipro.pocapp.ui.home.PocViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


class MyWiproPoc : MultiDexApplication(), KodeinAware {
    private var singleton: MyWiproPoc? = null
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        configureFresco()
    }

    fun getInstance(): MyWiproPoc {
        return singleton!!
    }

    /**
     * Method to add Additional configuration parameter
     *
     *
     * Refer : http://frescolib.org/docs/configure-image-pipeline.html
     */
    private fun configureFresco() {
        val config = ImagePipelineConfig.newBuilder(this)
            .setDownsampleEnabled(true)
            .build()
        Fresco.initialize(this, config)
    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MyWiproPoc))

        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from provider { NetworkApi(instance(), instance()) }

        /**
         * REPOSITORIES
         * =============================================
         * Bind all the repositories that your create here
         * */

        bind() from provider { PocRepository(instance()) }



               /**
         * VIEWMODEL FACTORIES
         * =============================================
         * Bind all the ViewModel Factories that your create here
         * */

        bind() from provider { PocViewModelFactory(instance()) }

    }
}