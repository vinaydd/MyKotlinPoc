package com.doctoremr.ui.base

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.wipro.pocapp.R


abstract class BaseDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.AppTheme_DialogFragment)
    }

    override fun show(manager: FragmentManager, tag: String?) {
        manager.beginTransaction().also {
            it.add(this, tag)
            it.commitAllowingStateLoss()
        }
    }
}