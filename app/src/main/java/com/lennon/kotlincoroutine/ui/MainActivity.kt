package com.lennon.kotlincoroutine.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lennon.kotlincoroutine.R
import com.lennon.kotlincoroutine.viewmodel.RepositoryViewModel
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {

    private val repositoryViewModel: RepositoryViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
