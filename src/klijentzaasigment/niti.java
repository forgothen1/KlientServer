
package klijentzaasigment;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class niti extends Thread{
    @Override
    public void run()
    {
         try (DatagramSocket dServer = new DatagramSocket(1001);)
        {
             byte[] buff = new byte[128];
            byte[] buff1 = new byte[128];
            while (true) {
                   System.out.println("Listening...");
                DatagramPacket p = new DatagramPacket(buff, buff.length);
                    dServer.receive(p);
                DatagramPacket s = new DatagramPacket(buff1, buff1.length);                   
                    dServer.receive(s);  
                String broj = new String(s.getData(), 0, s.getLength());
                String broj1 = new String(p.getData(), 0, p.getLength()); 
                  Integer broj3 =Integer.parseInt(broj);
                  Integer broj4 =Integer.parseInt(broj1);
                int rezultat=broj3+broj4;
                String rezultat1= String.valueOf(rezultat);        
                buff1=rezultat1.getBytes();
                  s=new DatagramPacket(buff1,buff1.length,s.getAddress(),s.getPort());
             
                System.out.println("Sending...");
               
          
                 dServer.send(s);
            }
 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
