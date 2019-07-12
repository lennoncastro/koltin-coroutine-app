package com.lennon.kotlincoroutine.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lennon.kotlincoroutine.R
import com.lennon.kotlincoroutine.data.model.vo.RepositoryVO
import com.lennon.kotlincoroutine.viewmodel.RepositoryViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val repositoryViewModel: RepositoryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repositoryViewModel.fetchRepositories()
        onSuccessObserver()
    }

    private fun onSuccessObserver() {
        repositoryViewModel.response.observe(this, Observer {
            it?.let { repositoriesList ->
                setupRepositoriesList(repositoriesList)
            }
        })
    }

    private fun setupRepositoriesList(it: List<RepositoryVO>) {
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        repositories.layoutManager = layoutManager
        repositories.adapter = RepositoryAdapter(it)
        repositories.addItemDecoration(
            DividerItemDecoration(
                repositories.context,
                layoutManager.orientation
            )
        )
    }
}
