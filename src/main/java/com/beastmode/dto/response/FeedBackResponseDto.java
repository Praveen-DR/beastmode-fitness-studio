package com.beastmode.dto.response;

import com.beastmode.models.FeedbackType;
import com.beastmode.models.Trainer;
import com.beastmode.models.User;

public record FeedBackResponseDto(String feedbackId, String content, int rating, FeedbackType feedbackType, Trainer trainer) {
}
