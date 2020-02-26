
package klijentzaasigment;



import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;


public class KlijentZaAsigment {

    
    public static void main(String[] args) throws InterruptedException {
        niti mt = new niti();
        mt.start();
        
        Frame okvir= new Frame();
        LayoutManager layout = new FlowLayout();
        okvir.setLayout(layout);
    
        okvir.addWindowListener(new WindowAdapter()
   {
         @Override
         public void windowClosing(WindowEvent we){
         System.exit(0);
         }
   });

        Label naslov = new Label("unesite 2 broja da izracunate zbir");
        okvir.add(naslov);
        TextField prvi = new TextField();
        TextField drugi = new TextField(); 
        
        Label treci = new Label();
        
     
        Button buton = new Button ("Saberi");
        buton.addActionListener((ActionEvent e) -> {
            String broj1 = prvi.getText();
            String broj2 = drugi.getText();
            
            
             try (DatagramSocket dClient = new DatagramSocket();) 
        {   
        
          
            byte[] buff = new byte[256];
            byte[] buff1 = new byte[256];
         buff=broj1.getBytes();
         buff1=broj2.getBytes();
           
            InetSocketAddress ep = new InetSocketAddress("127.0.0.1", 1001);
            DatagramPacket p = new DatagramPacket(buff, buff.length, ep);
            DatagramPacket s = new DatagramPacket(buff1,buff1.length, ep);
            
            dClient.send(p);
             dClient.send(s);
        
            buff1 = new byte[256];
        
             s = new DatagramPacket(buff1,buff1.length, ep);
              dClient.receive(s);
              String prevod= new String(s.getData(),0,s.getLength());
           treci.setText(prevod);
            System.out.println(prevod);
          

        } 
             catch (IOException ex) {
            System.out.println(ex.getMessage());  
      
   
        }
        });
        
    
        okvir.add(prvi);
          okvir.add(drugi);
           okvir.add(buton);
           
           Label o = new Label("ZBIR:");
           okvir.add(o); 
           okvir.add(treci);
               okvir.setSize(300, 300);
         Thread.sleep(100);
        okvir.setVisible(true);
        
        
        
   
    }
}

   


