package librarymanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffForm extends JFrame implements ActionListener {
	JButton btnAddMember,btnAddBook,btnBooksList;

    public StaffForm() {

        setTitle("Staff Panel");
        setSize(400, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Resmi yükle
        ImageIcon bg = new ImageIcon(
                "C:\\Users\\atili\\OneDrive\\Desktop\\Java\\Workspace-GorselTabanliProgramlama\\"
                + "LibraryManagementSystem\\img\\staffform3.png"
        );

        // Resmi pencere boyutuna göre ölçekle
        Image img = bg.getImage();
        Image scaledImg = img.getScaledInstance(400, 300, Image.SCALE_SMOOTH);

        // Arka plan label
        JLabel background = new JLabel(new ImageIcon(scaledImg));
        
        btnAddMember=new JButton("Add Member");
		btnAddMember.addActionListener(this);
		btnBooksList=new JButton("Books List");
		btnBooksList.addActionListener(this);
		btnAddBook=new JButton("Add Book");
		
		
		Dimension size = new Dimension(120, 30);

		btnAddMember.setPreferredSize(size);
		btnBooksList.setPreferredSize(size);
		btnAddBook.setPreferredSize(size);
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
		background.add(btnBooksList,gbc);
		
		gbc.gridx=0;
		gbc.gridy=2;
		background.add(btnAddBook,gbc);
        // Content pane olarak ayarla
        setContentPane(background);

        setVisible(true);
    }

    @Override
	public void actionPerformed(ActionEvent e) {
    	
    }
}