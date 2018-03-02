package de.inces.nearcon.backend.auth;

import org.apache.commons.validator.routines.InetAddressValidator;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import de.inces.nearcon.core.util.Strings;

public class HttpProxyHeaders {

    public static final String X_FORWARDED_FOR = "X-Forwarded-For";
    public static final String X_REAL_IP = "X-Real-Ip";

    private static InetAddressValidator validator = InetAddressValidator.getInstance();

    public static String extractRemoteAddress(String xffHeader, String realIpHeader) {
        if (validator.isValidInet4Address(realIpHeader)) {
            return realIpHeader;
        }
        if (xffHeader != null) {
            String[] addresses = xffHeader.split(Strings.Comma.$());
            String clientAddress = addresses[0];
            if (validator.isValidInet4Address(clientAddress)) {
                return clientAddress;
            }
        }
        return null;
    }

}
