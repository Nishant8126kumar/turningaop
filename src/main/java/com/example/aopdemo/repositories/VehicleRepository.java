package com.example.aopdemo.repositories;

import com.example.aopdemo.exceptions.DBException;
import com.example.aopdemo.models.Vehicle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class VehicleRepository {

    @Autowired
    private MongoCollection<Document> mongoCollection;

    @Autowired
    private Gson gson;
    @Autowired
    ObjectMapper objectMapper;

    public VehicleRepository() {

    }

    public Map<String, Object> createVehicle(Vehicle vehicle) {
        Map<String, Object> response = new HashMap<>();
        try {
            String jsonString = objectMapper.writeValueAsString(vehicle);//gson.toJson(vehicle);
            Document doc = Document.parse(jsonString);
            doc.put("_id", vehicle.getUuid());
            mongoCollection.insertOne(doc);
            response.put("msg", "vehicle created successfully");
            response.put("vehicleId", vehicle);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, Object> updateVehicle(Vehicle vehicle) {
        Map<String, Object> response = new HashMap<>();
        try {
            String jsonString = objectMapper.writeValueAsString(vehicle);
            Document doc = Document.parse(jsonString);
            doc.put("timeStamp", System.currentTimeMillis());
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.append("_id", vehicle.getUuid());
            BasicDBObject updateQuery = new BasicDBObject();
            updateQuery.append("$set", doc);
            UpdateResult updateResult = mongoCollection.updateOne(searchQuery, updateQuery);
            response.put("massage", "Vehicle updated successfully");
            response.put("data", updateResult);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException("Some Exception occurred while update vehicle");
        }
    }

    public Vehicle vehicleByVehicleId(String vehicleId) {
        try {
            BasicDBObject basicDBObject = new BasicDBObject();
            Vehicle vehicle;
            basicDBObject.append("_id", vehicleId);
            MongoCursor mongoCursor = mongoCollection.find(Filters.and(basicDBObject)).iterator();
            if (mongoCursor.hasNext()) {
                Document doc = (Document) mongoCursor.next();
                doc.remove("_id");
                String json = doc.toJson();
                vehicle = objectMapper.readValue(json, Vehicle.class);
                System.out.println(vehicle);
                return vehicle;
            } else {
                throw new DBException("no record found on this vehicleId=:" + vehicleId);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return null;
    }

    public Vehicle getVehicleByServKeyValue(String key, String value) throws JsonProcessingException {
        MongoCursor cursor = mongoCollection.find(Filters.eq(key, value)).limit(1).iterator();
        if (cursor.hasNext()) {
            Document document = (Document) cursor.next();
            document.remove("_id");
            String jsonString = document.toJson();
            Vehicle vehicle = objectMapper.readValue(jsonString, Vehicle.class);
            return vehicle;
        }
        return null;
    }
//    ORDER BY CREATEDTIME DESC
//    public static void main(String[] args) {
//        String str="hl1code";
//        String result = str.replaceAll("()([A-Z])", "$1_$2").toUpperCase();
//        System.out.println(result);
//    }
}