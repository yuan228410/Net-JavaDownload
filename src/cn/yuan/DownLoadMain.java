package cn.yuan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument.BranchElement;

import org.omg.IOP.TAG_RMI_CUSTOM_MAX_STREAM_FORMAT;

public class DownLoadMain {

	public static void main(String[] args) {
		JFrame jFrame=new JFrame("下载程序");
		jFrame.setSize(600, 400);
		jFrame.setLocation(100, 100);
		JPanel jPanel=new JPanel();
		JLabel label=new JLabel("Please input URL:");
		JTextField jTextField=new JTextField(30);
		jPanel.add(label);
		jPanel.add(jTextField);
		jFrame.getContentPane().add(jPanel, "North");
		JTextArea jTextArea=new JTextArea();
		jFrame.getContentPane().add(jTextArea, "Center");
		JButton jButton=new JButton("DownLoad");
		jFrame.getContentPane().add(jButton,"South");
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String str=jTextField.getText();
				URL url = null;
				try {
					url = new URL(str);
					URLConnection urlConnection=url.openConnection();
					String line=System.getProperty("line.separator");
					jTextArea.append(url.getHost());
					jTextArea.append(line);
					jTextArea.append("Port:"+url.getDefaultPort());
					jTextArea.append(line);
					jTextArea.append("ContentType:"+urlConnection.getContentType());
					jTextArea.append("ContentLength:"+urlConnection.getContentLength());
				InputStream iStream=urlConnection.getInputStream();
				//InputStreamReader inputStreamReader=new InputStreamReader(iStream);
				//BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
				FileOutputStream fileOutputStream=new FileOutputStream("1.html");
				//String strLine;
				//while ((strLine=bufferedReader.readLine())!=null) {
				int data;
				while ((data=iStream.read())!=-1) {
				//fileOutputStream.write(strLine.getBytes());
				//fileOutputStream.write(line.getBytes());
					fileOutputStream.write(data);
					
				}
				//bufferedReader.close();
				iStream.close();
				fileOutputStream.close();
				} catch (Exception el){
					el.printStackTrace();
				}
				
			}
		});
		jFrame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		jFrame.setVisible(true);
	}
}
