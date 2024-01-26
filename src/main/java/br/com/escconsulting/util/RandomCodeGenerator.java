package br.com.escconsulting.util;

import java.security.SecureRandom;

/**
 *
 * @author Ederson Sergio Monteiro Coelho
 *
 */
public class RandomCodeGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom random = new SecureRandom();

    private RandomCodeGenerator() {
    }

    public static String generateCode(int length) {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(randomIndex));
        }
        return code.toString();
    }
}