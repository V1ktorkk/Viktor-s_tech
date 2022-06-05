package ru.itmo.kotikilab5controllers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itmo.kotikilab5controllers.kafkaCommunication.Message;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/ownercontroller")
public class OwnerController {

    @Autowired
    private Message message;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity findOwner(@RequestParam(value = "id", required = false) Integer id) {
        if (id == null){
            return new ResponseEntity("id can't be null", HttpStatus.BAD_REQUEST);
        }
        Map<String, String> parameters = new HashMap<>();
        parameters.put("id", Integer.toString(id));
        boolean result = message.sendMessage("owner.get", parameters);
        if (result){
            return new ResponseEntity<>("sucsess", HttpStatus.ACCEPTED);
        }

        else{
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addNewOwner(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "birthdate") String birthdate)
            throws ParseException {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", name);
        parameters.put("birthdate", birthdate);

        boolean result = message.sendMessage("owner.post", parameters);
        if (result){
            return new ResponseEntity<>("sucsess", HttpStatus.ACCEPTED);
        }

        else{
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@RequestParam(value = "id") Integer id) {
        if (id == null){
            return new ResponseEntity("id can't be null", HttpStatus.BAD_REQUEST);
        }
        Map<String, String> parameters = new HashMap<>();
        parameters.put("id", Integer.toString(id));
        boolean result = message.sendMessage("owner.delete", parameters);
        if (result){
            return new ResponseEntity<>("sucsess", HttpStatus.ACCEPTED);
        }

        else{
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }
    }

//    @RequestMapping(method = RequestMethod.GET, path = "/findall")
//    public ResponseEntity findAllOwners() {
//        return ResponseEntity.ok(ownerService.findAllOwners());
//    }


}
