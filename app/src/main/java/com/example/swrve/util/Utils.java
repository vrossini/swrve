package com.example.swrve.util;

import java.net.URL;
import java.net.URISyntaxException;
import java.net.MalformedURLException;

public class Utils {
    public static boolean isValidURL(String url) throws MalformedURLException, URISyntaxException {
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException e) {
            throw new MalformedURLException();
        } catch (URISyntaxException e) {
            throw new URISyntaxException(e.getInput(), e.getReason());
        }
    }
}
