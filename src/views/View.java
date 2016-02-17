package views;

import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import book.Author;
import book.Book;
import book.Library;
import dialog.AddBookDialog;
import dialog.EditBookDialog;

public class View extends ViewPart {
	public View() {
	}

	public static final String ID = "BookProject.view";
	private Table table;
	private TableViewer tableViewer;
	private Library library;

	class ViewLabelProvider extends LabelProvider {
		public String getColumnText(Object obj, int index) {
			return getText(obj);
		}

		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}

		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
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
				result = "" + b.getId();
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
		Book book3 = new Book("Trzecia Ksiazka",new Author("Jak", "Kowalski"));
		Book book2 = new Book("Druga Ksiazka",new Author("Kazimierz","Nowak"));
		Book book1 = new Book("Pierwsza Ksiazka",new Author("Anna","Wanna"));
		library.add(book1);
		library.add(book2);
		library.add(book3);
		this.setLibrary(library);
	}

	public void createPartControl(Composite parent) {
		TableColumnLayout layout = new TableColumnLayout();
		parent.setLayout(layout);
		tableViewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		tableViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {

			}
		});
		table = tableViewer.getTable();
		table.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, false, true, 5, 5));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setToolTipText("xxx");

		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnFirst = tableViewerColumn.getColumn();
		layout.setColumnData(tblclmnFirst, new ColumnWeightData(2, ColumnWeightData.MINIMUM_WIDTH, true));
		tblclmnFirst.setText("Id");
		tblclmnFirst.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				library.sortById();
				tableViewer.setInput(library);
			}
		});

		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnLast = tableViewerColumn_1.getColumn();
		// Specify width using weights
		layout.setColumnData(tblclmnLast, new ColumnWeightData(2, ColumnWeightData.MINIMUM_WIDTH, true));
		tblclmnLast.setText("Title");
		tblclmnLast.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				library.sortByTitle();
				tableViewer.setInput(library);
			}
		});

		Menu menu = new Menu(table);
		table.setMenu(menu);
		
				MenuItem mntmAdd = new MenuItem(menu, SWT.NONE);
				mntmAdd.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						AddBookDialog dialog = new AddBookDialog(null);
						dialog.create();
						if (dialog.open() == Window.OK) {
							library.add(new Book(dialog.getLastName(),new Author(dialog.getFirstName(), dialog.getLastName())));
							tableViewer.setInput(library);
						}
					}
				});
				mntmAdd.setText("Add");
		
		MenuItem mntmEdit = new MenuItem(menu, SWT.NONE);
		mntmEdit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
				List list = selection.toList();
				Book book = (Book)list.get(0);
				EditBookDialog dialog = new EditBookDialog(null, book);
				dialog.create();
				if (dialog.open() == Window.OK) {
					book.setTitle(dialog.getTitleName());
					book.getAuthor().setFirstName(dialog.getFirstName());
					book.getAuthor().setLastName(dialog.getLastName());
					tableViewer.setInput(library);
				}
			}
		});
		mntmEdit.setText("Edit");

		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
				List list = selection.toList();
				Boolean ifDelete = MessageDialog.openConfirm(parent.getShell(), "WARNING!", "Do You really want to delete this book?");
				if (list.size() > 0 && ifDelete) {
					Book book = (Book) list.get(0);
					library.remove(book);
					tableViewer.setInput(library);
				}
			};
		});
		mntmNewItem.setSelection(true);
		mntmNewItem.setText("Remove");
		tableViewer.setLabelProvider(new TableLabelProvider());
		tableViewer.setContentProvider(new ContentProvider());
		initLibrary();
		getSite().setSelectionProvider(tableViewer);
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