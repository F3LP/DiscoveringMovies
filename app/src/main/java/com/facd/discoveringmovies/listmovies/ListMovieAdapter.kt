package com.facd.discoveringmovies.listmovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.facd.discoveringmovies.R
import com.facd.discoveringmovies.domain.model.SearchMovieByTitleResponse
import kotlinx.android.synthetic.main.item_movie.view.*


class ListMovieAdapter(
    private var foundMovies: SearchMovieByTitleResponse,
    private val clickListener: OnItemClickListener
) : RecyclerView.Adapter<ListMovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)

        return MovieViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = foundMovies.results[position]
        val uri: String = movie.image

        Glide.with(holder.itemView.context).load(uri).into(holder.imageCover)
        holder.title.text = movie.title
        holder.description.text = movie.description
    }

    override fun getItemCount() = foundMovies.results.size


    inner class MovieViewHolder(itemView: View, listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val imageCover: ImageView = itemView.image_cover
        val title: TextView = itemView.textview_title
        val description: TextView = itemView.textview_description

        init {
            itemView.setOnClickListener {
                val foundMovies = foundMovies.results[adapterPosition]
                listener.onItemClick(foundMovies.id, foundMovies.title)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(id: String, title: String)
    }

}