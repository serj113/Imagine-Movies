package com.serj113.data.model

import com.serj113.domain.entity.Review
import com.squareup.moshi.Json

data class RemoteReview (
    @field:Json(name = "author")
    var author: String,
    @field:Json(name = "content")
    var content: String,
    @field:Json(name = "id")
    var id: String,
    @field:Json(name = "url")
    var url: String
)

fun RemoteReview.toReviewEntity() = Review(author, content, id, url)

fun List<RemoteReview>.toReviewEntities(): List<Review> = map { it.toReviewEntity() }