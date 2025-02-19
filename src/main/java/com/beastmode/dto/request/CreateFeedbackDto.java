package com.beastmode.dto.request;

import com.beastmode.models.FeedbackType;

import java.time.LocalDateTime;

public record CreateFeedbackDto(String content, Integer rating, FeedbackType feedbackType, String userId, String trainerId, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
