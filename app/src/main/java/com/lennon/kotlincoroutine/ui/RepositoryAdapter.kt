package com.lennon.kotlincoroutine.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lennon.kotlincoroutine.R
import com.lennon.kotlincoroutine.ui.vo.RepositoryVO
import com.squareup.picasso.Picasso

class RepositoryAdapter :
    RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    private var items: MutableList<RepositoryVO> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repositorie, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repository = items[position]
        holder.bind(repository)
    }

    fun updateList(repositories: List<RepositoryVO>) {
        items.addAll(repositories)
        notifyDataSetChanged()
    }

    inner class RepositoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var name: TextView = view.findViewById(R.id.name)
        var description: TextView = view.findViewById(R.id.description)
        var login: TextView = view.findViewById(R.id.description)
        var avatar: ImageView = view.findViewById(R.id.avatar)
        var forks: TextView = view.findViewById(R.id.forks)
        var stars: TextView = view.findViewById(R.id.stars)

        fun bind(repository: RepositoryVO) {
            name.text = repository.name
            description.text = repository.description
            login.text = repository.login
            forks.text = repository.forks.toString()
            stars.text = repository.stars.toString()
            Picasso.get().load(repository.avatar).into(avatar)
        }
    }
}
