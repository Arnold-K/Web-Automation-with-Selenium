package com.org.Selenium.quickstart;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Configure extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JTextArea textArea;
	private JLabel lblPostDescription;
	private JScrollPane scrollPane_1;
	private JTextField filePath;
	private JCheckBox includeImage;
	private JPanel panel_1;
	private JScrollPane scrollPane_2;
	private JTable table_1;
	private JButton btnRemoveUrl;
	private JButton btnRemoveUrl_1;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Configure() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				boolean hasImage = false;
				if(includeImage.isSelected()) 
					hasImage = true;
				ApplyConfigurations apc = new ApplyConfigurations();
				apc.showConfiguration(table, table_1, textArea, filePath, includeImage);
				
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 902, 624);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel table_panel = new JPanel();
		
		panel = new JPanel();
		
		lblPostDescription = new JLabel("Post Description");
		lblPostDescription.setHorizontalAlignment(SwingConstants.CENTER);
		
		scrollPane_1 = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
						.addComponent(lblPostDescription, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPostDescription)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		panel.setLayout(gl_panel);
		
		includeImage = new JCheckBox("Include Image");
		
		filePath = new JTextField();
		filePath.setColumns(10);
		
		final JButton btnChooseFile = new JButton("Choose File");
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File file;
				final JFileChooser fc = new JFileChooser(); 
	            int returnVal = fc.showOpenDialog(new JFrame(" Pick a file!"));
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	                file = fc.getSelectedFile();
	                filePath.setText(file.getAbsolutePath());
	            }
			}
		});
		
		panel_1 = new JPanel();
		
		scrollPane_2 = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 271, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 344, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Group 2"
			}
		));
		scrollPane_2.setViewportView(table_1);
		panel_1.setLayout(gl_panel_1);
		
		JButton btnAddNewUrl = new JButton("Add New URL");
		btnAddNewUrl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNewURL anu = new AddNewURL(table, table_1);
				anu.setVisible(true);
				
			}
		});
		
		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isChecked = false;
				String imgDir = "";
				String description = "";
				if(includeImage.isSelected())
					isChecked = true;
				imgDir = filePath.getText();
				description = textArea.getText();
				
				ApplyConfigurations apc = new ApplyConfigurations();
				apc.updateImageDescription(isChecked, imgDir, description);
				
				Automation atm = new Automation();
				atm.setVisible(true);
				dispose();
				
			}
		});
		
		btnRemoveUrl = new JButton("Remove URL 1");
		btnRemoveUrl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String value = (String)table.getValueAt(table.getSelectedRow(), 0);
				ApplyConfigurations apc = new ApplyConfigurations();
				apc.removeURL(value, 1);
				apc.reloadTables(table, table_1);
			}
		});
		
		btnRemoveUrl_1 = new JButton("Remove URL 2");
		btnRemoveUrl_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String value = (String)table_1.getValueAt(table_1.getSelectedRow(), 0);
				ApplyConfigurations apc = new ApplyConfigurations();
				apc.removeURL(value, 2);
				apc.reloadTables(table, table_1);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(table_panel, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(panel, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE))
								.addComponent(includeImage))
							.addContainerGap(25, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(filePath, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnChooseFile)
							.addPreferredGap(ComponentPlacement.RELATED, 279, Short.MAX_VALUE)
							.addComponent(btnApply)
							.addGap(139))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnAddNewUrl)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnRemoveUrl)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnRemoveUrl_1)
							.addContainerGap(557, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE)
						.addComponent(table_panel, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(includeImage)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(filePath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnChooseFile)
						.addComponent(btnApply))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddNewUrl)
						.addComponent(btnRemoveUrl)
						.addComponent(btnRemoveUrl_1))
					.addContainerGap(89, Short.MAX_VALUE))
		);
		
		scrollPane = new JScrollPane();
		scrollPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println(e.getKeyCode());
			}
		});
		GroupLayout gl_table_panel = new GroupLayout(table_panel);
		gl_table_panel.setHorizontalGroup(
			gl_table_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_table_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(516, Short.MAX_VALUE))
		);
		gl_table_panel.setVerticalGroup(
			gl_table_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_table_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Group 1"
			}
		));
		table_panel.setLayout(gl_table_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
