package l1challenge.app.controllers;

import l1challenge.app.Operation;
import l1challenge.app.User;
import l1challenge.app.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/api")
public class OperationsController {
    @Autowired
    private OperationRepository operationRepository;

    @GetMapping(path="/operations/all")
    public @ResponseBody
    Iterable<Operation> getAllUsers() {
        return operationRepository.findAll();
    }


}
