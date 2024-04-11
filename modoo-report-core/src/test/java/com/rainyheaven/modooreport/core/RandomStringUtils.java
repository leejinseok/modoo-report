package com.rainyheaven.modooreport.core;

import java.util.Random;

public class RandomStringUtils {

    // Define the characters that can be used in the random string
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    // Method to generate a random string of a specified length
    public static String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int length = 10; // Change the length as needed
        String randomString = generateRandomString(length);
        System.out.println("Random String: " + randomString);
    }

}
