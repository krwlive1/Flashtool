package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JTextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AskBox extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	String result = "no";
	
	public AskBox() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//if (f!=null) f.delete();
				result = "no";
				dispose();
			}
		});
	}
	
	static String getReplyOf(String message) {
		AskBox box = new AskBox();
		box.setTitle("MessageBox");
		box.setBounds(100, 100, 450, 190);
		box.getContentPane().setLayout(new BorderLayout());
		box.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		box.getContentPane().add(box.contentPanel, BorderLayout.CENTER);
		box.contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		{
			JTextArea msg = new JTextArea(message);
			msg.setEditable(false);
			box.contentPanel.add(msg, "2, 4, 3, 1, fill, fill");
		}
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			box.getContentPane().add(buttonPane, BorderLayout.SOUTH);

				
				
				JButton okButton = new JButton("Yes");
				okButton.addActionListener(new BoxActionListener(box,"yes"));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				box.getRootPane().setDefaultButton(okButton);

				JButton cancelButton = new JButton("No");
				cancelButton.addActionListener(new BoxActionListener(box,"no"));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				box.setResizable(false);
				box.setModal(true);
				box.setVisible(true);
		return box.getResult();
	}

	static void showOKbox(String message) {
		AskBox box = new AskBox();
		box.setTitle("MessageBox");
		box.setBounds(100, 100, 450, 190);
		box.getContentPane().setLayout(new BorderLayout());
		box.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		box.getContentPane().add(box.contentPanel, BorderLayout.CENTER);
		box.contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		{
			JTextArea msg = new JTextArea(message);
			msg.setEditable(false);
			box.contentPanel.add(msg, "2, 4, 3, 1, fill, fill");
		}
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			box.getContentPane().add(buttonPane, BorderLayout.SOUTH);

				
				
				JButton okButton = new JButton("Yes");
				okButton.addActionListener(new BoxActionListener(box,"yes"));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				box.getRootPane().setDefaultButton(okButton);
				box.setResizable(false);
				box.setModal(true);
				box.setVisible(true);
	}

	public String getResult() {
		return result;
	}

}
