package com.juanvivas.roomwordsample.repository.persistence.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.juanvivas.roomwordsample.repository.persistence.database.dao.WordDao
import com.juanvivas.roomwordsample.repository.persistence.database.data.PopulateDB
import com.juanvivas.roomwordsample.repository.persistence.database.entity.Word

@Database(entities = [Word::class], version = 1)
abstract class DatabaseConfig : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {

        private const val nameDB = "DataBase"
        @Volatile
        private var INSTANCE: DatabaseConfig? = null

        fun getInstance(context: Context): DatabaseConfig =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                DatabaseConfig::class.java, nameDB
            )
                // prepopulate the database after onCreate was called
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // insert the data on the IO Thread
                        ioThread {
                            getInstance(context).wordDao().insert(PopulateDB().getWords())
                        }
                    }
                })
                .build()
    }
}