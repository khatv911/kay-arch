package com.kay.core.enviroments

import okhttp3.OkHttpClient

/**
 * Created by Kay Tran on 2/2/18.
 * Profile: https://github.com/khatv911
 * Email: khatv911@gmail.com
 */

fun OkHttpClient.Builder.inject(): okhttp3.OkHttpClient.Builder {
    return this
}


fun android.app.Application.injectEnvSettings() {}