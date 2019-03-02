package com.juanvivas.roomwordsample.presentation.word

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.juanvivas.roomwordsample.R
import com.juanvivas.roomwordsample.repository.persistence.database.entity.Word


class WordListAdapter(context: Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private var mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mWords: List<Word> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = mInflater.inflate(com.juanvivas.roomwordsample.R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mWords.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = mWords[position]
        holder.wordItemView.text = current.mWord
    }

    fun setWords(words: List<Word>) {
        mWords = words
        notifyDataSetChanged()
    }

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var wordItemView: TextView = itemView.findViewById(R.id.textView)
    }
}