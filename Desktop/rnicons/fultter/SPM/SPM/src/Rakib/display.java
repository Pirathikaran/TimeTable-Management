/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablesys;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Rakib Uddin
 */
public class display extends javax.swing.JFrame {

    /**
     * Creates new form display
     */
    ResultSet rs=null;
    private static Statement st;
    
    public display() {
        initComponents();
        st=connect.connection();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        barStubtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Barteabtn = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        stnPie = new javax.swing.JButton();
        lecturerPie = new javax.swing.JButton();
        subPie = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1600, 1000));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(51, 0, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Data Visualization");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("TIMETABLE MANAGEMENT");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(520, 520, 520)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(413, 413, 413)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(406, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(31, 31, 31))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1370, 160);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        barStubtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        barStubtn.setText("Students");
        barStubtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barStubtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Bar Chart Representation");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Pie chart Representation");

        Barteabtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Barteabtn.setText("Teachers");
        Barteabtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BarteabtnActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Subjects");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        stnPie.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        stnPie.setText("Students");
        stnPie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stnPieActionPerformed(evt);
            }
        });

        lecturerPie.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lecturerPie.setText("Teachers");
        lecturerPie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lecturerPieActionPerformed(evt);
            }
        });

        subPie.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        subPie.setText("Subjects");
        subPie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subPieActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton7.setText("Back");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Barteabtn))
                                .addGap(359, 359, 359)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(barStubtn)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(496, 496, 496)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(subPie)
                                    .addComponent(lecturerPie)
                                    .addComponent(stnPie)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(397, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(barStubtn)
                    .addComponent(stnPie))
                .addGap(70, 70, 70)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Barteabtn)
                    .addComponent(lecturerPie))
                .addGap(69, 69, 69)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(subPie))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 120, 1370, 600);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void barStubtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barStubtnActionPerformed
        try {
            // TODO add your handling code here:
            
            String sql="select count(count) AS total from session where groupID LIKE '%" + "Y1"+"%' ";
            rs=st.executeQuery(sql);
            rs.next();
            int res1 = rs.getInt("total");
            
            String sql2="select count(count) AS total from session where groupID LIKE '%" + "Y2"+"%' ";
            rs=st.executeQuery(sql2);
            rs.next();
            int res2 = rs.getInt("total");
            
            String sql3="select count(count) AS total from session where groupID LIKE '%" + "Y3"+"%' ";
            rs=st.executeQuery(sql3);
            rs.next();
            int res3 = rs.getInt("total");
            
            String sql4="select count(count) AS total from session where groupID LIKE '%" + "Y4"+"%' ";
            rs=st.executeQuery(sql4);
            rs.next();
            int res4 = rs.getInt("total");
            
            DefaultCategoryDataset dataSet= new DefaultCategoryDataset();
            
            dataSet.setValue(res1, "Student count", "1st year");
            dataSet.setValue(res2, "Student count", "2nd year");
            dataSet.setValue(res3, "Student count", "3rd year");
            dataSet.setValue(res4, "Student count", "4th year");
            

            
            JFreeChart chart=ChartFactory.createBarChart("Data Representation of Students", "Academic year ", "Student count", dataSet,PlotOrientation.VERTICAL,false,true,false);
            CategoryPlot p=chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.black);
            ChartFrame frame=new ChartFrame("Bar char for student",chart);
            frame.setVisible(true);
            frame.setSize(700,450);
        } catch (SQLException ex) {
            Logger.getLogger(display.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_barStubtnActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
         new NewHome().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void BarteabtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BarteabtnActionPerformed
        try {
            // TODO add your handling code here:

            String sql="select count(id) AS total from lecturer where faculty='Computing' ";
            rs=st.executeQuery(sql);
            rs.next();
            int res1 = rs.getInt("total");
            
            String sql2="select count(id) AS total from lecturer where faculty='Engineering' ";
            rs=st.executeQuery(sql2);
            rs.next();
            int res2 = rs.getInt("total");
            
            String sql3="select count(id) AS total from lecturer where faculty='Bissiness' ";
            rs=st.executeQuery(sql3);
            rs.next();
            int res3 = rs.getInt("total");
            
            String sql4="select count(id) AS total from subject where faculty='Humanities & Sciences' ";
            rs=st.executeQuery(sql4);
            rs.next();
            int res4 = rs.getInt("total");
            
            DefaultCategoryDataset dataSet= new DefaultCategoryDataset();
            dataSet.setValue(res1, "Teacher count", "Computing");
            dataSet.setValue(res2, "Teacher count", "Engineering");
            dataSet.setValue(res3, "Teacher count", "Business");
            dataSet.setValue(res4, "Teacher count", "Humanities & Sciences");
            
            JFreeChart chart=ChartFactory.createBarChart("Data Representation of Teachers", "Faculty ", "Teacher count", dataSet,PlotOrientation.VERTICAL,false,true,false);
            CategoryPlot p=chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.black);
            ChartFrame frame=new ChartFrame("Bar char for Teacher",chart);
            frame.setVisible(true);
            frame.setSize(700,450);
        } catch (SQLException ex) {
            Logger.getLogger(display.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BarteabtnActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            // TODO add your handling code here:

            String sql="select count(id) AS total from subject where offeredYear LIKE '%" + "Y1"+"%' ";
            rs=st.executeQuery(sql);
            rs.next();
            int res1 = rs.getInt("total");
            
            String sql2="select count(id) AS total from subject where offeredYear LIKE '%" + "Y2"+"%' ";
            rs=st.executeQuery(sql2);
            rs.next();
            int res2 = rs.getInt("total");
            
            String sql3="select count(id) AS total from subject where offeredYear LIKE '%" + "Y3"+"%' ";
            rs=st.executeQuery(sql3);
            rs.next();
            int res3 = rs.getInt("total");
            
            String sql4="select count(id) AS total from subject where offeredYear LIKE '%" + "Y4"+"%' ";
            rs=st.executeQuery(sql4);
            rs.next();
            int res4 = rs.getInt("total");
            
            DefaultCategoryDataset dataSet= new DefaultCategoryDataset();
            dataSet.setValue(res1, "Subject count", "1st Year");
            dataSet.setValue(res2, "Subject count", "2nd Year");
            dataSet.setValue(res3, "Subject count", "3rd Year");
            dataSet.setValue(res4, "Subject count", "4th Year");
            
            JFreeChart chart=ChartFactory.createBarChart("Data Representation of Subject", "Academic year ", "Subject count", dataSet,PlotOrientation.VERTICAL,false,true,false);
            CategoryPlot p=chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.black);
            ChartFrame frame=new ChartFrame("Bar char for student",chart);
            frame.setVisible(true);
            frame.setSize(700,450);
        } catch (SQLException ex) {
            Logger.getLogger(display.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void stnPieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stnPieActionPerformed
        try {
            // TODO add your handling code here:

            String sql="select count(id) AS total from student where faculty='Computing' ";
            rs=st.executeQuery(sql);
            rs.next();
            int res1 = rs.getInt("total");
            
            String sql2="select count(id) AS total from student where faculty='Engineering' ";
            rs=st.executeQuery(sql2);
            rs.next();
            int res2 = rs.getInt("total");
            
            String sql3="select count(id) AS total from student where faculty='Business' ";
            rs=st.executeQuery(sql3);
            rs.next();
            int res3 = rs.getInt("total");
            
            String sql4="select count(id) AS total from student where faculty='Humanities & Sciences' ";
            rs=st.executeQuery(sql4);
            rs.next();
            int res4 = rs.getInt("total");
            
            DefaultPieDataset pieDataSet= new DefaultPieDataset();
            pieDataSet.setValue("1st Year", new Integer(5));
            pieDataSet.setValue("2nd Year", new Integer(12));
            pieDataSet.setValue("3rd Year", new Integer(15));
            pieDataSet.setValue("4th Year", new Integer(4));
            
            JFreeChart chart=ChartFactory.createPieChart("Pie chart for Student", pieDataSet,true,true,true);
            PiePlot p=(PiePlot) chart.getPlot();
            //p.setForegroundAlpha(TOP_ALIGNMENT);
            ChartFrame frame = new ChartFrame("Pie chart for Student",chart);
            frame.setVisible(true);
            frame.setSize(550,600);
        } catch (SQLException ex) {
            Logger.getLogger(display.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_stnPieActionPerformed

    private void lecturerPieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lecturerPieActionPerformed
        try {
            // TODO add your handling code here:

            String sql="select count(id) AS total from lecturer where faculty='Computing' ";
            rs=st.executeQuery(sql);
            rs.next();
            int res1 = rs.getInt("total");
            
            String sql2="select count(id) AS total from lecturer where faculty='Engineering' ";
            rs=st.executeQuery(sql2);
            rs.next();
            int res2 = rs.getInt("total");
            
            String sql3="select count(id) AS total from lecturer where faculty='Business' ";
            rs=st.executeQuery(sql3);
            rs.next();
            int res3 = rs.getInt("total");
            
            String sql4="select count(id) AS total from lecturer where faculty='Humanities & Sciences' ";
            rs=st.executeQuery(sql4);
            rs.next();
            int res4 = rs.getInt("total");
            
            DefaultPieDataset pieDataSet= new DefaultPieDataset();
            pieDataSet.setValue("Computing faculty", res1);
            pieDataSet.setValue("Engineering faculty",res2);
            pieDataSet.setValue("Business faculty", res3);
            pieDataSet.setValue("Humanities & Sciences", res4);
            
            JFreeChart chart=ChartFactory.createPieChart("Pie chart for Teacher", pieDataSet,true,true,true);
            PiePlot p=(PiePlot) chart.getPlot();
            //p.setForegroundAlpha(TOP_ALIGNMENT);
            ChartFrame frame = new ChartFrame("Pie chart for Teacher",chart);
            frame.setVisible(true);
            frame.setSize(550,600);
        } catch (SQLException ex) {
            Logger.getLogger(display.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_lecturerPieActionPerformed

    private void subPieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subPieActionPerformed
        try {
            // TODO add your handling code here:

            String sql="select count(id) AS total from subject where offeredYear LIKE '%" + "Y1"+"%' ";
            rs=st.executeQuery(sql);
            rs.next();
            int res1 = rs.getInt("total");
            
            String sql2="select count(id) AS total from subject where offeredYear LIKE '%" + "Y2"+"%' ";
            rs=st.executeQuery(sql2);
            rs.next();
            int res2 = rs.getInt("total");
            
            String sql3="select count(id) AS total from subject where offeredYear LIKE '%" + "Y3"+"%' ";
            rs=st.executeQuery(sql3);
            rs.next();
            int res3 = rs.getInt("total");
            
            String sql4="select count(id) AS total from subject where offeredYear LIKE '%" + "Y4"+"%' ";
            rs=st.executeQuery(sql4);
            rs.next();
            int res4 = rs.getInt("total");
            
            DefaultPieDataset pieDataSet= new DefaultPieDataset();
            pieDataSet.setValue("Computing faculty", res1);
            pieDataSet.setValue("Engineering faculty", res2);
            pieDataSet.setValue("Business faculty", res3);
            pieDataSet.setValue("Humanities & Sciences",res4);
            
            JFreeChart chart=ChartFactory.createPieChart("Pie chart for Subjects", pieDataSet,true,true,true);
            PiePlot p=(PiePlot) chart.getPlot();
            //p.setForegroundAlpha(TOP_ALIGNMENT);
            ChartFrame frame = new ChartFrame("Pie chart for Teacher",chart);
            frame.setVisible(true);
            frame.setSize(550,600);
        } catch (SQLException ex) {
            Logger.getLogger(display.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_subPieActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new display().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Barteabtn;
    private javax.swing.JButton barStubtn;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton lecturerPie;
    private javax.swing.JButton stnPie;
    private javax.swing.JButton subPie;
    // End of variables declaration//GEN-END:variables
}
