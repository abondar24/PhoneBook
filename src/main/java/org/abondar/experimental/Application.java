package org.abondar.experimental;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "PhoneBook",
                version = "${api.version}",
                description = "${openapi.description}",
                license = @License(name = "MIT"),
                contact = @Contact(url = "https://github.com/abondar24",name = "Alex", email = "abondar24@ya.ru")
        )
)
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
