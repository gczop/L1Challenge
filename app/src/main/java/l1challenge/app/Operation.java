package l1challenge.app;

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
    private OperationTypes.OPERATION_TYPE type;

    public Operation(){}
    public Operation(int wallet, String currency, String amount, String date, OperationTypes.OPERATION_TYPE type){
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

    public OperationTypes.OPERATION_TYPE getType() {
        return this.type;
    }

    public void setType(OperationTypes.OPERATION_TYPE type){
        this.type = type;
    }


}
