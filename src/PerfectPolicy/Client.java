package PerfectPolicy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class Client extends JFrame implements ActionListener, WindowListener {


    // Various network related declarations
    private Socket socket = null;
    private DataInputStream console = null;
    private DataOutputStream streamOut = null;
    private ChatClientThread2 client2 = null;
    private String serverName = "localhost";
    private int serverPort = 4444;


    // Various declarations for UI elements
    JLabel lblTitle, lblName, lblEnterAnswerAndSubmit, lblTopic, lblQuestion, lblA1, lblA2, lblA3, lblA4, lblA5,
            lblAnswer, lblMessage;
    JButton btnSubmit, btnExit, btnConnect;
    JTextArea txtaTopic, txtaName, txtaQuestion, txta1, txta2, txta3, txta4, txta5, txtaAnswer, txtaMessage;

    SpringLayout myLayout = new SpringLayout();

    public Client(){
        SetUpFrame();
        getParameters();
    }

    private void SetUpFrame(){
        this.setBackground(Color.LIGHT_GRAY);
        this.setTitle("Perfect Policy Quiz");
        this.setResizable(false);
        setLayout(myLayout);
        setTitle("Perfect Policy: Employee");
        setResizable(false);
        //getContentPane().setLayout(null);
        setSize(500, 580);
        setLocation(200,200);
        AddWindowListenerToFrame();

        SetUpGUI();
        setVisible(true);
    }

    private void AddWindowListenerToFrame() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                System.exit(0);
            }
        });
    }

    // Sets up GUI
    private void SetUpGUI(){

        lblTitle = UIComponentLibrary.CreateJLabel("Policy Questions", 120, 10, this, myLayout, 30);
        lblName = UIComponentLibrary.CreateJLabel("Staff Name:", 10, 50,this, myLayout, 14);
        lblEnterAnswerAndSubmit = UIComponentLibrary.CreateJLabel("Enter Your Answer and Click Submit", 120, 90, this, myLayout, 15);
        lblTopic = UIComponentLibrary.CreateJLabel("Topic:", 10, 120, this, myLayout, 14);
        lblQuestion = UIComponentLibrary.CreateJLabel("Question:", 10, 160, this, myLayout, 14);
        lblA1 = UIComponentLibrary.CreateJLabel("Option 1: ", 10, 200, this, myLayout, 14);
        lblA2 = UIComponentLibrary.CreateJLabel("Option 2: ", 10, 240, this, myLayout, 14);
        lblA3 = UIComponentLibrary.CreateJLabel("Option 3: ", 10, 280, this, myLayout, 14);
        lblA4 = UIComponentLibrary.CreateJLabel("Option 4: ", 10, 320, this, myLayout, 14);
        lblA5 = UIComponentLibrary.CreateJLabel("Option 5: ", 10, 360, this, myLayout, 14);
        lblAnswer = UIComponentLibrary.CreateJLabel("Your Answer:", 10, 420, this, myLayout, 16);
        lblMessage = UIComponentLibrary.CreateJLabel("Message:",10,500,this,myLayout,12);


        txtaName = UIComponentLibrary.CreateJTextArea(35, 2, 90, 50, true, this, myLayout );
        txtaTopic = UIComponentLibrary.CreateJTextArea(35, 2, 90, 120, false,this, myLayout );
        txtaQuestion = UIComponentLibrary.CreateJTextArea(35, 2, 90, 160, false, this, myLayout);
        txta1 = UIComponentLibrary.CreateJTextArea(35, 2, 90, 200, false, this, myLayout);
        txta2 = UIComponentLibrary.CreateJTextArea(35, 2, 90, 240, false, this, myLayout);
        txta3 = UIComponentLibrary.CreateJTextArea(35, 2, 90, 280, false, this, myLayout);
        txta4 = UIComponentLibrary.CreateJTextArea(35, 2, 90, 320, false, this, myLayout);
        txta5 = UIComponentLibrary.CreateJTextArea(35, 2, 90, 360, false, this, myLayout);
        txtaAnswer = UIComponentLibrary.CreateJTextArea(10, 2, 120, 420, true, this, myLayout);
        txtaMessage = UIComponentLibrary.CreateJTextArea(35, 1, 90, 500, false, this, myLayout);

        btnSubmit = UIComponentLibrary.CreateJButton("Submit", 170, 20, 10, 470, this, this, myLayout);
        btnExit = UIComponentLibrary.CreateJButton("Exit", 170, 20, 300, 470, this, this, myLayout);
        btnConnect = UIComponentLibrary.CreateJButton("Connect", 170, 20, 300, 430, this, this, myLayout);






    }




    // Connection method
    public void connect(String serverName, int serverPort)
    {
        println("Establishing connection. Please wait ...");
        try
        {
            socket = new Socket(serverName, serverPort);
            println("Connected: " + socket);
            open();
        }
        catch (UnknownHostException uhe)
        {
            println("Host unknown: " + uhe.getMessage());
        }
        catch (IOException ioe)
        {
            println("Unexpected exception: " + ioe.getMessage());
        }
    }

    private void send()
    {
        try
        {
            streamOut.writeUTF(txtaAnswer.getText());
            streamOut.flush();
            txtaAnswer.setText("");
            txtaMessage.setText("Answer Sent");
        }
        catch (IOException ioe)
        {
            println("Sending error: " + ioe.getMessage());
            close();
        }
    }

    public void handle(String msg)
    {
        if (msg.equals(".bye"))
        {
            println("Good bye. Press EXIT button to exit ...");
            close();
        }

        else
        {
            System.out.println("Handle: " + msg);
            println(msg);

            String temp[] = msg.split(": ");
            if (temp.length > 5){
                txtaQuestion.setText(temp[4]);
                txtaTopic.setText(temp[2]);
                txta1.setText(temp[5]);
                txta2.setText(temp[6]);
                txta3.setText(temp[7]);
                txta4.setText(temp[8]);
                txta5.setText(temp[9]);
            }

            /*String message = questionNo + ": " + topicText + ": " + subTopicText + ": " + questionText + ": " + answerAText + ": " + answerBText + ": " +
                    answerCText + ": " + answerDText + ": " + answerEText + ": " + correctAnsText;*/

        }
    }

    // Opens data stream
    public void open()
    {
        try
        {
            streamOut = new DataOutputStream(socket.getOutputStream());
            client2 = new ChatClientThread2(this, socket);
        }
        catch (IOException ioe)
        {
            println("Error opening output stream: " + ioe);
        }
    }

    // Closes socket and data stream
    public void close()
    {
        try
        {
            if (streamOut != null)
            {
                streamOut.close();
            }
            if (socket != null)
            {
                socket.close();
            }
        }
        catch (IOException ioe)
        {
            println("Error closing ...");
        }
        client2.close();
        client2.stop();
    }

    // Prints info to message box
    void println(String msg)
    {
        //display.appendText(msg + "\n");
        lblMessage.setText(msg);
    }

    // Retrieves server parameters
    public void getParameters()
    {
//        serverName = getParameter("host");
//        serverPort = Integer.parseInt(getParameter("port"));

        serverName = "localhost";
        serverPort = 4444;
    }



    // Various Windows events
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnConnect){
            connect(serverName, serverPort);
        }
        if(e.getSource() == btnExit){
            System.exit(0);
        }
        if(e.getSource() == btnSubmit){
            send();
        }
    }

    // Window event methods (not always reliable on Windows 10)
    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosing(WindowEvent e) {}
    @Override
    public void windowClosed(WindowEvent e) {}
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}
}
