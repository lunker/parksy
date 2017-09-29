package com.dk.thekids.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class ScheduleBuilder {

    private int NUM_OF_TEACHERS=0;
    private int ONE_DAY_ROWS=3;


    public ScheduleBuilder() {}

    public Schedule createSchedule(DefaultTableModel model){

        Schedule table = new Schedule(model);

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
        {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % ONE_DAY_ROWS == 0 ? Color.LIGHT_GRAY : Color.WHITE);
                return c;
            }
        });

        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int row=table.rowAtPoint(e.getPoint());
                int col=table.columnAtPoint(e.getPoint());

                if(row%4==0) {
                    return;
                }

                if(SwingUtilities.isRightMouseButton(e)){
                    System.out.println("selected row : " + row);
//                    pops.getRow(table.getSelectedRow());
//                    pops.show(e.getComponent(),e.getX(),e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                int row=table.rowAtPoint(e.getPoint());
                int col=table.columnAtPoint(e.getPoint());

                if(row % 4 == 0 )
                    return;

                System.out.println("selected cell :  " + row + "," + col );
                if (row >= 0 && row < table.getRowCount()) {
                    table.setRowSelectionInterval(row, row);
                } else {
                    table.clearSelection();
                }

                int rowindex = table.getSelectedRow();
                if (rowindex < 0)
                    return;
                if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem menu1 = new JMenuItem("1");
                    JMenuItem menu2 = new JMenuItem("2");
                    popup.add(menu1);
                    popup.add(menu2);
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }// end mouserelease

        });// end mouse listener

        return table;
    }

    public class Schedule extends JTable {

        private DefaultTableModel model=null;
        Calendar cal = new GregorianCalendar();

        private Schedule(DefaultTableModel model) {
            super(model);
            this.model=model;
        }

        @Override
        public boolean isCellEditable(int row, int column) {

            if(row%4==0)
                return false;
            else
                return true;
        }

        public void updateMonth() {
            cal.set(Calendar.DAY_OF_MONTH, 1);

            String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
            int year = cal.get(Calendar.YEAR);
            int startDay = cal.get(Calendar.DAY_OF_WEEK);
            int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            int weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);

            model.setRowCount(0);
            model.setRowCount(weeks*(ONE_DAY_ROWS));

            int dayIndex = startDay-1;
            System.out.println("numberOfDays : " + numberOfDays); // 30
            for(int day=1;day<=numberOfDays;day++){
                int row=dayIndex /7;
                int col=dayIndex %7;

                model.setValueAt(day,row + ONE_DAY_ROWS*row, col);
                model.setValueAt("선영이",row + ONE_DAY_ROWS*row + 1, col);
                model.setValueAt("시간표",row + ONE_DAY_ROWS*row + 2, col);

//                model.setValueAt(day, i/7 + 3*(i/7) , i%7 );
//                model.setValueAt("선영이", dayIndex/7 + 3*(dayIndex/7) + 1 , dayIndex%7 );
//                model.setValueAt("내꺼", i/7 + 3
// *(i/7) + 2, dayIndex%7);
                dayIndex = dayIndex + 1;
            }
        }
    }
}
