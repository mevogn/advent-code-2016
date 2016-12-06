package advent.impl;

import advent.DoorIDDecoder;

import java.math.BigInteger;
import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;

public class DoorIDDecoderImpl implements DoorIDDecoder {

    public String getDoorPassword(String input, boolean isPartA) {

        char[] password = {'x','x','x','x','x','x','x','x'};
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            BigInteger i = new BigInteger("0");
            for (int j = 0; j<8; ++j) {
                boolean found = false;
                while (!found) {
                    String newInput = input + i.toString();
                    md.update(newInput.getBytes());
                    byte[] digest = md.digest();
                    String hex = DatatypeConverter.printHexBinary(digest);
                    if (hex.startsWith("00000")){
                        found = true;
                        if (isPartA) {
                            password[j] = hex.charAt(5);
                        } else {
                            int sixthIndex = (int) hex.charAt(5);
                            if (sixthIndex > 47 && sixthIndex < 56) {
                                if (password[Character.getNumericValue(hex.charAt(5))] == 'x') {
                                    password[Character.getNumericValue(hex.charAt(5))] = hex.charAt(6);
                                } else {
                                    found =  false;
                                }
                            } else {
                                found = false;
                            }
                        }
                    }
                    i = i.add(BigInteger.ONE);
                }
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error - incorrect algorithm");
        }
        return new String(password);
    }
}
