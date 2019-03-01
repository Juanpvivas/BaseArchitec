package com.juanvivas.roomwordsample.persistence.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.juanvivas.roomwordsample.persistence.dataBase.dao.WordDao
import com.juanvivas.roomwordsample.persistence.dataBase.entity.Word

@Database(entities = [Word::class], version = 1)
abstract class WordRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}