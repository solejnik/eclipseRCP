package views;

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
import dialog.SearchBookDialog;

public class BooksView extends ViewPart {
	public BooksView() {
	}

	public static final String ID = "BookProject.view";
	private Table table;
	private TableViewer tableViewer;
	private Library library;
	private Library permanentLobrary;

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
		Book book1 = new Book("UltranexlinieClass", new Author("Wawrzyniec", "Was"));
		Book book2 = new Book("Ambrozy &amp; Naum Space", new Author("Erwin", "Serwin"));
		Book book3 = new Book("Ultra godne Kurtki", new Author("Tegomir", "Ciura"));
		Book book4 = new Book("Mega bycze Artykuly budowlane", new Author("Sulislawa", "Lukasika"));
		Book book5 = new Book("Tyno Ekspert", new Author("Nikita", "Pospiech"));
		Book book6 = new Book("Fullnierzy3D", new Author("Tytus", "Konopka"));
		Book book7 = new Book("Modne Loki", new Author("Marcelin", "Herman"));
		Book book8 = new Book("Alma &amp; Alban Online", new Author("Ulryka", "Sobocinski"));
		Book book9 = new Book("Mega mroczne Patyki", new Author("Mojzesz", "Koc"));
		Book book10 = new Book("Super krotkie Tloki", new Author("Budzislawa", "Salamon"));
		Book book11 = new Book("Mafeso Class", new Author("Wiecemir", "Boguslawski"));
		Book book12 = new Book("Hyper", new Author("Chryzostom", "Neumann"));
		Book book13 = new Book("Full slodkie Mydla", new Author("Zyta", "Januszkiewicz"));
		Book book14 = new Book("Sadka", new Author("Drogomysl", "Klocek"));
		Book book15 = new Book("UltrarzyTour", new Author("Eulogiusz", "Nadolny"));
		Book book16 = new Book("Rygapafe Triple", new Author("Serwacy", "Fortuna"));
		Book book17 = new Book("Hiper kozackie Patyki", new Author("Wolfgang", "Gacek"));
		Book book18 = new Book("Ignacego Hepki", new Author("Anna", "Wanna"));
		Book book19 = new Book("Ultra szerokie Opony", new Author("Jan", "Kowalski"));
		Book book20 = new Book("Pachnaca", new Author("Kazimierz", "Nowak"));
		library.add(book1);
		library.add(book2);
		library.add(book3);
		library.add(book4);
		library.add(book5);
		library.add(book6);
		library.add(book7);
		library.add(book8);
		library.add(book9);
		library.add(book10);
		library.add(book11);
		library.add(book12);
		library.add(book13);
		library.add(book14);
		library.add(book15);
		library.add(book16);
		library.add(book17);
		library.add(book18);
		library.add(book19);
		library.add(book20);
		this.setLibrary(library);
		permanentLobrary = new Library(library.getBooks());
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
					permanentLobrary.add(
							new Book(dialog.getLastName(), new Author(dialog.getFirstName(), dialog.getLastName())));
					library.setBooks(permanentLobrary.getBooks());
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
				Object firstElement = selection.getFirstElement();
				Book book = (Book) firstElement;
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
				Object firstElement = selection.getFirstElement();
				if (firstElement != null && MessageDialog.openConfirm(parent.getShell(), "WARNING!",
						"Do You really want to delete this book?")) {
					Book book = (Book) firstElement;
					permanentLobrary.remove(book);
					library.setBooks(permanentLobrary.getBooks());
					tableViewer.setInput(library);
				}
			};
		});
		mntmNewItem.setSelection(true);
		mntmNewItem.setText("Remove");

		MenuItem mntmSearch = new MenuItem(menu, SWT.NONE);
		mntmSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SearchBookDialog dialog = new SearchBookDialog(null);
				dialog.create();
				if (dialog.open() == Window.OK) {
					library.setBooks(permanentLobrary.searchBook(dialog.getCriteria()));
					tableViewer.setInput(library);
				}
			}
		});
		mntmSearch.setText("Search");
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