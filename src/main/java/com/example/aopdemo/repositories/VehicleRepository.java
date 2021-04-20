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
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springfox.documentation.spring.web.json.Json;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Filter;

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
        Map<String, Object> respose = new HashMap<>();
        try {
            String jsonString = objectMapper.writeValueAsString(vehicle);//gson.toJson(vehicle);
            Document doc = Document.parse(jsonString);
            doc.put("_id", UUID.randomUUID().toString());
            doc.put("timeStamp", System.currentTimeMillis());
            mongoCollection.insertOne(doc);
            respose.put("msg", "vehicle created successfully");
            respose.put("vehicleId", vehicle);
            return respose;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, Object> updateVehicle(Vehicle vehicle) throws JsonProcessingException {
        Map<String, Object> response = new HashMap<>();
        try {
            String jsonString = objectMapper.writeValueAsString(vehicle);
            Document doc = Document.parse(jsonString);
            doc.put("timeStamp", System.currentTimeMillis());
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.append("vehicleId", vehicle.getVehicleId());
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

    public Vehicle VehicleByVehicleId(String vehicleId) {
        try {
            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.append("vehicleId", vehicleId);
            MongoCursor mongoCursor = mongoCollection.find(basicDBObject).iterator();
            if (mongoCursor.hasNext()) {
                Document doc = Document.parse(mongoCursor.next().toString());
                doc.remove("_id");
//                Json json=J


            }

        } catch (Exception e) {

        }
        return null;

    }
}
