package com.example.thesisapp.data.disk.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.thesisapp.data.disk.model.RoomUser

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getUsers(): List<RoomUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUsers(users: Collection<RoomUser>)
}
