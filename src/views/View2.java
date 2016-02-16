package views;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import book.Book;
import book.Library;

public class View2 extends ViewPart {
	public View2() {
	}
	public static final String ID = "BookProject.view";
	private Table table;
	private TableViewer tableViewer;
	private Library library;


	class ViewLabelProvider extends LabelProvider  {
		public String getColumnText(Object obj, int index) {
			return getText(obj);
		}

		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}

		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(
					ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	private class TableLabelProvider extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			Book b = (Book) element;
			String result = "";
			switch (columnIndex) {
			case 0:
				result = ""+b.getId();
				break;
			case 1:
				result = b.getTitle();
				break;
			default:
				// should not reach here
				result = "";
			}
			return result;
		}
	}
	
	private static class ContentProvider implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			Library r = (Library) inputElement;
			return r.getBooks().toArray();
		}
		
		public void dispose() {
		}
		
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	private void initLibrary() {
		Library library = new Library();
		Book book3 = new Book(3l,"Trzecia Ksiazka");
		Book book2 = new Book(2l,"Druga Ksiazka");
		Book book1 = new Book(1l,"Pierwsza Ksiazka");
		library.add(book1);
		library.add(book2);
		library.add(book3);
		this.setLibrary(library);
	}
	public void createPartControl(Composite parent) {
//		parent.setLayout(new GridLayout(1, true));
		//Add TableColumnLayout
				TableColumnLayout layout = new TableColumnLayout();
				parent.setLayout(layout);
		tableViewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, false, true, 5, 5));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnFirst = tableViewerColumn.getColumn();
		layout.setColumnData(tblclmnFirst, new ColumnWeightData(2, ColumnWeightData.MINIMUM_WIDTH, true));
		tblclmnFirst.setText("First");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnLast = tableViewerColumn_1.getColumn();
		// Specify width using weights
		layout.setColumnData(tblclmnLast, new ColumnWeightData(2, ColumnWeightData.MINIMUM_WIDTH, true));
		tblclmnLast.setText("Last");
		tableViewer.setLabelProvider(new TableLabelProvider());
		tableViewer.setContentProvider(new ContentProvider());
		initLibrary();
		
	}


	public void setLibrary(Library library) {
		this.library = library;
		this.tableViewer.setInput(library);
	}
	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		table.setFocus();
	}
}