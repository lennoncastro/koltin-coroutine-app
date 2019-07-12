package com.lennon.kotlincoroutine.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lennon.kotlincoroutine.R
import com.lennon.kotlincoroutine.viewmodel.RepositoryViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val repositoryViewModel: RepositoryViewModel by viewModel()

    private val adapter: RepositoryAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupFetchRepositoriesButton()
        setupRepositoriesList()
        onSuccessObserver()
        onErrorObserver()
        onShowLoadingObserver()
    }

    private fun setupFetchRepositoriesButton() {
        fetch_repositories_btn.setOnClickListener {
            fetchRepositories()
        }
    }

    private fun fetchRepositories() {
        repositoryViewModel.fetchRepositories()
    }

    private fun setupRepositoriesList() {
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        repositories_rv.layoutManager = layoutManager
        repositories_rv.adapter = adapter
        repositories_rv.addItemDecoration(
            DividerItemDecoration(
                repositories_rv.context,
                layoutManager.orientation
            )
        )
    }

    private fun onSuccessObserver() {
        repositoryViewModel.successResponse.observe(this, Observer {
            it?.let { repositoriesList ->
                adapter.updateList(repositoriesList)
                showRepositoriesList()
            }
        })
    }

    private fun showRepositoriesList() {
        fetch_repositories_btn.visibility = View.GONE
        repositories_rv.visibility = View.VISIBLE
    }

    private fun onErrorObserver() {
        repositoryViewModel.errorResponse.observe(this, Observer {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        })
    }

    private fun onShowLoadingObserver() {
        repositoryViewModel.showLoading.observe(this, Observer {
            it?.let { state ->
                val message = if(state) "show loading..." else "hide loading..."
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
