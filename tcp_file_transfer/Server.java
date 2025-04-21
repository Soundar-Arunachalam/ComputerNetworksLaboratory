import java.net.*;
import java.io.*;
public class Server{
    public static void main(String args[]){
        try{
            DatagramSocket socket = new DatagramSocket(4000);
            byte[] buffer = new byte[1024];
            int packetnumber = 0;
            DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
            socket.receive(packet);
            InetAddress clientIP = packet.getAddress();
            int clientPort = packet.getPort();
            System.out.println("Client IP: " + clientIP);
            System.out.println("Client Port: " + clientPort);
            System.out.println("Receiving file...");
            FileOutputStream fos = new FileOutputStream ("received_dataset.csv");
            boolean receiving = true;
            while(receiving){
                socket.receive(packet);
                byte[] data = packet.getData();
                int length = packet.getLength();
                String str = new String(data,0,length);
                System.out.println("Received packet number: " + ++packetnumber);
                if (str.equals("end")){
                    receiving = false;
                    break;
                }
                fos.write(data,0,length);
                fos.flush();
            }
            socket.close();
            fos.close();
            System.out.println("File received successfully.");
            System.out.println("Total packets received: " + packetnumber);

        }
        catch(Exception e){

        }
    }
}