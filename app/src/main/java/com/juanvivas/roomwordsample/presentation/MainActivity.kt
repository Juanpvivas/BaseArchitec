package com.juanvivas.roomwordsample.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import com.juanvivas.roomwordsample.R
import com.juanvivas.roomwordsample.presentation.word.WordListAdapter
import com.juanvivas.roomwordsample.presentation.word.WordViewModel
import com.juanvivas.roomwordsample.repository.persistence.database.entity.Word
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.juanvivas.roomwordsample.presentation.word.NewWordActivity
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.startActivityForResult


class MainActivity : AppCompatActivity(), LifecycleOwner {

    private lateinit var mWordViewModel: WordViewModel
    val NEW_WORD_ACTIVITY_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val adapter = WordListAdapter(this)
        rv_content.adapter = adapter
        rv_content.layoutManager =  LinearLayoutManager(this)

        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        mWordViewModel.getAllWords().observe(this, Observer<List<Word>> { words ->
            adapter.setWords(words)
        })

        fab.setOnClickListener {
            startActivityForResult<NewWordActivity>(NEW_WORD_ACTIVITY_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val word = Word(data!!.getStringExtra(NewWordActivity().EXTRA_REPLY))
            mWordViewModel.setWord(word)
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
