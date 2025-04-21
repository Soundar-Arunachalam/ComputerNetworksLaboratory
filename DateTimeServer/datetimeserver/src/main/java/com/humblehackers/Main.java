package com.humblehackers;
import java.net.*;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try(ServerSocket server = new ServerSocket(1099);){
            Socket client = server.accept();
            System.out.println("Client connected");
            new ClientThread(client).start();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}