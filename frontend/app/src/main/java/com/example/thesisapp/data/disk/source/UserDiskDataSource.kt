package com.example.thesisapp.data.disk.source

import com.example.thesisapp.data.disk.dao.UserDao
import com.example.thesisapp.data.disk.model.RoomUser

class UserDiskDataSource(
    private val userDao: UserDao
) {

    fun getUsers() = userDao.getUsers()

    fun saveUsers(users: Collection<RoomUser>) {
        userDao.saveUsers(users)
    }
}
