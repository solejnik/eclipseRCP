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

public class View2 extends ViewPart {
	public View2() {
	}
	public static final String ID = "BookProject.view";
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Label lblAutor = new Label(parent, SWT.NONE);
		lblAutor.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblAutor.setText("Autor");
		
		Label lblNewLabel = new Label(parent, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblNewLabel.setText("");
		
		Label lblNewLabel_1 = new Label(parent, SWT.NONE);
		lblNewLabel_1.setText("New Label");
		
		Label lblNewLabel_2 = new Label(parent, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblNewLabel_2.setText("");
		
		Label lblNewLabel_3 = new Label(parent, SWT.NONE);
		lblNewLabel_3.setText("New Label");
		
		getSite().getPage().addSelectionListener(new ISelectionListener() {
			@Override
			public void selectionChanged(IWorkbenchPart part, ISelection selection) {
				if (selection != null & selection instanceof IStructuredSelection) {
					IStructuredSelection strucSelection = (IStructuredSelection) selection;
					Object o = strucSelection.getFirstElement();
					if (o instanceof Book) {
						Book element = (Book) o;
						lblNewLabel.setText(element.getAuthor().getFirstName());
						lblNewLabel_2.setText(element.getAuthor().getLastName());
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