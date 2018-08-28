import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.synth.SynthLookAndFeel;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.lang.Math;

import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionEvent;

public class PachoSwing extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearchtext;
	Pacho pacho = new Pacho();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initLookAndFeel();
					PachoSwing frame = new PachoSwing();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Set the L&F.
	 */
	private static void initLookAndFeel() {
		SynthLookAndFeel Synth = new SynthLookAndFeel();
		try {
			Synth.load(PachoSwing.class.getResourceAsStream("/res/Synth.xml"), PachoSwing.class);
		UIManager.setLookAndFeel(Synth);
		}
		catch (Exception e) {
			System.err.println("Couldnt get specified look and feel");
			e.printStackTrace();
		}
	}


	/**
	 * Create the frame.
	 */
	public PachoSwing() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int appHeight = (int) Math.round(.8333333333 * screenSize.height);
		int highAppHeight = (int) Math.round(.09 * screenSize.height);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, highAppHeight, 650, 80);
		setUndecorated(true);
		//getRootPane().setWindowDecorationStyle(JRootPacne.NONE);
		
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(10, 0));
		setContentPane(contentPane);
		
		txtSearchtext = new JTextField();
		Font font;
		try {
			InputStream myStream = PachoSwing.class.getResourceAsStream("/res/overpass-mono-regular.otf");
			font = Font.createFont(Font.TRUETYPE_FONT, myStream);
			txtSearchtext.setFont(font.deriveFont(Font.PLAIN, 27));
		} catch (FontFormatException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		txtSearchtext.setText("");
		txtSearchtext.setMargin(new Insets(5, 20, 0, 0));
		contentPane.add(txtSearchtext, BorderLayout.CENTER);
		txtSearchtext.setColumns(10);
		
		
		JButton btnSearchbutton = new JButton("");
		btnSearchbutton.setIcon(new ImageIcon(PachoSwing.class.getResource("/res/playbutton.png")));
		btnSearchbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] query = txtSearchtext.getText().split(" ");
				try {
					Pacho.main(query);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnSearchbutton, BorderLayout.EAST);
		

	}

}

