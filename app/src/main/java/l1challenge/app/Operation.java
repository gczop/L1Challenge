package l1challenge.app;

import com.google.gson.JsonObject;
import l1challenge.app.utils.OperationTypes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Operation {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int operationId;
    private int wallet;
    private String currency;
    private String amount;
    private String date;
    private String type;

    public Operation(){}
    public Operation(int wallet, String currency, String amount, String date, String type){
        this.wallet = wallet;
        this.currency = currency;
        this.amount = amount;
        this.date = date;
        this.type = type;
    }

    public int getOperationId() {
        return operationId;
    }

    public void setOperationId(int operationId) {
        this.operationId = operationId;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }


    public JsonObject mapToJsonObject() {
        JsonObject jo = new JsonObject();
        jo.addProperty("operationId", this.operationId);
        jo.addProperty("wallet", this.wallet);
        jo.addProperty("currency", this.currency);
        jo.addProperty("date", this.date);
        jo.addProperty("amount", this.amount);
        jo.addProperty("type", this.type.toString());
        return jo;
    }
}
