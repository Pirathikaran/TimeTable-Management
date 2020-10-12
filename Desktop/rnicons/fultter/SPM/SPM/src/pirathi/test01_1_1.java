
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pirathi;

import pirathi.DbConn;
import pirathi.DbConn;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author asvin
 */
public class test01_1_1 extends javax.swing.JFrame {
    
    private static String batch = null;
    private String gp,xx,rr, session = null;
    
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet result = null;
    /**
     * Creates new form test01
     */
    public test01_1_1() {
        initComponents();
        conn = DbConn.ConnecrDb();
        loadStudentGroup();
        loadRoom();
    }
    
    
    private void loadStudentGroup() {
                       jPanel6.setVisible(false);
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            String query = "SELECT distinct room FROM roomforsession";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            ArrayList<String> studentGroup = new ArrayList<String>();
            studentGroup.add("Select");
            while (rs.next()) {
                studentGroup.add(rs.getString("room"));
            }          
          
            gpComboBox.setModel(new DefaultComboBoxModel<String>(studentGroup.toArray(new String[0])));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadRoom() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM sessionRoom";
            
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            

            ArrayList<String> rooms = new ArrayList<String>();
//            rooms.add("Select");
            while (rs.next()) {
                rooms.add(rs.getString("room"));
            }  
                        
            romComboBox.setModel(new DefaultComboBoxModel<String>(rooms.toArray(new String[0])));
                        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
      
    private void loadSession() {      
        
            gp = gpComboBox.getSelectedItem().toString();
                         rr = sesComboBox.getSelectedItem().toString();
                         System.out.println(gp);

            
            PreparedStatement stmt,smt2 = null;
            ResultSet rs ,rs1= null;

            try {
                String query = "SELECT * FROM roomforsession WHERE room='"+ gp +"'";
                stmt = conn.prepareStatement(query);
                rs1 = stmt.executeQuery();
                xx=rs1.getString("id");
                System.out.println(xx);
                
                
                String query1 = "SELECT * FROM session1 WHERE Id="+ xx +"";
                smt2 = conn.prepareStatement(query1);
                rs = smt2.executeQuery();
                System.out.println(rs);
                
                ArrayList<String> sessions = new ArrayList<String>();
        
//                sessions.add("Select");
                
                 while (rs.next()) {
                sessions.add(rs.getString("subject") + " " + rs.getString("subcod") + " \n"
                        + rs.getString("tag") + " \n" + rs.getString("groupid") + " \n"
                        + rs.getString("count") + "(" + rs.getString("duration") + ")");              
                }            
            sesComboBox.setModel(new DefaultComboBoxModel<String>(sessions.toArray(new String[0])));
            

            } catch (Exception e) {
                e.printStackTrace();
            }

            loadRoom();
    }
    
//    private void loadRoom() {
//        if (sesComboBox.getItemCount() != 0) {
//            String selSession;
//            selSession = sesComboBox.getSelectedItem().toString();
//            String[] selSessionID = selSession.split("-");
//
//            try {
//                PreparedStatement stmt, stmt2 = null;
//                ResultSet rs, rs2 = null;
//
//                ArrayList<String> tag = new ArrayList<String>();
//
//                String query = "select tag from session1 where sessionId='" + selSessionID[0] + "'";
//                stmt = conn.prepareStatement(query);
//                rs = stmt.executeQuery();
//
//                while (rs.next()) {
//                    tag.add(rs.getString("tag"));
//                }
//
//                String query2 = null;
//
//                if (tag.get(0).equalsIgnoreCase("labs")) {
//                    query2 = "select room from sessionRoom where tag='labs'";
//                } else {
//                    query2 = "select room from sessionRoom where tag='lecture' or tag='tute'";
//                }
//
//                stmt2 = conn.prepareStatement(query2);
//                rs2 = stmt2.executeQuery();
//
//                ArrayList<String> rooms = new ArrayList<String>();
//                while (rs2.next()) {
//                    rooms.add(rs2.getString("room"));
//                }
//
//                romComboBox.setModel(new DefaultComboBoxModel<String>(rooms.toArray(new String[0])));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            ArrayList<String> sessions = new ArrayList<String>();
//            sessions.add(null);
//            romComboBox.setModel(new DefaultComboBoxModel<String>(sessions.toArray(new String[0])));
//        }
//    }
    
    private void loadBatch() {
        PreparedStatement stmt, stmt1 = null;
        ResultSet rs, rs1 = null;

        try {
            
            String gp = gpComboBox.getSelectedItem().toString();

            String query = "select batch from timetable where studentGroup='"+ gp +"'";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            ArrayList<String> batchList = new ArrayList<String>();
            while (rs.next()) {
                batchList.add(rs.getString("batch"));
            }

            String query1 = null;

            if (batchList.isEmpty()) {
                query1 = "SELECT batch FROM workingdays";
            } else {
                query1 = "SELECT batch FROM workingdays WHERE batch='" + batchList.get(0) + "'";
            }

            stmt1 = conn.prepareStatement(query1);
            rs1 = stmt1.executeQuery();

            ArrayList<String> batches = new ArrayList<String>();
            while (rs1.next()) {
                batches.add(rs1.getString("batch"));
            }

            bthComboBox.setModel(new DefaultComboBoxModel<String>(batches.toArray(new String[0])));
        } catch (Exception e) {
            e.printStackTrace();
        }

        loadDataToTable();

        loadDayAndTimeslot(bthComboBox.getSelectedItem().toString());
    }
    
    private void loadDayAndTimeslot(String batch) {
        PreparedStatement stmt, stmt1 = null;
        ResultSet rs, rs1 = null;

        try {

            String query = "SELECT days\n"
                    + "FROM day\n"
                    + "WHERE batch=(SELECT distinct batch FROM workingdays WHERE batch='" + batch + "')";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            ArrayList<String> days = new ArrayList<String>();
            while (rs.next()) {
                days.add(rs.getString("days"));
            }

            String query1 = "SELECT time_slot\n"
                    + "FROM timeSlot\n"
                    + "WHERE batch=(SELECT distinct batch FROM workingdays WHERE batch='" + batch + "')";
            
            stmt1 = conn.prepareStatement(query1);
            rs1 = stmt1.executeQuery();

            ArrayList<String> timeSlots = new ArrayList<String>();
            while (rs1.next()) {
                timeSlots.add(rs1.getString("time_slot"));
            }

            dyComboBox.setModel(new DefaultComboBoxModel<String>(days.toArray(new String[0])));

            tmComboBox.setModel(new DefaultComboBoxModel<String>(timeSlots.toArray(new String[0])));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadDataToTable() {
        DefaultTableModel dtm = (DefaultTableModel)viewTable.getModel();

        PreparedStatement stmt, stmt2, stmt3 = null;
        ResultSet rs, rs2, rs3 = null;
        
        String grp = gpComboBox.getSelectedItem().toString();
                String rr = romComboBox.getSelectedItem().toString();
                System.out.println(grp);


        try {            

            String query = "select * from timetable where room='"+grp+"'";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            ArrayList<String> timetableList = new ArrayList<String>();
            while (rs.next()) {
                timetableList.add(rs.getString("batch"));
            }

            if (!timetableList.isEmpty()) {

                String query2 = "select days from day where batch=(select batch from workingdays where batch='" + timetableList.get(0) + "')";
                stmt2 = conn.prepareStatement(query2);
                rs2 = stmt2.executeQuery();

                int[] arr = new int[]{0, 0, 0, 0, 0, 0, 0};

                ArrayList<String> days = new ArrayList<String>();
                while (rs2.next()) {
                    days.add(rs2.getString("days"));
                    if (rs2.getString("days").equalsIgnoreCase("Monday")) {
                        arr[0] = 1;
                    } else if (rs2.getString("days").equalsIgnoreCase("Tuesday")) {
                        arr[1] = 1;
                    } else if (rs2.getString("days").equalsIgnoreCase("Wednesday")) {
                        arr[2] = 1;
                    } else if (rs2.getString("days").equalsIgnoreCase("Thursday")) {
                        arr[3] = 1;
                    } else if (rs2.getString("days").equalsIgnoreCase("Friday")) {
                        arr[4] = 1;
                    } else if (rs2.getString("days").equalsIgnoreCase("Saturday")) {
                        arr[5] = 1;
                    } else if (rs2.getString("days").equalsIgnoreCase("Sunday")) {
                        arr[6] = 1;
                    }
                }

                ArrayList<String> dayandtime = new ArrayList<String>();
                dayandtime.add("Time");

                if (arr[0] == 1) {
                    dayandtime.add("Monday");
                }

                if (arr[1] == 1) {
                    dayandtime.add("Tuesday");
                }

                if (arr[2] == 1) {
                    dayandtime.add("Wednesday");
                }

                if (arr[3] == 1) {
                    dayandtime.add("Thursday");
                }

                if (arr[4] == 1) {
                    dayandtime.add("Friday");
                }

                if (arr[5] == 1) {
                    dayandtime.add("Saturday");
                }

                if (arr[6] == 1) {
                    dayandtime.add("Sunday");
                }

                dtm.setColumnIdentifiers(dayandtime.toArray(new String[0]));
                dtm.setRowCount(0);

                String query3 = "select time_slot from timeSlot where batch='" + timetableList.get(0) + "'";
//                String query3 = "select time_slot from time_slot where batch=(select batch from workingdays where batch='" + timetableList.get(0) + "')";
                
                stmt3 = conn.prepareStatement(query3);
                rs3 = stmt3.executeQuery();

                //ArrayList<String> timeslots = new ArrayList<String>();
                while (rs3.next()) {
                    Vector vector = new Vector();
                    vector.add(rs3.getString("time_slot"));

                    if (arr[0] == 1) {
                        String monData = getTableData(grp, timetableList.get(0), "Monday", rs3.getString("time_slot"));
                        vector.add(monData);
                    }
                    
                    if (arr[1] == 1) {
                        String tueData = getTableData(grp, timetableList.get(0), "Tuesday", rs3.getString("time_slot"));
                        vector.add(tueData);
                    }
                    
                    if (arr[2] == 1) {
                        String wedData = getTableData(grp, timetableList.get(0), "Wednesday", rs3.getString("time_slot"));
                        vector.add(wedData);
                    }
                    
                    if (arr[3] == 1) {
                        String thurData = getTableData(grp, timetableList.get(0), "Thursday", rs3.getString("time_slot"));
                        vector.add(thurData);
                    }
                    
                    if (arr[4] == 1) {
                        String friData = getTableData(grp, timetableList.get(0), "Friday", rs3.getString("time_slot"));
                        vector.add(friData);
                    }
                    
                    if (arr[5] == 1) {
                        String satData = getTableData(grp, timetableList.get(0), "Saturday", rs3.getString("time_slot"));
                        vector.add(satData);
                    }
                    
                    if (arr[6] == 1) {
                        String sunData = getTableData(grp, timetableList.get(0), "Sunday", rs3.getString("time_slot"));
                        vector.add(sunData);
                    }
                    
                    dtm.addRow(vector);
                }
            } else {
                ArrayList<String> dayandtimenull = new ArrayList<String>();
                dayandtimenull.add(null);
                dtm.setColumnIdentifiers(dayandtimenull.toArray(new String[0]));
                dtm.setRowCount(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }       
    
    //method to set value from timetable to viewtable;
    private String getTableData(String room, String batch, String day, String timeslot) {
        ArrayList<String> data = new ArrayList<String>();

        pst = null;
        result = null;

        String retVal = null;

        try {

            String query = "select session from timetable where room=? and batch=? and day=? and timeSlot=?";

            pst = conn.prepareStatement(query);
            pst.setString(1, room);
            pst.setString(2, batch);
            pst.setString(3, day);
            pst.setString(4, timeslot);

            result = pst.executeQuery();

            while (result.next()) {
                data.add(result.getString("session" ));
            }
            if (!data.isEmpty()) {
                retVal = data.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
                try{
                    //                rs.close();
                    pst.close();

                }catch(Exception e){

                }
            }

        return retVal;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        mainpanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        tmComboBox = new javax.swing.JComboBox<>();
        dyComboBox = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        sesComboBox = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        bthComboBox = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        romComboBox = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        viewTable = new javax.swing.JTable();
        gpComboBox = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jDesktopPane1.setBackground(new java.awt.Color(240, 240, 240));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 606, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 778, Short.MAX_VALUE)
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tmComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        dyComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Session");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Batch");

        sesComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        sesComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sesComboBoxActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Day");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Timeslot");

        bthComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        bthComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthComboBoxActionPerformed(evt);
            }
        });

        jButton1.setText("Genarate");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Fill All details");

        romComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Room");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(sesComboBox, 0, 136, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bthComboBox, 0, 136, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dyComboBox, 0, 136, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tmComboBox, 0, 136, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(romComboBox, 0, 136, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {bthComboBox, dyComboBox, romComboBox, sesComboBox, tmComboBox});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel11, jLabel12, jLabel13, jLabel9});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(sesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(romComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(22, 22, 22)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel11)
                    .addComponent(bthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel12)
                    .addComponent(dyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel13)
                    .addComponent(tmComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(61, 61, 61))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {bthComboBox, dyComboBox, romComboBox, sesComboBox, tmComboBox});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel12, jLabel13, jLabel9});

        viewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(viewTable);

        gpComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gpComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gpComboBoxActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Room");

        jButton2.setText("Print Time Table");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainpanelLayout = new javax.swing.GroupLayout(mainpanel);
        mainpanel.setLayout(mainpanelLayout);
        mainpanelLayout.setHorizontalGroup(
            mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainpanelLayout.createSequentialGroup()
                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainpanelLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, Short.MAX_VALUE)
                            .addComponent(gpComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 836, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 23, Short.MAX_VALUE))))
        );
        mainpanelLayout.setVerticalGroup(
            mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainpanelLayout.createSequentialGroup()
                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(gpComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(47, 47, 47)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(231, 231, 231)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel12.setBackground(new java.awt.Color(51, 51, 255));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("TIME TABLE MANAGEMENT");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("View Room Time Table");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(344, 344, 344)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(216, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addGap(0, 24, Short.MAX_VALUE))
        );

        jButton3.setText("Back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(189, 189, 189)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(mainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(mainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 1178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5551, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jDesktopPane1.setLayer(jPanel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(691, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 822, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1179, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void gpComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gpComboBoxActionPerformed
        // TODO add your handling code here:
        loadSession();
        if (gpComboBox.getSelectedItem().toString() != "Select")
        loadBatch();
    }//GEN-LAST:event_gpComboBoxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String studentGroup = gpComboBox .getSelectedItem().toString();
        String room = romComboBox .getSelectedItem().toString();
        String batch = bthComboBox .getSelectedItem().toString();
        String day = dyComboBox .getSelectedItem().toString();
        String timeslot = tmComboBox .getSelectedItem().toString();
        String session = sesComboBox .getSelectedItem().toString();

        PreparedStatement stmt = null;

        try {

            String query = "insert into timetable(session,room,studentGroup,batch,day,timeSlot) values(?,?,?,?,?,?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, session);
            stmt.setString(2, room);
            stmt.setString(3, studentGroup);
            stmt.setString(4, batch);
            stmt.setString(5, day);
            stmt.setString(6, timeslot);
            stmt.execute();

            JOptionPane.showMessageDialog(this, "Successfully Inserted.");
            loadStudentGroup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bthComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthComboBoxActionPerformed
        // TODO add your handling code here:
        loadDayAndTimeslot(bthComboBox.getSelectedItem().toString());
    }//GEN-LAST:event_bthComboBoxActionPerformed

    private void sesComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sesComboBoxActionPerformed
        // TODO add your handling code here:
        loadRoom();
    }//GEN-LAST:event_sesComboBoxActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        MessageFormat header =new MessageFormat("Time Table for "+  gpComboBox.getSelectedItem().toString());
                MessageFormat footer =new MessageFormat("Time Table End");

        try{
            viewTable.print(JTable.PrintMode.NORMAL,header,footer);
            
        }
        catch(Exception e){
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
             ViewTimeTable add_lecture = new ViewTimeTable();
             add_lecture.setVisible(true);
             
             add_lecture.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
             dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(test01.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(test01.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(test01.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(test01.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new test01_1_1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> bthComboBox;
    private javax.swing.JComboBox<String> dyComboBox;
    private javax.swing.JComboBox<String> gpComboBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JComboBox<String> romComboBox;
    private javax.swing.JComboBox<String> sesComboBox;
    private javax.swing.JComboBox<String> tmComboBox;
    private javax.swing.JTable viewTable;
    // End of variables declaration//GEN-END:variables
}
