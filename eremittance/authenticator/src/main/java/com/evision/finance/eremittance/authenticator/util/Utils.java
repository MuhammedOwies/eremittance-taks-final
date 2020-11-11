package com.evision.finance.eremittance.authenticator.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Utils {
    private static final Map<Object, Thread> lock = new HashMap<>();

    public static String getPrincipal() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = (auth != null) ? auth.getPrincipal() : null;
        if (principal != null) {
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else {
                return "Anonymous";
            }
        } else return "Anonymous";
    }

    public static void lock(Object o) {
        do {
            synchronized (lock) {
                if (lock.get(o) == null) {
                    lock.put(o, Thread.currentThread());
                }
            }
        }
        while (lock.get(o) == null || (lock.get(o) != null && !lock.get(o).equals(Thread.currentThread())));
    }

    public static void release(Object o) {
        synchronized (lock) {
            lock.remove(o);
        }
    }

//    @SneakyThrows
//    public static synchronized String decrypt(String algorithm, String key, String iv, String cipher) {
//        Cipher c = Cipher.getInstance(algorithm);
//        c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Base64.getDecoder().decode(key), algorithm.substring(0, algorithm.indexOf("/"))), new IvParameterSpec(Base64.getDecoder().decode(iv)));
//        return new String(c.doFinal(Base64.getDecoder().decode(cipher)));
//    }
}