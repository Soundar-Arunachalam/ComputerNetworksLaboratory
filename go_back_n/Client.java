import java.net.*;
import java.io.*;
public class Client{
    private static final int TIMEOUT_MS = 1000;
    private static final int totalPackets= 10;
    private static final int windowSize = 4;
    public static void main(String args[]) throws Exception{
    String host = args[0];
    int port = Integer.parseInt(args[1]);
    Socket socket = new Socket(host,port);
    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    
    PrintWriter out = new PrintWriter(socket.getOutputStream());
    


        int sf = 0,sn = 0; // sf -> first packet to receive acknowledgement, sn-> next packet to send to the server
        long[] timers = new long[totalPackets];

        while(sf < totalPackets){//still need to receive acknowledgement
            while(sn < sf+windowSize && sn < totalPackets){
                System.out.println("[SEND] packet "+sn);
                out.println("[DATA]:"+sn);
                timers[sn] = System.currentTimeMillis();
                sn++;
            }
            socket.setSoTimeout(100);
            try {
                String response = in.readLine();
                if(response!=null && response.startsWith("[ACK]")){
                    int packetNum = Integer.parseInt(response.split(":")[1]);
                    sf = packetNum+1;
                }
                }
            catch(SocketTimeoutException e){

            }
            if(sf<sn && (System.currentTimeMillis() - timers[sf]) > TIMEOUT_MS){
                System.out.println("[TIMEOUT] for packet "+sf);
                sn = sf;
            }
    }
    out.println("[END]");
}
}