package com.juanvivas.roomwordsample.presentation.word

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.juanvivas.roomwordsample.repository.WordRepository
import com.juanvivas.roomwordsample.repository.persistence.database.entity.Word

class WordViewModel(application: Application) : AndroidViewModel(application) {
    private var mRepository: WordRepository = WordRepository(application)
    private var mAllWords: LiveData<List<Word>> = mRepository.getAllWord()!!

    fun getAllWords(): LiveData<List<Word>> = mAllWords
    fun setWord(word: Word) {
        mRepository.insertWord(word)
    }
}