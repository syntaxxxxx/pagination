

package com.syntax.android.moviepager

import java.util.concurrent.Executors

private val BG_EXECUTOR = Executors.newSingleThreadExecutor()

fun bgThread(f : () -> Unit) {
  BG_EXECUTOR.execute(f)
}