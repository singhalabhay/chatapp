import java.io.*;
import java.net.*;
public class Client {
    Thread r1,r2;
    BufferedReader br;
    PrintWriter out;
    Socket socket;
    public void  StartReading(){
        System.out.println("Reader start");
        /* thread
        for reading
         */
        Runnable r1=()->{
            while(true){
                try{
                    String mssg;
                    mssg = br.readLine();
                    if(mssg.equals("exit")){
                        System.out.println("Client terminated the chat");
                        break;
                    }
                    System.out.println("Client:"+mssg);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        };
        new Thread(r1).start();
    }
    public void   StartWriting(){
        System.out.println("Writer start");
        /* thread
        for writing
         */
        Runnable r1=()->{
            while(true){
                try{
                    BufferedReader b1=new BufferedReader(new InputStreamReader(System.in));
                    String content=b1.readLine();
                    out.println(content);
                    out.flush();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        };
        new Thread(r2).start();
    }
    public Client(){
       try{
           System.out.println("Sending request to server");
      socket =new Socket("129.0.0.1",7777);
           System.out.println("Connection done");
       }catch (Exception e){
       e.printStackTrace();
       }
        try {
            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            out=new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        StartReading();
       StartWriting();
   }
    public static void main(String[] args) {
        System.out.println("this is client");
        new Client();
    }
}
