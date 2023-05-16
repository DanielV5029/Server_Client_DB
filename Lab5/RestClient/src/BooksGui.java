import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class BooksGui {

	public static void main(String[] args) {
        JFrame frame = new JFrame("Database Button GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));
        
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new GridLayout(4, 1));
        
        JLabel idLabel = new JLabel("ID: ");
        JTextField idField = new JTextField("Enter id here");
        JLabel authorLabel = new JLabel("Author: ");
        JTextField authorField = new JTextField("Enter author here");
        JLabel titleLabel = new JLabel("Title: ");
        JTextField titleField = new JTextField("Enter title here");
        JLabel yearLabel = new JLabel("Year: ");
        JTextField yearField = new JTextField("Enter year here");
        

        //GET
        JButton button = new JButton("Get Database Info");
        JTextArea textArea = new JTextArea(20, 30);
        JScrollPane scrollPane = new JScrollPane(textArea);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    BooksGet.main(new String[0]);
                    textArea.setText(BooksGet.getOutput());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        
        //INSERT
        JButton button2 = new JButton("Insert Book");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String author = authorField.getText();
                String title = titleField.getText();
                String year = yearField.getText();

                try {
                    BookPost.insertBook(id, author, title, year);
                    System.out.println("Book inserted successfully!");
                    BooksGet.main(new String[0]);
                	textArea.setText("");
                    textArea.setText(BooksGet.getOutput());
                } catch (Exception ex) {
                    System.out.println("Error inserting book: " + ex.getMessage());
                }
            }
        });

        
        //DELETE
        JButton button3 = new JButton("Delete Book");
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (e.getSource() == button3) {
                        String id = idField.getText();
                        BookDelete.deleteBook(id);
                        BooksGet.main(new String[0]);
                        textArea.setText(BooksGet.getOutput());
                    }
                } catch (Exception ex) {
                    System.out.println("Error inserting book: " + ex.getMessage());
                }
            }
        });
        
        
        //PUT
        JButton button4 = new JButton("Update Book");
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String author = authorField.getText();
                String title = titleField.getText();
                String year = yearField.getText();

                try {
                    BookUpdate.updateBook(id, author, title, year);
                    System.out.println("Book updated successfully!");
                    BooksGet.main(new String[0]);
                    textArea.setText(BooksGet.getOutput());
                } catch (Exception ex) {
                    System.out.println("Error inserting book: " + ex.getMessage());
                }
            }
        });
        
        textFieldPanel.add(idLabel);
        textFieldPanel.add(idField);
        textFieldPanel.add(authorLabel);
        textFieldPanel.add(authorField);
        textFieldPanel.add(titleLabel);
        textFieldPanel.add(titleField);
        textFieldPanel.add(yearLabel);
        textFieldPanel.add(yearField);
        buttonPanel.add(button);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button2.setBackground(Color.BLACK);
        button2.setForeground(Color.WHITE);
        button3.setBackground(Color.BLACK);
        button3.setForeground(Color.WHITE);
        button4.setBackground(Color.BLACK);
        button4.setForeground(Color.WHITE);
        idLabel.setBackground(Color.BLACK);
        idLabel.setForeground(Color.WHITE);
        authorLabel.setForeground(Color.WHITE);
        titleLabel.setForeground(Color.WHITE);
        yearLabel.setForeground(Color.WHITE);
        textFieldPanel.setBackground(Color.BLACK);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(textFieldPanel, BorderLayout.WEST);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }

}
