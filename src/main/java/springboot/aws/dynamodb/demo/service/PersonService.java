package springboot.aws.dynamodb.demo.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.aws.dynamodb.demo.entity.Person;
import springboot.aws.dynamodb.demo.repository.PersonRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person findById(String personId){
        return personRepository.findById(personId).orElse(null);
    }

    public Person addPerson(Person person) {
       return personRepository.addPerson(person);
    }


    public Person deletePerson(Person person){
       return personRepository.deletePerson(person);
    }

    public void updatePerson(Person person){
       personRepository.updatePerson(person);

    }
    public void updatePersonPartial(Person person){
        personRepository.updatePartial(person);

    }

}
