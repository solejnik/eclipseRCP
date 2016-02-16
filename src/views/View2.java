package views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;

public class View2 extends ViewPart {
	public View2() {
	}
	public static final String ID = "BookProject.view";
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(2, false));
		
		Label lblNewLabel = new Label(parent, SWT.NONE);
		lblNewLabel.setText("Sort by:");
		
		Label lblNewLabel_1 = new Label(parent, SWT.NONE);
		lblNewLabel_1.setText("New Label");
		lblNewLabel_1.setVisible(false);
		
		Button btnRadioButton = new Button(parent, SWT.RADIO);
		btnRadioButton.setText("Id");
		new Label(parent, SWT.NONE);
		btnRadioButton.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				lblNewLabel_1.setText("id");
			}
		});
		
		Button btnRadioButton_1 = new Button(parent, SWT.RADIO);
		btnRadioButton_1.setText("Title");
		new Label(parent, SWT.NONE);
		btnRadioButton_1.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				lblNewLabel_1.setText("title");
			}
		});		
		Button btnNewButton = new Button(parent, SWT.NONE);
		btnNewButton.setText("New Button");
		btnNewButton.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				System.out.println(lblNewLabel_1.getText());
			}
		});
	}


	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
	}
}