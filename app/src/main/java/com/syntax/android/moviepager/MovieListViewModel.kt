
package com.syntax.android.moviepager

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList

class MovieListViewModel(application: Application) : AndroidViewModel(application) {
  private val dao = MovieDatabase.get(application).movieDao()

  val allMovies = LivePagedListBuilder(dao.allMovies(), PagedList.Config.Builder()
      .setPageSize(PAGE_SIZE)
      .setEnablePlaceholders(ENABLE_PLACEHOLDERS)
      .build()).build()

  fun remove(movie: Movie) = bgThread {
    dao.delete(movie)
  }

  companion object {
    private const val PAGE_SIZE = 30
    private const val ENABLE_PLACEHOLDERS = true
  }
}