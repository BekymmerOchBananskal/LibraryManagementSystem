package librarymanagementsystem.forms;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import librarymanagementsystem.DBHelper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookManagementForm extends JFrame implements ActionListener {

    JLabel lblTitle, lblAuthor, lblCategory, lblPublisher, lblPageCount;
    JTextField txtTitle, txtAuthor, txtCategory, txtPublisher, txtPageCount;

    JButton btnAdd, btnUpdate, btnDelete, btnClear;

    JTable table;
    DefaultTableModel model;
    JScrollPane scrollPane;

    JPanel rightPanel, btnPanel;

    int selectedBookId = -1;

    public BookManagementForm() {

        setTitle("Book Management");
        setSize(950, 550);
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon bg = new ImageIcon(
                "C:\\Users\\atili\\OneDrive\\Desktop\\Java\\Workspace-GorselTabanliProgramlama\\"
                        + "LibraryManagementSystem\\img\\login6.jpeg"
        );

        Image img = bg.getImage();
        Image scaledImg = img.getScaledInstance(400, 550, Image.SCALE_SMOOTH);

        JLabel leftPanel = new JLabel(new ImageIcon(scaledImg));
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.setPreferredSize(new Dimension(400, 550));

        TitledBorder leftBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 3),
                "Book Information"
        );

        leftBorder.setTitleColor(Color.WHITE);

        leftPanel.setBorder(leftBorder);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(15, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        Dimension fieldSize = new Dimension(170, 25);

        lblTitle = new JLabel("Title: ");
        lblTitle.setForeground(Color.WHITE);

        txtTitle = new JTextField();
        txtTitle.setPreferredSize(fieldSize);

        gbc.gridx = 0;
        gbc.gridy = 0;
        leftPanel.add(lblTitle, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        leftPanel.add(txtTitle, gbc);

        lblAuthor = new JLabel("Author: ");
        lblAuthor.setForeground(Color.WHITE);

        txtAuthor = new JTextField();
        txtAuthor.setPreferredSize(fieldSize);

        gbc.gridx = 0;
        gbc.gridy = 1;
        leftPanel.add(lblAuthor, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        leftPanel.add(txtAuthor, gbc);

        lblCategory = new JLabel("Category: ");
        lblCategory.setForeground(Color.WHITE);

        txtCategory = new JTextField();
        txtCategory.setPreferredSize(fieldSize);

        gbc.gridx = 0;
        gbc.gridy = 2;
        leftPanel.add(lblCategory, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        leftPanel.add(txtCategory, gbc);

        lblPublisher = new JLabel("Publisher: ");
        lblPublisher.setForeground(Color.WHITE);

        txtPublisher = new JTextField();
        txtPublisher.setPreferredSize(fieldSize);

        gbc.gridx = 0;
        gbc.gridy = 3;
        leftPanel.add(lblPublisher, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        leftPanel.add(txtPublisher, gbc);

        lblPageCount = new JLabel("Page Count: ");
        lblPageCount.setForeground(Color.WHITE);

        txtPageCount = new JTextField();
        txtPageCount.setPreferredSize(fieldSize);

        gbc.gridx = 0;
        gbc.gridy = 4;
        leftPanel.add(lblPageCount, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        leftPanel.add(txtPageCount, gbc);

        btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        btnPanel.setOpaque(false);

        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");

        btnAdd.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnClear.addActionListener(this);

        btnPanel.add(btnAdd);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);
        btnPanel.add(btnClear);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        leftPanel.add(btnPanel, gbc);

        rightPanel = new JPanel(new BorderLayout());

        rightPanel.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.BLACK, 3),
                        "Book List"
                )
        );

        String[] columns = {
        	    "ID",
        	    "Title",
        	    "Author",
        	    "Category",
        	    "Publisher",
        	    "Page Count",
        	    "Status"
        	};

        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        table.setRowHeight(25);

        table.getColumnModel().getColumn(0).setMinWidth(40);
        table.getColumnModel().getColumn(0).setMaxWidth(40);

        table.getColumnModel().getColumn(1).setPreferredWidth(170);
        table.getColumnModel().getColumn(2).setPreferredWidth(130);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(130);
        table.getColumnModel().getColumn(5).setPreferredWidth(90);
        table.getColumnModel().getColumn(5).setPreferredWidth(50);

        scrollPane = new JScrollPane(table);

        rightPanel.add(scrollPane, BorderLayout.CENTER);

        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int selectedRow = table.getSelectedRow();

                if (selectedRow != -1) {

                    selectedBookId = Integer.parseInt(
                            model.getValueAt(selectedRow, 0).toString()
                    );

                    txtTitle.setText(model.getValueAt(selectedRow, 1).toString());
                    txtAuthor.setText(model.getValueAt(selectedRow, 2).toString());
                    txtCategory.setText(model.getValueAt(selectedRow, 3).toString());
                    txtPublisher.setText(model.getValueAt(selectedRow, 4).toString());
                    txtPageCount.setText(model.getValueAt(selectedRow, 5).toString());
                }
            }
        });

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        DBHelper.getAllBooks(model);

        setVisible(true);
    }

    private void clearFields() {

        txtTitle.setText("");
        txtAuthor.setText("");
        txtCategory.setText("");
        txtPublisher.setText("");
        txtPageCount.setText("");

        selectedBookId = -1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnAdd) {

            DBHelper.insertBook(
                    txtTitle.getText(),
                    txtAuthor.getText(),
                    txtCategory.getText(),
                    txtPublisher.getText(),
                    Integer.parseInt(txtPageCount.getText())
            );

            DBHelper.getAllBooks(model);

            clearFields();
        }

        if (e.getSource() == btnUpdate) {

            if (selectedBookId == -1) {

                JOptionPane.showMessageDialog(this,
                        "Please select a book.");

                return;
            }

            DBHelper.updateBook(
                    selectedBookId,
                    txtTitle.getText(),
                    txtAuthor.getText(),
                    txtCategory.getText(),
                    txtPublisher.getText(),
                    Integer.parseInt(txtPageCount.getText())
            );

            DBHelper.getAllBooks(model);

            clearFields();
        }

        if (e.getSource() == btnDelete) {

            if (selectedBookId == -1) {

                JOptionPane.showMessageDialog(this,
                        "Please select a book.");

                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this book?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {

                DBHelper.deleteBook(selectedBookId);

                DBHelper.getAllBooks(model);

                clearFields();
            }
        }

        if (e.getSource() == btnClear) {

            clearFields();
        }
    }
}