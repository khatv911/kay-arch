package kay.arch.errorhandler.resolver

import timber.log.Timber


/**
 * Created by Kay Tran on 28/7/18.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
class TimberResolver: UiResolver {

    override fun resolveErrorMessage(message: String, retryOption: Pair<Boolean, Int?>) {
        Timber.tag("TimberResolver").e(message)
    }

    override fun resolveErrorMessage(resource: Int, retryOption: Pair<Boolean, Int?>) {
        Timber.tag("TimberResolver").e("error")
    }

    override fun showConnectivity(available: Boolean) {
        if(available){
            Timber.tag("TimberResolver").d("Back Online")
        }else{
            Timber.tag("TimberResolver").e("Connection lost!")
        }
    }

    override fun resolveNotFound(retryOption: Pair<Boolean, Int?>) {
        Timber.tag("TimberResolver").e("Resource not found")
    }

    override fun resolveUnAuthorized() {
        Timber.tag("TimberResolver").e("Unauthorized!")
    }

    override fun resolveForbidden() {
        Timber.tag("TimberResolver").e("Forbidden!")
    }

    override fun showSuccess(message: String?) {
        Timber.tag("TimberResolver").d("Action success!")
    }
}