package springboot.aws.dynamodb.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.aws.dynamodb.demo.entity.Person;
import springboot.aws.dynamodb.demo.service.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/person/{personId}")
    public Person getPerson(@PathVariable String personId) {
        return personService.findById(personId);
    }

    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person) {
        return personService.addPerson(person);
    }

    @DeleteMapping("/person")
    public Person deletePerson(@RequestBody Person person) {
        return personService.deletePerson(person);
    }

    @PatchMapping("/person/email")
    public Person updatePersonPartial(@RequestBody Person person) {
         personService.updatePersonPartial(person);
         return person;
    }

    @PutMapping("/person")
    public void updatePerson(@RequestBody Person person) {
        personService.updatePerson(person);
    }
}
