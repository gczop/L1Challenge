package l1challenge.app.utils;

import com.google.gson.JsonObject;

public class ResponseMaker {

    public static JsonObject makeOkResponse(){
        final JsonObject jo = new JsonObject();
        jo.addProperty("success", true);
        jo.addProperty("message", "La operación fue ejecutada exitosamente");
        return jo;
    }
}
