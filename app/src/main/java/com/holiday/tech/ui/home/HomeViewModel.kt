package com.holiday.tech.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.holiday.backend.CATEGORY_ANDROID
import com.holiday.backend.RetrofitService
import com.holiday.tech.model.HomeVO
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {

    private val TAG = "HomeViewModel"

    private val _homeContent = MutableLiveData<List<HomeVO>>()

    val homeContent: LiveData<List<HomeVO>> = _homeContent

    fun getGankIOData(page: Int) {
        RetrofitService.getGankApi()
            .getCategoryData(CATEGORY_ANDROID, 20, page)
            .subscribeOn(Schedulers.io())
            .flatMap { t ->
                var data = ArrayList<HomeVO>()
                t.results.forEach {
                    val item = HomeVO(
                        it.createdAt,
                        it.desc,
                        it.publishedAt,
                        it.source,
                        it.type,
                        it.url,
                        it.used,
                        it.who,
                        it.images
                    )
                    data.add(item)
                }

                Observable.just(data)
            }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<HomeVO>> {
                override fun onComplete() {
                    Log.d(TAG, "oncomplete")
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: List<HomeVO>) {
                    _homeContent.value = t

                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "getGankIOData:", e)
                }

            })

    }
}