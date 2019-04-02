
package com.syntax.android.moviepager

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity() {

  private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
    ViewModelProviders.of(this).get(MovieListViewModel::class.java)
  }

  private val adapter = MovieListAdapter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_movie_list)

    movieRecyclerView.adapter = adapter

    viewModel.allMovies.observe(this, Observer(adapter::submitList))

    setupSwipeToDelete()
  }

  private fun setupSwipeToDelete() {
    ItemTouchHelper(object : ItemTouchHelper.Callback() {
      override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
          makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

      override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                          target: RecyclerView.ViewHolder): Boolean = false

      override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        (viewHolder as? MovieListAdapter.MovieViewHolder)?.movie?.let {
          viewModel.remove(it)
        }
      }
    }).attachToRecyclerView(movieRecyclerView)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    super.onCreateOptionsMenu(menu)
    menuInflater.inflate(R.menu.menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.goto_top -> scrollToTop()
      R.id.goto_bottom -> scrollToBottom()
    }
    return super.onOptionsItemSelected(item)
  }

  private fun scrollToTop() {
    movieRecyclerView.scrollToPosition(0)
  }

  private fun scrollToBottom() {
    movieRecyclerView.scrollToPosition(movieRecyclerView.adapter.itemCount - 1)
  }
}
