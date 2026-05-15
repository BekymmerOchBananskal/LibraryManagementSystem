package librarymanagementsystem.forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffForm extends JFrame implements ActionListener {
	JButton btnAddMember,btnBooksList,btnAddStaff,btnBorrowReturn,btnReports,btnLogout;

    public StaffForm() {

        setTitle("Staff Panel");
        setSize(400, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        ImageIcon bg = new ImageIcon(
                "C:\\Users\\atili\\OneDrive\\Desktop\\Java\\Workspace-GorselTabanliProgramlama\\"
                + "LibraryManagementSystem\\img\\staffform3.png"
        );

        
        Image img = bg.getImage();
        Image scaledImg = img.getScaledInstance(400, 300, Image.SCALE_SMOOTH);

        // Arka plan label
        JLabel background = new JLabel(new ImageIcon(scaledImg));
        
        btnAddMember=new JButton("Member Management");
		btnAddMember.addActionListener(this);
		btnAddStaff=new JButton("Staff Management");
		btnAddStaff.addActionListener(this);
		btnBooksList=new JButton("Book Management");
		btnBooksList.addActionListener(this);
		btnBorrowReturn=new JButton("Borrow / Return");
		btnBorrowReturn.addActionListener(this);
		btnReports=new JButton("Reports");
		btnReports.addActionListener(this);
		btnLogout=new JButton("Logout");
		btnLogout.addActionListener(this);
		
		
		Dimension size = new Dimension(170, 30);

		btnAddMember.setPreferredSize(size);
		btnAddStaff.setPreferredSize(size);
		btnBooksList.setPreferredSize(size);
		btnBorrowReturn.setPreferredSize(size);
		btnReports.setPreferredSize(size);
		btnLogout.setPreferredSize(size);
        background.setLayout(new GridBagLayout());
        GridBagConstraints gbc= new GridBagConstraints();
		gbc.insets=new Insets(5,5,5,5); //(top, left, bottom, right)elemanların etrafında boşluk bırakır. dış boşluk
		gbc.anchor=GridBagConstraints.CENTER; 

        gbc.gridx=0;
		gbc.gridy=0;
		gbc.gridwidth=2;
		background.add(btnAddMember,gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.gridwidth=2;
		background.add(btnAddStaff,gbc);
		
		gbc.gridx=0;
		gbc.gridy=2;
		background.add(btnBooksList,gbc);
		
		gbc.gridx=0;
		gbc.gridy=3;
		background.add(btnBorrowReturn,gbc);
		
		gbc.gridx=0;
		gbc.gridy=4;
		background.add(btnReports,gbc);
		
		gbc.gridx=0;
		gbc.gridy=5;
		background.add(btnLogout,gbc);
		
        // Content pane olarak ayarla
        setContentPane(background);

        setVisible(true);
    }

    @Override
	public void actionPerformed(ActionEvent e) {
    	if(e.getSource()==btnAddMember) {
    		new MemberManagementForm();
    	}
    }
}