package com.beastmode.services.feedback_service;

import com.beastmode.repositories.FeedbackRepository;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService{
    private final FeedbackRepository feedbackRepository;

    FeedbackServiceImpl(FeedbackRepository feedbackRepository){
        this.feedbackRepository = feedbackRepository;
    }
}
