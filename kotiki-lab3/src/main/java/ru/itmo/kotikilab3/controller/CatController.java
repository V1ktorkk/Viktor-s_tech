package ru.itmo.kotikilab3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itmo.kotikilab3.entity.Colors;
import ru.itmo.kotikilab3.entity.KotikiEntity;
import ru.itmo.kotikilab3.service.KotikiService;
import ru.itmo.kotikilab3.service.OwnerService;
import ru.itmo.kotikilab3.service.UserService;
import ru.itmo.kotikilab3.transformation.TransformCat;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/catcontroller")
public class CatController {

    @Autowired
    private KotikiService kotikiService;
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity findCat(@RequestParam(value = "id",required = false) Integer id, Authentication authentication) {
        if (id == null){
            return new ResponseEntity("id can't be null", HttpStatus.BAD_REQUEST);
        }
        var user = userService.loadUserByUsername(authentication.getName());
        boolean isAdmin = authentication.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals("ROLE_ADMIN"));
        var kotik = kotikiService.findById(id);
        if (isAdmin || Objects.equals(kotik.getOwner().getUsername(), authentication.getName())){
            return ResponseEntity.ok(TransformCat.transformEntityToWrappedEntity(kotik));
        }
        else{
            return (ResponseEntity) ResponseEntity.badRequest();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addNewCat(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "breed") String breed,
            @RequestParam(value = "color") Colors color,
            @RequestParam(value = "ownerId") int ownerId,
            @RequestParam(value = "birthdate") String birthdate,
            Authentication authentication)
            throws ParseException {
        boolean isAdmin = authentication.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals("ROLE_ADMIN"));
        if (isAdmin) {
            kotikiService.saveNewCat(name, breed, color, ownerId,
                    new Date(new SimpleDateFormat("yyyyMMdd").parse(birthdate).getTime()));
            return ResponseEntity.ok("okk");
        }
        else{
            return (ResponseEntity) ResponseEntity.badRequest();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@RequestParam(value = "id") Integer id, Authentication authentication) {
        if (id == null){
            return new ResponseEntity("id can't be null", HttpStatus.BAD_REQUEST);
        }
        var user = userService.loadUserByUsername(authentication.getName());
        boolean isAdmin = authentication.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals("ROLE_ADMIN"));
        var kotik = kotikiService.findById(id);
        if (isAdmin || Objects.equals(kotik.getOwner().getUsername(), authentication.getName())){
            kotikiService.delete(id);
            return ResponseEntity.ok("okk");
        }
        else {
            return (ResponseEntity) ResponseEntity.badRequest();
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/findall")
    public ResponseEntity findAllKotiki(Authentication authentication) {
        boolean isAdmin = authentication.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals("ROLE_ADMIN"));
        if (isAdmin) {
            return ResponseEntity.ok(kotikiService.findAllKotiki());
        }
        else{
            return (ResponseEntity) ResponseEntity.badRequest();
            }
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
