package com.plantwatering;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBService {
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "PlantWateringDB";
    private static final String COLLECTION_NAME = "WateringLogs";

    private final MongoDatabase database;

    public MongoDBService() {
        MongoClient mongoClient = MongoClients.create(CONNECTION_STRING);
        this.database = mongoClient.getDatabase(DATABASE_NAME);
    }

    public void logWatering(String dateTime, boolean watered) {
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
        Document log = new Document("dateTime", dateTime)
                .append("watered", watered);
        collection.insertOne(log);
    }
}
