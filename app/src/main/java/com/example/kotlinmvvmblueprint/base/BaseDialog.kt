package com.example.kotlinmvvmblueprint.base

import android.content.Context
import androidx.fragment.app.FragmentManager
import dagger.android.support.DaggerDialogFragment

abstract class BaseDialog : DaggerDialogFragment() {

    private lateinit var mActivity: BaseActivity<*, *>

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            mActivity = context
            mActivity.onFragmentAttached()
        }
    }

    override fun onDetach() {
        //todo implement mActivity = null
        super.onDetach()
    }

    fun dismissDialog(tag: String) {
        dismiss()
        mActivity.onFragmentDetached(tag)
    }

    fun getBaseActivity() = mActivity

    override fun show(fragmentManger: FragmentManager, tag: String) {
        var transaction = fragmentManger.beginTransaction()
        val fragment = fragmentManger.findFragmentByTag(tag)
        if (fragment != null) {
            transaction.remove(fragment)
        }
        transaction.addToBackStack(null)
        show(transaction, tag)
    }



}