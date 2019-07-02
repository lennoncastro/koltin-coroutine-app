package com.lennon.kotlincoroutine.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lennon.kotlincoroutine.R
import com.lennon.kotlincoroutine.data.model.vo.RepositorieVO

class RepositorieAdapter(
    private val repositories: List<RepositorieVO>
) :
    RecyclerView.Adapter<RepositorieAdapter.RepositorieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositorieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repositorie, parent, false)
        return RepositorieViewHolder(view)
    }

    override fun getItemCount() = repositories.size

    override fun onBindViewHolder(holder: RepositorieViewHolder, position: Int) {
        val repositorie = repositories[position]
        holder.bind(repositorie)
    }

    inner class RepositorieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var name: TextView = view.findViewById(R.id.name)
        var description: TextView = view.findViewById(R.id.description)
        var login: TextView = view.findViewById(R.id.description)
        var avatar: ImageView = view.findViewById(R.id.description)
        var forks: TextView = view.findViewById(R.id.forks)
        var stars: TextView = view.findViewById(R.id.stars)


        fun bind(repositorie: RepositorieVO) {
            name.text = repositorie.name
            description.text = repositorie.description
            login.text = repositorie.login
            forks.text = repositorie.forks.toString()
            stars.text = repositorie.stars.toString()
        }
    }
}