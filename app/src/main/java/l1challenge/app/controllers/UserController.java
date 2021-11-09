package l1challenge.app.controllers;

import l1challenge.app.User;
import l1challenge.app.repositories.ArsWalletRepository;
import l1challenge.app.repositories.UsdWalletRepository;
import l1challenge.app.repositories.UsdtWalletRepository;
import l1challenge.app.repositories.UserRepository;
import l1challenge.app.wallet.ArsWallet;
import l1challenge.app.wallet.UsdWallet;
import l1challenge.app.wallet.UsdtWallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path="/demo")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArsWalletRepository arsWalletRepository;
    @Autowired
    private UsdWalletRepository usdWalletRepository;
    @Autowired
    private UsdtWalletRepository usdtWalletRepository;



    @PostMapping(path="/add") // Map ONLY POST Requests
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

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path="/get")
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


}