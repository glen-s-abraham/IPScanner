package net;
import java.net.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
public class IPscanner implements ActionListener{
	JFrame frame;
	JLabel ipAddress;
	JTextField ipstart,limit;
	JButton scan;
	JTextArea ipTable;
	JScrollPane scroll;
	InetAddress ip;
	IPscanner()
	{
		frame=new JFrame("IP scanner");
		frame.setSize(440,500);
		ipAddress=new JLabel("IP Range");
		ipAddress.setBounds(10,20,100,50);
		ipstart=new JTextField("192.168.1.1");
		limit=new JTextField("0");
		ipstart.setBounds(120,20,100,50);
		limit.setBounds(240,20,50,50);
		scan=new JButton("Scan");
		scan.setBounds(300,20,100,50);
		ipTable=new JTextArea();
		ipTable.setBounds(10,120,400,300);
		scroll=new JScrollPane(ipTable);
		scroll.setBounds(10,120,400,300);
		frame.add(ipAddress);
		frame.add(ipstart);
		frame.add(limit);
		frame.add(scan);
		frame.add(scroll);
		
		
		scan.addActionListener(this);
		frame.setLayout(null);
		frame.setVisible(true);
		
	}
	public static String sendPingRequest(String inetAddress) throws UnknownHostException, IOException 
	{ 
		InetAddress ip = InetAddress.getByName(inetAddress); 
		if (ip.isReachable(10000)) 
			return "alive";
		else 
			return "unreachable";
		
	} 
	
	public static void main(String args[])
	{
		new IPscanner();
	}
	public void actionPerformed(ActionEvent ae)
	{
		String address=ipstart.getText();
		String saddress=address.substring(0, address.lastIndexOf('.')+1);
		int lim=Integer.parseInt(limit.getText());
		
		
		String pingAddr;
		int ladress=Integer.parseInt(address.substring(address.lastIndexOf('.')+1,address.length()));
		
		int j=ladress;
		do
		{	InetAddress host;
			pingAddr=saddress;
			pingAddr+=j;			
			try {
					String str=sendPingRequest(pingAddr);
					ipTable.setText(ipTable.getText()+"\n"+pingAddr+" "+str);
				}catch(Exception e) {e.printStackTrace();}			
			j++;
		}while(j<=lim);
		
		
	}

}
