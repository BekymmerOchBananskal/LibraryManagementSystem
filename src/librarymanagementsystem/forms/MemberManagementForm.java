package librarymanagementsystem.forms;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import librarymanagementsystem.DBHelper;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberManagementForm extends JFrame implements ActionListener{

    JLabel lblName, lblSurname, lblStudentno, lblEmail, lblPassword;
    JTextField txtName, txtSurname, txtStudentno, txtEmail, txtPassword;
    JButton btnAdd, btnUpdate, btnDelete, btnClear;
    JTable table;
    JPanel rightPanel, btnPanel;
    DefaultTableModel model;
    JScrollPane scrollPane;

    public MemberManagementForm() {
        setTitle("Member Management Panel");
        setSize(800, 500);
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon bg = new ImageIcon(
                "C:\\Users\\atili\\OneDrive\\Desktop\\Java\\Workspace-GorselTabanliProgramlama\\"
                        + "LibraryManagementSystem\\img\\login4.jpg"
        );

        Image img = bg.getImage();
        Image scaledImg = img.getScaledInstance(500, 500, Image.SCALE_SMOOTH);

        JLabel leftPanel = new JLabel(new ImageIcon(scaledImg));
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.setPreferredSize(new Dimension(370, 500));

        TitledBorder leftBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 3),
                "Student Information"
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

        lblStudentno = new JLabel("Student No: ");
        lblStudentno.setForeground(Color.WHITE);
        txtStudentno = new JTextField();
        txtStudentno.setPreferredSize(fieldSize);

        gbc.gridx = 0;
        gbc.gridy = 2;
        leftPanel.add(lblStudentno, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        leftPanel.add(txtStudentno, gbc);

        lblEmail = new JLabel("Email: ");
        lblEmail.setForeground(Color.WHITE);
        txtEmail = new JTextField();
        txtEmail.setPreferredSize(fieldSize);

        gbc.gridx = 0;
        gbc.gridy = 3;
        leftPanel.add(lblEmail, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        leftPanel.add(txtEmail, gbc);

        lblPassword = new JLabel("Password: ");
        lblPassword.setForeground(Color.WHITE);
        txtPassword = new JTextField();
        txtPassword.setPreferredSize(fieldSize);

        gbc.gridx = 0;
        gbc.gridy = 4;
        leftPanel.add(lblPassword, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        leftPanel.add(txtPassword, gbc);

        btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        btnPanel.setOpaque(false);

        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");

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
                        "Member List"
                )
        );
        String[] columns = {"ID", "Name", "Surname", "Student No", "Email"};

        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);
        table.setRowHeight(25);
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        JScrollPane scrollPane = new JScrollPane(table);

        rightPanel.add(scrollPane, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        
        DBHelper.getAllMembers(model);

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
	 if(e.getSource()==btnAdd) {
		 
	 }
 }
}