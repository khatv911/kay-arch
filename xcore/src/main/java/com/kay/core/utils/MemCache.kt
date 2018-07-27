package com.kay.core.utils

/**
 * Created by Kay Tran on 2/2/18.
 * Profile: https://github.com/khatv911
 * Email: khatv911@gmail.com
 */
object MemCache {
    private val maxMemory = Runtime.getRuntime().maxMemory() / 1024

    private val cache = androidx.collection.LruCache<Any, Any>((maxMemory / 8).toInt())

    operator fun set(key: Any, value: Any) {
        cache.put(key, value)
    }

    operator fun get(key: Any): Any? = cache.get(key)

    fun remove(key: Any): Boolean = cache.remove(key) != null

}