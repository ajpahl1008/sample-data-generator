package com.pahlsoft.utilities.generator;

import com.github.javafaker.Faker;

import java.util.*;

public class FraudulentTransactionGenerator {

    static Random rand = new Random();

    FraudulentTransactionGenerator() {
        throw new IllegalStateException("Utility class");
    }

    public static Map buildTransaction() {
        Faker faker = new Faker(new Locale("en-US"));
        Map<String, Object> jsonMap = new HashMap<>();
        Map<String, Object> jsonLocation = new HashMap<>();
        jsonMap.put("card_number", faker.business().creditCardNumber());
        jsonMap.put("posting_date", new Date());
        jsonMap.put("merchant_id", faker.number().numberBetween(1,50000));
        jsonMap.put("amount", randomDollarAmount(1,5000));
        jsonMap.put("merchant_category",faker.company().industry());
        jsonMap.put("address",faker.address().streetAddress());
        jsonMap.put("city",faker.address().cityName());
        jsonMap.put("zipcode",faker.address().zipCode());
        jsonMap.put("state",faker.address().stateAbbr());
        jsonMap.put("fraud",true);
        jsonLocation.put("lat",Float.parseFloat(faker.address().latitude()));
        jsonLocation.put("lon",Float.parseFloat(faker.address().longitude()));
        jsonMap.put("location",jsonLocation);
        return jsonMap;

    }

    private static float randomDollarAmount(int min, int max) {
        return rand.nextFloat() * (max - min) + min;
    }

}

