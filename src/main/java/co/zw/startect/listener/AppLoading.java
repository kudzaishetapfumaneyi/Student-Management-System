package co.zw.startect.listener;

import co.zw.startect.domain.Person;
import co.zw.startect.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;

import java.util.List;

public class AppLoading {

    @Autowired
    private PersonService personService;

    //@Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        List<Person> personList = personService.getAllPersons();
        for(Person person: personList) {
            System.out.println(person.getName());
        }
    }
}
