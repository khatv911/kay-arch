package kay.arch.objectbox

import android.app.Application
import io.objectbox.BoxStore

/**
 * Created by Kay Tran on 13/6/18.
 * Profile: https://github.com/khatv911
 * Email: khatv911@gmail.com
 */
abstract class ObjectBoxWrapper(private val application: Application) {
    abstract fun build(): BoxStore
}