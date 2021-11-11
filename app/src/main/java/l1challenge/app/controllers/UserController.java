package l1challenge.app.controllers;

import l1challenge.app.Operation;
import l1challenge.app.User;
import l1challenge.app.repositories.*;
import l1challenge.app.utils.OperationTypes;
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



    @PostMapping(path="/user") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String surname, @RequestParam String dni) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User(dni, name, surname);
        userRepository.save(n);
        arsWalletRepository.save(n.getArsWallet());
        usdWalletRepository.save(n.getUsdWallet());
        usdtWalletRepository.save(n.getUsdtWallet());
        return "Saved";
    }

    @GetMapping(path="/users/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path="/user")
    public @ResponseBody User getUserById(@RequestParam String dni) {
        User selectedUser = userRepository.findById(dni).get();
        ArsWallet userArsWallet = arsWalletRepository.findWalletByOwnerId(selectedUser.getDni());
        UsdWallet userUsdWallet = usdWalletRepository.findWalletByOwnerId(selectedUser.getDni());
        UsdtWallet userUsdtWallet = usdtWalletRepository.findWalletByOwnerId(selectedUser.getDni());
        selectedUser.setArsWallet(userArsWallet);
        selectedUser.setUsdWallet(userUsdWallet);
        selectedUser.setUsdtWallet(userUsdtWallet);
        return selectedUser;
    }

    @PostMapping(path="/user/deposit")
    public @ResponseBody boolean makeDepositToUser(@RequestParam String coin, @RequestParam String userDni, @RequestParam String amount){
        String operationCoin = coin.toUpperCase();
        switch(operationCoin){
            case "ARS":
                return makeDeposit(arsWalletRepository, userDni, amount);
            case "USD":
                return makeDeposit(usdWalletRepository, userDni, amount);
            case "USDT":
                return makeDeposit(usdtWalletRepository, userDni, amount);
        }
        return false;
    }

    @PostMapping(path="/user/extraction")
    public @ResponseBody boolean makeExtractionToUser(@RequestParam String coin, @RequestParam String userDni, @RequestParam String amount){
        String operationCoin = coin.toUpperCase();
        switch(operationCoin){
            case "ARS":
                return makeExtraction(arsWalletRepository, userDni, amount);
            case "USD":
                return makeExtraction(usdWalletRepository, userDni, amount);
            case "USDT":
                return makeExtraction(usdtWalletRepository, userDni, amount);
        }
        return false;
    }

    private boolean makeDeposit(WalletRepository walletRepository, String userDni, String amount) {
        Wallet wallet = walletRepository.findWalletByOwnerId(userDni);
        wallet.addAmount(amount);
        walletRepository.save(wallet);
        Operation newOperation = new Operation(wallet.getId(), wallet.getCurrency(), amount, getCurrentDate(), OperationTypes.OPERATION_TYPE.DEPOSIT);
        operationRepository.save(newOperation);
        return true;
    }

    private boolean makeExtraction(WalletRepository walletRepository, String userDni, String amount) {
        Wallet wallet = walletRepository.findWalletByOwnerId(userDni);
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