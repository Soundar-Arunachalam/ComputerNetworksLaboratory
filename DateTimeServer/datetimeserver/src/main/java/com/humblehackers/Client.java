package com.humblehackers;
import java.io.*;
import java.net.*;
import java.util.*;
public class Client {
    public static void main(String[] args) {
        try {
            // Create a socket object
            Socket socket = new Socket("localhost", 1099);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Date from server: "+br.readLine());
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
