package com.beastmode.mappers;

import com.beastmode.dto.response.FeedBackResponseDto;
import com.beastmode.models.Feedback;
import com.beastmode.models.FeedbackType;
import com.beastmode.models.Trainer;
import com.beastmode.models.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FeedbackMapper {
    public Feedback toFeedback(String feedbackId, String content, Integer rating, FeedbackType feedbackType, User user, Trainer trainer, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return Feedback.builder()
                .feedbackId(feedbackId)
                .content(content)
                .rating(rating)
                .feedbackType(feedbackType)
                .user(user)
                .trainer(trainer)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

    public FeedBackResponseDto toFeedBackResponseDto(Feedback feedback){
        return new FeedBackResponseDto(feedback.getFeedbackId(), feedback.getContent(), feedback.getRating(), feedback.getFeedbackType(), feedback.getTrainer());
    }
}
