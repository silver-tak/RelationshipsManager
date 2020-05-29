package com.silvertak.relationshipsmanager.view.dialog

import androidx.fragment.app.DialogFragment
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

open class BaseDialogFragment : DialogFragment() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun addDisposable (disposable: Disposable) = compositeDisposable.add(disposable)

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable?.apply { dispose() }
    }
}