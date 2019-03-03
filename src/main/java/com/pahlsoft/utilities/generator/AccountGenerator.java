package com.pahlsoft.utilities.generator;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;

import java.util.*;

public class AccountGenerator {

    AccountGenerator() {
        throw new IllegalStateException("Utility class");
    }

    public static Map buildAccount() {
        Faker faker = new Faker(new Locale("en-US"));
        Map<String, Object> jsonMap = new HashMap<>();

        // Timestamp for Last Update
        jsonMap.put("last_update_date", new Date());

        // Name
        jsonMap.put("name.first", faker.name().firstName());
        jsonMap.put("name.last", faker.name().lastName());
        jsonMap.put("name.email.main",faker.internet().emailAddress());
        jsonMap.put("name.email.other",faker.internet().emailAddress());
        jsonMap.put("name.phone.main", faker.phoneNumber().phoneNumber());
        jsonMap.put("name.phone.other",faker.phoneNumber().phoneNumber());
        jsonMap.put("name.phone.cell",faker.phoneNumber().cellPhone());

        // Name 2
        jsonMap.put("name2.first", faker.name().firstName());
        jsonMap.put("name2.last", faker.name().lastName());
        jsonMap.put("name2.email.main",faker.internet().emailAddress());
        jsonMap.put("name2.phone.cell",faker.phoneNumber().cellPhone());

        // Name 3
        jsonMap.put("name3.first", faker.name().firstName());
        jsonMap.put("name3.last", faker.name().lastName());
        jsonMap.put("name3.email.main",faker.internet().emailAddress());
        jsonMap.put("name3.phone.cell",faker.phoneNumber().cellPhone());

        // Name 4
        jsonMap.put("name4.first", faker.name().firstName());
        jsonMap.put("name4.last", faker.name().lastName());
        jsonMap.put("name4.email.main",faker.internet().emailAddress());
        jsonMap.put("name4.phone.cell",faker.phoneNumber().cellPhone());

        // current Address
        jsonMap.put("address.current.street", faker.address().streetAddress());
        jsonMap.put("address.current.city", faker.address().cityName());

        String state = faker.address().stateAbbr();
        jsonMap.put("address.current.state", state);
        jsonMap.put("address.current.zipcode", faker.address().zipCodeByState(state));

        // Temporary Address
        Address temporaryAddress = faker.address();
        jsonMap.put("address.temporary.street", temporaryAddress.streetAddress());
        jsonMap.put("address.temporary.city", temporaryAddress.cityName());

        String temporaryState = temporaryAddress.stateAbbr();
        jsonMap.put("address.temporary.state", temporaryState);
        jsonMap.put("address.temporary.zipcode", temporaryAddress.zipCodeByState(temporaryState));

        // Previous Address
        Address previousAddress = faker.address();

        jsonMap.put("address.previous.street", previousAddress.streetAddress());
        jsonMap.put("address.previous.city", previousAddress.cityName());

        String previousState = previousAddress.stateAbbr();
        jsonMap.put("address.previous.state", previousState);
        jsonMap.put("address.previous.zipcode", previousAddress.zipCodeByState(previousState));

        // Statement Address
        Address statementAddress = faker.address();

        jsonMap.put("address.statement.street", statementAddress.streetAddress());
        jsonMap.put("address.statement.city", statementAddress.cityName());

        String statementState = statementAddress.stateAbbr();
        jsonMap.put("address.statement.state", statementState);
        jsonMap.put("address.statement.zipcode", statementAddress.zipCodeByState(statementState));


        // PAN (Primary Account Number)
        jsonMap.put("pan",generateUUID());
        jsonMap.put("dda",generateUUID());

        // Date of Birth
        jsonMap.put("date_of_birth",faker.date().birthday(18,110));

        // Social Security Number
        jsonMap.put("ssn",faker.idNumber().ssnValid());

        return jsonMap;
    }

    private static synchronized String generateUUID() {
        return UUID.randomUUID().toString();
    }

}

