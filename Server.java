import java.io.*;
import java.net.*;
public class Server {
    
    ServerSocket server;
    Socket socket;
    BufferedReader br;
     PrintWriter out;
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
        Runnable r2=()->{
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
    public Server(){
       try {
           server = new ServerSocket(7777);
           System.out.println("Server is ready to accept of connection");
           System.out.println("waiting");
           socket=server.accept();
           br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
           out=new PrintWriter(socket.getOutputStream());
           StartReading();
           StartWriting();
       } catch(Exception e){
           e.printStackTrace();
       }
    }
    public static void main(String[] args) {
        System.out.println("This server runing");
        new Server();
    }
}
