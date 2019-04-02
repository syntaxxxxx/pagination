
package com.syntax.android.moviepager

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_movie.*


class MovieListAdapter : PagedListAdapter<Movie, MovieListAdapter.MovieViewHolder>(diffCallback) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
      MovieViewHolder(parent.inflate(R.layout.list_item_movie))

  override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  companion object {

    private val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
      override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
          oldItem.id == newItem.id
      override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
          oldItem == newItem
    }
  }

  class MovieViewHolder(override val containerView: View)
    : RecyclerView.ViewHolder(containerView), LayoutContainer {

    var movie: Movie? = null

    fun bind(movie: Movie?) {
      this.movie = movie
      title.text = movie?.title
      releaseDate.text = movie?.releaseDate?.substring(0, 4)
      rating.text = movie?.rating.toString()
    }
  }
}