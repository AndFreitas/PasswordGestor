package ipca.gestorpasswords.passwordgestor.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Passwords")
data class PasswordEntity(
    var password: String,
    var site: String,
    var descricao: String

):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}
