package gui;

import java.awt.event.ActionEvent;

public class controller {
	
	private model model;
	//private model_ausgabe model_ausgabe;
	private view_schedulerv1 view_schedulerv1;
	//private logic logic;
	
	public controller (model m, view_schedulerv1 v) {
		this.model = m;
		//this.model_ausgabe = ma;
		this.view_schedulerv1 = v;
//		this.logic = l;
		initView();
	}
	
	public void initView() {
		view_schedulerv1.getTable().setModel(this.model);
	}
	
	public void initController() {
		view_schedulerv1.get_Append().addActionListener(e -> appendEmptyRow(e));
		view_schedulerv1.get_Delete().addActionListener(e -> deleteRow(e));
		view_schedulerv1.get_Run().addActionListener(e -> run(e));
	}
	
	private void appendEmptyRow(ActionEvent e) {
		this.model.appendEmptyRow();
		int row = view_schedulerv1.getTable().getRowCount();
		view_schedulerv1.getTable().setRowSelectionInterval(row -1, row -1);
		view_schedulerv1.getTable().editCellAt(row -1, 0);
		view_schedulerv1.getTable().setSurrendersFocusOnKeystroke(true);
		view_schedulerv1.getTable().getEditorComponent().requestFocus();
	}

	private void deleteRow(ActionEvent e) {
		int row = view_schedulerv1.getTable().getSelectedRow();
		this.model.deleteRow(row);
	}
	
	private void run(ActionEvent e) {
		//model_ausgabe.setData(logic.inhalt());
		//view_schedulerv1.getAusgabe().setModel(this.model_ausgabe);
	
	}
}