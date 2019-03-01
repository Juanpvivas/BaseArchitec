package com.juanvivas.roomwordsample.repository

import android.app.Application
import com.juanvivas.roomwordsample.repository.persistence.dataBase.entity.Word
import androidx.lifecycle.LiveData
import com.juanvivas.roomwordsample.repository.persistence.dataBase.DatabaseConfig
import com.juanvivas.roomwordsample.repository.persistence.dataBase.dao.WordDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class WordRepository(application: Application) {
    private var mWordDao: WordDao? = null
    private var mAllWords: LiveData<List<Word>>? = null

    init {
        val db = DatabaseConfig.getInstance(application)
        mWordDao = db.wordDao()
        mAllWords = mWordDao!!.getAllWords()
    }

    fun getAllWord(): LiveData<List<Word>>? {
        return mAllWords
    }

    fun inserWord(word: Word) = GlobalScope.launch {
        mWordDao!!.insert(word)
    }
}