package com.juanvivas.roomwordsample.repository.persistence.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.juanvivas.roomwordsample.repository.persistence.database.entity.Word


@Dao
abstract class WordDao : BaseDao<Word> {
    @Query("DELETE FROM word")
    abstract fun deleteAll()

    @Query("SELECT * from word ORDER BY word ASC")
    abstract fun getAllWords(): LiveData<List<Word>>
}