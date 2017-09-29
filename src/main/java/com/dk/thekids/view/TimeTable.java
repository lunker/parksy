package com.dk.thekids.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class TimeTable extends JFrame{

    private int NUM_OF_TEACHERS = 3;
    private JPanel headerPanel;
    private JPanel bodyPanel;

    private ScheduleBuilder.Schedule schedule;
    Calendar cal = new GregorianCalendar();

    DefaultTableModel model;

    public TimeTable() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Swing Calandar");
        this.setSize(900,700);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

    }

    public void createView(){
        String [] columns = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
        model = new DefaultTableModel(null,columns);
        schedule=createSchedule(model);

        headerPanel=createHeader();
        // set layout
        JTable timeOrder = new JTable();
        JScrollPane timeOrderPane=new JScrollPane(timeOrder);

        JScrollPane schedulePane = new JScrollPane(schedule);

        bodyPanel = new JPanel();
        bodyPanel.setLayout(new BorderLayout());

        bodyPanel.add(timeOrderPane, BorderLayout.LINE_START);
        bodyPanel.add(schedulePane,BorderLayout.CENTER);

        this.add(headerPanel,BorderLayout.NORTH);
        this.add(bodyPanel,BorderLayout.CENTER);
        this.setVisible(true);
        schedule.updateMonth();

    }

    private JPanel createHeader(){
        JLabel label=null;
        JButton b1 = new JButton("<-");
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                cal.add(Calendar.MONTH, -1);
//                updateMonth(); //
                schedule.updateMonth();
            }
        });

        JButton b2 = new JButton("->");
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                cal.add(Calendar.MONTH, +1);
//                updateMonth();
                schedule.updateMonth();
            }
        });

        label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(b1,BorderLayout.WEST);
        panel.add(label,BorderLayout.CENTER);
        panel.add(b2,BorderLayout.EAST);

        return panel;
    }

    private ScheduleBuilder.Schedule createSchedule(DefaultTableModel model){
        ScheduleBuilder builder = new ScheduleBuilder();
        return builder.createSchedule(model);
    }

    private OfficeHourTable createOfficeHourTable(){
        return null;
    }
}
