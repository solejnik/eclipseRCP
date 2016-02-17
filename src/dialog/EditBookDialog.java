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

import book.Book;

public class EditBookDialog extends TitleAreaDialog {

  private Text bookTitleText;
  private Text authorFirstNameText;
  private Text authorLastNameText;

  private String bookTitle;
  private String authorFirstName;
  private String authorLastName;

  private Book book;
  public EditBookDialog(Shell parentShell,Book book) {
    super(parentShell);
    this.book = book;
  }

  @Override
  public void create() {
    super.create();
    setTitle("Edit a book");
    setMessage("There You can edit current book", IMessageProvider.INFORMATION);
  }

  @Override
  protected Control createDialogArea(Composite parent) {
    Composite area = (Composite) super.createDialogArea(parent);
    Composite container = new Composite(area, SWT.NONE);
    container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    GridLayout layout = new GridLayout(2, false);
    container.setLayout(layout);

    createBookTitle(container);
    createAuthorFirstName(container);
    createAuthorLastName(container);
    return area;
  }

  private void createBookTitle(Composite container) {
    Label lbtBookTitle = new Label(container, SWT.NONE);
    lbtBookTitle.setText("Book title");

    GridData dataBookTitle = new GridData();
    dataBookTitle.grabExcessHorizontalSpace = true;
    dataBookTitle.horizontalAlignment = GridData.FILL;

    bookTitleText = new Text(container, SWT.BORDER);
    bookTitleText.setLayoutData(dataBookTitle);
    bookTitleText.setText(book.getTitle());
  }
  
  private void createAuthorFirstName(Composite container) {
    Label lbtFirstName = new Label(container, SWT.NONE);
    lbtFirstName.setText("Author first name");
    
    GridData dataFirstName = new GridData();
    dataFirstName.grabExcessHorizontalSpace = true;
    dataFirstName.horizontalAlignment = GridData.FILL;
    
    authorFirstNameText = new Text(container, SWT.BORDER);
    authorFirstNameText.setLayoutData(dataFirstName);
    authorFirstNameText.setText(book.getAuthor().getFirstName());
  }
  
  private void createAuthorLastName(Composite container) {
	  Label lbtLastName = new Label(container, SWT.NONE);
	  lbtLastName.setText("Author last name");
	  
	  GridData dataLastName = new GridData();
	  dataLastName.grabExcessHorizontalSpace = true;
	  dataLastName.horizontalAlignment = GridData.FILL;
	  
	  authorLastNameText = new Text(container, SWT.BORDER);
	  authorLastNameText.setLayoutData(dataLastName);
	  authorLastNameText.setText(book.getAuthor().getLastName());
  }



  @Override
  protected boolean isResizable() {
    return true;
  }

  private void saveInput() {
    bookTitle = bookTitleText.getText();
    authorFirstName = authorFirstNameText.getText();
    authorLastName = authorLastNameText.getText();

  }

  @Override
  protected void okPressed() {
    saveInput();
    super.okPressed();
  }

  public String getTitleName() {
    return bookTitle;
  }

  public String getFirstName() {
    return authorFirstName;
  }
  
  public String getLastName() {
	  return authorLastName;
  }
  
  
} 