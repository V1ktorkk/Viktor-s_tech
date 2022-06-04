package ru.itmo.kotikilab5controllers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itmo.kotikilab5controllers.entity.Colors;
import ru.itmo.kotikilab5controllers.kafkaCommunication.Message;
import ru.itmo.kotikilab5controllers.service.UserService;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/catcontroller")
public class CatController {

    @Autowired
    private Message message;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity findCat(@RequestParam(value = "id",required = false) Integer id) {
        if (id == null){
            return new ResponseEntity("id can't be null", HttpStatus.BAD_REQUEST);
        }
        Map<String, String> parameters = new HashMap<>();
        parameters.put("id", Integer.toString(id));
        boolean result = message.sendMessage("kotiki.get", parameters);
        if (result){
            return new ResponseEntity<>("sucsess", HttpStatus.ACCEPTED);
        }

        else{
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addNewCat(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "breed") String breed,
            @RequestParam(value = "color") Colors color,
            @RequestParam(value = "ownerId") int ownerId,
            @RequestParam(value = "birthdate") String birthdate)
            throws ParseException {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", name);
        parameters.put("breed", breed);
        parameters.put("color", color.toString());
        parameters.put("ownerId", Integer.toString(ownerId));
        parameters.put("birthdate", birthdate);

        boolean result = message.sendMessage("kotiki.post", parameters);
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
        boolean result = message.sendMessage("kotiki.delete", parameters);
        if (result){
            return new ResponseEntity<>("sucsess", HttpStatus.ACCEPTED);
        }

        else{
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/findall")
    public ResponseEntity findAllKotiki() {
        boolean result = message.sendMessage("kotiki.findAll", null);
        if (result){
            return new ResponseEntity<>("sucsess", HttpStatus.ACCEPTED);
        }
        else{
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/addrelationship")
    public ResponseEntity addRelationship(@RequestParam(value = "idkotika") Integer idKotika,
                                          @RequestParam(value = "friendid") Integer friendId) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("idkotika", Integer.toString(idKotika));
        parameters.put("friendid", Integer.toString(friendId));
        boolean result = message.sendMessage("kotiki.addRelationShip", parameters);
        if (result){
            return new ResponseEntity<>("sucsess", HttpStatus.ACCEPTED);
        }

        else{
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/removerelationship")
    public ResponseEntity removeRelationship(@RequestParam(value = "idkotika") Integer idKotika,
                                             @RequestParam(value = "friendid") Integer friendId) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("idkotika", Integer.toString(idKotika));
        parameters.put("friendid", Integer.toString(friendId));
        boolean result = message.sendMessage("kotiki.removeRelationship", parameters);
        if (result){
            return new ResponseEntity<>("sucsess", HttpStatus.ACCEPTED);
        }

        else{
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }
    }

//    @RequestMapping(method = RequestMethod.GET, path = "/findbycolor")
//    public ResponseEntity findByColor(@RequestParam(value = "color") Colors color) {
//        List<KotikiEntity> kotiki;
//        if (color != null) {
//            kotiki = kotikiService.findAllByColor(color.toString());
//        } else {
//            kotiki = kotikiService.findAllKotiki();
//        }
//        return new ResponseEntity(kotiki.
//                stream().map(cats -> TransformCat.transformEntityToWrappedEntity(cats)).collect(Collectors.toList()), HttpStatus.ACCEPTED);
//    }
}
