package com.beastmode.mappers;

import com.beastmode.models.Feedback;
import com.beastmode.models.User;
import org.springframework.stereotype.Service;

@Service
public class FeedbackMapper {
    public Feedback toFeedback(String feedbackId, User userId, String comments, int rating) {
        return Feedback.builder()
                .feedbackId(feedbackId)
                .user(userId)
                .comments(comments)
                .rating(rating)
                .build();
    }
}
