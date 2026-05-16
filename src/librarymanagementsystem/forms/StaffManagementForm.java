package librarymanagementsystem.forms;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import librarymanagementsystem.DBHelper;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffManagementForm extends JFrame implements ActionListener{

    JLabel lblName, lblSurname, lblEmail, lblPassword;
    JTextField txtName, txtSurname,  txtEmail, txtPassword;
    JButton btnAdd, btnUpdate, btnDelete, btnClear;
    JTable table;
    JPanel rightPanel, btnPanel;
    DefaultTableModel model;
    JScrollPane scrollPane;
    int selectedMemberId;

    public StaffManagementForm() {
        setTitle("Staff Management Panel");
        setSize(670, 500);
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon bg = new ImageIcon(
                "C:\\Users\\atili\\OneDrive\\Desktop\\Java\\Workspace-GorselTabanliProgramlama\\"
                        + "LibraryManagementSystem\\img\\staffform.jpg"
        );

        Image img = bg.getImage();
        Image scaledImg = img.getScaledInstance(500, 500, Image.SCALE_SMOOTH);

        JLabel leftPanel = new JLabel(new ImageIcon(scaledImg));
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.setPreferredSize(new Dimension(370, 500));

        TitledBorder leftBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 3),
                "Staff Information"
        );
        leftBorder.setTitleColor(Color.WHITE);
        leftPanel.setBorder(leftBorder);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 5, 10);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.NONE;

        Dimension fieldSize = new Dimension(170, 20);

        lblName = new JLabel("Name: ");
        lblName.setForeground(Color.WHITE);
        txtName = new JTextField();
        txtName.setPreferredSize(fieldSize);

        gbc.gridx = 0;
        gbc.gridy = 0;
        leftPanel.add(lblName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        leftPanel.add(txtName, gbc);

        lblSurname = new JLabel("Surname: ");
        lblSurname.setForeground(Color.WHITE);
        txtSurname = new JTextField();
        txtSurname.setPreferredSize(fieldSize);

        gbc.gridx = 0;
        gbc.gridy = 1;
        leftPanel.add(lblSurname, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        leftPanel.add(txtSurname, gbc);

        

        

        lblEmail = new JLabel("Email: ");
        lblEmail.setForeground(Color.WHITE);
        txtEmail = new JTextField();
        txtEmail.setPreferredSize(fieldSize);

        gbc.gridx = 0;
        gbc.gridy = 2;
        leftPanel.add(lblEmail, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        leftPanel.add(txtEmail, gbc);

        lblPassword = new JLabel("Password: ");
        lblPassword.setForeground(Color.WHITE);
        txtPassword = new JTextField();
        txtPassword.setPreferredSize(fieldSize);

        gbc.gridx = 0;
        gbc.gridy = 3;
        leftPanel.add(lblPassword, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        leftPanel.add(txtPassword, gbc);

        btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        btnPanel.setOpaque(false);

        btnAdd = new JButton("Add");
        btnAdd.addActionListener(this);
        
        btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(this);
        
        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(this);
        
        btnClear = new JButton("Clear");
        btnClear.addActionListener(this);

        btnPanel.add(btnAdd);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);
        btnPanel.add(btnClear);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(25, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        leftPanel.add(btnPanel, gbc);

        rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.BLACK, 3),
                        "Staff List"
                )
        );
        String[] columns = {"ID", "Name", "Surname", "Email", "Password"};
        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);
        table.removeColumn(table.getColumnModel().getColumn(4));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(25);
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(70);
        table.getColumnModel().getColumn(2).setPreferredWidth(70);
        table.getColumnModel().getColumn(3).setPreferredWidth(110);
        
        JScrollPane scrollPane = new JScrollPane(table);

        rightPanel.add(scrollPane, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        
        DBHelper.getAllStaff(model);
        
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            	int selectedRow = table.getSelectedRow();

            	if (selectedRow != -1) {

                    selectedMemberId = Integer.parseInt(
                            model.getValueAt(selectedRow, 0).toString()
                    );
            	    txtName.setText(model.getValueAt(selectedRow, 1).toString());
            	    txtSurname.setText(model.getValueAt(selectedRow, 2).toString());
            	    txtEmail.setText(model.getValueAt(selectedRow, 3).toString());
            	    txtPassword.setText(model.getValueAt(selectedRow, 4).toString());
            	}
            }
        });

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
	 if(e.getSource()==btnAdd) {
		 String name=txtName.getText();
		 String surname = txtSurname.getText();
		 
		 String email=txtEmail.getText();
		 String password=txtPassword.getText();
		 
		 DBHelper.insertStaff(name,surname,email,password);
		 DBHelper.getAllStaff(model);
		 clearFields();
	 }
	 if(e.getSource()==btnUpdate){
		 DBHelper.updateStaff(
		            selectedMemberId,
		            txtName.getText(),
		            txtSurname.getText(),
		            
		            txtEmail.getText(),
		            txtPassword.getText()
		    );
		   
		    

		    DBHelper.getAllStaff(model);
		    clearFields();
		}
	 if (e.getSource() == btnDelete) {

		    if (selectedMemberId == -1) {
		        JOptionPane.showMessageDialog(this, "Please select a staff first.");
		        return;
		    }

		    int confirm = JOptionPane.showConfirmDialog(
		            this,
		            "Are you sure you want to delete this member?",
		            "Confirm Delete",
		            JOptionPane.YES_NO_OPTION
		    );

		    if (confirm == JOptionPane.YES_OPTION) {
		        DBHelper.deleteStaff(selectedMemberId);
		        DBHelper.getAllStaff(model);
		        clearFields();

		        JOptionPane.showMessageDialog(this, "Staff deleted successfully.");
		    }
		}
	 if (e.getSource() == btnClear) {
		 clearFields();
	 }
 }
    
    private void clearFields() {

        txtName.setText("");
        txtSurname.setText("");
        
        txtEmail.setText("");
        txtPassword.setText("");

        selectedMemberId = -1;
    }
}
