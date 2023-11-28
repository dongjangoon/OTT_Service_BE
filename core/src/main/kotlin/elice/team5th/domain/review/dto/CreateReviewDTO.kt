package elice.team5th.domain.review.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateReviewDTO(
    @JsonProperty("content_id")
    val contentId: Int,
    val contentType: String, // 필드 추가
    val detail: String,
    val rating: Int
)
