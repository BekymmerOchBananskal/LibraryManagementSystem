package librarymanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookManagementForm extends JFrame implements ActionListener{

	public BookManagementForm() {
		setTitle("Add Book");
        setSize(400, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Resmi yükle
        ImageIcon bg = new ImageIcon(
                "C:\\Users\\atili\\OneDrive\\Desktop\\Java\\Workspace-GorselTabanliProgramlama\\"
                + "LibraryManagementSystem\\img\\login6.jpeg"
        );

        // Resmi pencere boyutuna göre ölçekle
        Image img = bg.getImage();
        Image scaledImg = img.getScaledInstance(400, 500, Image.SCALE_SMOOTH);

        // Arka plan label
        JLabel background = new JLabel(new ImageIcon(scaledImg));
        
        
        setContentPane(background);

        setVisible(true);
	}
	@Override
    public void actionPerformed(ActionEvent e) {
		
	}
}
