package com.kay.core.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import retrofit2.Call
import ru.gildor.coroutines.retrofit.awaitResult
import ru.gildor.coroutines.retrofit.getOrThrow

/**
 * Created by Kay Tran on 2/2/18.
 * Profile: https://github.com/khatv911
 * Email: khatv911@gmail.com
 */

/**
 * This class can be useful in most cases (get a list of data from db, check for new data from network and submitList db)
 * But do not abuse it. Your own data logic can be sufficient.
 */

abstract class NetworkBoundResource<PersistedData, NetworkResponse : Any> {

    private val result = MediatorLiveData<PersistedData>()

    var retry: (() -> Any)? = null

    init {
        loadData()
    }

    private fun loadData() {
        val dbSource = loadFromDB()
        result.addSource(dbSource) { data ->
            if (shouldFetch(data)) {
                result.removeSource(dbSource)
                fetchFromNetwork(dbSource)
            } else {
                setValue(data)
                noNeedFetching()
            }
        }
    }

    fun asLiveData(): LiveData<PersistedData> {
        return result
    }


    private fun setValue(newValue: PersistedData?) {
        if (result.value != newValue) result.value = newValue
    }

    /**
     * You may want to override this function in case you you have to call multiple requests
     * If so, the NetworkResponse should be a wrapper of all responses.
     * and [createCall] can be empty
     */
    protected open fun fetchFromNetwork(dbSource: LiveData<PersistedData>) {
        result.addSource(dbSource) { setValue(it) }
        launch(UI) {
            try {
                result.removeSource(dbSource)
                val networkResult = createCall().awaitResult()
                val value = networkResult.getOrThrow()

                retry = null
                withContext(CommonPool + exceptionThrower) { saveCallResult(value) }
                result.addSource(loadFromDB()) {
                    setValue(it)
                }
                onFetchSuccess()
            } catch (e: Throwable) {
                retry = { fetchFromNetwork(dbSource) }
                result.addSource(loadFromDB()) { setValue(it) }
                onException(e)
            }

        }
    }

    protected abstract fun loadFromDB(): LiveData<PersistedData>

    protected abstract fun createCall(): Call<NetworkResponse>

    protected abstract fun saveCallResult(requestType: NetworkResponse)

    protected abstract fun shouldFetch(resultType: PersistedData?): Boolean

    protected abstract fun onFetchSuccess()

    protected abstract fun noNeedFetching()

    protected abstract fun onException(e: Throwable)


}