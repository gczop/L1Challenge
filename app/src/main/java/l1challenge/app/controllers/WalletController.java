package l1challenge.app.controllers;


import l1challenge.app.User;
import l1challenge.app.repositories.ArsWalletRepository;
import l1challenge.app.wallet.ArsWallet;
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

    @GetMapping(path="/wallet")
    public @ResponseBody
    Iterable<ArsWallet> getAllArsWallets() {
        // This returns a JSON or XML with the users
        return arsWalletRepository.findAll();
    }
}
