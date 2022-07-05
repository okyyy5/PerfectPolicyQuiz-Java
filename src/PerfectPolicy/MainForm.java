/*
Copyright [2022] [Oktay Turdu]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */



package PerfectPolicy;




import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.net.*;
import java.io.*;
import org.apache.commons.lang3.StringUtils;   // Third-Party Library

public class MainForm extends JFrame implements ActionListener, WindowListener, MouseListener
{


    private Socket socket = null;
    private DataInputStream console = null;
    private DataOutputStream streamOut = null;
    private ChatClientThread1 client = null;
    private String serverName = "localhost";
    private int serverPort = 4444;

    // Various declarations for UI Components and Objects for functionality
    JLabel lblTitle, lblSearch, lblTableTitle, lblSort, lblCorrectAnswer, lblLinkedList, lblBinaryTree,
            lblPreOrder, lblInOrder, lblPostOrder, lblMessage, lblTopic, lblSubTopic, lblQuestion,
            lblQuestionNo, lblAnswerA, lblAnswerB, lblAnswerC, lblAnswerD, lblAnswerE;
    JButton btnQuestionNo, btnTopic, btnSubTopic, btnExit, btnConnect, btnSend, btnPreOrder, btnInOrder, btnPostOrder,btnSave;
    JTextArea txtaLinkedList, txtaBinaryTree, txtaMessage, txtaQuestionNo, txtaTopic, txtaSubTopic,
              txtaQuestion, txtaAnswerA, txtaAnswerB, txtaAnswerC, txtaAnswerD, txtaAnswerE, txtaCorrectAns;
    JTextField txtSearch;
    JTable mainTable;
    GenericModel myModel;
    ArrayList<Object[]> tblData = new ArrayList<>();
    String file = new File("PerfectPoliciesQuiz_SampleData.txt").getAbsolutePath();

    int correctAns = 0;
    int tally = 0;
    String currentQ = "firstquestion";

    DList dList = new DList();

    HashMap<Integer, Object> hashmap = new HashMap<>();
    BinaryTree binaryTree = new BinaryTree();
    StringBuilder binaryText = new StringBuilder();

    SpringLayout myLayout = new SpringLayout();


    public MainForm()
    {

        setUpFrame();
        getParameters();
    }

    // Sets up overall frame
    private void setUpFrame()
    {
        this.setBackground(Color.LIGHT_GRAY);
        this.setTitle("Perfect Policy Quiz");
        this.setResizable(false);
        setLayout(myLayout);
        setTitle("Perfect Policy");
        setResizable(false);
        //getContentPane().setLayout(null);
        setSize(1000, 800);
        setLocation(200,200);
        AddWindowListenerToFrame();
        setUpOtherLabels();
        setUpTextAreas();
        setUpButtons();
        setUpTitle();
        setUpSearch();
        ReadFile(file, tblData);
        setUpTable(myLayout,tblData);
        SearchTable(txtSearch);
        mainTable.addMouseListener(this);
        setVisible(true);
    }


    // Allows functionality of WindowListener to Frame - for closing operation
    private void AddWindowListenerToFrame()
    {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                System.exit(0);
            }
        });
    }

    // Sets up various UI Labels
    private void setUpOtherLabels()
    {
        lblTableTitle = UIComponentLibrary.CreateJLabel("Policy Questions:",10,100,this,myLayout,20);
        lblSort = UIComponentLibrary.CreateJLabel("Sort By:",10, 430, this, myLayout, 14);
        lblLinkedList = UIComponentLibrary.CreateJLabel("Linked List:",10, 500, this, myLayout, 12);
        lblBinaryTree = UIComponentLibrary.CreateJLabel("Binary Tree:",10, 590, this, myLayout, 12);
        lblPreOrder = UIComponentLibrary.CreateJLabel("Pre-Order", 30, 680, this, myLayout, 14);
        lblInOrder = UIComponentLibrary.CreateJLabel("In-Order", 200, 680, this, myLayout,14);
        lblPostOrder = UIComponentLibrary.CreateJLabel("Post-Order", 370, 680, this, myLayout, 14);
        lblMessage = UIComponentLibrary.CreateJLabel("Message:", 10, 730, this, myLayout, 12);
        lblQuestionNo = UIComponentLibrary.CreateJLabel("Question No:", 530, 70, this, myLayout, 14);
        lblTopic = UIComponentLibrary.CreateJLabel("Topic:", 530, 90, this, myLayout, 14);
        lblSubTopic = UIComponentLibrary.CreateJLabel("SubTopic:", 530, 110,this, myLayout, 14);
        lblQuestion = UIComponentLibrary.CreateJLabel("Question:", 530, 150,this, myLayout, 14);
        lblAnswerA = UIComponentLibrary.CreateJLabel("Answer A:", 530, 190,this, myLayout, 14);
        lblAnswerB = UIComponentLibrary.CreateJLabel("Answer B:", 530, 230,this, myLayout, 14);
        lblAnswerC = UIComponentLibrary.CreateJLabel("Answer C:", 530, 270,this, myLayout, 14);
        lblAnswerD = UIComponentLibrary.CreateJLabel("Answer D:", 530, 310,this, myLayout, 14);
        lblAnswerE = UIComponentLibrary.CreateJLabel("Answer E:", 530, 350,this, myLayout, 14);
        lblCorrectAnswer = UIComponentLibrary.CreateJLabel("Correct Answer: ", 530, 390, this, myLayout, 14);



    }

    // Sets up Text Areas in UI
    private void setUpTextAreas()
    {
        txtaLinkedList = UIComponentLibrary.CreateJTextArea(88,4, 10, 520, false, this, myLayout);
        txtaBinaryTree = UIComponentLibrary.CreateJTextArea(88,4, 10, 610, false, this, myLayout);
        txtaMessage = UIComponentLibrary.CreateJTextArea(88, 1, 60, 730, false, this, myLayout);
        txtaQuestionNo = UIComponentLibrary.CreateJTextArea(32,1,620,70, false,this,  myLayout);
        txtaTopic = UIComponentLibrary.CreateJTextArea(32, 1, 620, 90, false, this, myLayout);
        txtaSubTopic = UIComponentLibrary.CreateJTextArea(32, 2, 620, 110, false, this, myLayout);
        txtaQuestion = UIComponentLibrary.CreateJTextArea(32, 2, 620, 150, false, this, myLayout);
        txtaAnswerA = UIComponentLibrary.CreateJTextArea(32, 2, 620, 190, false, this, myLayout);
        txtaAnswerB = UIComponentLibrary.CreateJTextArea(32, 2, 620, 230, false, this, myLayout);
        txtaAnswerC = UIComponentLibrary.CreateJTextArea(32, 2, 620, 270, false, this, myLayout);
        txtaAnswerD = UIComponentLibrary.CreateJTextArea(32, 2, 620, 310, false, this, myLayout);
        txtaAnswerE = UIComponentLibrary.CreateJTextArea(32, 2, 620, 350, false, this, myLayout);
        txtaCorrectAns = UIComponentLibrary.CreateJTextArea(31, 1, 630, 390, false, this, myLayout);

    }


    // Sets up Buttons for UI
    private void setUpButtons()
    {
        btnQuestionNo = UIComponentLibrary.CreateJButton("Qn #",60,20, 60, 430, this, this, myLayout);
        btnTopic = UIComponentLibrary.CreateJButton("Topic",80,20, 125, 430, this, this, myLayout);
        btnSubTopic = UIComponentLibrary.CreateJButton("SubTopic", 90, 20, 210, 430, this, this, myLayout);
        btnExit = UIComponentLibrary.CreateJButton("Exit", 170, 20, 10, 470, this, this, myLayout);
        btnConnect = UIComponentLibrary.CreateJButton("Connect", 100, 20, 200, 470, this, this, myLayout);
        btnPreOrder = UIComponentLibrary.CreateJButton("Display", 80, 20, 20, 700, this, this, myLayout);
        btnInOrder = UIComponentLibrary.CreateJButton("Display", 80, 20, 185, 700, this, this, myLayout);
        btnPostOrder = UIComponentLibrary.CreateJButton("Display",80,20, 360, 700, this, this, myLayout);
        btnSave = UIComponentLibrary.CreateJButton("Save",80,20, 800, 680, this, this, myLayout);
        btnSend = UIComponentLibrary.CreateJButton("Send", 100, 40,870, 450, this, this,myLayout);

    }

    // Method to set up title
    private void setUpTitle()
    {
        lblTitle = UIComponentLibrary.CreateJLabel("Perfect Policy Quiz",10,10,this,myLayout, 30);

    }

    // Sets up Search Panel
    private void setUpSearch()
    {
        lblSearch = UIComponentLibrary.CreateJLabel("Search Topic:",10,70,this,myLayout, 14 );
        txtSearch = UIComponentLibrary.CreateJTextField(20,100,70,this,myLayout);
    }

    // Sets up Table Panel
    public void setUpTable(SpringLayout myPanelLayout, ArrayList<Object[]> objects)
    {
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        add(top);

        String[] columnNames = {"#", "Topic", "SubTopic"};

        myModel = new GenericModel(objects, columnNames); // Creates model for table with objects and columnNames

        mainTable = new JTable(myModel); // Assigns model to tabel

        // Various aesthetic GUI configurations for table
        mainTable.isForegroundSet();
        mainTable.setShowHorizontalLines(false);
        mainTable.setShowVerticalLines(true);
        mainTable.setColumnSelectionAllowed(true);
        add(mainTable);
        mainTable.setSelectionForeground(Color.white);
        mainTable.setSelectionBackground(Color.gray);

        // Adds scroll Pane to Table
        JScrollPane scrollPane = new JScrollPane(mainTable);
        top.add(scrollPane, BorderLayout.CENTER);
        top.setPreferredSize(new Dimension(500,300));

        myPanelLayout.putConstraint(SpringLayout.WEST, top, 10, SpringLayout.WEST, this);
        myPanelLayout.putConstraint(SpringLayout.NORTH, top, 130, SpringLayout.NORTH, this);



    }


    // Standard Read File method
    private void ReadFile(String file, ArrayList<Object[]> dataValues )
    {
        try
        {
            FileInputStream fstream = new FileInputStream(file);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new BufferedReader(new InputStreamReader(in)));

            int i = 0; // count of lines
            String line;
            String[] lineArray = new String[10]; // An Array with 10 elements


            while((line = br.readLine()) != null)
            {
                lineArray[i] = line;
                i++;
                if(i == 10) {
                    i = 0;
                    dataValues.add((lineArray));
                    lineArray = new String[10]; // Creates new String array after assigning previous array with data
                }

            }
            br.close();
            in.close();
            fstream.close();
            tblData = dataValues;
        }
        catch (Exception e)
        {
            System.err.println("Failed to Read File: " + e.getMessage());
        }
    }


    /**
     * Method to Search Table and Filter as necessary
     * @param textField
     */
    private void SearchTable(JTextField textField)
    {
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(mainTable.getModel());
        mainTable.setRowSorter(rowSorter); // Allows sorting of rows


        // Listens to input from text field and filters table as necessary
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = textField.getText();
                if(text.trim().length() == 0){
                    rowSorter.setRowFilter(null);

                }else{
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));

                }


            }
            // Listens to any updates of text field and updates table as necessary
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = textField.getText();
                if(text.trim().length() == 0){
                    rowSorter.setRowFilter(null);

                }else{
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));

                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not Supported");
            }
        });

    }

    // Send method for the transfering of Data
    private void SendData(){
            if (currentQ.equals("firstquestion")){}
            else{
                dList.head.append(new Node(currentQ +  " " + correctAns + " from " + (tally - 1) + " responses correct <---> " ));
            }


            int qn = Integer.parseInt(txtaQuestionNo.getText());
            String top = txtaTopic.getText();
            String sub = txtaSubTopic.getText();

            binaryTree.addNode(qn,top,sub);

            txtaBinaryTree.setText("");
            binaryText.append(binaryTree.findNode(qn).toString());
            txtaBinaryTree.setText(binaryText.toString());
            txtaLinkedList.setText( "HEAD <-> " + dList.toString() + " <-> TAIL");
            hashmap.put(qn,binaryTree.findNode(qn).toString());
            currentQ = "  Qn " + txtaQuestionNo.getText() + " - " + txtaTopic.getText() + "(" + txtaSubTopic.getText() + ") ," +
                "<---> ";
    }

    // Calls PostOrder sort method for Binary Tree
    private void BinaryTreePostOrder(){
        try{
            txtaBinaryTree.setText("");
            binaryTree.btData = "";
            binaryTree.postOrderTraverseTree(binaryTree.root);
            txtaBinaryTree.setText(binaryTree.btData);
        }catch(Exception e){
            txtaMessage.setText(e.toString());
        }
    }

    // Calls InOrder sort method for Binary Tree
    private void BinaryTreeInOrder(){
        try{
            txtaBinaryTree.setText("");
            binaryTree.btData = "";
            binaryTree.inOrderTraverseTree(binaryTree.root);
            txtaBinaryTree.setText(binaryTree.btData);

        }catch(Exception e){
            txtaMessage.setText(e.toString());
        }
    }

    // Calls PreOrder sort method for Binary Tree
    private void BinaryTreePreOrder(){
        try{
            txtaBinaryTree.setText("");
            binaryTree.btData = "";
            binaryTree.preorderTraverseTree(binaryTree.root);
            txtaBinaryTree.setText(binaryTree.btData);
        }catch(Exception e){
            txtaMessage.setText(e.toString());
        }
    }


    // Assigns Hash map to binary tree before outputting to file
    private void HashBinary(){
        try {
            File hashFile = new File("HashedBinaryTree.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(hashFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(hashmap);

        }catch(Exception e){
            txtaMessage.setText(e.toString());
        }
    }


    // Clears table text
    private void clearQuestionTable(){
            txtaQuestion.setText("");
            txtaCorrectAns.setText("");
            txtaTopic.setText("");
            txtaSubTopic.setText("");
            txtaQuestionNo.setText("");
            txtaAnswerA.setText("");
            txtaAnswerB.setText("");
            txtaAnswerC.setText("");
            txtaAnswerD.setText("");
            txtaAnswerE.setText("");
    }


    // Assigns data to 'right' table
    public void sendDataToQuestionTable(int rowID){
        clearQuestionTable();
        Object[] obj = tblData.get(rowID);
        String questionNo = (String) obj[0];
        String topic = (String) obj[1];
        String subtopic = (String) obj[2];
        String question = (String) obj[3];
        String answerA = (String) obj[4];
        String answerB = (String) obj[5];
        String answerC = (String) obj[6];
        String answerD = (String) obj[7];
        String answerE = (String) obj[8];
        String correctans = (String) obj[9];

        txtaQuestionNo.setText(questionNo);
        txtaTopic.setText(topic);
        txtaSubTopic.setText(subtopic);
        txtaQuestion.setText(question);
        txtaAnswerA.setText(answerA);
        txtaAnswerB.setText(answerB);
        txtaAnswerC.setText(answerC);
        txtaAnswerD.setText(answerD);
        txtaAnswerE.setText(answerE);
        txtaCorrectAns.setText(correctans);

    }



    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == btnQuestionNo){
            Sorts.bubbleSort(tblData);
            myModel.fireTableDataChanged();
        }
        if(e.getSource() == btnExit){
            System.exit(0);
        }
        if(e.getSource() == btnTopic){
            Sorts.InsertionSort(tblData);
            myModel.fireTableDataChanged();
        }
        if(e.getSource() == btnTopic){
            Sorts.InsertionSort(tblData);
            myModel.fireTableDataChanged();
        }
        if(e.getSource() == btnSubTopic){
            Sorts.SelectionSort(tblData);
            myModel.fireTableDataChanged();
        }
        if(e.getSource() == btnSend){
            SendData();
            send();
        }
        if(e.getSource() == btnInOrder){
            BinaryTreeInOrder();
        }
        if(e.getSource() == btnPostOrder){
            BinaryTreePostOrder();
        }
        if(e.getSource() == btnPreOrder){
            BinaryTreePreOrder();
        }
        if(e.getSource() == btnSave){
            HashBinary();
            txtaMessage.setText("File is saved with Hash.");
        }
        if(e.getSource() == btnConnect){
            connect(serverName, serverPort);
        }


    }

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

    // Sends
    private void send()
    {
        try
        {

            String questionNo = txtaQuestionNo.getText();
            String topicText =  txtaTopic.getText();
            String subTopicText =  txtaSubTopic.getText();
            String questionText =  txtaQuestion.getText();
            String answerAText =  txtaAnswerA.getText();
            String answerBText =  txtaAnswerB.getText();
            String answerCText =  txtaAnswerC.getText();
            String answerDText =  txtaAnswerD.getText();
            String answerEText =  txtaAnswerE.getText();
            String correctAnsText =  txtaCorrectAns.getText();

            String message = questionNo + ": " + topicText + ": " + subTopicText + ": " + questionText + ": " + answerAText + ": " + answerBText + ": " +
                    answerCText + ": " + answerDText + ": " + answerEText + ": " + correctAnsText;


            streamOut.writeUTF(message);
            streamOut.flush();
            tally = 0;
            correctAns = 0;

        }
        catch (IOException ioe)
        {
            println("Sending error: " + ioe.getMessage());
            close();
        }
    }


    // General handle method for main form
    public void handle(String msg)
    {
        if (msg.equals(".bye"))
        {
            println("Good bye. Press EXIT button to exit ...");
            close();
        }
        else
        {
            println(msg);
            System.out.println(msg);
            String temp[] = msg.split(": ");

            // Utilizing Apache Commons Ignore Case Method
            if (StringUtils.equalsIgnoreCase(temp[1], txtaCorrectAns.getText())){
                txtaMessage.setText("Correct!");
                correctAns++;
            }else{
                txtaMessage.setText("Incorrect!");
            }
            tally++;
            txtaLinkedList.setText( "HEAD <-> " + dList.toString() + " <-> TAIL");
        }
    }

    // Opens socket and stream
    public void open()
    {
        try
        {
            streamOut = new DataOutputStream(socket.getOutputStream());
            client = new ChatClientThread1(this, socket);
        }
        catch (IOException ioe)
        {
            println("Error opening output stream: " + ioe);
        }
    }

    // Closes data stream
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
        client.close();
        client.stop();
    }

    // General print method for messages
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




    // Windows events (not reliable on Windows 10)
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


    // Various mouse events that listen to Table interactions
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == mainTable){
            int rowID = mainTable.getSelectedRow();
            sendDataToQuestionTable(rowID);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}


