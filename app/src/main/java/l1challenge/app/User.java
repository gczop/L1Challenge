package l1challenge.app;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import l1challenge.app.wallet.ArsWallet;
import l1challenge.app.wallet.UsdWallet;
import l1challenge.app.wallet.UsdtWallet;
import l1challenge.app.wallet.Wallet;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Column(unique = true)
    private String alias;
    private String name;
    private String surname;
    @Column(unique = true)
    private String email;

    @Transient
    private ArsWallet arsWallet;
    @Transient
    private UsdWallet usdWallet;
    @Transient
    private UsdtWallet usdtWallet;

    public User(){}

    public User(String name, String surname, String email, String alias){
        this.name = name;
        this.surname = surname;
        this.alias = alias;
        this.email = email;
        arsWallet = new ArsWallet(alias);
        usdWallet = new UsdWallet(alias);
        usdtWallet = new UsdtWallet(alias);
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public String getSurname(){
        return this.surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public ArsWallet getArsWallet(){
        return this.arsWallet;
    }

    public void setArsWallet(ArsWallet userArsWallet) {
        this.arsWallet = userArsWallet;
    }

    public UsdWallet getUsdWallet() {
        return this.usdWallet;
    }


    public void setUsdWallet(UsdWallet wallet){
        this.usdWallet = wallet;
    }


    public void setUsdtWallet(UsdtWallet wallet){
        this.usdtWallet = wallet;
    }

    public UsdtWallet getUsdtWallet() {
        return this.usdtWallet;
    }

    public JsonElement toJsonObject() {
        JsonObject jo = new JsonObject();
        jo.addProperty("id", this.id);
        jo.addProperty("alias", this.alias);
        jo.addProperty("email", this.email);
        jo.addProperty("name",this.name);
        jo.addProperty("surname",this.surname);
        jo.add("arsWallet", this.arsWallet.toJsonObject());
        jo.add("usdWallet", this.usdWallet.toJsonObject());
        jo.add("usdtWallet", this.usdtWallet.toJsonObject());
        return jo;
    }
}
