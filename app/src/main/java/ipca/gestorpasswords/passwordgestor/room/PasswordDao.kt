package ipca.gestorpasswords.passwordgestor.room

import androidx.room.*

@Dao
interface PasswordDao {

    @Insert
    suspend fun addPassword(password : PasswordEntity)

    @Query("SELECT * FROM passwords ORDER BY id DESC")
    suspend fun getAllPasswords() : List<PasswordEntity>

    @Update
    suspend fun updatePassword(password: PasswordEntity)

    @Delete
    suspend fun deletePassword(password: PasswordEntity)

}
