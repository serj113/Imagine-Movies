package com.serj113.imaginemovies.feature.detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.serj113.imaginemovies.feature.detail.IMovieDetailViewModel
import com.serj113.imaginemovies.feature.detail.R
import com.serj113.imaginemovies.feature.detail.data.MovieDetailAction
import com.serj113.imaginemovies.feature.detail.data.MovieDetailViewState
import com.serj113.imaginemovies.lib.march.base.Spacing.x16
import com.serj113.imaginemovies.lib.march.base.Spacing.x24
import com.serj113.imaginemovies.lib.march.base.Spacing.x32
import com.serj113.imaginemovies.lib.march.base.Spacing.x4
import com.serj113.imaginemovies.lib.march.base.Spacing.x48
import com.serj113.imaginemovies.lib.march.base.Spacing.x8
import com.serj113.imaginemovies.lib.march.ext.size16
import com.serj113.imaginemovies.lib.march.ext.size32

private const val TMDB_IMAGE_URL = "https://image.tmdb.org/t/p/original"
private const val BACKDROP_HEIGHT = 420
private const val CARD_HEIGHT = 80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    viewState: MovieDetailViewState,
    viewModel: IMovieDetailViewModel,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            MovieDetailContent(
                viewState = viewState,
                onBackClick = { viewModel.onUiAction(MovieDetailAction.OnBackPressed) },
                onMovieClick = { /* Handle movie click if needed */ }
            )
        }
    }
}

@Composable
private fun MovieDetailContent(
    viewState: MovieDetailViewState,
    onBackClick: () -> Unit,
    onMovieClick: (MovieDetailViewState.Movie) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Backdrop Image
        BackdropImageSection(
            backdropUrl = viewState.backdropUrl,
            onBackClick = onBackClick
        )

        Spacer(modifier = Modifier.height(x8))

        // Synopsis Section
        SynopsisCard(synopsis = viewState.synopsis)

        Spacer(modifier = Modifier.height(x8))

        // Rating Section
        RatingCard(
            voteAverage = viewState.voteAverage,
            voteCount = viewState.voteCount
        )

        Spacer(modifier = Modifier.height(x8))

        // Details Section
        DetailsCard(
            originalTitle = viewState.originalTitle,
            releaseDate = viewState.releaseDate,
            status = viewState.status,
            budget = viewState.budget,
            revenue = viewState.revenue
        )

        Spacer(modifier = Modifier.height(x8))

        // Casts Section
        if (viewState.casts.isNotEmpty()) {
            CastsSection(casts = viewState.casts)
        }

        Spacer(modifier = Modifier.height(x8))

        // Reviews Section
        if (viewState.reviews.isNotEmpty()) {
            ReviewsSection(reviews = viewState.reviews)
        }

        Spacer(modifier = Modifier.height(x8))

        // Recommendations Section
        if (viewState.recommendations.isNotEmpty()) {
            RecommendationsSection(
                movies = viewState.recommendations,
                onMovieClick = onMovieClick
            )
        }

        Spacer(modifier = Modifier.height(x8))

        // Similar Movies Section
        if (viewState.similar.isNotEmpty()) {
            SimilarSection(
                movies = viewState.similar,
                onMovieClick = onMovieClick
            )
        }

        Spacer(modifier = Modifier.height(x16))
    }
}

@Composable
private fun BackdropImageSection(
    backdropUrl: String,
    onBackClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(backdropUrl)
                .crossfade(true)
                .build(),
            contentDescription = "Movie Backdrop",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(BACKDROP_HEIGHT.dp)
        )

        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .padding(x24)
                .size32()
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black
            )
        }

        // Bottom rounded corner decoration
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(x24)
                .background(
                    Color.White,
                    RoundedCornerShape(topStart = x16, topEnd = x16)
                )
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(x32)
                    .height(x4)
                    .background(Color.Gray, RoundedCornerShape(2.dp))
            )
        }
    }
}

@Composable
private fun SynopsisCard(synopsis: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = x8),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(x16)) {
            Text(
                text = stringResource(R.string.info_synopsis),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(x8))
            Text(
                text = synopsis,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun RatingCard(voteAverage: String, voteCount: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = x8),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(x16),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = stringResource(R.string.info_avg_rating),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = x8)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        tint = Color(0xFFFFD700),
                        modifier = Modifier.size(x48)
                    )
                    Text(
                        text = voteAverage,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "/10",
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = x4)
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Votes",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size16()
                    )
                    Text(
                        text = voteCount,
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Composable
private fun DetailsCard(
    originalTitle: String,
    releaseDate: String,
    status: String,
    budget: String,
    revenue: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = x8),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(x16)) {
            Text(
                text = stringResource(R.string.info_details),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(x8))

            DetailRow(
                label = stringResource(R.string.info_original_title),
                value = originalTitle
            )
            DetailRow(
                label = stringResource(R.string.info_release_date),
                value = releaseDate
            )
            DetailRow(
                label = stringResource(R.string.info_status),
                value = status
            )
            DetailRow(
                label = stringResource(R.string.info_budget),
                value = budget
            )
            DetailRow(
                label = stringResource(R.string.info_revenue),
                value = revenue
            )
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = x4),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun CastsSection(casts: List<MovieDetailViewState.Cast>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = x8)
    ) {
        Text(
            text = stringResource(R.string.info_casts),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(x4))

        LazyHorizontalGrid(
            rows = GridCells.Fixed(1),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = x8),
            horizontalArrangement = Arrangement.spacedBy(x8)
        ) {
            items(casts) { cast ->
                CastItem(cast = cast)
            }
        }
    }
}

@Composable
private fun CastItem(cast: MovieDetailViewState.Cast) {
    Card(
        modifier = Modifier.width(CARD_HEIGHT.dp),
        shape = RoundedCornerShape(x8)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(x8)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(if (cast.profilePath.isNotEmpty()) TMDB_IMAGE_URL + cast.profilePath else null)
                    .crossfade(true)
                    .build(),
                contentDescription = cast.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(x8))
            )
            Spacer(modifier = Modifier.height(x4))
            Text(
                text = cast.name,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun ReviewsSection(reviews: List<MovieDetailViewState.Review>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = x8)
    ) {
        Text(
            text = stringResource(R.string.info_reviews),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(x4))

        Column {
            reviews.take(4).forEach { review ->
                ReviewItem(review = review)
                Spacer(modifier = Modifier.height(x8))
            }
        }
    }
}

@Composable
private fun ReviewItem(review: MovieDetailViewState.Review) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(x16)) {
            Text(
                text = review.author,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(x4))
            Text(
                text = review.content,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 3
            )
        }
    }
}

@Composable
private fun RecommendationsSection(
    movies: List<MovieDetailViewState.Movie>,
    onMovieClick: (MovieDetailViewState.Movie) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = x8)
    ) {
        Text(
            text = stringResource(R.string.info_movie_recommendations),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(x4))

        LazyHorizontalGrid(
            rows = GridCells.Fixed(1),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = x8),
            horizontalArrangement = Arrangement.spacedBy(x8)
        ) {
            items(movies) { movie ->
                MovieItem(
                    movie = movie,
                    onClick = { onMovieClick(movie) }
                )
            }
        }
    }
}

@Composable
private fun SimilarSection(
    movies: List<MovieDetailViewState.Movie>,
    onMovieClick: (MovieDetailViewState.Movie) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = x8)
    ) {
        Text(
            text = stringResource(R.string.info_movie_similar),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(x4))

        LazyHorizontalGrid(
            rows = GridCells.Fixed(1),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = x8),
            horizontalArrangement = Arrangement.spacedBy(x8)
        ) {
            items(movies) { movie ->
                MovieItem(
                    movie = movie,
                    onClick = { onMovieClick(movie) }
                )
            }
        }
    }
}

@Composable
private fun MovieItem(
    movie: MovieDetailViewState.Movie,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(x8)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.posterUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(x8))
            )
            Spacer(modifier = Modifier.height(x4))
            Text(
                text = movie.title,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                modifier = Modifier.padding(x4)
            )
            Text(
                text = movie.rating,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(horizontal = x4)
            )
        }
    }
}

// ==================== PREVIEW FUNCTIONS ====================

private class MockViewModel : IMovieDetailViewModel {
    override fun onUiAction(action: MovieDetailAction) {}
}

@Preview(
    name = "Full Data",
    showBackground = true,
    widthDp = 360,
    heightDp = 800,
    backgroundColor = 0xFFFFFF
)
@Composable
private fun MovieDetailScreenPreview_FullData() {
    MaterialTheme {
        MovieDetailScreen(
            viewState = MovieDetailViewState(
                backdropUrl = "https://image.tmdb.org/t/p/original/nNmJRsrc8k7bQzI0jYWbmAmj6y9J.jpg",
                synopsis = "Pool mechanic Wade Wilson is diagnosed with terminal cancer. He turns to a shady recruiter for a dangerous experimental procedure that transforms him into Deadpool, a morally flexible anti-hero with incredible healing powers. As he hunts down the man responsible for nearly destroying his life, Wade assembles a team of mutants to fight against the villainous Cable and save a young fire-wielding mutant named Russell.",
                voteAverage = "8.2",
                voteCount = "9,314",
                originalTitle = "Deadpool 2",
                releaseDate = "May 18, 2018",
                status = "Released",
                budget = "$110,000,000",
                revenue = "$786,000,000",
                casts = listOf(
                    MovieDetailViewState.Cast(
                        name = "Ryan Reynolds",
                        character = "Wade Wilson / Deadpool",
                        profilePath = "/k2cIh5t0g0Z6p9Y8gX1fL3gC4mV.jpg"
                    ),
                    MovieDetailViewState.Cast(
                        name = "Josh Brolin",
                        character = "Cable",
                        profilePath = "/b2f0Q2IT6s1PgIb4X6Y8gH1l0kC.jpg"
                    ),
                    MovieDetailViewState.Cast(
                        name = "Morena Baccarin",
                        character = "Vanessa",
                        profilePath = "/r5j3d4k1l2m6n8o0p1q2r4s6t8u0v2w.jpg"
                    ),
                    MovieDetailViewState.Cast(
                        name = "Julian Dennison",
                        character = "Russell",
                        profilePath = "/t1x2y3z4a5b6c7d8e9f0g1h2i3j4k5l.jpg"
                    )
                ),
                reviews = listOf(
                    MovieDetailViewState.Review(
                        author = "John Doe",
                        content = "An absolute masterpiece of comedy and action. Ryan Reynolds delivers the perfect performance once again. The fourth-wall breaking humor is on point and the action sequences are spectacular.",
                        reviewUrl = "https://example.com/review/1"
                    ),
                    MovieDetailViewState.Review(
                        author = "Jane Smith",
                        content = "Better than the first one! The story is more emotional and the introduction of Cable was brilliant. Can't wait for the next installment.",
                        reviewUrl = "https://example.com/review/2"
                    ),
                    MovieDetailViewState.Review(
                        author = "Movie Buff",
                        content = "Great pacing, excellent humor, and amazing visual effects. This movie knows exactly what it wants to be and delivers on every front.",
                        reviewUrl = "https://example.com/review/3"
                    ),
                    MovieDetailViewState.Review(
                        author = "Peter Parker",
                        content = "The chemistry between the characters is fantastic. The X-Force scene alone is worth the price of admission.",
                        reviewUrl = "https://example.com/review/4"
                    )
                ),
                recommendations = listOf(
                    MovieDetailViewState.Movie(
                        title = "Logan",
                        rating = "8.1",
                        posterUrl = "https://image.tmdb.org/t/p/original/tbA4UXx2gVHd1gVAKjI2H6g2.jpg"
                    ),
                    MovieDetailViewState.Movie(
                        title = "The Wolverine",
                        rating = "6.7",
                        posterUrl = "https://image.tmdb.org/t/p/original/kj3d4l5m6n7o8p9q0r1s2t3u4v5w6x.jpg"
                    ),
                    MovieDetailViewState.Movie(
                        title = "X-Men: Days of Future Past",
                        rating = "7.9",
                        posterUrl = "https://image.tmdb.org/t/p/original/p1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p.jpg"
                    )
                ),
                similar = listOf(
                    MovieDetailViewState.Movie(
                        title = "The Suicide Squad",
                        rating = "7.3",
                        posterUrl = "https://image.tmdb.org/t/p/original/q1w2e3r4t5y6u7i8o9p0a1s2d3f4g5h.jpg"
                    ),
                    MovieDetailViewState.Movie(
                        title = "Venom",
                        rating = "6.8",
                        posterUrl = "https://image.tmdb.org/t/p/original/z1x2c3v4b5n6m7q8w9e0r1t2y3u4i5o.jpg"
                    ),
                    MovieDetailViewState.Movie(
                        title = "Guardians of the Galaxy Vol. 2",
                        rating = "7.7",
                        posterUrl = "https://image.tmdb.org/t/p/original/p1a2s3d4f5g6h7j8k9l0q1w2e3r4t5y.jpg"
                    )
                )
            ),
            viewModel = MockViewModel()
        )
    }
}

@Preview(
    name = "Empty State",
    showBackground = true,
    widthDp = 360,
    heightDp = 800,
    backgroundColor = 0xFFFFFF
)
@Composable
private fun MovieDetailScreenPreview_EmptyState() {
    MaterialTheme {
        MovieDetailScreen(
            viewState = MovieDetailViewState(
                synopsis = "Loading...",
                voteCount = "0"
            ),
            viewModel = MockViewModel()
        )
    }
}

@Preview(
    name = "No Casts Or Reviews",
    showBackground = true,
    widthDp = 360,
    heightDp = 800,
    backgroundColor = 0xFFFFFF
)
@Composable
private fun MovieDetailScreenPreview_NoCastsOrReviews() {
    MaterialTheme {
        MovieDetailScreen(
            viewState = MovieDetailViewState(
                backdropUrl = "https://image.tmdb.org/t/p/original/nNmJRsrc8k7bQzI0jYWbmAmj6y9J.jpg",
                synopsis = "A young boy finds a magical amulet and embarks on an epic adventure to save the world from darkness.",
                voteAverage = "7.5",
                voteCount = "4,521",
                originalTitle = "Amulet Quest",
                releaseDate = "March 15, 2024",
                status = "Released",
                budget = "$50,000,000",
                revenue = "$150,000,000",
                recommendations = listOf(
                    MovieDetailViewState.Movie(
                        title = "The Dragon's Son",
                        rating = "7.2",
                        posterUrl = "https://image.tmdb.org/t/p/original/abc123xyz.jpg"
                    ),
                    MovieDetailViewState.Movie(
                        title = "Shadow Kingdom",
                        rating = "6.9",
                        posterUrl = "https://image.tmdb.org/t/p/original/def456uvw.jpg"
                    )
                ),
                similar = listOf(
                    MovieDetailViewState.Movie(
                        title = "Magic and Mystery",
                        rating = "7.0",
                        posterUrl = "https://image.tmdb.org/t/p/original/ghi789rst.jpg"
                    )
                )
            ),
            viewModel = MockViewModel()
        )
    }
}

@Preview(
    name = "Long Synopsis",
    showBackground = true,
    widthDp = 360,
    heightDp = 800,
    backgroundColor = 0xFFFFFF
)
@Composable
private fun MovieDetailScreenPreview_LongSynopsis() {
    MaterialTheme {
        MovieDetailScreen(
            viewState = MovieDetailViewState(
                backdropUrl = "https://image.tmdb.org/t/p/original/nNmJRsrc8k7bQzI0jYWbmAmj6y9J.jpg",
                synopsis = "In a world where humanity has colonized the solar system, a detective and a ship's captain come together to investigate a young woman's disappearance that exposes the greatest conspiracy in human history. The investigation leads them through the dangerous political landscape of the Martian Congress, the industrial wasteland of asteroid mining colonies, and the luxurious living habitats of the Belt. As they dig deeper, they uncover secrets that threaten to destabilize the fragile peace between Earth, Mars, and the Belt, potentially plunging humanity back into war. With powerful forces working against them, they must race against time to expose the truth before it's too late.",
                voteAverage = "8.5",
                voteCount = "12,000",
                originalTitle = "The Expanse",
                releaseDate = "December 1, 2015",
                status = "Released",
                budget = "$200,000,000",
                revenue = "$500,000,000",
                casts = listOf(
                    MovieDetailViewState.Cast(
                        name = "Steven Strait",
                        character = "Jim Holden",
                        profilePath = "/a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p.jpg"
                    ),
                    MovieDetailViewState.Cast(
                        name = "Dominique Tipper",
                        character = "Naomi Nagata",
                        profilePath = "/q1w2e3r4t5y6u7i8o9p0a1s2d3f4g5h.jpg"
                    ),
                    MovieDetailViewState.Cast(
                        name = "Thomas Jane",
                        character = "Joe Miller",
                        profilePath = "/z1x2c3v4b5n6m7q8w9e0r1t2y3u4i5o.jpg"
                    )
                ),
                reviews = listOf(
                    MovieDetailViewState.Review(
                        author = "Space Enthusiast",
                        content = "This show sets a new standard for science fiction. The attention to detail and the realistic depiction of space travel is unmatched.",
                        reviewUrl = ""
                    )
                )
            ),
            viewModel = MockViewModel()
        )
    }
}

@Preview(
    name = "Large Reviews List",
    showBackground = true,
    widthDp = 360,
    heightDp = 800,
    backgroundColor = 0xFFFFFF
)
@Composable
private fun MovieDetailScreenPreview_LargeReviewsList() {
    MaterialTheme {
        MovieDetailScreen(
            viewState = MovieDetailViewState(
                backdropUrl = "https://image.tmdb.org/t/p/original/nNmJRsrc8k7bQzI0jYWbmAmj6y9J.jpg",
                synopsis = "An epic tale of adventure and discovery.",
                voteAverage = "7.8",
                voteCount = "8,500",
                originalTitle = "Adventure Beyond",
                releaseDate = "June 20, 2023",
                status = "Released",
                budget = "$75,000,000",
                revenue = "$300,000,000",
                reviews = listOf(
                    MovieDetailViewState.Review(
                        author = "Movie Critic 1",
                        content = "Excellent film with great pacing and character development. The director has outdone themselves with this masterpiece.",
                        reviewUrl = ""
                    ),
                    MovieDetailViewState.Review(
                        author = "Film Fanatic 2",
                        content = "Visually stunning and emotionally resonant. This is the kind of movie that stays with you long after the credits roll.",
                        reviewUrl = ""
                    ),
                    MovieDetailViewState.Review(
                        author = "Cinema Lover 3",
                        content = "A triumph of storytelling and visual artistry. Every frame is a painting and every scene flows perfectly into the next.",
                        reviewUrl = ""
                    ),
                    MovieDetailViewState.Review(
                        author = "Reviewer 4",
                        content = "Outstanding performances from the entire cast. The chemistry between the leads is palpable and authentic.",
                        reviewUrl = ""
                    ),
                    MovieDetailViewState.Review(
                        author = "Critic 5",
                        content = "Beautiful cinematography and a compelling narrative. This is a must-watch for any film enthusiast.",
                        reviewUrl = ""
                    ),
                    MovieDetailViewState.Review(
                        author = "Analyst 6",
                        content = "A fresh take on a classic genre, bringing new life and energy to familiar tropes through innovative direction.",
                        reviewUrl = ""
                    )
                ),
                recommendations = listOf(
                    MovieDetailViewState.Movie(
                        title = "Journey Beyond",
                        rating = "7.6",
                        posterUrl = "https://image.tmdb.org/t/p/original/mock1.jpg"
                    )
                )
            ),
            viewModel = MockViewModel()
        )
    }
}

@Preview(
    name = "No Recommendations Or Similar",
    showBackground = true,
    widthDp = 360,
    heightDp = 800,
    backgroundColor = 0xFFFFFF
)
@Composable
private fun MovieDetailScreenPreview_NoRecommendationsOrSimilar() {
    MaterialTheme {
        MovieDetailScreen(
            viewState = MovieDetailViewState(
                backdropUrl = "https://image.tmdb.org/t/p/original/nNmJRsrc8k7bQzI0jYWbmAmj6y9J.jpg",
                synopsis = "A documentary about the making of an independent film that changed the industry forever.",
                voteAverage = "8.8",
                voteCount = "15,200",
                originalTitle = "Behind the Scenes",
                releaseDate = "October 10, 2023",
                status = "Released",
                budget = "$5,000,000",
                revenue = "$50,000,000",
                casts = listOf(
                    MovieDetailViewState.Cast(
                        name = "Director Name",
                        character = "Himself",
                        profilePath = ""
                    ),
                    MovieDetailViewState.Cast(
                        name = "Producer Name",
                        character = "Herself",
                        profilePath = ""
                    )
                ),
                reviews = listOf(
                    MovieDetailViewState.Review(
                        author = "DocuViewer",
                        content = "A fascinating look at the creative process. Every filmmaker should watch this.",
                        reviewUrl = ""
                    )
                )
            ),
            viewModel = MockViewModel()
        )
    }
}