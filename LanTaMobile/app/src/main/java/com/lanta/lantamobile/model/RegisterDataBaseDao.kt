package com.lanta.lantamobile.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
interface RegisterDatabaseDao {

    @Insert
    suspend fun insert(register: RegisterEntity)

    @Query("SELECT * FROM Register_users_table ORDER BY userId DESC")
    fun getAllUsers(): LiveData<List<RegisterEntity>>

    @Query("DELETE FROM Register_users_table")
    suspend fun deleteAll(): Int

    @Query("SELECT * FROM Register_users_table WHERE phone_number LIKE :phone_number")
    suspend fun getUsername(phone_number: String): RegisterEntity?
    @Query("SELECT * FROM Register_users_table WHERE first_name LIKE :text")
    suspend fun getFirstName(text: String): RegisterEntity?
    @Query("SELECT * FROM Register_users_table WHERE last_name LIKE :text")
    suspend fun getLastName(text: String): RegisterEntity?
}