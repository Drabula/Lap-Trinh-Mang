/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bai1;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
/**
 *
 * @author drabu
 */
public class Bai1 {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            System.out.println("Connections Succes");
        } catch (IOException e) {
            System.out.println("No Internet");
        }
    }
}
