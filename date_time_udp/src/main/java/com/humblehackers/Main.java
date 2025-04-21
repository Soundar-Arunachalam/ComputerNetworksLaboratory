
import java.net.*;
import java.io.*;
public class Main {
    public static void main(String[] args) {
        try {
            // Create a socket to listen on port 12345
            DatagramSocket socket = new DatagramSocket(12345);
            System.out.println("Server is running...");

            while (true) {
                // Create a buffer to hold incoming data
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                // Receive the packet
                socket.receive(packet);

                // Convert the received data to a string
                String receivedData = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received: " + receivedData);

                // Get the current date and time
                String dateTime = java.time.LocalDateTime.now().toString();

                // Send the date and time back to the client
                byte[] responseData = dateTime.getBytes();
                DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, packet.getAddress(), packet.getPort());
                socket.send(responsePacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
    }
}
}