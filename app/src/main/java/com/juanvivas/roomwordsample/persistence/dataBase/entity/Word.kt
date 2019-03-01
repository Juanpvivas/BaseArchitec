package com.juanvivas.roomwordsample.persistence.dataBase.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
class Word(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word") val mWord: String? = null
)