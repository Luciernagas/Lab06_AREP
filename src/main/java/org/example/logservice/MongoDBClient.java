package org.example.logservice;

import com.mongodb.client.*;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import java.util.*;
public class MongoDBClient {
    private final MongoCollection<Document> collection;
    public MongoDBClient() {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("arep");
        collection = database.getCollection("lab6");
    }

    public void anadirCadena(String cadena){
        try {
            Document nuevaCadena = new Document("cadena", cadena).append("fecha", new Date());
            collection.insertOne(nuevaCadena);
            System.out.println("Cadena insertada correctamente en la base de datos.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<StringDatePair> getUltimosDiez() {
        List<StringDatePair> latestStrings = new ArrayList<>();
        FindIterable<Document> resultado = collection.find().sort(Sorts.descending("fecha")).limit(10);
        MongoCursor<Document> cursor = resultado.iterator();
        while (cursor.hasNext()) {
            Document document = cursor.next();
            String cadena = document.getString("cadena");
            String fecha = document.getDate("fecha").toString();
            latestStrings.add(new StringDatePair(cadena, fecha));
        }
        return latestStrings;
    }

}
