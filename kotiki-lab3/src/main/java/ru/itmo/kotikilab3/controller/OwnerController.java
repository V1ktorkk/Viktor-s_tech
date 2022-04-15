package ru.itmo.kotikilab3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itmo.kotikilab3.service.OwnerService;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping(path = "/ownercontroller")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity findOwner(@RequestParam(value = "id", required = false) Integer id) {
        if (id == null){
            return new ResponseEntity("id can't be null", HttpStatus.BAD_REQUEST);
        }
        else {
            return ResponseEntity.ok(ownerService.findById(id));
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addNewOwner(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "birthdate") String birthdate)
            throws ParseException {
        ownerService.saveNewOwner(name,
                new Date(new SimpleDateFormat("yyyyMMdd").parse(birthdate).getTime()));
        return ResponseEntity.ok("okk");
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@RequestParam(value = "id") Integer id) {
        if (id == null){
            return new ResponseEntity("id can't be null", HttpStatus.BAD_REQUEST);
        }
        else {
            ownerService.delete(id);
            return ResponseEntity.ok("okk");
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/findall")
    public ResponseEntity findAllOwners() {
        return ResponseEntity.ok(ownerService.findAllOwners());
    }


}
