package com.example.project.hotel.aiSystem;

import com.google.firebase.vertexai.FirebaseVertexAI;

public class FirebaseManager {
    public static FirebaseVertexAI getVertexAI() {
        return FirebaseVertexAI.getInstance();
    }
}