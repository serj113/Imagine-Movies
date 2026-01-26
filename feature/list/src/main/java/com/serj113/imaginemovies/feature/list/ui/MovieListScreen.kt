package com.serj113.imaginemovies.feature.list.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serj113.imaginemovies.feature.list.IMovieListViewModel
import com.serj113.imaginemovies.feature.list.data.MovieListAction
import com.serj113.imaginemovies.feature.list.data.MovieListViewState
import com.serj113.imaginemovies.lib.march.base.Spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(viewState: MovieListViewState, viewModel: IMovieListViewModel) {
    Scaffold(
        content = { paddingValues: PaddingValues ->
            val scrollState = rememberLazyGridState()
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = paddingValues,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                state = scrollState,
            ) {
                items(viewState.movies) { movie ->
                    MovieItem(viewState = movie)
                }

                if (viewState.isLoading) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(Spacing.x16),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .width(64.dp)
                                    .height(64.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                strokeWidth = 4.dp,
                            )
                        }
                    }
                }
            }

            LaunchedEffect(
                key1 = !scrollState.canScrollForward,
                block = {
                    if (!scrollState.canScrollForward) {
                        viewModel.onUiAction(MovieListAction.LoadMore)
                    }
                },
            )
        }
    )
}

@Preview
@Composable
fun PreviewMovieListScreen() {
    MovieListScreen(
        viewState = MovieListViewState(
            movies = listOf(
                MovieListViewState.MovieViewState(
                    title = "Movie 1",
                    rating = "10"
                ),
                MovieListViewState.MovieViewState(
                    title = "Movie 2",
                    rating = "10"
                )
            ),
        ),
        viewModel = object : IMovieListViewModel {
            override fun onUiAction(action: MovieListAction) {
                /* no-op */
            }
        }
    )
}
