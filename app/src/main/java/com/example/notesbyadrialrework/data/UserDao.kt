package com.example.notesbyadrialrework.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.crocodic.core.data.CoreDao
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserDao: CoreDao<User> {

    @Query("DELETE FROM User")
    abstract suspend fun deleteAll()

    @Query("SELECT * FROM User WHERE idRoom = 1")
    abstract fun getUser(): LiveData<User>

    @Query("SELECT EXISTS(SELECT 1 FROM User WHERE idRoom = 1)")
    abstract suspend fun islogin(): Boolean
}