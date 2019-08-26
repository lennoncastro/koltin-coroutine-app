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

    private val viewModel: RepositoryViewModel by viewModel()

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
        viewModel.fetchRepositories()
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

    private fun showRepositoriesList() {
        fetch_repositories_btn.visibility = View.GONE
        repositories_rv.visibility = View.VISIBLE
    }

    private fun onSuccessObserver() {
        viewModel.onSuccess().observe(this, Observer {
            it?.let { repositoriesList ->
                adapter.updateList(repositoriesList)
                showRepositoriesList()
            }
        })
    }

    private fun onErrorObserver() {
        viewModel.onError().observe(this, Observer {
            if(it.showErrorMessage)
                Toast.makeText(this, it.throwable.message, Toast.LENGTH_LONG).show()
        })
    }

    private fun onShowLoadingObserver() {
        viewModel.showLoading().observe(this, Observer {
            it?.let { state ->
                val (isEnabled, message) = if(state) {
                    Pair(false, getString(R.string.loading))
                } else {
                    Pair(true, getString(R.string.fetch_repositories))
                }

                fetch_repositories_btn.isEnabled = isEnabled
                fetch_repositories_btn.text = message
            }
        })
    }
}
