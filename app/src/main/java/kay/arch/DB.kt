package kay.arch

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import kay.arch.objectbox.base.BaseBox


/**
 * Created by Kay Tran on 28/7/18.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
@Entity
class DB: BaseBox() {
    @Id
    var id: Long=0L

}