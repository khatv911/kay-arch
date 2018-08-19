package kay.arch.errorhandler.resolver

import android.support.v4.app.Fragment
import android.widget.Toast
import es.dmoral.toasty.Toasty


/**
 * Created by Kay Tran on 28/7/18.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
class ToastyResolver(fragment: Fragment) : AbsUiResolver(fragment) {

    override fun resolveErrorMessage(message: String, retryOption: Pair<Boolean, Int?>) {
        val fragment = fragmentRef.get()
        fragment?.let {
            Toasty.error(it.requireContext(),message,Toast.LENGTH_LONG,true).show()
        }
    }

    override fun resolveErrorMessage(resource: Int, retryOption: Pair<Boolean, Int?>) {
        val fragment = fragmentRef.get()
        fragment?.let {
            Toasty.error(it.requireContext(),it.getString(resource),Toast.LENGTH_LONG,true).show()
        }
    }

    override fun showConnectivity(available: Boolean) {
        val fragment = fragmentRef.get()
        fragment?.let {
            if (available) {
                Toasty.success(it.requireContext(), "Connected!", Toast.LENGTH_SHORT, true).show()
            } else {
                Toasty.error(it.requireContext(), "Disconnected!", Toast.LENGTH_SHORT, true).show()
            }
        }
    }

    override fun resolveNotFound(retryOption: Pair<Boolean, Int?>) {
        val fragment = fragmentRef.get()
        fragment?.let {
            Toasty.error(it.requireContext(),"Resource not found!",Toast.LENGTH_LONG,true).show()
        }
    }

    override fun resolveUnAuthorized() {
        val fragment = fragmentRef.get()
        fragment?.let {
            Toasty.error(it.requireContext(),"Unauthorized!",Toast.LENGTH_LONG,true).show()
        }
    }

    override fun resolveForbidden() {
        val fragment = fragmentRef.get()
        fragment?.let {
            Toasty.error(it.requireContext(),"Unauthorized!",Toast.LENGTH_LONG,true).show()
        }
    }

    override fun showSuccess(message: String?) {
        val fragment = fragmentRef.get()
        fragment?.let {
            Toasty.success(it.requireContext(),message?:"Success!",Toast.LENGTH_SHORT,true).show()
        }
    }


}
