package ru.itmo.kotikilab3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itmo.kotikilab3.entity.Colors;
import ru.itmo.kotikilab3.entity.KotikiEntity;
import ru.itmo.kotikilab3.service.KotikiService;
import ru.itmo.kotikilab3.transformation.TransformCat;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/catcontroller")
public class CatController {

    @Autowired
    private KotikiService kotikiService;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity findCat(@RequestParam(value = "id", required = false) Integer id) {
        if (id == null){
            return new ResponseEntity("id can't be null", HttpStatus.BAD_REQUEST);
        }
        else {
            return ResponseEntity.ok(TransformCat.transformEntityToWrappedEntity(kotikiService.findById(id)));
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
        kotikiService.saveNewCat(name, breed, color, ownerId,
                new Date(new SimpleDateFormat("yyyyMMdd").parse(birthdate).getTime()));
        return ResponseEntity.ok("okk");
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@RequestParam(value = "id") Integer id) {
        if (id == null){
            return new ResponseEntity("id can't be null", HttpStatus.BAD_REQUEST);
        }
        else {
            kotikiService.delete(id);
            return ResponseEntity.ok("okk");
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/findall")
    public ResponseEntity findAllKotiki() {
        return ResponseEntity.ok(kotikiService.findAllKotiki());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/addrelationship")
    public ResponseEntity addRelationship(@RequestParam(value = "idkotika") Integer idKotika,
                                          @RequestParam(value = "friendid") Integer friendId) {
        if ((idKotika == null) || (friendId == null)){
            return new ResponseEntity("id can't be null", HttpStatus.BAD_REQUEST);
        }
        else {
            kotikiService.addRelationship(idKotika, friendId);
            return ResponseEntity.ok("okk");
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/removerelationship")
    public ResponseEntity removeRelationship(@RequestParam(value = "idkotika") Integer idKotika,
                                             @RequestParam(value = "friendid") Integer friendId) {
        if ((idKotika == null) || (friendId == null)){
            return new ResponseEntity("id can't be null", HttpStatus.BAD_REQUEST);
        }
        else {
            kotikiService.removeRelationship(friendId, idKotika);
            return ResponseEntity.ok("okk");
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/findbycolor")
    public ResponseEntity findByColor(@RequestParam(value = "color") Colors color) {
        List<KotikiEntity> kotiki;
        if (color != null) {
            kotiki = kotikiService.findAllByColor(color.toString());
        } else {
            kotiki = kotikiService.findAllKotiki();
        }
        return new ResponseEntity(kotiki.
                stream().map(cats -> TransformCat.transformEntityToWrappedEntity(cats)).collect(Collectors.toList()), HttpStatus.ACCEPTED);
    }
}
