package l1challenge.app.controllers;


import l1challenge.app.User;
import l1challenge.app.repositories.ArsWalletRepository;
import l1challenge.app.repositories.UsdWalletRepository;
import l1challenge.app.repositories.UsdtWalletRepository;
import l1challenge.app.wallet.ArsWallet;
import l1challenge.app.wallet.UsdWallet;
import l1challenge.app.wallet.UsdtWallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class WalletController {

    @Autowired // This means to get the bean called userRepository
    private ArsWalletRepository arsWalletRepository;
    @Autowired
    private UsdWalletRepository usdWalletRepository;
    @Autowired
    private UsdtWalletRepository usdtWalletRepository;

    @GetMapping(path="/arswallet")
    public @ResponseBody
    Iterable<ArsWallet> getAllArsWallets() {
        return arsWalletRepository.findAll();
    }

    @GetMapping(path="/usdtwallet")
    public @ResponseBody
    Iterable<UsdtWallet> getAllUsdtWallets() {
        return usdtWalletRepository.findAll();
    }

    @GetMapping(path="/usdwallet")
    public @ResponseBody
    Iterable<UsdWallet> getAllUsdWallets() {
        return usdWalletRepository.findAll();
    }
}
