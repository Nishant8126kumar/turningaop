package com.example.aopdemo.repositories;

import com.common.models.Vehicle;
import com.example.aopdemo.exceptions.DBException;
import com.example.aopdemo.exceptions.MongoDbException;
import com.example.aopdemo.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Repository
public class VehicleRepository {

//    @Autowired(required = false)
    MongoCollection<Document> mongoCollection;

    @Autowired
    private Gson gson;

    @Autowired
    ObjectMapper objectMapper;

    public VehicleRepository(MongoDatabase mongoDatabase) {
        mongoCollection = mongoDatabase.getCollection("vehicle");
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        Map<String, Object> response = new HashMap<>();
        try {
//            String jsonString = gson.toJson(vehicle);
            Document doc = Document.parse(vehicle.toString());
            doc.put("_id", vehicle.getUuid());
            mongoCollection.insertOne(doc);
            response.put("msg", "vehicle created successfully");
            response.put("vehicleId", "vehicle");
            return vehicle;
        } catch (Exception e) {
            System.out.print("Exception Occurred :"+e.getMessage());
        }
        return null;
    }

    public Map<String, Object> updateVehicle(Vehicle vehicle) {
        Map<String, Object> response = new HashMap<>();
        try {
            String jsonString = objectMapper.writeValueAsString(vehicle);
            Document doc = Document.parse(jsonString);
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.append("_id", vehicle.getUuid());
            BasicDBObject updateQuery = new BasicDBObject();
            updateQuery.append("$set", doc);
            UpdateResult updateResult = mongoCollection.updateOne(searchQuery, updateQuery);
            response.put("massage", "Vehicle updated successfully");
            response.put("data", updateResult);
            response.put("status", 200);
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
            MongoCursor<Document> mongoCursor = mongoCollection.find(Filters.and(basicDBObject)).iterator();
            if (mongoCursor.hasNext()) {
                Document doc = mongoCursor.next();
                doc.remove("_id");
                String json = objectMapper.writeValueAsString(doc);
                System.out.println("Here :"+json);
                vehicle = objectMapper.readValue(json, Vehicle.class);
                System.out.println("Now :"+vehicle);
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
        MongoCursor<Document> cursor = mongoCollection.find(Filters.eq(key, value)).limit(1).iterator();
        if (cursor.hasNext()) {
            Document document = cursor.next();
            document.remove("_id");
            document.remove("creationTime");
            document.remove("updateTime");
            String jsonString = document.toJson();
            Vehicle vehicle = objectMapper.readValue(jsonString, Vehicle.class);
            return vehicle;
        }
        return null;
    }

    public JSONObject getVehicleByProjection(String key, String value, List<String> projectionFiels) {
        try {
            BasicDBObject requiredFields = new BasicDBObject();
            for (String projection : projectionFiels) {
                requiredFields.put(projection, 1);
            }
            MongoCursor<Document> cursor = mongoCollection.find(Filters.eq(key, value)).projection(requiredFields).limit(1).iterator();
            if (cursor.hasNext()) {
                Document doc = cursor.next();
                doc.remove("_id");
                doc.remove("timeStamp");
                String jsonString = doc.toJson();
                return new JSONObject(jsonString);
            }
        } catch (JSONException e) {
            throw new MongoDbException("Exception occurred while fetch data on " + key + ":" + value + "");
        }
        throw new ResourceNotFoundException("Resource not found");
    }

    public List<Vehicle> getVehicles(int offset, int limit, String sortBy, String sortIn, List<String> includeFields) throws JsonProcessingException {
        List<Vehicle> vehicleList = new ArrayList<>();
        BasicDBObject basicDBObject = new BasicDBObject();
        BasicDBObject fields = new BasicDBObject();
        for (String value : includeFields) {
            basicDBObject.put(value, 1);
        }
        basicDBObject.put(sortBy, sortIn.equalsIgnoreCase("ASC") ? 1 : -1);
        mongoCollection.createIndex(new BasicDBObject("vehicleRnNumber", 1));
        try {
            MongoCursor<Document> cursor = mongoCollection.find().sort(basicDBObject).skip(offset).limit(limit).iterator();
            while (cursor.hasNext()) {
                Document document = cursor.next();
                document.remove("_id");
                document.remove("timeStamp");
                String jsonString = document.toJson();
                Vehicle vehicle = objectMapper.readValue(jsonString, Vehicle.class);
                vehicleList.add(vehicle);
            }
            return vehicleList;
        } catch (Exception e) {
            throw new MongoDbException("Some Exception occurred while fetch data from mongodb");
        }
    }
}
