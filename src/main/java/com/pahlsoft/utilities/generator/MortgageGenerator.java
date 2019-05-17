package com.pahlsoft.utilities.generator;

import com.github.javafaker.Faker;

import java.util.*;

public class MortgageGenerator {

    MortgageGenerator() {
        throw new IllegalStateException("Utility class");
    }

    public static Map buildAccount() {
        Faker faker = new Faker(new Locale("en-US"));
        Map<String, Object> jsonMap = new HashMap<>();

        jsonMap.put("last_update", new Date());

        jsonMap.put("loan.platform", getRandomPlatform(faker.number().numberBetween(0,8)));

        jsonMap.put("loan.id", generateUUID());
        jsonMap.put("loan.type", getLoanType(faker.number().numberBetween(0,2)));
        jsonMap.put("loan.amount", randomDollarAmount(30000,200000));
        jsonMap.put("loan.term.num_length", getLoanLength(faker.number().numberBetween(0,2)));
        jsonMap.put("loan.term.cycle", getLoanCycle(faker.number().numberBetween(0,1)));

        jsonMap.put("location_via_ip", faker.internet().ipV4Address());

        jsonMap.put("status.transaction", getTransactionStatus(faker.number().numberBetween(0,3)));
        jsonMap.put("status.application", getApplicationStatus(faker.number().numberBetween(0,3)));

        jsonMap.put("demographics.income", faker.number().numberBetween(15000,100000));
        jsonMap.put("demographics.occupation", faker.job().title());
        jsonMap.put("demographics.age", faker.number().numberBetween(18,99));
        jsonMap.put("demographics.credit_score.equifax", faker.number().numberBetween(300,850));
        jsonMap.put("demographics.credit_score.experian", faker.number().numberBetween(300,850));
        jsonMap.put("demographics.credit_score.transunion", faker.number().numberBetween(300,850));
        jsonMap.put("customer_experience_score",faker.number().numberBetween(1,10));
        jsonMap.put("customer_experience_score",faker.number().numberBetween(1,10));


        return jsonMap;
    }

    private static  String getRandomPlatform(int randomNumber) {
        ArrayList<String> types = new ArrayList<>();
        types.add("Loan Advisor Portal (Portal)");
        types.add("Correspondent Assignment Center (CAC)");
        types.add("Loan Product Advisor (LPA)");
        types.add("Loan Collateral Advisor Extension (LCAE)");
        types.add("Loan Quality Advisor (LQA))");
        types.add("Condo Project Advisor (CoPA)");
        types.add("Loan Closing Advisor (LCLA)");
        types.add("Loan Selling Advisor (LSA)");
        types.add("Loan Coverage Advisor (LCVA)");
        return types.get(randomNumber);
    }

    // [approved, pending_approval, credit_review, decline]
    private static String getApplicationStatus(int randomNumber) {
        ArrayList<String> types = new ArrayList<>();
        types.add("approved");
        types.add("pending_approval");
        types.add("credit_review");
        types.add("decline");

        return types.get(randomNumber);
    }

    private static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    private static String getLoanType(int randomNumber) {
        ArrayList<String> types = new ArrayList<>();
        types.add("single-family");
        types.add("multi-family");
        types.add("commercial");
        return types.get(randomNumber);
    }

    // [started, processing, completed, failure]
    private static String getTransactionStatus(int randomNumber) {
        ArrayList<String> types = new ArrayList<>();
        types.add("started");
        types.add("processing");
        types.add("completed");
        types.add("failure");
        return types.get(randomNumber);
    }

    private static String getLoanCycle(int randomNumber) {
        ArrayList<String> types = new ArrayList<>();
        types.add("monthly");
        types.add("yearly");
        return types.get(randomNumber);
    }

    private static int getLoanLength(int randomNumber) {
        ArrayList<Integer> loanLengths = new ArrayList<>();
        loanLengths.add(15);
        loanLengths.add(30);
        loanLengths.add(35);
        return loanLengths.get(randomNumber);
    }

    private static synchronized int randomDollarAmount(int min, int max) {
        Random rand = new Random();

        return Math.abs(rand.nextInt() * (max - min) + min);
    }


}
