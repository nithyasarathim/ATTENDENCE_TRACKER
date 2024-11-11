package com.example;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class AttendanceTracker {
    public static void main(String[] args) {
        // Create a MongoDB client
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/");
        
        // Get the database
        MongoDatabase database = mongoClient.getDatabase("attendanceDB");
        
        // Get the collection
        MongoCollection<Document> collection = database.getCollection("attendance");

        // Test Case 1: Record attendance for a student
        Document attendance1 = new Document("date", "2024-11-11")
            .append("studentId", "S001")
            .append("status", "Present");

        Document attendance2 = new Document("date", "2024-11-11")
            .append("studentId", "S002")
            .append("status", "Absent");

        collection.insertOne(attendance1);
        collection.insertOne(attendance2);

        System.out.println("Attendance records inserted successfully!");

        // Test Case 2: Retrieve attendance records for a specific date
        String queryDate = "2024-11-11";
        System.out.println("\nAttendance records for date: " + queryDate);
        FindIterable<Document> documents = collection.find(Filters.eq("date", queryDate));
        
        for (Document doc : documents) {
            System.out.println(doc.toJson());
        }

        // Close the connection
        mongoClient.close();
    }
}