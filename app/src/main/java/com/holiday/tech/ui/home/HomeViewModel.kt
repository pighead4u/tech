package com.holiday.tech.ui.home

import androidx.lifecycle.*
import com.holiday.backend.CATEGORY_ANDROID
import com.holiday.backend.RetrofitService
import com.holiday.tech.model.HomeVO
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {

    private val TAG = "HomeViewModel"

    private var page = 1

    private val query = MutableLiveData<Int>()

    val homeContent: LiveData<List<HomeVO>> = Transformations.switchMap(
        query,
        ::getGankIOData
    )


    fun getGankIOData(page: Int): LiveData<List<HomeVO>> {
        val tmp = RetrofitService.getGankApi()
            .getCategoryData(CATEGORY_ANDROID, 20, page)
            .subscribeOn(Schedulers.io())
            .flatMap { t ->
                var data = listOf<HomeVO>()
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
                    data.plus(item)
                }

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