package l1challenge.app.controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import l1challenge.app.Operation;
import l1challenge.app.repositories.ArsWalletRepository;
import l1challenge.app.repositories.OperationRepository;
import l1challenge.app.repositories.UsdWalletRepository;
import l1challenge.app.repositories.UsdtWalletRepository;
import l1challenge.app.utils.OperationTypes;
import l1challenge.app.utils.ResponseMaker;
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
    JsonObject getUserOperations(@RequestParam String alias, @RequestParam(required = false) String currency, @RequestParam(required = false) String type, @RequestParam(required = false) Integer limit, @RequestParam(required = false) Integer offset ) {
        Wallet arsWallet = arsWalletRepository.findWalletByOwnerAlias(alias);
        Wallet usdWallet = usdWalletRepository.findWalletByOwnerAlias(alias);
        Wallet usdtWallet = usdtWalletRepository.findWalletByOwnerAlias(alias);
        if((arsWallet == null) ||(usdWallet == null) ||(usdtWallet == null) ){
            return ResponseMaker.makeInvalidUserResponse();
        }
        if((currency != null) &&((!currency.equals("ARS")) && (!currency.equals("USD")) && (!currency.equals("USDT")))){
            return ResponseMaker.makeInvalidCurrencyResponse();
        }
        if((type != null) && (!type.equals(OperationTypes.DEPOSIT)) &&(!type.equals(OperationTypes.EXTRACTION))){
            return ResponseMaker.makeInvalidOperationTypeResponse();
        }
        if((limit != null)&&(limit<0)){
            return ResponseMaker.makeInvalidLimitResponse();
        }
        if((offset != null)&&(offset<0)){
            return ResponseMaker.makeInvalidOffsetResponse();
        }
        List<Integer> idList = new ArrayList();
        idList.add(arsWallet.getId());
        idList.add(usdWallet.getId());
        idList.add(usdtWallet.getId());
        Iterable<Operation> ops = operationsRepository.findOperationsWithFilters(idList, type, currency, (limit == null)?2147483647 :limit, (offset==null)?0:offset);
        return mapOperationIterableToJsonArray(ops);
    }

    private JsonObject mapOperationIterableToJsonArray(Iterable<Operation> collection){
        JsonArray ja = new JsonArray();
        for(Operation op : collection){
            ja.add(op.mapToJsonObject());
        }
        return ResponseMaker.makeOperationsResultResponse(ja);
    }



}
