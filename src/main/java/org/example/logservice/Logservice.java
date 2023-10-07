package org.example.logservice;

import com.google.gson.Gson;
import java.util.List;
import static spark.Spark.*;

public class Logservice {
    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/public");
        MongoDBClient client = new MongoDBClient();
        get("/addstring/:string", (req, res) -> {
            String cadena = req.params(":string");
            client.anadirCadena(cadena);
            List<StringDatePair> latestStrings = client.getUltimosDiez();
            return new Gson().toJson(latestStrings);
        });
    }

    private static int getPort(){
        if(System.getenv("PORT") != null ){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000;
    }
}