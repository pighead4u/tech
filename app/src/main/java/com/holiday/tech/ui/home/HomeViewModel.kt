package com.holiday.tech.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.holiday.backend.CATEGORY_WELFARE
import com.holiday.backend.RetrofitService
import com.holiday.tech.model.HomeVO
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {

    private val TAG = "HomeViewModel"

    private var page = 1

    private val query = MutableLiveData<Int>()

    val homeContent: LiveData<MutableList<HomeVO>> = Transformations.switchMap(
        query,
        ::getGankIOData
    )


    fun getGankIOData(page: Int): LiveData<MutableList<HomeVO>> {
        val tmp = RetrofitService.getGankApi()
            .getCategoryData(CATEGORY_WELFARE, 20, page)
            .subscribeOn(Schedulers.io())
            .flatMap { t ->
                var data = mutableListOf<HomeVO>()
                t.results.forEach {
                    Log.d(TAG, it.toString())
                    val item = HomeVO(
                        it.createdAt,
                        it.desc,
                        it.publishedAt,
                        it.source,
                        it.type,
                        it.url,
                        it.used,
                        it.who
                    )
                    data.add(item)
                }

                Log.d(TAG, data.toString())


                Flowable.just(data)
            }
            .subscribeOn(Schedulers.computation())

        return LiveDataReactiveStreams.fromPublisher(tmp)

    }

    fun addPage() {
        page++
        query.value = page
    }
}