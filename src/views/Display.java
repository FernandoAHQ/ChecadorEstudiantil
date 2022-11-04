package views;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.*;
import javax.swing.text.AttributeSet.ColorAttribute;

import com.google.zxing.WriterException;

import interfaces.ShowStudent;
import models.Student;
import services.QRGenerator;
import services.Utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import java.awt.geom.Ellipse2D;

public class Display extends JFrame implements ActionListener{
	
    // declare some things we need
	String id;
	Color bg = Color.black;
	Color tecBlue = new Color(27, 57, 106);
	
	JLabel logoTecnm,logoTecnm2, logoITC,
			footerMsg,
			qrBg, qr,
			lblTime, lblDate, lblPoint,
			lblName, lblLast, lblControl, lblImage;
	
	
	
	JPanel footer, qrPanel, leftPanel, rightPanel, centerPanel;
	
	ImageIcon photoPlaceholder;
	
	public Display(String id) {
		this.id = id;
		initComponents();
		
   this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
	    this.setResizable(false);
	    this.setUndecorated(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void initComponents() {
		Dimension size = Toolkit. getDefaultToolkit(). getScreenSize();
		 int width = (int)size.getWidth();
	     int height = (int)size.getHeight();
	        
	     // create container to hold GUI in window
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());
        JPanel pane = new JPanel(new BorderLayout());
        
        Toolkit tool = Toolkit.getDefaultToolkit(); 
 
        
        //LOGO
        String imagePath = "src/assets/images/tecnm-logo.png";
        Image myPicture;
		try {

			myPicture = ImageIO.read(new File(imagePath));
			myPicture = myPicture.getScaledInstance(-1, 150,Image.SCALE_SMOOTH);
			 logoTecnm = new JLabel(new ImageIcon(myPicture));

			 	imagePath = "src/assets/images/itc-logo.png";
				myPicture = ImageIO.read(new File(imagePath));
				myPicture = myPicture.getScaledInstance(-1, 150,Image.SCALE_SMOOTH);
				 logoITC = new JLabel(new ImageIcon(myPicture));

				 imagePath = "src/assets/images/tecnm-logo2.png";
					myPicture = ImageIO.read(new File(imagePath));
					myPicture = myPicture.getScaledInstance(-1, 150,Image.SCALE_SMOOTH);
					 logoTecnm2 = new JLabel(new ImageIcon(myPicture));

					 imagePath = "src/assets/images/qr-bg.png";
						myPicture = ImageIO.read(new File(imagePath));
						myPicture = myPicture.getScaledInstance(-1, 300,Image.SCALE_SMOOTH);
						qrBg = new JLabel(new ImageIcon(myPicture));
		
						imagePath = "src/assets/images/profilePlaceholder.png";
							myPicture = ImageIO.read(new File(imagePath));
							myPicture = myPicture.getScaledInstance(-1, 180,Image.SCALE_SMOOTH);
							photoPlaceholder = new ImageIcon(myPicture);
						qr = new JLabel();

			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
		JPanel logos = new JPanel(new GridLayout(1,3));
		logos.add(logoTecnm2);
		logos.add(logoTecnm);
		logos.add(logoITC);
		
		
		footerMsg = new JLabel("Escanea el código QR en la App para ingresar...");
		footerMsg.setFont(new Font("Arial", Font. BOLD, 20));
		footerMsg.setForeground(Color.white);
		footer = new JPanel(new FlowLayout(FlowLayout.CENTER));
		footer.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		
		qrPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
	//	qrPanel.add(lblTop1, gbc);
	//	qrPanel.add(qrBg, gbc);
		JPanel qrImage = new JPanel(new BorderLayout());
		qrImage.setBorder(BorderFactory.createLineBorder(new Color(27, 57, 106), 10));
		qrImage.add(qr, BorderLayout.CENTER);
		qrImage.setMinimumSize(new Dimension(410,410));
		qrImage.setMaximumSize(new Dimension(410,410));
		qrImage.setPreferredSize(new Dimension(410,410));
		
		
		qrPanel.add(qrImage, gbc);
	//	qrPanel.add(qr, BorderLayout.CENTER);
	//	qrPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
	//	qrPanel.setBackground(Color.red);
		qrPanel.setMinimumSize(new Dimension((int)(width /3), (int)(height * 0.6)));
		qrPanel.setMaximumSize(new Dimension((int)(width /3), (int)(height * 0.6)));
		qrPanel.setPreferredSize(new Dimension((int)(width /3), (int)(height * 0.6)));


		footer.add(footerMsg);
		
		
		lblTime = new JLabel("02:45 PM");
		lblTime.setFont(new Font("Arial", Font. BOLD, 40));
		lblDate = new JLabel(Utilities.getDate());
		lblDate.setFont(new Font("Arial", Font. BOLD, 30));
		lblPoint = new JLabel("TORNIQUETE 1");
		lblPoint.setFont(new Font("Arial", Font.BOLD, 15));
		
		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.add(lblDate);
		leftPanel.add(lblTime);
		leftPanel.add(lblPoint);
		leftPanel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 30));
		leftPanel.setMinimumSize(new Dimension((int)(width * 0.33), (int)(height * 0.6)));
		leftPanel.setMaximumSize(new Dimension((int)(width * 0.33), (int)(height * 0.6)));
		leftPanel.setPreferredSize(new Dimension((int)(width * 0.33), (int)(height * 0.6)));

		String name = "";
		String last = "";
		String control = "";
		lblName = new JLabel(name);
		lblName.setForeground(Color.DARK_GRAY);
		lblName.setFont(new Font("Arial", Font. PLAIN, 28));
		lblLast = new JLabel(last);
		lblLast.setForeground(Color.DARK_GRAY);
		lblLast.setFont(new Font("Arial", Font. PLAIN, 28));
		lblControl = new JLabel(control);
		lblControl.setForeground(Color.DARK_GRAY);
		lblControl.setFont(new Font("Arial", Font. BOLD, 32));
		lblImage = new JLabel();
		rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 30));
		rightPanel.add(lblImage);
		rightPanel.add(new JLabel("  "));
		rightPanel.add(lblControl);
		rightPanel.add(new JLabel("  "));
		rightPanel.add(lblName);
		rightPanel.add(lblLast);
		//rightPanel.setBackground(Color.PINK);

		rightPanel.setMinimumSize(new Dimension((int)(width /3), (int)(height * 0.6)));
		rightPanel.setMaximumSize(new Dimension((int)(width /3), (int)(height * 0.6)));
		rightPanel.setPreferredSize(new Dimension((int)(width /3), (int)(height * 0.6)));
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
		

		centerPanel.add(leftPanel);
		centerPanel.add(qrPanel);
		centerPanel.add(rightPanel);


		
		pane.add(logos, BorderLayout.NORTH);
		pane.add(footer, BorderLayout.SOUTH);
		pane.add(centerPanel, BorderLayout.CENTER);
	//	pane.add(leftPanel, BorderLayout.WEST);
//		pane.add(rightPanel, BorderLayout.EAST);
		pane.setBackground(bg);
		footer.setBackground(tecBlue);
		container.add(pane, BorderLayout.CENTER);
		
		
	
	}

	
	public void setTime(String time) {
		lblTime.setText(time);
	}
	
	public void setDate(String date) {
		lblDate.setText(date);
	}
	
	public void setStudent(String img, String f, String l, String c) {
		
		try {
			BufferedImage photo = ImageIO.read(new URL(img));
			lblImage.setIcon(new ImageIcon(photo.getScaledInstance(-1, 180,Image.SCALE_SMOOTH)));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		lblName.setText(f);
		lblLast.setText(l);
		lblControl.setText(c);
	}
	
	public void resetStudent() {
		lblImage.setIcon(null);
		lblName.setText("");
		lblLast.setText("");
		lblControl.setText("");	
	}
	
	public void updateCode(String data) {
	
		try {
			QRGenerator.generateQRcode(id + "\n" + data);
		} catch (WriterException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			String imagePath = "src/assets/code/qr.png";
			Image myPicture = ImageIO.read(new File(imagePath));
		//	myPicture = myPicture.getScaledInstance(-1, 300,Image.SCALE_SMOOTH);
			qr.setIcon(new ImageIcon(myPicture));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void displayStudent(Student student) {
		Thread t = new Thread(new ShowStudent(this, student));
		t.start();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
