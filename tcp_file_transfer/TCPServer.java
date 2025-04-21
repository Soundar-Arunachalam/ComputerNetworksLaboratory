
import java.net.*;
import java.io.*;
public class TCPServer{
    private static final int PORT = 1099;
    public static void main(String[] args) {
        try(ServerSocket socket = new ServerSocket(PORT)){
            Socket clientSocket = socket.accept();
         InputStream in = clientSocket.getInputStream();
            FileOutputStream fos = new FileOutputStream("received.csv");
            byte[] buffer = new byte[4096];
            int bytesRead;
            while((bytesRead=in.read(buffer))!=-1){
                System.out.println(bytesRead+" bytes read");
                fos.write(buffer,0,bytesRead);
            }
            fos.close();
System.out.println("File received successfully");

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}