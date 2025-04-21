
import java.net.*;
public class Client {
    public static void main(String[] args) {
        try {
            // Create a socket to send and receive data
            DatagramSocket socket = new DatagramSocket();

            // Prepare the data to be sent
            String requestData = "Requesting current date and time";
            byte[] requestDataBytes = requestData.getBytes();

            // Create a packet to send the data to the server
            InetAddress serverAddress = InetAddress.getByName("localhost");
            DatagramPacket requestPacket = new DatagramPacket(requestDataBytes, requestDataBytes.length, serverAddress, 12345);
            socket.send(requestPacket);

            // Prepare a buffer to receive the response
            byte[] responseBuffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);

            // Receive the response from the server
            socket.receive(responsePacket);

            // Convert the response data to a string and print it
            String responseData = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println("Received from server: " + responseData);

            // Close the socket
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
}
    }
    }