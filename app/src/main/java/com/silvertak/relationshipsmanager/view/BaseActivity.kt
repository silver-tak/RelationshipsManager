package com.silvertak.relationshipsmanager.view

import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

open class BaseActivity : AppCompatActivity() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun addDisposable(disposable: Disposable) = disposable?.let { compositeDisposable.add(disposable) }

    override fun onStop() {
        super.onStop()
        compositeDisposable.dispose()
    }
}