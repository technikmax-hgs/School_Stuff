package MVCTable;

public class app {
	public static void main (String[] args) {
		
		model m = new model();
		view v = new view();
		controller c = new controller(m, v);
		c.initController();
		v.setVisible(true);
		
	}
}
