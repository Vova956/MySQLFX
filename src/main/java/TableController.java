import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableController{
    private static TableController tableController = new TableController();

    private DefaultTableModel model;
    private JTable table;

    public static TableController getInstance(){
        return tableController;
    }

    private TableController(){
        model = new DefaultTableModel();
        table = new JTable(model);
    }

    public JScrollPane getTable(int x,int y,int width,int height){
        JScrollPane panel = new JScrollPane(table);
        panel.setBounds(x,y,width,height);
        return panel;
    }

    public void setHeaders(String[] headers){
        model.setColumnIdentifiers(headers);
    }

    public  void append(String[] arr){
        model.addRow(arr);
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void addResult(ResultSet resultSet) throws SQLException {
        int columns = resultSet.getMetaData().getColumnCount();
        String[] arr = new String[columns];

        for (int i = 0; i < columns; i++) {
            arr = new String[columns];
            arr[i] = resultSet.getMetaData().getColumnName(i);
        }

        setHeaders(arr);

        int data = 0;
        while(resultSet.next()){
            String[] str = new String[columns];
            for (int i = 0; i < columns; i++) {
                str[i] = resultSet.getString(data+i);
            }
            append(str);
            data += columns;
        }


    }

}
