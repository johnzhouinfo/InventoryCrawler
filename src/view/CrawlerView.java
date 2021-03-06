/*
 * Created by JFormDesigner on Sun Apr 05 03:56:26 EDT 2020
 */

package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @Author: Lingjue
 * @Date: 2020-04-05
 */
public class CrawlerView extends JFrame {
    CrawlerController crawlerController;

    public CrawlerView(CrawlerController crawlerController) {
        initComponents();
        setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
        setTitle("Inventory Crawler");
        this.crawlerController = crawlerController;
        bestbuyStatus.setText("");
        theSourceStatus.setText("");
        timer.setText("300s");
        status.setText("STOP");
    }

    private void quitActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void clearActionPerformed(ActionEvent e) {
        log.setText("");
    }

    private void startActionPerformed(ActionEvent e) {
        crawlerController.start();
        start.setEnabled(false);
        stop.setEnabled(true);
    }

    private void stopActionPerformed(ActionEvent e) {
        crawlerController.stop();
        start.setEnabled(true);
        stop.setEnabled(false);
    }

    public void setBestbuyStatus(String bestbuyStatus) {
        this.bestbuyStatus.setText(bestbuyStatus);
    }

    public void setTheSourceStatus(String theSourceStatus) {
        this.theSourceStatus.setText(theSourceStatus);
    }

    public void setTimer(String timer) {
        this.timer.setText(timer);
    }

    public void setLog(String log) {
        this.log.append(log);
        //set the scroll bar in the end
        scrollPane1.doLayout();
        JScrollBar jscrollBar = scrollPane1.getVerticalScrollBar();
        if (jscrollBar != null)
            jscrollBar.setValue(jscrollBar.getMaximum());

    }

    public void setStatus(String status) {
        this.status.setText(status);
    }

    public void setTestText(String test) {
        this.test.setText(test);
    }

    private void testActionPerformed(ActionEvent e) {
        crawlerController.playSound();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        status = new JLabel();
        label8 = new JLabel();
        timer = new JLabel();
        label3 = new JLabel();
        bestbuyStatus = new JLabel();
        label5 = new JLabel();
        theSourceStatus = new JLabel();
        scrollPane1 = new JScrollPane();
        log = new JTextArea();
        start = new JButton();
        stop = new JButton();
        clear = new JButton();
        quit = new JButton();
        test = new JButton();
        label9 = new JLabel();

        //======== this ========
        setMinimumSize(new Dimension(540, 380));
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("Nintendo Switch Animal Crossing Inventory Checker");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 5f));
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(40, 5), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("Status");
        contentPane.add(label2);
        label2.setBounds(30, 35, 65, label2.getPreferredSize().height);

        //---- status ----
        status.setText("Start");
        contentPane.add(status);
        status.setBounds(105, 35, 65, status.getPreferredSize().height);

        //---- label8 ----
        label8.setText("Update in next");
        contentPane.add(label8);
        label8.setBounds(new Rectangle(new Point(365, 40), label8.getPreferredSize()));

        //---- timer ----
        timer.setText("TIMER");
        contentPane.add(timer);
        timer.setBounds(460, 40, 60, timer.getPreferredSize().height);

        //---- label3 ----
        label3.setText("BestBuy");
        contentPane.add(label3);
        label3.setBounds(30, 60, 65, label3.getPreferredSize().height);

        //---- bestbuyStatus ----
        bestbuyStatus.setText("INSTORE");
        contentPane.add(bestbuyStatus);
        bestbuyStatus.setBounds(105, 60, 156, bestbuyStatus.getPreferredSize().height);

        //---- label5 ----
        label5.setText("The Source");
        contentPane.add(label5);
        label5.setBounds(new Rectangle(new Point(30, 85), label5.getPreferredSize()));

        //---- theSourceStatus ----
        theSourceStatus.setText("INSTORE");
        contentPane.add(theSourceStatus);
        theSourceStatus.setBounds(105, 85, 156, theSourceStatus.getPreferredSize().height);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(log);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(5, 109, 515, 191);

        //---- start ----
        start.setText("Start");
        start.addActionListener(e -> startActionPerformed(e));
        contentPane.add(start);
        start.setBounds(85, 305, 75, start.getPreferredSize().height);

        //---- stop ----
        stop.setText("Stop");
        stop.setEnabled(false);
        stop.addActionListener(e -> stopActionPerformed(e));
        contentPane.add(stop);
        stop.setBounds(180, 305, 75, stop.getPreferredSize().height);

        //---- clear ----
        clear.setText("Clear");
        clear.addActionListener(e -> clearActionPerformed(e));
        contentPane.add(clear);
        clear.setBounds(275, 305, 75, clear.getPreferredSize().height);

        //---- quit ----
        quit.setText("Quit");
        quit.addActionListener(e -> quitActionPerformed(e));
        contentPane.add(quit);
        quit.setBounds(370, 305, 75, quit.getPreferredSize().height);

        //---- test ----
        test.setText("Test");
        test.addActionListener(e -> testActionPerformed(e));
        contentPane.add(test);
        test.setBounds(420, 70, 84, 19);

        //---- label9 ----
        label9.setText("Alarm");
        contentPane.add(label9);
        label9.setBounds(365, 70, 55, label9.getPreferredSize().height);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JLabel status;
    private JLabel label8;
    private JLabel timer;
    private JLabel label3;
    private JLabel bestbuyStatus;
    private JLabel label5;
    private JLabel theSourceStatus;
    private JScrollPane scrollPane1;
    private JTextArea log;
    private JButton start;
    private JButton stop;
    private JButton clear;
    private JButton quit;
    private JButton test;
    private JLabel label9;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
