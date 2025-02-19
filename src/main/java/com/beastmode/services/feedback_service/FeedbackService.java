package com.beastmode.services.feedback_service;

import com.beastmode.models.Feedback;
import com.beastmode.models.FeedbackType;
import com.beastmode.models.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface FeedbackService {
    String createFeedback(String content, Integer rating, FeedbackType feedbackType, String userId, String trainerId, LocalDateTime createdAt, LocalDateTime updatedAt);
    String updateFeedback(String feedbackId, String content, Integer rating, FeedbackType feedbackType, String userId, String trainerId, LocalDateTime createdAt, LocalDateTime updatedAt);
    List<Feedback> getAllFeedback();
    Feedback getFeedbackById(String feedbackId);
    List<Feedback> getFeedbackByUser(String userId);
    List<Feedback> getFeedbackByTrainer(String trainerId);
    List<Feedback> getFeedbackByType(FeedbackType feedbackType);
    String deleteFeedback(String feedbackId);
    List<Feedback> getFeedbackByRatingRange(int min, int max);

}
