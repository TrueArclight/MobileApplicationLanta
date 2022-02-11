package com.lanta.lantamobile.repository

import com.lanta.lantamobile.model.RegisterDatabaseDao
import com.lanta.lantamobile.model.RegisterEntity

class RegisterRepository(private val dao: RegisterDatabaseDao) {

    val users = dao.getAllUsers()

    suspend fun insert(user: RegisterEntity) {
        return dao.insert(user)
    }

    suspend fun getUserName(userName: String):RegisterEntity?{
        return dao.getUsername(userName)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }
}