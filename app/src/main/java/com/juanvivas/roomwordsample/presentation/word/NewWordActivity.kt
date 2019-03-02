package com.juanvivas.roomwordsample.presentation.word

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.content.Intent
import com.juanvivas.roomwordsample.R
import kotlinx.android.synthetic.main.activity_new_word.*


class NewWordActivity : AppCompatActivity() {

    val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        button_save.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(edit_word.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = edit_word.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
}
