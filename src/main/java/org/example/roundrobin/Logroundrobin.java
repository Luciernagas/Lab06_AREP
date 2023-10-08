package org.example.roundrobin;
import java.io.IOException;

import static spark.Spark.*;

public class Logroundrobin {
    public static void main( String[] args ) {
        port(getport());
        staticFiles.location("/public");
        get("localhost", (req, pesp) -> {
            String val = req.queryParams("value");
            return logMessage(val);
        });
    }

    private static Object logMessage(String val) throws IOException {
        return HttpRemoteCaller.remoteLocalCall(val);
    }

    private static int getport() {
        if (System.getenv("PORT")!= null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080;
    }
}