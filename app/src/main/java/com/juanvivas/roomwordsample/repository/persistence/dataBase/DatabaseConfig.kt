package com.juanvivas.roomwordsample.repository.persistence.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.juanvivas.roomwordsample.repository.persistence.dataBase.dao.WordDao
import com.juanvivas.roomwordsample.repository.persistence.dataBase.entity.Word
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [Word::class], version = 1)
abstract class DatabaseConfig : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {

        private const val nameDB = "APSoft"
        @Volatile
        private var INSTANCE: DatabaseConfig? = null

        fun getInstance(context: Context): DatabaseConfig =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                DatabaseConfig::class.java, nameDB)
                // prepopulate the database after onCreate was called
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // insert the data on the IO Thread
                        GlobalScope.launch {
                            //getInstance(context).countryDao().insertList(DataConfig().countries())
                        }
                    }
                })
                .build()
    }
}