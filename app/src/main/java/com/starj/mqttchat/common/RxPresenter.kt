package com.starj.mqttchat.common

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class RxPresenter{

    private val disposables: CompositeDisposable by lazy { CompositeDisposable() }

    protected fun dispose() {
        if(!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    protected fun add(disposable: Disposable) {
        disposables.add(disposable)
    }
}