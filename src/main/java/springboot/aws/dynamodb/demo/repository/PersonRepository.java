package springboot.aws.dynamodb.demo.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springboot.aws.dynamodb.demo.entity.Person;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class PersonRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonRepository.class);

    @Autowired
    private DynamoDBMapper dbMapper;

    public Person addPerson(Person person) {
        dbMapper.save(person);
        LOGGER.info("Person added successfully- > " + person);
        return person;
    }

    public Optional<Person> findById(String personId) {
        Optional<Person> person = Optional.ofNullable(dbMapper.load(Person.class, personId));
        return person;
    }

    public Person deletePerson(Person person) {
        dbMapper.delete(person);
        LOGGER.info("Person deleted successfully- > " + person);
        return person;
    }

    public void updatePartial(Person person) {
        Optional<Person> savedPersonOpt = findById(person.getPersonId());
        if (savedPersonOpt.isPresent()) {
            Person savedPerson = savedPersonOpt.get();
            savedPerson.setEmail(person.getEmail());
            dbMapper.save(savedPerson, dbExpression(person.getPersonId()));
        }

    }

    public void updatePerson(Person person) {

        dbMapper.save(person, dbExpression(person.getPersonId()));
        LOGGER.info("Person updated successfully->" + person);
    }

    public DynamoDBSaveExpression dbExpression(String personId) {
        DynamoDBSaveExpression expression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedMap = new HashMap<>();
        expectedMap.put("personId", new ExpectedAttributeValue().withValue(new AttributeValue().withS(personId)));
        expression.setExpected(expectedMap);
        return expression;
    }
}
