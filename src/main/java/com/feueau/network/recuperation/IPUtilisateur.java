package com.feueau.network.recuperation;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPUtilisateur {
    public static String getIPAddress() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            return localHost.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
