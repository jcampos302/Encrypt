// Jorge Campos Test File

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

import java.io.*;

import java.awt.Container; 
import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




class EncryptFrame extends JFrame implements ActionListener{

    JFileChooser fc;
    JTextArea log;
    JButton Encrypt,Decrypt,OpenFile;


    public EncryptFrame() {
        setSize(400,400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();

        Encrypt = new JButton("Encrypt");
        Encrypt.setBounds(50,150,90,30);
        Encrypt.addActionListener(this);
        
        Decrypt = new JButton("Decrypt");
        Decrypt.setBounds(270, 150, 90, 30);
        Decrypt.addActionListener(this);

        OpenFile = new JButton("Open File...");
        OpenFile.setBounds(25,50,125,30);
        OpenFile.addActionListener(this);

        log = new JTextArea(5,20);
        log.setBounds(150,50,225,30);
        log.setEditable(false);
        

        contentPane.add(Encrypt);
        contentPane.add(Decrypt);
        contentPane.add(OpenFile);
        contentPane.add(log);


        


    }
    public void actionPerformed(ActionEvent e) {
        fc = new JFileChooser();

        if (e.getSource() == OpenFile) {
            int returnVal = fc.showOpenDialog(EncryptFrame.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                log.append("Opening: " +file.getName() +".\n");
            } else {
                log.append("Open command cancelled by user.\n");
            }
            log.setCaretPosition(log.getDocument().getLength());
        }
        else if (e.getSource() == Encrypt) {

        }
        else if (e.getSource() == Decrypt) {

        }



    }
}

public class Encript {

    public static void main(String[] args) {

        System.out.println("File Encript Starting");

        EncryptFrame EncryptFrame = new EncryptFrame();
        EncryptFrame.setVisible(true);


    }
}