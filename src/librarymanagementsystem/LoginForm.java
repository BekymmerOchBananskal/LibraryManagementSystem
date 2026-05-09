package librarymanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame implements ActionListener {
	JTextField emailField;
    JPasswordField passwordField;
    JLabel lblEmail, lblPassword;
    JButton btnLogin;
    JRadioButton staffRadio, memberRadio;

	public LoginForm() {
		setTitle("Login");
		setSize(300, 200);
        setResizable(false);
        setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(null);
	    
	    ImageIcon bg = new ImageIcon(
	    		"C:\\Users\\atili\\OneDrive\\Desktop\\Java\\Workspace-GorselTabanliProgramlama\\"
	    		+ "LibraryManagementSystem\\img\\img.jpg");
	    Image img = bg.getImage();
	    Image scaledImg = img.getScaledInstance(300, 200, Image.SCALE_SMOOTH);

	    JLabel background = new JLabel(new ImageIcon(scaledImg));
	    
	    

        
        background.setBounds(0,0,300,200);
        
        background.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 5, 5, 5);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.1;
        gbc.weightx = 0;
        add(new JLabel(), gbc);

        lblEmail = new JLabel("Email: ");
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setFont(new Font("Arial", Font.BOLD, 16));
        lblEmail.setOpaque(true);
        lblEmail.setBackground(new Color(0,0,0,150));
        emailField = new JTextField(15);

        lblPassword = new JLabel("Password: ");
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setOpaque(true);
        lblPassword.setBackground(new Color(0,0,0,150));
        lblPassword.setFont(new Font("Arial", Font.BOLD, 16));
        passwordField = new JPasswordField(15);
        
        staffRadio = new JRadioButton("Staff");
        memberRadio = new JRadioButton("Member");
        staffRadio.setOpaque(false);
        memberRadio.setOpaque(false);

        staffRadio.setForeground(Color.WHITE);
        memberRadio.setForeground(Color.WHITE);
        
        ButtonGroup group = new ButtonGroup();
        group.add(staffRadio);
        group.add(memberRadio);

        btnLogin = new JButton("Login");
        btnLogin.addActionListener(this);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0;
        gbc.weightx = 0;
        background.add(lblEmail, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        background.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        background.add(lblPassword, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        background.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 0, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        background.add(staffRadio, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        background.add(memberRadio, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 0;
        gbc.weighty = 0.5;
        gbc.anchor = GridBagConstraints.EAST;
        background.add(btnLogin, gbc);

        setContentPane(background);
        setVisible(true);
	}
	
	
	 @Override
	    public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == btnLogin) {

	            String email = emailField.getText();
	            String password = new String(passwordField.getPassword());
	            String role = "";

	            if (memberRadio.isSelected()) {
	                role = "member";
	            } else if (staffRadio.isSelected()) {
	                role = "staff";
	            } else {
	                JOptionPane.showMessageDialog(this, "Please select a role");
	                return;
	            }
	            
	            boolean result = DBHelper.checkLogin(email, password, role);

	            if (result && role.equals("member")) {
	                JOptionPane.showMessageDialog(this, "Login successful");

	                MemberForm s = new MemberForm();
	                dispose();

	            } else if (result && role.equals("staff")) {
	                JOptionPane.showMessageDialog(this, "Login successful");

	                StaffForm t = new StaffForm();
	                dispose();

	            } 
	            else {
	                JOptionPane.showMessageDialog(this, "Login failed");
	            }
	        }
	    }
		 	
		
	 
	 	
}

