package PerfectPolicy;

import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class GenericModel extends AbstractTableModel
{
    ArrayList<Object[]> dataObj;
    String[] header;

    int column;

    // Constructor
    GenericModel(ArrayList<Object[]> obje, String[] header){
        this.header = header;
        dataObj = obje;
        column = this.findColumn("SubTopic");
    }


    public Object getValueAt(int rowIndex, int columnIndex)
    {
        return dataObj.get(rowIndex)[columnIndex];
    }

    @Override
    public int getRowCount() {
        return dataObj.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    public String getColumnName(int index)
    {
        return header[index];
    }

    public Class getColumnClass(int columnIndex)
    {
        if(columnIndex == column)
        {
            return String.class;
        }
        // Unless specified in If statement set class to the column's default index class
        return super.getColumnClass(columnIndex);
    }

    void add(String no, String topic, String subtopic, String question, String answerA, String answerB, String answerC, String answerD, String answerE, String correctans)
    {
        Object[] item = new Object[10];
        item[0] = no;
        item[1] = topic;
        item[2] = subtopic;
        item[3] = question;
        item[4] = answerA;
        item[5] = answerB;
        item[6] = answerC;
        item[7] = answerD;
        item[8] = answerE;
        item[9] = correctans;


        fireTableDataChanged();
    }

}
