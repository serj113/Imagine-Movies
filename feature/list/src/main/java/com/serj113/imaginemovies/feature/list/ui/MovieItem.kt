package com.serj113.imaginemovies.feature.list.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.serj113.imaginemovies.feature.list.data.MovieListViewState
import com.serj113.imaginemovies.lib.march.base.Spacing

@Composable
fun MovieItem(viewState: MovieListViewState.MovieViewState) {
    Card(
        content = {
            AsyncImage(
                model = viewState.posterUrl,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(Spacing.x8))
            Text(
                text = viewState.title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .padding(horizontal = Spacing.x8)
            )
            Spacer(modifier = Modifier.height(Spacing.x8))
            Text(
                text = viewState.rating,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(horizontal = Spacing.x8)
            )
        }
    )
}