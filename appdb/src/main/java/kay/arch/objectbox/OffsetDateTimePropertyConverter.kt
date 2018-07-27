package kay.arch.objectbox

import io.objectbox.converter.PropertyConverter
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter


/**
 * Created by Kay Tran on 2/6/18.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
class OffsetDateTimePropertyConverter: PropertyConverter<OffsetDateTime?, String> {

    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    override fun convertToDatabaseValue(entityProperty: OffsetDateTime?): String {
        return entityProperty?.format(formatter) ?: "NULL"

    }

    override fun convertToEntityProperty(databaseValue: String?): OffsetDateTime? {
        return if("NULL" == databaseValue || databaseValue?.isEmpty() != false){
            null
        }else{
            formatter.parse(databaseValue, OffsetDateTime::from)
        }
    }
}