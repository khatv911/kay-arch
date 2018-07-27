package kay.arch.objectbox.base

import io.objectbox.annotation.BaseEntity
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Created by Kay Tran on 19/7/18.
 * Profile: https://github.com/khatv911
 * Email: khatv911@gmail.com
 */
@Entity
abstract class BaseBox {

    @Id
    var id: Long=0L
    /**
     * Server sync status
     */
    var syncStatus: Int= SyncStatus.SYNC_SUCCEEDED
    /**
     * last sync utc timestamp
     */
    var lastSync: Long? =null

    /**
     * by who?
     */
    var lastUpdatedBy: String?=null

}