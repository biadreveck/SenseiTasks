package com.github.gbazilio.faire.domain

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Executors {
    fun io() = Schedulers.io()
    fun mainThread() = AndroidSchedulers.mainThread()
}