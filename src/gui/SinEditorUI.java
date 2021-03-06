package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import flashsystem.HexDump;
import flashsystem.SinFile;

import javax.swing.JLabel;
import javax.swing.JTextField;

import org.logger.MyLogger;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class SinEditorUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textSin;
	private JTextField textPartition;
	private JTextField textSpare;
	private SinFile sin;
	private JTextField textCtype;
	private JButton btnUnpack;
	private JButton btnDumpData;
	private JButton btnDumpHeader;
	private JButton btnCreateSin;


	/**
	 * Create the dialog.
	 */
	public SinEditorUI() {
		setModal(true);
		setTitle("Sin Editor");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 346);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		{
			JLabel lblSinFil = new JLabel("Sin File :");
			contentPanel.add(lblSinFil, "2, 2, 5, 1");
		}
		{
			textSin = new JTextField();
			textSin.setEditable(false);
			contentPanel.add(textSin, "2, 4, 5, 1, fill, default");
			textSin.setColumns(10);
		}
		{
			JButton button = new JButton("...");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String file=chooseSin();
					if (!file.equals("ERROR")) {
						try {
						sin = new SinFile(file);
						textSin.setText(file);
						String p = HexDump.toHex(sin.getPartitionInfo()).replaceAll(", ", "");
						textPartition.setText(p.substring(1, p.length()-1));
						textSpare.setText(HexDump.toHex(sin.getSpare()));
						textCtype.setText(sin.getIdent());
						btnDumpData.setEnabled(true);
						btnDumpHeader.setEnabled(true);
						btnCreateSin.setEnabled(true);
						}
						catch (Exception e) {}
					}
				}
			});
			contentPanel.add(button, "8, 4");
		}
		{
			JLabel lblPartitionInfo = new JLabel("Partition Info :");
			contentPanel.add(lblPartitionInfo, "2, 6, 5, 1");
		}
		{
			textPartition = new JTextField();
			textPartition.setEditable(false);
			contentPanel.add(textPartition, "2, 8, 5, 1, fill, default");
			textPartition.setColumns(10);
		}
		{
			JLabel lblSpareInfo = new JLabel("Spare Info :");
			contentPanel.add(lblSpareInfo, "2, 10, 5, 1");
		}
		{
			textSpare = new JTextField();
			textSpare.setEditable(false);
			contentPanel.add(textSpare, "2, 12, 5, 1, fill, default");
			textSpare.setColumns(10);
		}
		{
			btnDumpData = new JButton("Dump data");
			btnDumpData.setEnabled(false);
			btnDumpData.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						sin.dumpImage();
					}
					catch (Exception e) {
						MyLogger.getLogger().error(e.getMessage());
					}
				}
			});
			{
				JLabel lblContentType = new JLabel("Content type :");
				contentPanel.add(lblContentType, "2, 14");
			}
			{
				textCtype = new JTextField();
				textCtype.setEditable(false);
				contentPanel.add(textCtype, "2, 16, 5, 1, fill, default");
				textCtype.setColumns(10);
			}
			contentPanel.add(btnDumpData, "2, 18, center, center");
		}
		{
			btnDumpHeader = new JButton("Dump header");
			btnDumpHeader.setEnabled(false);
			btnDumpHeader.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						sin.dumpHeader();
					}
					catch (IOException ioe) {}
				}
			});
			contentPanel.add(btnDumpHeader, "4, 18");
		}
		{
			btnUnpack = new JButton("Unpack data");
			contentPanel.add(btnUnpack, "6, 18, center, default");
			btnUnpack.setEnabled(false);
		}
		{
			btnCreateSin = new JButton("Create Sin As");
			btnCreateSin.setEnabled(false);
			btnCreateSin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					SinCreatorUI cui = new SinCreatorUI(sin.getName(),textPartition.getText(),textSpare.getText());
					cui.setVisible(true);
				}
			});
			contentPanel.add(btnCreateSin, "4, 20");
		}
		{
			//JButton btnUseAsTemplate = new JButton("Use as template");
			//contentPanel.add(btnUseAsTemplate, "4, 20");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Close");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	public String chooseSin() {
		JFileChooser chooser = new JFileChooser(new java.io.File(".")); 

		FileFilter ff = new FileFilter(){
			public boolean accept(File f){
				if(f.isDirectory()) return true;
				else if(f.getName().endsWith(".sin")) return true;
				else return false;
			}
			public String getDescription(){
				return "*.sin";
			}
		};
		 
		chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter());
		chooser.setFileFilter(ff);
		
	    chooser.setDialogTitle("Choose sin file)");
	    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    //chooser.setFileFilter(newkernelimgFileFilter);
	    //
	    // disable the "All files" option.
	    //
	    chooser.setAcceptAllFileFilterUsed(false);
	    //    
	    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
	    	return chooser.getSelectedFile().getAbsolutePath();
	    }
	    return "ERROR";
	}

}
