package views;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import book.Book;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class View2 extends ViewPart {
	public View2() {
	}
	private boolean update;
	private String authorFirstName;
	private String authorLastName;
	public static final String ID = "BookProject.view";
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(3, false));
		Label lblAutor,lblNewLabel,lblNewLabel_1,lblNewLabel_2,lblNewLabel_3;
		lblAutor = new Label(parent, SWT.NONE);
		lblAutor.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblAutor.setText("Autor");
		new Label(parent, SWT.NONE);
		
		Label lblUpdateType = new Label(parent, SWT.NONE);
		lblUpdateType.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblUpdateType.setText("Update type");
		
		lblNewLabel = new Label(parent, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblNewLabel.setText("First name");
		new Label(parent, SWT.NONE);
		
		Button btnDynamically = new Button(parent, SWT.RADIO);
		btnDynamically.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				update = true;
			}
		});
		btnDynamically.setText("Dynamically");
		
		lblNewLabel_1 = new Label(parent, SWT.NONE);
		lblNewLabel_1.setText("                    ");
		new Label(parent, SWT.NONE);
		
		Button btnManually = new Button(parent, SWT.RADIO);
		btnManually.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				update = false;
			}
		});
		btnManually.setText("Manually");
		
		lblNewLabel_2 = new Label(parent, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblNewLabel_2.setText("Last name");
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		
		
		lblNewLabel_3 = new Label(parent, SWT.NONE);
		lblNewLabel_3.setText("                    ");
		new Label(parent, SWT.NONE);
		Button btnUpdate = new Button(parent, SWT.NONE);
		btnUpdate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lblNewLabel_1.setText(authorFirstName);
				lblNewLabel_3.setText(authorLastName);
			}
		});
		btnUpdate.setText("Update");
		
		Menu menu = new Menu(parent);
		parent.setMenu(menu);
		
		MenuItem mntmHideAuthor = new MenuItem(menu, SWT.NONE);
		mntmHideAuthor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lblAutor.setVisible(false);lblNewLabel.setVisible(false);lblNewLabel_1.setVisible(false);lblNewLabel_2.setVisible(false);lblNewLabel_3.setVisible(false);;
			}
		});
		mntmHideAuthor.setText("Hide author");
		
		MenuItem mntmShowAuthor = new MenuItem(menu, SWT.NONE);
		mntmShowAuthor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lblAutor.setVisible(true);lblNewLabel.setVisible(true);lblNewLabel_1.setVisible(true);lblNewLabel_2.setVisible(true);lblNewLabel_3.setVisible(true);;
			}
		});
		mntmShowAuthor.setText("Show author");
		
		getSite().getPage().addSelectionListener(new ISelectionListener() {
			@Override
			public void selectionChanged(IWorkbenchPart part, ISelection selection) {
				if (selection != null & selection instanceof IStructuredSelection) {
					IStructuredSelection strucSelection = (IStructuredSelection) selection;
					Object o = strucSelection.getFirstElement();
					if (o instanceof Book) {
						Book element = (Book) o;
						authorFirstName = element.getAuthor().getFirstName();
						authorLastName = element.getAuthor().getLastName();
						if(update){
							lblNewLabel_1.setText(authorFirstName);
							lblNewLabel_3.setText(authorLastName);
						}
					}
				}
			}
		});

	}


	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
	}
}