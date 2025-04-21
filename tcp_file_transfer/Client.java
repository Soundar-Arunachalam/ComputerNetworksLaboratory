import java.net.*;
import java.io.*;
public class Client{
    public static void main(String args[]) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverIP = InetAddress.getByName("localhost");
        int serverPort = 4000;
        String filePath = "../dataset.csv";
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int bytesRead;
        int packetnumber = 0;
        DatagramPacket packet;
        System.out.println("Sending file to server...");

        while((bytesRead = fis.read(buffer)) != -1){
            packet = new DatagramPacket(buffer, bytesRead, serverIP, serverPort);
            socket.send(packet);
            System.out.println("Sent packet number: " + ++packetnumber);
        }
        String endMessage = "end";
        packet = new DatagramPacket(endMessage.getBytes(), endMessage.length(), serverIP, serverPort);
        socket.send(packet);
        System.out.println("File sent successfully.");
        System.out.println("Total packets sent: " + packetnumber);
        socket.close();
        fis.close();
        System.out.println("Socket closed.");
        
    }
}