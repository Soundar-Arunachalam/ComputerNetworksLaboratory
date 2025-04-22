import java.net.*;
import java.io.*;
public class Server{
    public static void main(String args[]) throws Exception{
        ServerSocket server = new ServerSocket(1099);
        Socket socket =server.accept();
        int rn = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        
        while(rn <= 10){
            String msg = "";
                if((msg = br.readLine())!=null){
                    if(msg.startsWith("[DATA]")){
                        int packetNum = Integer.parseInt(msg.split(":")[1]);
                        if(packetNum == rn){
                            rn = rn+1;
                        }
                            System.out.println("[RECEIVED] "+packetNum);
                            out.println("[ACK]"+rn);
                    }
                }
                if(msg.equals("[END]"))break;
        }
    }
}