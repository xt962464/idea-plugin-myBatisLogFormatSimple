package com.alan.plugins.MyBatisLogFormatter.component;

import com.intellij.ui.components.JBScrollPane;

import javax.swing.*;
import java.awt.*;

public class DataListComponent {
    private final JPanel mainPanel;
    private JBScrollPane scrollPane;
    private boolean reverseInd = false;

    private int rowHeight = 100;

    public DataListComponent() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        scrollPane = new JBScrollPane(mainPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    public void addRow(JComponent row) {
        row.setPreferredSize(new Dimension(0, 30));
        row.setMaximumSize(new Dimension(Short.MAX_VALUE, 30));
        if (reverseInd) {
            mainPanel.add(row, 0); // 插入到首部
        } else {
            mainPanel.add(row);
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void removeAll() {
        mainPanel.removeAll();
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JBScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JBScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public boolean isReverseInd() {
        return reverseInd;
    }

    public void setReverseInd(boolean reverseInd) {
        this.reverseInd = reverseInd;
    }

    public int getRowHeight() {
        return rowHeight;
    }

    public void setRowHeight(int rowHeight) {
        this.rowHeight = rowHeight;
    }
}
