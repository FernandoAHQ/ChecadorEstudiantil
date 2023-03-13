package views;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.google.zxing.WriterException;

import controllers.Config;
import interfaces.ShowError;
import interfaces.ShowStudent;
import models.Student;
import services.QRGenerator;
import services.Utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.*;    


public class Display extends JDialog implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Dimension size = Toolkit. getDefaultToolkit(). getScreenSize();
	 int width = (int)size.getWidth()/2;
    int height = (int)size.getHeight();
  
    // declare some things we need
	String id;
	Color bg = Color.black;
	Color tecBlue = new Color(27, 57, 106);
	
	JLabel logoTecnm,logoTecnm2, logoITC,
			footerMsg,
			qrBg, qr,
			lblTime, lblDate, lblPoint,
			lblName, lblLast, lblControl, lblImage;
	
	
	
	JPanel footer, qrPanel, leftPanel, rightPanel, topPanel, centerPanel;
	
	ImageIcon photoPlaceholder;
	int side;
	Config config = new Config();
	String ip = config.getIP();
	
	public Display(String id, int side) {
		this.id = id;
		this.side = side;
		initComponents();
		
		Dimension size = Toolkit. getDefaultToolkit(). getScreenSize();

   //this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		this.setSize((int)size.getWidth()/2, (int)size.getHeight());
		this.setLocation(((int)size.getWidth()/2)*side, 0);
		this.setResizable(false);
	    this.setUndecorated(true);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		this.setModal(true);
		this.setModalityType(ModalityType.APPLICATION_MODAL);		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void initComponents() {
		     
	     // create container to hold GUI in window
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());
        JPanel pane = new JPanel(new BorderLayout());
        
        //LOGO
        String imagePath = "src/assets/images/tecnm-logo.png";
        Image myPicture;
		try {

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

        
	//	JPanel logos = new JPanel(new GridLayout(1,3));
	//	logos.add(logoTecnm2);
	//	logos.add(logoTecnm);
	//	logos.add(logoITC);
		
		
		footerMsg = new JLabel("Ingresa a cae.cancun.tecnm.mx para escanea el código QR");
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
		Dimension qrDim = new Dimension((int)(width /2), (int)(width /2));
		qrImage.setMinimumSize(qrDim);
		qrImage.setMaximumSize(qrDim);
		qrImage.setPreferredSize(qrDim);
		
		qrPanel.add(qrImage, gbc);
	//	qrPanel.add(qr, BorderLayout.CENTER);
	//	qrPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
	//	qrPanel.setBackground(Color.red);
		Dimension qrPanelDim = new Dimension((int)(width /2), (int)(width /2));
		
		qrPanel.setMinimumSize(qrPanelDim);
		qrPanel.setMaximumSize(qrPanelDim);
		qrPanel.setPreferredSize(qrPanelDim);

		footer.add(footerMsg);
		
		
		lblTime = new JLabel("");
		lblTime.setFont(new Font("Arial", Font. BOLD, 40));
		lblDate = new JLabel(Utilities.getDate());
		lblDate.setFont(new Font("Arial", Font. BOLD, 22));
		lblPoint = new JLabel(config.getName(side).toUpperCase());
		lblPoint.setFont(new Font("Arial", Font.BOLD, 25));
		
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
		lblName = new JLabel(name, SwingConstants.RIGHT);
	//	lblName.setForeground(Color.DARK_GRAY);
		lblName.setFont(new Font("Arial", Font. PLAIN, 20));
		lblLast = new JLabel(last, SwingConstants.RIGHT);
	//	lblLast.setForeground(Color.DARK_GRAY);
		lblLast.setFont(new Font("Arial", Font. PLAIN, 20));
		lblControl = new JLabel(control, SwingConstants.RIGHT);
		lblControl.setForeground(Color.DARK_GRAY);
		lblControl.setFont(new Font("Arial", Font.PLAIN, 24));
		lblImage = new JLabel("",SwingConstants.RIGHT);
		rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 30));
		rightPanel.add(lblImage);
	//	rightPanel.add(new JLabel("  "));
		rightPanel.add(lblName);
		rightPanel.add(lblLast);
		rightPanel.add(lblControl);
		


		rightPanel.setMinimumSize(new Dimension((int)(width /3), (int)(height * 0.6)));
		rightPanel.setMaximumSize(new Dimension((int)(width /3), (int)(height * 0.6)));
		rightPanel.setPreferredSize(new Dimension((int)(width /3), (int)(height * 0.6)));
		
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);
		centerPanel = new JPanel(fl);
		//centerPanel.setLayout(new BorderLayout());
		GridBagConstraints gbc2 = new GridBagConstraints();
		//gbc.gridwidth = GridBagConstraints.REMAINDER;

		//centerPanel.add(leftPanel, gbc2);
		
		centerPanel.add(qrPanel, gbc2);
		
		topPanel = new JPanel(new GridLayout(1,2));
		topPanel.add(leftPanel);
		topPanel.add(rightPanel);
		
		topPanel.setPreferredSize(new Dimension(-1,(int)(size.height * 0.35)));
		
		pane.add(footer, BorderLayout.SOUTH);
		pane.add(centerPanel, BorderLayout.CENTER);
		pane.add(topPanel, BorderLayout.NORTH);
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
	
	public void setError() {
		
		this.resetStudent();
		
		try {
			String imagePath = "src/assets/images/error.png";
			Image myPicture = ImageIO.read(new File(imagePath));
			lblImage.setIcon(new ImageIcon(myPicture.getScaledInstance(-1, (int)(size.getHeight() * 0.15),Image.SCALE_SMOOTH)));
		}catch(Exception e) {
			System.out.println(e);
		}
		
		lblName.setForeground(Color.red);
		lblName.setText("Acceso Denegado");
	}
	

	public void setStudent(String img, String f, String l,  String c) {
		
		String imgUrl = this.ip + "api/images/students/" + img;
		System.out.println(imgUrl);
		try {
			BufferedImage photo = ImageIO.read(new URL(imgUrl));
			lblImage.setIcon(new ImageIcon(photo.getScaledInstance(-1, (int)(size.getHeight() * 0.15),Image.SCALE_SMOOTH)));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	//	lblName.setForeground(Color.black);
		lblName.setText(f);
		lblLast.setText(l);
		lblControl.setText(c);
	}
	
	public void resetStudent() {
		lblImage.setIcon(null);
		lblName.setForeground(Color.black);
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
			myPicture = myPicture.getScaledInstance((int) (width/2), (int) (width/2), Image.SCALE_SMOOTH);
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
	
	
	public void displayError() {
		Thread t = new Thread(new ShowError(this));
		t.start();
	}
	
	public void DisplayDate(String date) {
		lblDate.setText(date);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	 
	
}
