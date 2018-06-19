package com.org.Selenium.quickstart;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Scrollbar;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.ComponentOrientation;
import java.awt.ScrollPane;
import javax.swing.SpringLayout;
import java.awt.BorderLayout;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.Button;
import java.awt.TextArea;
import javax.swing.JSpinner;
import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class Automation extends JFrame {

	private JPanel contentPane;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JTextArea textArea;
	private JLabel elapsed_time;
	private JLabel start_time;
	private int current_status = 0; // 0 stop  1 - pause  2 - running

	/**
	 * Create the frame.
	 */
	private Proccess prc = null;
	public Automation() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				start_time.setText("0");
				elapsed_time.setText("0");
				prc = new Proccess();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 877, 669);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "WARNING",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				    System.exit(0);
				}
		});
		mnFile.add(mntmExit);
		
		JMenu mnConfig = new JMenu("Config");
		menuBar.add(mnConfig);
		
		JMenuItem mntmAutomationjson = new JMenuItem("automation.json");
		mnConfig.add(mntmAutomationjson);
		
		JMenuItem mntmConfigini = new JMenuItem("config.ini");
		mnConfig.add(mntmConfigini);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmHelp = new JMenuItem("About");
		mnHelp.add(mntmHelp);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel facebook_panel = new JPanel();
		facebook_panel.setBackground(Color.LIGHT_GRAY);
		
		JPanel features_panel = new JPanel();
		
		JPanel log_panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(features_panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(facebook_panel, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))
					.addGap(26)
					.addComponent(log_panel, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(79, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(log_panel, GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(facebook_panel, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
							.addGap(29)
							.addComponent(features_panel, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(74, Short.MAX_VALUE))
		);
		log_panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		TextArea log_area = new TextArea();
		log_panel.add(log_area);

		
		
		
		
		JLabel lblFeatures = new JLabel("Features");
		lblFeatures.setHorizontalAlignment(SwingConstants.CENTER);
		lblFeatures.setForeground(Color.BLUE);
		lblFeatures.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFeatures.setBackground(Color.WHITE);
		
		final JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				prc.ControlApp.start();
			}
		});
		
		JButton btnNewButton = new JButton("Stop");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prc.ControlApp.suspend();
				btnStart.setText("Start");
			}
		});
		
		start_time = new JLabel("start_time");
		
		JLabel lblStarted = new JLabel("Started:");
		
		JLabel lblNewLabel = new JLabel("Elapsed:");
		
		elapsed_time = new JLabel("elapsed_time");
		
		final JSpinner intervalTime = new JSpinner();
		intervalTime.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(Integer.parseInt(intervalTime.getValue().toString()) < 0) {
					JOptionPane.showMessageDialog(null, "Value cannot be below 0!");
					intervalTime.setValue(0);
				}
			}
		});
		intervalTime.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JLabel lblChooseInterval = new JLabel("Choose Interval");
		
		JButton conf = new JButton("Configure");
		conf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Configure con = new Configure();
				con.setVisible(true);
				setVisible(false);
			}
		});
		GroupLayout gl_features_panel = new GroupLayout(features_panel);
		gl_features_panel.setHorizontalGroup(
			gl_features_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_features_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_features_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_features_panel.createSequentialGroup()
							.addGroup(gl_features_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_features_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(lblFeatures, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_features_panel.createSequentialGroup()
										.addComponent(lblStarted)
										.addGap(18)
										.addComponent(start_time))
									.addGroup(gl_features_panel.createSequentialGroup()
										.addComponent(lblNewLabel)
										.addGap(18)
										.addComponent(elapsed_time)))
								.addGroup(gl_features_panel.createSequentialGroup()
									.addGroup(gl_features_panel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(conf, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnStart, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)))
							.addContainerGap())
						.addGroup(gl_features_panel.createSequentialGroup()
							.addGroup(gl_features_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(intervalTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblChooseInterval))
							.addGap(215))))
		);
		gl_features_panel.setVerticalGroup(
			gl_features_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_features_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblFeatures, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_features_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(elapsed_time))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_features_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStarted)
						.addComponent(start_time))
					.addGap(33)
					.addComponent(lblChooseInterval)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(intervalTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
					.addGroup(gl_features_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnStart)
						.addComponent(btnNewButton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(conf)
					.addGap(20))
		);
		features_panel.setLayout(gl_features_panel);
		
		JLabel lblIncludeLoginTo = new JLabel("Include login to facebook");
		lblIncludeLoginTo.setBackground(Color.WHITE);
		lblIncludeLoginTo.setForeground(Color.BLUE);
		lblIncludeLoginTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblIncludeLoginTo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		emailField = new JTextField();
		emailField.setColumns(10);
		
		JLabel lblEmailOrUsername = new JLabel("E-mail or Username");
		
		passwordField = new JPasswordField();
		
		JLabel lblPassword = new JLabel("Password");
		
		JCheckBox include_login = new JCheckBox("Include Login");
		GroupLayout gl_facebook_panel = new GroupLayout(facebook_panel);
		gl_facebook_panel.setHorizontalGroup(
			gl_facebook_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_facebook_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_facebook_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(include_login)
						.addComponent(lblIncludeLoginTo, GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
						.addComponent(lblEmailOrUsername)
						.addGroup(gl_facebook_panel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(passwordField, Alignment.LEADING)
							.addComponent(emailField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE))
						.addComponent(lblPassword))
					.addContainerGap())
		);
		gl_facebook_panel.setVerticalGroup(
			gl_facebook_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_facebook_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblIncludeLoginTo)
					.addGap(45)
					.addComponent(lblEmailOrUsername)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(emailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(21)
					.addComponent(lblPassword)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(include_login)
					.addContainerGap(131, Short.MAX_VALUE))
		);
		facebook_panel.setLayout(gl_facebook_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
