package net.code.spring.play.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
/**
 * Created by code on 10/12/2016.
 */
@RestController
@RequestMapping("/persons1")
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Person>> getAllPersons(){
        return new ResponseEntity<>((Collection<Person>) repository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Person> getPersonWithId(@PathVariable Long id) {
        return new ResponseEntity<>(repository.findOne(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"lastName"})
    public ResponseEntity<Collection<Person>> findPersonWithName(@RequestParam(value="lastName") String lastName) {
        return new ResponseEntity<>(repository.findByLastName(lastName), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addPerson(@RequestBody Person input) {
        return new ResponseEntity<>(repository.save(input), HttpStatus.CREATED);
    }
}
