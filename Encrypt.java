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

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;




class EncryptFrame extends JFrame implements ActionListener{

    JFileChooser fc;
    JTextArea log;
    File file;
    JButton Encrypt,Decrypt,OpenFile;

    Signature sign;
    KeyPairGenerator keyPairGen;
    KeyPair pair;
    Cipher cipher;


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
    public void OpenFile(){
        int returnVal = fc.showOpenDialog(EncryptFrame.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
                log.append("Opening: " +file.getName() +".\n");
            } else {
                log.append("Open command cancelled by user.\n");
            }
            log.setCaretPosition(log.getDocument().getLength());
            System.out.println("Done here");
            
    }

    public void GenKey(){
        try{
            sign = Signature.getInstance("SHA256withRSA");

            keyPairGen = KeyPairGenerator.getInstance("RSA");
    
            keyPairGen.initialize(2048);
    
            pair = keyPairGen.generateKeyPair(); 
        }catch(Exception e){
            System.out.println("Error");
        }
    }

    public void Encryption(){
       try{

        cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        cipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());
        
        byte[] input = file.toString().getBytes();
        cipher.update(input);

        byte[] cipherText = cipher.doFinal();	 
        System.out.println(new String(cipherText, "UTF8"));

        }catch(Exception e){
            System.out.println("Error :(");
        }
    }

    public void Decryption(){
        try{
     
        } catch(Exception e){
            System.out.println("Error");
        }
    }

    public void actionPerformed(ActionEvent e){
        fc = new JFileChooser();
        GenKey();

        if (e.getSource() == OpenFile) {
            OpenFile();
        
        }
        else if (e.getSource() == Encrypt) {
            System.out.println(file.getName());
            Encryption();

        }
        else if (e.getSource() == Decrypt) {

        }



    }
}

public class Encrypt {

    public static void main(String[] args) throws Exception{

        System.out.println("File Encript Starting");

        EncryptFrame EncryptFrame = new EncryptFrame();
        EncryptFrame.setVisible(true);


    }
}