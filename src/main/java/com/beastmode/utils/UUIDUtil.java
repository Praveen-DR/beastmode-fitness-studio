package com.beastmode.utils;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UUIDUtil {
    public String generateUuid(){
        return UUID.randomUUID().toString();
    }
}