package com.distributed.system.project;


import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class Main {

    public static void main(String[] args) throws Exception {
        KeyPair keyPair=security.buildKeyPair();
        byte[] a=security.encrypt(keyPair.getPublic(),"Hussein");
        for (int i=0;i<a.length;i++){
            System.out.print((char)a[i]);
        }
        System.out.println();
        System.out.println(security.decrypt(keyPair.getPrivate(),a));
        byte[] b=security.decrypt(keyPair.getPrivate(),a);
        for (int i=0;i<b.length;i++){
            System.out.print((char)b[i]);
        }
    }
}
