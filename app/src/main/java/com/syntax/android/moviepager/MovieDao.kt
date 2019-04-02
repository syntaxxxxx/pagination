
package com.syntax.android.moviepager

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface MovieDao {
  
  @Query("SELECT * FROM Movie ORDER BY ranking")
  fun allMovies(): DataSource.Factory<Int, Movie>

  @Insert
  fun insert(movies: List<Movie>)

  @Insert
  fun insert(movie: Movie)

  @Delete
  fun delete(movie: Movie)
}