package l1challenge.app;

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
    private String alias;
    private String name;
    private String surname;
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
}
