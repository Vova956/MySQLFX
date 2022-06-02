import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MySQLFrame extends JFrame {

    public void init(){
        setBounds(150, 150, 500, 700);
        setLayout(null);
        setTitle("Table");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public MySQLFrame(){
        init();
        //Panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,0,500,700);
        panel.setBackground(Color.GRAY);
        add(panel);

        //Table
        panel.add(TableController.getInstance().getTable(50,50,400,300));

        //TextField
        JTextArea textArea = new JTextArea();
        textArea.setBounds(50,400,400,100);
        panel.add(textArea);

        //Button
        JButton button = new JButton("DO IT!");
        button.setBounds(200,550,100,20);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = textArea.getText();
                Statement statement = null;

                try {
                    Class.forName("org.sqlite.JDBC");
                    Connection connection = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
                     statement = connection.createStatement();
                    //ResultSet resultSet = statement.executeQuery("SELECT * FROM Student");

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                if(str.substring(0,6).equals("SELECT")){
                    try {
                        ResultSet resultSet = statement.executeQuery(str);
                        TableController.getInstance().addResult(resultSet);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
                else {
                    try {
                        DBController.getInstance().executeUpdate(str);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
        panel.add(button);





        setVisible(true);
    }

}
