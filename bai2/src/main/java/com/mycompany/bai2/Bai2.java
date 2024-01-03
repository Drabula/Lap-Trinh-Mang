/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bai2;
import java.net.InetAddress;
import java.net.UnknownHostException;
/**
 *
 * @author drabu
 */
public class Bai2 {

    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            String ipAddress = address.getHostAddress();
            System.out.println("Ip Web: " + ipAddress);
        } catch (UnknownHostException e) {
            System.out.println("Can't get IP Web.");
        }
    }
}
