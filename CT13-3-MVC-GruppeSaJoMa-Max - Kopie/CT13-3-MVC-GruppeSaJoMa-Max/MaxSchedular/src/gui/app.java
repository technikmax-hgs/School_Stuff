package gui;

public class app {
	public static void main (String[] args) {
		
		model m = new model();
		view_schedulerv1 v = new view_schedulerv1();
		controller c = new controller(m, v);
		c.initController();
		v.setVisible(true);
	}
}



