package com.pahlsoft.utilities.generator;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;

import java.util.*;

public class LocomotiveGenerator {

    LocomotiveGenerator() {
        throw new IllegalStateException("Utility class");
    }

    public static Map buildLocomotive() {
        Faker faker = new Faker(new Locale("en-US"));
        Map<String, Object> jsonMap = new HashMap<>();
        Map<String, Object> jsonLocation = new HashMap<>();


        // Timestamp for Last Update
        jsonMap.put("update_timestamp", new Date());

        // Location Information
        jsonLocation.put("lat",Float.parseFloat(faker.address().latitude()));
        jsonLocation.put("lon",Float.parseFloat(faker.address().longitude()));
        jsonMap.put("geoCoordinates",jsonLocation);

        jsonMap.put("altitude", faker.number().numberBetween(-100, 5000));
        jsonMap.put("directionOfTravel", faker.number().numberBetween(-1, 1));
        jsonMap.put("currentPositionUncertainty", faker.number().numberBetween(-1, 20));
        jsonMap.put("messageTypeId", faker.number().numberBetween(1,500));

        jsonMap.put("headEndMilePost", faker.number().numberBetween(1,10000));
        jsonMap.put("headEndMilePostPrefix", faker.internet().macAddress());
        jsonMap.put("headEndMilePostSuffix", faker.internet().macAddress());
        jsonMap.put("headEndTrackName", faker.harryPotter().character());
        jsonMap.put("headEndRailroadScac", faker.ancient().titan());
        jsonMap.put("headEndPtcSubDivDistrictId", faker.number().numberBetween(1,2000));

        jsonMap.put("rearEndMilePost", faker.number().numberBetween(1,10000));
        jsonMap.put("rearEndMilePostPrefix", faker.internet().macAddress());
        jsonMap.put("rearEndMilePostSuffix", faker.internet().macAddress());
        jsonMap.put("rearEndTrackName", faker.harryPotter().character());
        jsonMap.put("rearEndRailroadScac", faker.ancient().titan());
        jsonMap.put("rearEndPtcSubDivDistrictId", faker.number().numberBetween(1,2000));

        jsonMap.put("locoId", faker.number().numberBetween(1,3000));
        jsonMap.put("trainId", faker.number().numberBetween(1,1500));
        jsonMap.put("locomotivePTCState", faker.number().randomDigit());
        jsonMap.put("locomotivePTCStateSummary", faker.number().randomDigit());
        jsonMap.put("speed", faker.number().numberBetween(0,70));
        jsonMap.put("currentPositionUncertainty", faker.number().numberBetween(0,70));
        jsonMap.put("flags", faker.number().numberBetween(0,70));

        // Header Fields
        jsonMap.put("messageBody", faker.harryPotter().quote());
        jsonMap.put("messageNumber", faker.number().randomNumber());
        jsonMap.put("messageTime", new Date());
        jsonMap.put("routingQOS", faker.number().randomNumber());
        jsonMap.put("rrSpecConfigFilesetVersion", faker.number().numberBetween(0,4));
        jsonMap.put("reasonForSending", faker.number().randomNumber());
        jsonMap.put("specialHandling", faker.number().randomNumber());
        jsonMap.put("sourceAddress", faker.address().fullAddress());  //
        jsonMap.put("messageVersion", faker.number().numberBetween(1,30));
        jsonMap.put("networkPreference", faker.number().numberBetween(0,70));
        jsonMap.put("onBoardSoftwareVer", faker.number().numberBetween(0,15));
        jsonMap.put("protocolVersion", faker.number().numberBetween(1,6));
        jsonMap.put("ptcAuthorityReferenceNumber", faker.number().randomDigit());
        jsonMap.put("qosClass", faker.number().numberBetween(25,100));
        jsonMap.put("qosDeliveryNtfnRequested", faker.random().nextBoolean());
        jsonMap.put("qosNtwkCompRequested", faker.random().nextBoolean());
        jsonMap.put("qosOutcomeNtfnRequested", faker.random().nextBoolean());
        jsonMap.put("qosPriority", faker.number().numberBetween(1,1000));
        jsonMap.put("gpsPositionValidity", faker.number().numberBetween(0,70));
        jsonMap.put("clearanceNumber", faker.number().numberBetween(0,70));
        jsonMap.put("commonConfigFilesetVersion", faker.harryPotter().quote());
        jsonMap.put("destinationAddress", faker.address().fullAddress());

        // PTC Info
        jsonMap.put("reasonForPTCReport", faker.number().randomDigit());
        jsonMap.put("wiuId", faker.number().randomDigit());
        jsonMap.put("subscriptionTimeframe", faker.number().randomDigit());

        // 1080 Stats
        jsonMap.put("distanceLapsedFrom1080Message", faker.number().randomDigit());
        jsonMap.put("timeLapsedFrom1080Message", faker.number().randomDigit());


        // Human Information
        jsonMap.put("employeeId",generateUUID());
        jsonMap.put("name.first", faker.name().firstName());
        jsonMap.put("name.middle", getRandomLetter());
        jsonMap.put("name.first", faker.name().lastName());



        return jsonMap;
    }

    private static synchronized String generateUUID() {
        return UUID.randomUUID().toString();
    }


    private static synchronized String getRandomLetter() {
        Random rnd = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return String.valueOf(characters.charAt(rnd.nextInt(characters.length())));
    }
}

