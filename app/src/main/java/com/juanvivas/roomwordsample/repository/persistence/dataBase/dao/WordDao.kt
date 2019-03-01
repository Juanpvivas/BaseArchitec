package com.juanvivas.roomwordsample.repository.persistence.dataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.juanvivas.roomwordsample.repository.persistence.dataBase.entity.Word


@Dao
abstract class WordDao : BaseDao<Word> {
    @Query("DELETE FROM word_table")
    abstract fun deleteAll()

    @Query("SELECT * from word_table ORDER BY word ASC")
    abstract fun getAllWords(): LiveData<List<Word>>
}