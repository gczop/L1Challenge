package l1challenge.app.controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import l1challenge.app.Operation;
import l1challenge.app.repositories.ArsWalletRepository;
import l1challenge.app.repositories.OperationRepository;
import l1challenge.app.repositories.UsdWalletRepository;
import l1challenge.app.repositories.UsdtWalletRepository;
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
public class OperationController {
    @Autowired
    OperationRepository operationsRepository;
    @Autowired
    private ArsWalletRepository arsWalletRepository;
    @Autowired
    private UsdWalletRepository usdWalletRepository;
    @Autowired
    private UsdtWalletRepository usdtWalletRepository;

    @GetMapping(path="/operations")
    public @ResponseBody
    JsonArray getUserOperations(@RequestParam String alias) {
        Wallet arsWallet = arsWalletRepository.findWalletByOwnerAlias(alias);
        Wallet usdWallet = usdWalletRepository.findWalletByOwnerAlias(alias);
        Wallet usdtWallet = usdtWalletRepository.findWalletByOwnerAlias(alias);
        List<Integer> idList = new ArrayList();
        idList.add(arsWallet.getId());
        idList.add(usdWallet.getId());
        idList.add(usdtWallet.getId());
        Iterable<Operation> ops = operationsRepository.findByWalletIn(idList);
        return mapOperationIterableToJsonArray(ops);
    }

    private JsonArray mapOperationIterableToJsonArray(Iterable<Operation> collection){
        JsonArray ja = new JsonArray();
        for(Operation op : collection){
            ja.add(op.mapToJsonObject());
        }
        return ja;
    }



}
