package l1challenge.app.controllers;

import com.google.gson.JsonObject;
import l1challenge.app.Operation;
import l1challenge.app.User;
import l1challenge.app.repositories.*;
import l1challenge.app.utils.OperationTypes;
import l1challenge.app.utils.ResponseMaker;
import l1challenge.app.wallet.ArsWallet;
import l1challenge.app.wallet.UsdWallet;
import l1challenge.app.wallet.UsdtWallet;
import l1challenge.app.wallet.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping(path="/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArsWalletRepository arsWalletRepository;
    @Autowired
    private UsdWalletRepository usdWalletRepository;
    @Autowired
    private UsdtWalletRepository usdtWalletRepository;
    @Autowired
    private OperationRepository operationRepository;



    @PostMapping(path="/user")
    public @ResponseBody
    JsonObject addNewUser (@RequestParam String name, @RequestParam String surname, @RequestParam String alias, @RequestParam String email) {
        User n = new User(name, surname, email, alias);
        //TODO: Chequear que no se repita email/alias. No repetir billeteras.
        userRepository.save(n);
        arsWalletRepository.save(n.getArsWallet());
        usdWalletRepository.save(n.getUsdWallet());
        usdtWalletRepository.save(n.getUsdtWallet());
        return ResponseMaker.makeOkResponse();
    }

    @GetMapping(path="/user")
    public @ResponseBody User getUser(@RequestParam String alias) {
        User selectedUser = userRepository.findByAlias(alias);
        //TODO: Ver de mejorar esto y que no tenga que usar repositorios.
        ArsWallet userArsWallet = arsWalletRepository.findWalletByOwnerAlias(selectedUser.getAlias());
        UsdWallet userUsdWallet = usdWalletRepository.findWalletByOwnerAlias(selectedUser.getAlias());
        UsdtWallet userUsdtWallet = usdtWalletRepository.findWalletByOwnerAlias(selectedUser.getAlias());
        selectedUser.setArsWallet(userArsWallet);
        selectedUser.setUsdWallet(userUsdWallet);
        selectedUser.setUsdtWallet(userUsdtWallet);
        return selectedUser;
    }

    @PostMapping(path="/user/deposit")
    public @ResponseBody boolean makeDepositToUser(@RequestParam String coin, @RequestParam String alias, @RequestParam String amount){
        String operationCoin = coin.toUpperCase();
        switch(operationCoin){
            case "ARS":
                return makeDeposit(arsWalletRepository, alias, amount);
            case "USD":
                return makeDeposit(usdWalletRepository, alias, amount);
            case "USDT":
                return makeDeposit(usdtWalletRepository, alias, amount);
        }
        return false;
    }

    @PostMapping(path="/user/extraction")
    public @ResponseBody boolean makeExtractionToUser(@RequestParam String coin, @RequestParam String alias, @RequestParam String amount){
        String operationCoin = coin.toUpperCase();
        switch(operationCoin){
            case "ARS":
                return makeExtraction(arsWalletRepository, alias, amount);
            case "USD":
                return makeExtraction(usdWalletRepository, alias, amount);
            case "USDT":
                return makeExtraction(usdtWalletRepository, alias, amount);
        }
        return false;
    }

    private boolean makeDeposit(WalletRepository walletRepository, String userAlias, String amount) {
        Wallet wallet = walletRepository.findWalletByOwnerAlias(userAlias);
        wallet.addAmount(amount);
        walletRepository.save(wallet);
        Operation newOperation = new Operation(wallet.getId(), wallet.getCurrency(), amount, getCurrentDate(), OperationTypes.OPERATION_TYPE.DEPOSIT);
        operationRepository.save(newOperation);
        return true;
    }

    private boolean makeExtraction(WalletRepository walletRepository, String userAlias, String amount) {
        Wallet wallet = walletRepository.findWalletByOwnerAlias(userAlias);
        wallet.extractAmount(amount);
        walletRepository.save(wallet);
        Operation newOperation = new Operation(wallet.getId(), wallet.getCurrency(), amount, getCurrentDate(), OperationTypes.OPERATION_TYPE.EXTRACTION);
        operationRepository.save(newOperation);
        return true;
    }

    private String getCurrentDate(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

}