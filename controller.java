package MVCTable;

public class controller {
	private model model;
	private view view;
	
	public controller (model m, view v) {
		this.model = m;
		this.view = v;
		initView();
	}
	public void initView() {
		view.getTable().setModel(this.model);

	}
	public void initController() {
	}
		
	}


	