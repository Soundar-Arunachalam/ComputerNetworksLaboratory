package com.humblehackers;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.PrintWriter;
import java.util.Date;
public class ClientThread extends Thread{
    private Socket socket;
    ClientThread( Socket socket){
         this.socket = socket;
    }
    public void run(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pw = new PrintWriter(socket.getOutputStream());){
            pw.println(new Date());
        }
        catch(Exception e ){
            e.printStackTrace();
        }
    }
}
