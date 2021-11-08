package l1challenge.app;

import l1challenge.app.wallet.ArsWallet;
import l1challenge.app.wallet.UsdWallet;
import l1challenge.app.wallet.UsdtWallet;
import l1challenge.app.wallet.Wallet;

import javax.persistence.*;

@Entity
public class User {
    @Id
    private String dni;
    private String name;
    private String surname;

    @Transient
    private ArsWallet arsWallet;
    @Transient
    private UsdWallet usdWallet;
    @Transient
    private UsdtWallet usdtWallet;

    public User(){}

    public User(String dni, String name, String surname){
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        arsWallet = new ArsWallet(dni);
        usdWallet = new UsdWallet(dni);
        usdtWallet = new UsdtWallet(dni);
    }


    public void setDni(String dni){
        this.dni = dni;
    }

    public String getDni(){
        return this.dni;
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

    public ArsWallet getArsWallet(){
        return this.arsWallet;
    }
    public void setArsWallet(ArsWallet userArsWallet) {
        this.arsWallet = userArsWallet;
    }
}
