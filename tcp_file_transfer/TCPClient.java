import java.io.*;
import java.net.*;
public class TCPClient{
    public static void main(String args[]){
        try(Socket socket = new Socket("localhost",1099);){
            byte[] buffer = new byte[4096];
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream("../dataset.csv"));
            int bytesRead;
            OutputStream os = socket.getOutputStream() ;
            while((bytesRead = bis.read(buffer))!=-1){
                System.out.println(bytesRead+ " bytes of data read");
                os.write(buffer,0,bytesRead);

            }
            os.flush();
        bis.close();
        socket.close();
System.out.println("File Sent Successfully");
        }
        catch(Exception e){

        }
    }
}