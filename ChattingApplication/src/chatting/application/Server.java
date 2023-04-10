package chatting.application;
import static chatting.application.Client.a1;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;

public class Server implements ActionListener
{
    JTextField text;
    JPanel a1;
    static Box vertical = Box.createVerticalBox();
    static JFrame f=new JFrame();
    static DataOutputStream dout;
    
    Server()
    {
       f.setLayout(null);
       JPanel p1=new JPanel();
       p1.setBackground(Color.ORANGE);
       p1.setBounds(0,0,450,70);
       p1.setLayout(null);
       f.add(p1);
       
       ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("Icons/Arrow.png"));
       Image i2=i1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
       ImageIcon i3=new ImageIcon(i2);
       JLabel back = new JLabel(i3);
       back.setBounds(5,20,25,25);
       p1.add(back);
       
       back.addMouseListener(new MouseAdapter())
               {
                   public void mouseClicked(MouseEvent ae)
                   {
                       System.exit(0);
                   }
                };
       ImageIcon i4= new ImageIcon(ClassLoader.getSystemResource("Icons/Women.png"));
       Image i5=i4.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
       ImageIcon i6=new ImageIcon(i5);
       JLabel profile = new JLabel(i6);
       profile.setBounds(40,10,50,50);
       p1.add(profile);
       
       ImageIcon i7= new ImageIcon(ClassLoader.getSystemResource("Icons/VC1.png"));
       Image i8=i7.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
       ImageIcon i9=new ImageIcon(i8);
       JLabel video = new JLabel(i9);
       video.setBounds(280,20,40,40);
       p1.add(video);
       
       ImageIcon i10= new ImageIcon(ClassLoader.getSystemResource("Icons/Call.png"));
       Image i11=i10.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
       ImageIcon i12=new ImageIcon(i11);
       JLabel phone = new JLabel(i12);
       phone.setBounds(350,20,40,40);
       p1.add(phone);
       
       ImageIcon i13= new ImageIcon(ClassLoader.getSystemResource("Icons/dot1.png"));
       Image i14=i13.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
       ImageIcon i15=new ImageIcon(i14);
       JLabel dot = new JLabel(i15);
       dot.setBounds(400,20,40,40);
       p1.add(dot);
          
       JLabel name=new JLabel("Pooja");
       name.setBounds(110,15,100,30);
       name.setForeground(Color.BLACK);
       name.setFont(new Font("SAN_SERIF",Font.BOLD,18));
       p1.add(name);
       
       JLabel status=new JLabel("Active Now");
       status.setBounds(110,35,100,30);
       status.setForeground(Color.BLACK);
       status.setFont(new Font("SAN_SERIF",Font.BOLD,12));
       p1.add(status);
       
       a1=new JPanel();
       a1.setBounds(5, 75, 425, 520);
       f.add(a1);
       
       text= new JTextField();
       text.setBounds(5,600,310,40);
       text.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
       f.add(text);
       
       JButton send=new JButton("Send");
       send.setBounds(320, 600, 110, 40);
       send.setBackground(Color.darkGray);
       send.setForeground(Color.WHITE);
       send.addActionListener(this);
       text.setFont(new Font("SAN_SERIF",Font.PLAIN,15));
       f.add(send);
       
       f.setSize(450,700);
       f.setLocation(200,50);
       f.getContentPane().setBackground(Color.WHITE);
       f.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        try{
        String out=text.getText();
      
        
        JPanel p2=formatLabel(out);
        
                
        a1.setLayout(new BorderLayout());
        
        JPanel right =new JPanel(new BorderLayout());
        right.add(p2, BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));
        
        a1.add(vertical,BorderLayout.PAGE_START);
        
        dout.writeUTF(out);
        
        text.setText("");
        
        f.repaint();
        f.invalidate();
        f.validate();
        }
        catch(Exception e)
                {
                    e.printStackTrace();  
                }
        
    }
    public static JPanel formatLabel(String out){
        JPanel panel =new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel output=new JLabel("<html><p style=\"width: 140px\">"+ out + "</p></html>");
        output.setFont(new Font("Tahoma",Font.PLAIN,15));
        panel.setBackground(Color.LIGHT_GRAY);
        output.setOpaque(false);
        output.setBorder(new EmptyBorder(10,10,10,50));
        
        panel.add(output);
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        JLabel time=new JLabel();
        time.setText(sdf.format(cal.getTime()));
        
        panel.add(time);
        
        return panel;
    }
    
   public static void main(String[] args){
       new Server();
       try{
           ServerSocket ss=new ServerSocket(6001);
           while(true)
           {
              Socket s= ss.accept();
              DataInputStream din= new DataInputStream(s.getInputStream());
              dout=new DataOutputStream(s.getOutputStream());
              
              while(true)
              {
                  String msg=din.readUTF();
                  JPanel panel=formatLabel(msg);
                  
                  JPanel left=new JPanel(new BorderLayout());
                  left.add(panel,BorderLayout.LINE_START);
                  vertical.add(left);
                  f.validate();
                  
                  
              }
           }
       }catch (Exception e){
           e.printStackTrace();
       }
   } 
}
