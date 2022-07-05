package PerfectPolicy;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class UIComponentLibrary
{
    public static JButton CreateJButton(String name, int sizeX, int sizeY, int posX, int posY, ActionListener listener, Frame frame, SpringLayout layout)
    {
        JButton btnFrame = new JButton(name); //Creates button
        btnFrame.addActionListener(listener); //Adds action listener to register clicks
        btnFrame.setPreferredSize(new Dimension(sizeX,sizeY)); //Set dimensions
        layout.putConstraint(SpringLayout.WEST,btnFrame,posX, SpringLayout.WEST,  frame); //Sets X coordinate
        layout.putConstraint(SpringLayout.NORTH,btnFrame,posY, SpringLayout.NORTH,  frame); //Sets Y coordinate
        frame.add(btnFrame); //Adds button to panel
        return btnFrame;
    }

    public static JTextField CreateJTextField(int size, int posX, int posY, Frame frame, SpringLayout layout)
    {
        JTextField txtFieldFrame = new JTextField(size); //Sets size of text field
        layout.putConstraint(SpringLayout.WEST,txtFieldFrame,posX, SpringLayout.WEST, frame); //Sets X coordinate
        layout.putConstraint(SpringLayout.NORTH,txtFieldFrame,posY, SpringLayout.NORTH, frame); //Sets Y coordinate
        frame.add(txtFieldFrame); //Adds text field to frame
        return txtFieldFrame;
    }

    public static JLabel CreateJLabel(String text, int posX, int posY, Frame frame, SpringLayout layout, int fontsize)
    {
        JLabel labelFrame = new JLabel(text); //Sets text of label
        labelFrame.setFont(new Font(Font.SERIF,Font.PLAIN,fontsize));
        labelFrame.setOpaque(true);
        layout.putConstraint(SpringLayout.WEST,labelFrame,posX, SpringLayout.WEST, frame); //Sets X coordinate
        layout.putConstraint(SpringLayout.NORTH,labelFrame,posY, SpringLayout.NORTH, frame); //Sets Y coordinate
        frame.add(labelFrame); //Adds label to frame
        return labelFrame;
    }

    public static JTextArea CreateJTextArea(int width, int height,int posX, int posY,boolean editable, Frame frame, SpringLayout layout )
    {
        JTextArea txtArea = new JTextArea(height,width); //Declares, instantiates textarea, sets width and height
        txtArea.setEditable(editable); //Disallows manual editing of the textArea
        layout.putConstraint(SpringLayout.WEST,txtArea,posX, SpringLayout.WEST, frame); //Sets X coordinate
        layout.putConstraint(SpringLayout.NORTH,txtArea,posY, SpringLayout.NORTH, frame); //Sets Y coordinate
        txtArea.setLineWrap(true);
        frame.add(txtArea);  //Adds text area to specified frame
        return txtArea;
    }

    public static JComboBox<String> CreateStringJComboBox(String[] array, int posX, int posY,ActionListener listener , Frame frame, SpringLayout layout){
        JComboBox<String> box = new JComboBox<>(array); //Declares, instantiates combobox, sets values to be specified array
        box.addActionListener(listener); //Adds Actionlistener to combobox
        box.setEditable(false); //Disallows combobox to be editable
        layout.putConstraint(SpringLayout.WEST,box,posX, SpringLayout.WEST, frame); //Sets X coordinate
        layout.putConstraint(SpringLayout.NORTH,box,posY, SpringLayout.NORTH, frame); //Sets Y coordinate
        frame.add(box); //Adds combobox to specified frame
        return box;
    }

    public static JPanel CreateJPanel(int width, int height,int posX, int posY, String colour, Frame frame, SpringLayout layout)
    {
        JPanel panel = new JPanel(); //Declares, instantiates panel
        panel.setPreferredSize(new Dimension(width,height));
        panel.setBackground(Color.decode(colour));
        layout.putConstraint(SpringLayout.WEST,panel,posX, SpringLayout.WEST, frame); //Sets X coordinate
        layout.putConstraint(SpringLayout.NORTH,panel,posY, SpringLayout.NORTH, frame); //Sets Y coordinate
        return panel;
    }

}

