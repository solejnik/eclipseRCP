package views;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import book.Book;

public class AuthorView extends ViewPart {
	public AuthorView() {
	}
	private boolean update;
	private String authorFirstName;
	private String authorLastName;
	private String authorJson;
	public static final String ID = "BookProject.view";
	private Text text;
	private ObjectMapper mapper = new ObjectMapper();
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(3, false));
		Label lblAutor,lblNewLabel,lblNewLabel_1,lblNewLabel_2,lblNewLabel_3;
		lblAutor = new Label(parent, SWT.NONE);
		lblAutor.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblAutor.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblAutor.setText("Autor");
		
		Label label = new Label(parent, SWT.SEPARATOR | SWT.VERTICAL);
		GridData gd_label = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 5);
		gd_label.heightHint = 100;
		label.setLayoutData(gd_label);
		
		Label lblUpdateType = new Label(parent, SWT.NONE);
		lblUpdateType.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblUpdateType.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblUpdateType.setText("Update type");
		
		lblNewLabel = new Label(parent, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblNewLabel.setText("First name");
		
		Button btnDynamically = new Button(parent, SWT.RADIO);
		btnDynamically.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnDynamically.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				update = true;
			}
		});
		btnDynamically.setText("Dynamically");
		
		lblNewLabel_1 = new Label(parent, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("                    ");
		
		Button btnManually = new Button(parent, SWT.RADIO);
		btnManually.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnManually.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				update = false;
			}
		});
		btnManually.setText("Manually");
		
		lblNewLabel_2 = new Label(parent, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblNewLabel_2.setText("Last name");
		new Label(parent, SWT.NONE);
		
		
		lblNewLabel_3 = new Label(parent, SWT.NONE);
		lblNewLabel_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_3.setText("                    ");
		Button btnUpdate = new Button(parent, SWT.NONE);
		btnUpdate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnUpdate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lblNewLabel_1.setText(authorFirstName);
				lblNewLabel_3.setText(authorLastName);
				text.setText(authorJson);
			}
		});
		btnUpdate.setText("Update");
		
		Menu menu = new Menu(parent);
		parent.setMenu(menu);
		
		MenuItem mntmHideAuthor = new MenuItem(menu, SWT.NONE);
		mntmHideAuthor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.setText("");
			}
		});
		mntmHideAuthor.setText("Hide author");
		
		MenuItem mntmShowAuthor = new MenuItem(menu, SWT.NONE);
		mntmShowAuthor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.setText(authorJson);
			}
		});
		mntmShowAuthor.setText("Show author");
		
		text = new Text(parent, SWT.BORDER);
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1);
		gd_text.widthHint = 300;
		text.setLayoutData(gd_text);
		
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
						try {
							authorJson = mapper.writeValueAsString(element.getAuthor());
						} catch (JsonProcessingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(update){
							lblNewLabel_1.setText(authorFirstName);
							lblNewLabel_3.setText(authorLastName);
							text.setText(authorJson);
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