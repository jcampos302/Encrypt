// Jorge Campos Test File

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

class EncryptFrame extends JFrame implements ActionListener {

    JFileChooser fc;
    JTextArea Filelog, shiftText, statusLog;
    JLabel shiftLabel;
    File file;
    JButton Encrypt, Decrypt, OpenFile;
    String NewFile;

    public EncryptFrame() {
        setSize(400, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();

        Encrypt = new JButton("Encrypt");
        Encrypt.setBounds(50, 200, 90, 30);
        Encrypt.addActionListener(this);

        Decrypt = new JButton("Decrypt");
        Decrypt.setBounds(270, 200, 90, 30);
        Decrypt.addActionListener(this);

        shiftLabel = new JLabel("Enter a Shift # (1-25)");
        shiftLabel.setBounds(25, 125, 225, 30);
        shiftLabel.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        
        shiftText = new JTextArea(5,20);
        shiftText.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        shiftText.setBounds(250, 125, 50, 30);
        shiftText.setEditable(true);

        OpenFile = new JButton("Open File...");
        OpenFile.setBounds(25, 50, 125, 30);
        OpenFile.addActionListener(this);

        Filelog = new JTextArea(5, 20);
        Filelog.setBounds(150, 50, 225, 30);
        Filelog.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        Filelog.setEditable(false);

        statusLog = new JTextArea(5,20);
        statusLog.setBounds(35,275,325,30);
        statusLog.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        statusLog.setEditable(false);

        contentPane.add(Encrypt);
        contentPane.add(Decrypt);
        contentPane.add(OpenFile);
        contentPane.add(Filelog);
        contentPane.add(shiftText);
        contentPane.add(shiftLabel);
        contentPane.add(statusLog);

        statusLog.setText("Welcome to Encrypt\n");

    }

    public void Encrypt() {
        statusLog.setText("Starting Encryption on: "+ file.getName());
        try{
            int shift = Integer.parseInt(shiftText.getText());
            
            try{
                Scanner input = new Scanner(file);
                PrintStream output = new PrintStream(new File("Encrypted_"+NewFile));
                while(input.hasNextLine()){
                    output.println(caesar(input.nextLine(),shift));
                }
                input.close();
                statusLog.setText("Created file Encrypted_"+NewFile);
            } catch (IOException e){
                statusLog.setText("Error File is unable to Encrypt.\n");
            }
        } catch (NumberFormatException e){
            statusLog.setText("Error Shift # Not Accepted.\n");
        }
    }

    public void Decrypt() {
        statusLog.setText("Starting Decryption on: "+ file.getName());
        try{
            int shift = 26 - Integer.parseInt(shiftText.getText());
            try{
                Scanner input = new Scanner(file);
                PrintStream output = new PrintStream(new File("DecryptFile_"+NewFile));
                while(input.hasNextLine()){
                    output.println(caesar(input.nextLine(),shift));
                }
                input.close();
                statusLog.setText("Created File DecryptedFile_"+NewFile);
            } catch (IOException e){
                statusLog.setText("Error File is unable to Decrypt\n");
            }
        } catch (NumberFormatException e){
            statusLog.setText("Error Shift # Not Accepted.\n");
        }
    }

    public static String caesar(String text, int shift) {
        shift %= 26;
        if (shift == 0) return text;
        StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            int c = text.charAt(i);

            if (c >= 'A' && c <= 'Z') {
                c += shift;
                if (c > 'Z') c -= 26;
            } else if (c >= 'a' && c <= 'z') {
                c += shift;
                if (c > 'z') c -= 26;
            }

            sb.append((char) c);
        }
        return sb.toString();
    }
     
    public void OpenFile(){
        int returnVal = fc.showOpenDialog(EncryptFrame.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            Filelog.setText("Opening: " +file.getName() +".\n");
        } else {
            Filelog.setText("Open Command Cancelled by user.\n");
        }
        Filelog.setCaretPosition(Filelog.getDocument().getLength());
        NewFile = file.getName();    
    }

    public void actionPerformed(ActionEvent e){
        fc = new JFileChooser();

        if (e.getSource() == OpenFile) {
            OpenFile();
        
        }
        else if (e.getSource() == Encrypt) {
            try{
                Encrypt();
            } catch (NullPointerException e1){
                statusLog.setText("Error Please Select a File.\n");
            }
        }
        else if (e.getSource() == Decrypt) {
            try{
            Decrypt();
            } catch (NullPointerException e1){
                statusLog.setText("Error Please Select a File.\n");
            }
        }

    }
}

public class Encrypt {

    public static void main(String[] args) throws Exception{

        EncryptFrame EncryptFrame = new EncryptFrame();
        EncryptFrame.setVisible(true);
        
    }
}