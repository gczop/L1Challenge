package l1challenge.app.utils;

import com.google.gson.JsonObject;

public class ResponseMaker {

    public static JsonObject makeOkResponse(){
        return makeResponse(true, "La operación fue ejecutada exitosamente.");
    }

    public static JsonObject makeInvalidOperationResultResponse() {
        return makeResponse(false, "No se puede realizar la operación con el monto indicado.");
    }

    public static JsonObject makeInvalidCurrencyResponse(){
        return makeResponse(false, "No se puede realizar la operación con el monto indicado.");
    }

    private static JsonObject makeResponse(boolean result, String message){
        final JsonObject jo = new JsonObject();
        jo.addProperty("success", result);
        jo.addProperty("message", message);
        return jo;
    }
}
