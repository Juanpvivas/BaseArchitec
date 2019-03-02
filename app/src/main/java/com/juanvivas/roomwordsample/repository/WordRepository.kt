package com.juanvivas.roomwordsample.repository

import android.app.Application
import com.juanvivas.roomwordsample.repository.persistence.database.entity.Word
import androidx.lifecycle.LiveData
import com.juanvivas.roomwordsample.repository.persistence.database.DatabaseConfig
import com.juanvivas.roomwordsample.repository.persistence.database.dao.WordDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class WordRepository(application: Application) {
    private var mWordDao: WordDao = DatabaseConfig.getInstance(application).wordDao()
    private var mAllWords: LiveData<List<Word>> = mWordDao.getAllWords()

    fun getAllWord(): LiveData<List<Word>> {
        return mAllWords
    }

    fun insertWord(word: Word) = GlobalScope.launch {
        mWordDao.insert(word)
    }
}