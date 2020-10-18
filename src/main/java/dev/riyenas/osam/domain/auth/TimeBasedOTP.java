package dev.riyenas.osam.domain.auth;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;

public class TimeBasedOTP {

    private TimeBasedOTP() {}

    private static final int[] DIGITS_POWER = {1,10,100,1000,10000,100000,1000000,10000000,100000000};

    private static byte[] hmac_sha1(String crypto, byte[] keyBytes, byte[] text) {
        try {
            Mac hmac;
            hmac = Mac.getInstance(crypto);
            SecretKeySpec macKey =
                    new SecretKeySpec(keyBytes, "RAW");
            hmac.init(macKey);
            return hmac.doFinal(text);
        } catch (GeneralSecurityException gse) {
            throw new UndeclaredThrowableException(gse);
        }
    }

    private static byte[] hexStrToBytes(String hex) {
        byte[] bArray = new BigInteger("10" + hex, 16).toByteArray();

        byte[] ret = new byte[bArray.length - 1];

        for (int i = 0; i < ret.length ; i++)
            ret[i] = bArray[i+1];

        return ret;
    }

    public static String calcSteps(Long time, Long T0, Long X) {
        long T = (time - T0) / X;

        String steps = "0";
        steps = Long.toHexString(T).toUpperCase();

        while (steps.length() < 16) {
            steps = "0" + steps;
        }

        return steps;
    }

    public static String generateTOTP(String key, String time, String returnDigits) {
        return generateTOTP(key, time, returnDigits, CryptoType.HmacSHA1);
    }

    public static String generateTOTP256(String key, String time, String returnDigits) {
        return generateTOTP(key, time, returnDigits, CryptoType.HmacSHA256);
    }

    public static String generateTOTP512(String key, String time, String returnDigits) {
        return generateTOTP(key, time, returnDigits, CryptoType.HmacSHA512);
    }

    public static String generateTOTP(String key, String time, String returnDigits, CryptoType type) {
        int codeDigits = Integer.decode(returnDigits).intValue();
        String result = null;
        byte[] hash;

        while(time.length() < 16 ) {
            time = "0" + time;
        }

        byte[] msg = hexStrToBytes(time);
        byte[] k = hexStrToBytes(key);

        hash = hmac_sha1(type.toString(), k, msg);

        int offset = hash[hash.length - 1] & 0xf;

        int binary = ((hash[offset] & 0x7f) << 24) |
                ((hash[offset + 1] & 0xff) << 16) |
                ((hash[offset + 2] & 0xff) << 8) |
                (hash[offset + 3] & 0xff);

        int otp = binary % DIGITS_POWER[codeDigits];

        result = Integer.toString(otp);

        while (result.length() < codeDigits) {
            result = "0" + result;
        }

        return result;
    }
}