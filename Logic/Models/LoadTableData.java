package Models;

import javax.swing.table.AbstractTableModel;

public class LoadTableData extends AbstractTableModel {

    //private static final long serialVersionUID = 1L;
    private String[] columnNames;
    private Object[][] data;

    LoadTableData(int rows, int columns, String[] columnNames) {
        setColumnCount(columns);
        setRowsCount(rows);
        this.columnNames = columnNames;
        for (int i = 0; i < columnNames.length; i++)           
            setValueAt(0,i,columnNames[i]);
    }

    public void setColumnCount(int count) {
        this.columnNames = new String[count];
    }

    public void setRowsCount(int count) {
        this.data = new Object[count][this.getColumnCount()];
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }
   
    public void setValueAt(int row, int column, Object value)
    {
        this.data[row][column] = value;
    }
}
