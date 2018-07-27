package kay.arch.objectbox.base

/**
 * Created by Kay Tran on 19/7/18.
 * Profile: https://github.com/khatv911
 * Email: khatv911@gmail.com
 */
class SyncStatus {
    companion object {
        const val SYNC_SUCCEEDED = 0x001
        const val SYNC_PENDING = SYNC_SUCCEEDED shl 1
        const val SYNC_FAILED = SYNC_PENDING shl 1
    }
}