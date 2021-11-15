package l1challenge.app.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import l1challenge.app.User;

public class ResponseMaker {

    public static JsonObject makeOkResponse(){
        return makeResponse(true, "La operación fue ejecutada exitosamente.");
    }

    public static JsonObject makeInvalidOperationResultResponse() {
        return makeResponse(false, "No se puede realizar la operación con el monto indicado.");
    }

    public static JsonObject makeInvalidCurrencyResponse(){
        return makeResponse(false, "La operación debe realizarse con una de las siguientes monedas: ARS, USD, USDT.");
    }

    private static JsonObject makeResponse(boolean result, String message){
        final JsonObject jo = new JsonObject();
        jo.addProperty("success", result);
        jo.addProperty("message", message);
        return jo;
    }

    public static JsonObject makeOperationsResultResponse(JsonArray ja) {
        final JsonObject jo = new JsonObject();
        jo.add("operations", ja);
        return jo;
    }

    public static JsonObject makeInvalidUserResponse() {
        return makeResponse(false, "El alias del usuario deseado no existe. Intente con un alias nuevo.");
    }

    public static JsonObject makeInvalidOperationTypeResponse() {
        return makeResponse(false, "El tipo de operación debe ser uno de los siguientes: DEPOSIT, EXTRACTION.");
    }

    public static JsonObject makeInvalidOffsetResponse() {
        return makeResponse(false, "El campo offset debe ser mayor a 0.");
    }

    public static JsonObject makeInvalidLimitResponse() {
        return makeResponse(false, "El campo limit debe ser mayor a 0");
    }

    public static JsonObject makeOkUserResponse(User selectedUser) {
        final JsonObject jo = new JsonObject();
        jo.add("user", selectedUser.toJsonObject());
        return jo;
    }

    public static JsonObject makeExistingUserResponse() {
        return makeResponse(false, "El usuario ya se encuentra registrado. Por favor ingrese datos nuevos.");
    }
}
