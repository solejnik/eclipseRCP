package dialog;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SearchBookDialog extends TitleAreaDialog {

  private Text bookCriteriaText;

  private String bookCriteria;

  public SearchBookDialog(Shell parentShell) {
    super(parentShell);
  }

  @Override
  public void create() {
    super.create();
    setTitle("Search books");
    setMessage("There You can search books", IMessageProvider.INFORMATION);
  }

  @Override
  protected Control createDialogArea(Composite parent) {
    Composite area = (Composite) super.createDialogArea(parent);
    Composite container = new Composite(area, SWT.NONE);
    container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    GridLayout layout = new GridLayout(2, false);
    container.setLayout(layout);

    createBookCriteria(container);
    return area;
  }

  private void createBookCriteria(Composite container) {
    Label lbtBookTitle = new Label(container, SWT.NONE);
    lbtBookTitle.setText("Search criteria");

    GridData dataBookTitle = new GridData();
    dataBookTitle.grabExcessHorizontalSpace = true;
    dataBookTitle.horizontalAlignment = GridData.FILL;

    bookCriteriaText = new Text(container, SWT.BORDER);
    bookCriteriaText.setLayoutData(dataBookTitle);
  }
  

  




  @Override
  protected boolean isResizable() {
    return true;
  }

  private void saveInput() {
    bookCriteria = bookCriteriaText.getText();

  }

  @Override
  protected void okPressed() {
    saveInput();
    super.okPressed();
  }

  public String getCriteria() {
    return bookCriteria;
  }

  
  
} 