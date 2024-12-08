package me.app.kelbengkel.frames;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public interface FrameBase {
  public void loadData();

  public void clearInput();

  public HashMap<String, Object> processInput();

  default public DefaultTableModel getUpdatedModel(JTable table, ArrayList<Object[]> dataList) {
    final DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.setRowCount(0);
    for (Object[] rowData : dataList) {
      model.addRow(rowData);
    }
    return model;
  }
}
