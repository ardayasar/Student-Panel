import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class opening extends JFrame{
    private JPanel opening_panel;
    private JButton button1;
    private JTable table1;
    private JButton refresh;

    public DefaultTableModel loadTableModel(){
        String[] headers = {"Öğrenci Numarası", "Ad", "Soyad"};

        DefaultTableModel tblModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblModel.setColumnCount(3);
        tblModel.setColumnIdentifiers(headers);

        String DB_URL = "jdbc:mysql://localhost:3306/ogrenci_panel?useSSL=false";
        String USER = "admin";
        String PASS = "";
        String QUERY = "SELECT ogrenci_id, ad, soyad FROM ogrenciler WHERE deleted = 'false' ORDER BY ogrenci_id DESC";
        PreparedStatement preparedStatement = null;
        Connection c = null;
        ResultSet rs = null;
        try {
            c = DriverManager.getConnection(DB_URL, USER, PASS);
            preparedStatement = c.prepareStatement(QUERY);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                String[] ogrenci = {rs.getString("ogrenci_id"), rs.getString("ad"), rs.getString("soyad")};
                tblModel.addRow(ogrenci);
            }
        } catch (SQLException er) {
            System.out.println("No Response");
        }
        return tblModel;
    }

    public opening(){
        add(opening_panel);
        setSize(600, 400);
        setTitle("Öğrenci Sistemi");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        table1.setModel(loadTableModel());

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel p = new panel();
                p.setVisible(true);
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable table =(JTable) e.getSource();
                Point point = e.getPoint();
                int row = table.rowAtPoint(point);
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
//                    System.out.println(row);
                    String ogrenci_no = String.valueOf(table.getValueAt(row, 0));
//                    System.out.println(ogrenci_no);
                    studentinformation st = new studentinformation(ogrenci_no);
                    st.setVisible(true);
                }
            }
        });
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table1.setModel(loadTableModel());
            }
        });
    }
}
