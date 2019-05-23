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

        jsonMap.put("loan.platform", getRandomPlatform(randomNumber(1,9)));

        jsonMap.put("loan.id", generateUUID());
        jsonMap.put("loan.type", getLoanType(randomNumber(1,3)));
        jsonMap.put("loan.amount", randomDollarAmount(30000,200000));
        jsonMap.put("loan.term.num_length", getLoanLength(randomNumber(1,3)));
        jsonMap.put("loan.term.cycle", getLoanCycle(randomNumber(1,2)));

        jsonMap.put("location_via_ip", faker.internet().ipV4Address());

        jsonMap.put("status.transaction", getTransactionStatus(randomNumber(1,4)));
        jsonMap.put("status.application", getApplicationStatus(randomNumber(1,4)));

        jsonMap.put("demographics.income", randomDollarAmount(15000,100000));
        jsonMap.put("demographics.occupation", faker.job().title());
        jsonMap.put("demographics.age", randomNumber(18,99));
        jsonMap.put("demographics.credit_score.equifax", randomNumber(300,850));
        jsonMap.put("demographics.credit_score.experian", randomNumber(300,850));
        jsonMap.put("demographics.credit_score.transunion", randomNumber(300,850));
        jsonMap.put("customer_experience_score",randomNumber(1,10));

        jsonMap.put("incident.status",getIncidentStatus(randomNumber(1,3)));
        jsonMap.put("incident.description", faker.chuckNorris().fact());

        return jsonMap;
    }

    private static String getIncidentStatus(int randomIncidentStatus) {
        String[] types = new String[4];
        types[1] = "open";
        types[2] = "closed";
        types[3] = "triage";
        return types[randomIncidentStatus];
    }

    private static  String getRandomPlatform(int randomNumber) {

        String[] types = new String[10];

        types[1] = "Loan Advisor Portal (Portal)";
        types[2] = "Correspondent Assignment Center (CAC)";
        types[3] = "Loan Product Advisor (LPA)";
        types[4] = "Loan Collateral Advisor Extension (LCAE)";
        types[5] = "Loan Quality Advisor (LQA))";
        types[6] = "Condo Project Advisor (CoPA)";
        types[7] = "Loan Closing Advisor (LCLA)";
        types[8] = "Loan Selling Advisor (LSA)";
        types[9] = "Loan Coverage Advisor (LCVA)";
        return types[randomNumber];
    }

    // [approved, pending_approval, credit_review, decline]
    private static String getApplicationStatus(int randomApplicationStatus) {
        String[] types = new String[5];
        types[1] = "approved";
        types[2] = "pending_approval";
        types[3] = "credit_review";
        types[4] = "decline";

        return types[randomApplicationStatus];
    }

    private static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    private static String getLoanType(int randomLoanType) {
         String[] types = new String[4];
          types[1]  = "single-family";
          types[2] = "multi-family";
          types[3] = "commercial";
        return types[randomLoanType];
    }

    // [started, processing, completed, failure]
    private static String getTransactionStatus(int randomStatus) {
        String[] types = new String[5];
        types[1]  = "started";
        types[2] = "processing";
        types[3] = "completed";
        types[4] = "failure";
        return types[randomStatus];
    }

    private static String getLoanCycle(int randomLoanCycle) {
        String[] types = new String[3];
        types[1] = "monthly";
        types[2] = "yearly";
        return types[randomLoanCycle];
    }

    private static int getLoanLength(int randomLoandLength) {
        Integer[] types = new Integer[4];
        types[1] = 15;
        types[2] = 30;
        types[3] = 35;
        return types[randomLoandLength];
    }

    private static synchronized int randomDollarAmount(int min, int max) {
        Random rand = new Random();
        return Math.abs(rand.nextInt() * (max - min) + min);
    }

    private static synchronized int randomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

}
