package l1challenge.app.controllers;

import l1challenge.app.Operation;
import l1challenge.app.repositories.*;
import l1challenge.app.wallet.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="/api")
public class OperationsController {

    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private ArsWalletRepository arsWalletRepository;
    @Autowired
    private UsdWalletRepository usdWalletRepository;
    @Autowired
    private UsdtWalletRepository usdtWalletRepository;


    @GetMapping(path="/operations")
    public @ResponseBody
    Iterable<Operation> getOperationsForUser(@RequestParam String userDni, @RequestParam(required = false) String limit, @RequestParam(required = false) String offset, @RequestParam(required = false) String type, @RequestParam(required = false) String currency) {

        Wallet arsWallet = arsWalletRepository.findWalletByOwnerId(userDni);
        Wallet usdWallet = usdWalletRepository.findWalletByOwnerId(userDni);
        Wallet usdtWallet = usdtWalletRepository.findWalletByOwnerId(userDni);
        List<Integer> walletIds = new ArrayList();
        walletIds.add(arsWallet.getId());
        walletIds.add(usdWallet.getId());
        walletIds.add(usdtWallet.getId());
        return operationRepository.findByWalletIn(walletIds);
    }


}
