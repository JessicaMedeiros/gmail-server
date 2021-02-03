package io.github.jetmedeiros.gmailserver.controller;

import io.github.jetmedeiros.gmailserver.dao.UserDTO;
import io.github.jetmedeiros.gmailserver.model.User;
import io.github.jetmedeiros.gmailserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private UserService service;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<User> find(@PathVariable Integer id){
        User obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody UserDTO objDTO){
        User obj = service.fromDTO(objDTO);
        obj = service.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody UserDTO updatedObj){
        User cat = service.fromDTO(updatedObj);
        cat.setId(id);
        cat = service.update(cat);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> list = service.findAll();
        //vai percorrer a lista e fazer uma operacao para cada elemento da lista
        List<UserDTO> listDTO =  list.stream().map(obj ->
                new UserDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

//    @RequestMapping(value = "/page", method=RequestMethod.GET)
//    public ResponseEntity<Page<UsuarioDTO>> findPage(@RequestParam (value = "page", defaultValue = "0") Integer page,
//                                                     @RequestParam (value = "linesPerPage", defaultValue = "24") Integer linesPerPage, //24 é mais responsivo
//                                                     @RequestParam (value = "orderBy", defaultValue = "name") String orderBy,
//                                                     @RequestParam (value = "direction", defaultValue = "ASC") String direction){
//
//        Page<Category> list = service.findPage(page, linesPerPage, orderBy, direction);
//        //vai percorrer a lista e fazer uma operacao para cada elemento da lista
//        Page<CategoryDTO> listDTO =  list.map(obj ->
//                new CategoryDTO(obj));
//        return ResponseEntity.ok().body(listDTO);
//    }
}
