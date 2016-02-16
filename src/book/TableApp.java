package book;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.eclipse.jface.action.CoolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Label;

public class TableApp extends ApplicationWindow {
	private class TableLabelProvider extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			Person p = (Person) element;
			String result = "";
			switch (columnIndex) {
			case 0:
				result = p.getFirst();
				break;
			case 1:
				result = p.getLast();
				break;
			case 2:
				result = p.getTitle();
				break;
			case 3:
				result = p.getEmail();
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
			Workgroup w = (Workgroup) inputElement;
			return w.getMemberSet().toArray();
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	private Table table;
	private TableViewer tableViewer;
	private Workgroup workgroup;

	/**
	 * Create the application window,
	 */
	public TableApp() {
		super(null);
		setShellStyle(SWT.CLOSE | SWT.MIN | SWT.MAX | SWT.RESIZE);
		createActions();
		addCoolBar(SWT.FLAT);
		addMenuBar();
		addStatusLine();
	}

	/**
	 * Create contents of the application window.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));

		// Create the composite
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		// Add TableColumnLayout
		TableColumnLayout layout = new TableColumnLayout();
		composite.setLayout(layout);

		tableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
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

		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnTitle = tableViewerColumn_2.getColumn();
		// Specify width using weights
		layout.setColumnData(tblclmnTitle, new ColumnWeightData(4, ColumnWeightData.MINIMUM_WIDTH, true));
		tblclmnTitle.setText("Title");

		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnEmail = tableViewerColumn_3.getColumn();
		// Specify width using weights
		layout.setColumnData(tblclmnEmail, new ColumnWeightData(6, ColumnWeightData.MINIMUM_WIDTH, true));
		tblclmnEmail.setText("Email");

		Button btnNewButton = new Button(container, SWT.NONE);
		Listener listener = new Listener() {

			@Override
			public void handleEvent(Event event) {
				Person p = new Person();
				p.setFirst("John");
				p.setLast("Smith");
				p.setTitle("Manager");
				p.setEmail("jsmith@somecompany.com");
				add(p);
				setWorkgroup(workgroup);
				System.out.println(123);
			}
		};
		btnNewButton.addListener(SWT.Selection, listener);
		btnNewButton.setText("Add");
		
		Button btnDelete = new Button(container, SWT.NONE);
		btnDelete.setText("Delete");
		Listener listener2 = new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				System.out.println(workgroup.memberSet.size());
				workgroup.memberSet.clear();
				setWorkgroup(workgroup);
			}
		};
		btnDelete.addListener(SWT.Selection, listener2);
		tableViewer.setLabelProvider(new TableLabelProvider());
		tableViewer.setContentProvider(new ContentProvider());
		initWorkgroup();
		return container;
	}

	private void initWorkgroup() {
		Workgroup w = new Workgroup();
		Person p = new Person();
		p.setFirst("John");
		p.setLast("Smith");
		p.setTitle("Manager");
		p.setEmail("jsmith@somecompany.com");
		w.add(p);
		this.setWorkgroup(w);
	}

	private void add(Person person) {
		workgroup.add(person);
		this.tableViewer.add(person);
		this.tableViewer.refresh();
	}

	private void remove(Person person) {
		workgroup.remove(person);
		this.tableViewer.remove(person);
		this.tableViewer.refresh();
	}

	public void setWorkgroup(Workgroup workgroup) {
		this.workgroup = workgroup;
		this.tableViewer.setInput(workgroup);
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Create the menu manager.
	 * 
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		return menuManager;
	}

	/**
	 * Create the coolbar manager.
	 * 
	 * @return the coolbar manager
	 */
	@Override
	protected CoolBarManager createCoolBarManager(int style) {
		CoolBarManager coolBarManager = new CoolBarManager(style);
		return coolBarManager;
	}

	/**
	 * Create the status line manager.
	 * 
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			TableApp window = new TableApp();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * 
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("TableApp");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		return new Point(dim.width, dim.height);
	}
}
