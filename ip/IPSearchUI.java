/*
 * Creation date 2004-7-6
 */
package ip;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Main Frame
 * 
 * @author terry
 */
public class IPSearchUI extends JFrame {

	public static String ipfile;
	private static Locale locale;

	private ButtonGroup buttonGroup;
	private JTextArea textArea;
	private JTextField textField;
	private JButton button;
	private JLabel label;

	private static IPSeeker seeker;
	private static String CRLF = System.getProperty("line.separator");

	/**
	 * Constructor
	 */
	public IPSearchUI () {
		super();
		JFrame.setDefaultLookAndFeelDecorated(false);
		initialize();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SwingUtilities.updateComponentTreeUI(IPSearchUI.this);
			}
		});
	}

	private void clear () {
		textField.setText("");
		textArea.setText("");
	}

	private void locate () {
		textArea.setText("");
		String ip = textField.getText().trim();
		StringBuffer sb = new StringBuffer();
		sb.append(I18n.getResourceString("text.ip"));
		sb.append(ip);
		sb.append(CRLF);
		sb.append(I18n.getResourceString("text.location"));
		sb.append(seeker.getCountry(ip));
		sb.append("  ");
		sb.append(seeker.getArea(ip));
		sb.append(CRLF);
		textArea.setText(sb.toString());
	}

	private void locateLocalhost (String ip) {
		textArea.setText("");
		StringBuffer sb = new StringBuffer();
		sb.append(I18n.getResourceString("text.ip"));
		sb.append(ip);
		sb.append(CRLF);
		sb.append(I18n.getResourceString("text.location"));
		sb.append(seeker.getCountry(ip));
		sb.append("  ");
		sb.append(seeker.getArea(ip));
		sb.append(CRLF);
		textArea.setText(sb.toString());
		sb.append(I18n.getResourceString("text.host"));
		try {
			sb.append(InetAddress.getLocalHost().getCanonicalHostName());
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
		sb.append(CRLF);
		textArea.setText(sb.toString());
	}

	private void search () {
		textArea.setText("");
		String location = textField.getText().trim();
		StringBuffer sb = new StringBuffer();
		int count = 0;
		List list = seeker.getIPEntries(location);
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			IPEntry ipe = (IPEntry) iter.next();
			sb.append(ipe.beginIp);
			sb.append(" - ");
			sb.append(ipe.endIp);
			sb.append(" ");
			sb.append(ipe.country);
			sb.append(" ");
			sb.append(ipe.area);
			sb.append(CRLF);
			++count;
		}
		sb.append("匹配记录：" + count);
		sb.append(CRLF);
		textArea.setText(sb.toString());
		sb.delete(0, sb.length());
	}

	private void query () {
		textArea.setText("");
		textArea.setText("Under development...");
	}

	/**
	 * 初始化目录
	 * 
	 * @param args
	 */
	public static void init (String[] args) {
		//正确的参数格式
		if (args.length == 1) {
			loadPath(args[0]);
			seeker = new IPSeeker(ipfile);
			I18n lang = new I18n("ip.locale.IPSearch", locale, ClassLoader.getSystemClassLoader());
		}
	}

	/**
	 * 从配置文件读取ip数据库的绝对路径
	 * 
	 * @param url
	 */
	public static void loadPath (String url) {
		try {
			FileInputStream fin = new FileInputStream(url);
			Properties p = new Properties();
			p.load(fin);
			ipfile = p.getProperty("ipdata");
			String temp = p.getProperty("locale");
			if (temp.equalsIgnoreCase("Locale.CHINESE"))
				locale = Locale.CHINESE;
			else if (temp.equalsIgnoreCase("Locale.ENGLISH"))
				locale = Locale.ENGLISH;
			else
				locale = Locale.getDefault();
			fin.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化UI
	 */
	private void initialize () {
		buttonGroup = new ButtonGroup();
		final JToolBar toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);
		final JButton button = new JButton();
		button.setIcon(new ImageIcon(IPSearchUI.class.getResource("icons/console_view.gif")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				InetAddress addr;
				try {
					addr = InetAddress.getLocalHost();
					if (!textArea.getText().equals("")) {
						clear();
					}
					locateLocalhost(addr.getHostAddress());
				}
				catch (UnknownHostException e1) {
					e1.printStackTrace();
				}
			}
		});
		toolBar.add(button);
		button.setText(I18n.getResourceString("button.localhost"));
		final JButton button_1 = new JButton();
		button_1.setIcon(new ImageIcon(IPSearchUI.class.getResource("icons/output_folder_attrib.gif")));
		toolBar.add(button_1);
		button_1.setText(I18n.getResourceString("button.export"));
		final JButton button_2 = new JButton();
		button_2.setIcon(new ImageIcon(IPSearchUI.class.getResource("icons/updates_obj.gif")));
		toolBar.add(button_2);
		button_2.setText(I18n.getResourceString("button.update"));
		final JButton button_3 = new JButton();
		button_3.setIcon(new ImageIcon(IPSearchUI.class.getResource("icons/clear_co.gif")));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				clear();
			}
		});
		toolBar.add(button_3);
		button_3.setText(I18n.getResourceString("button.clear"));

		final JButton button_6 = new JButton();
		button_6.setIcon(new ImageIcon(IPSearchUI.class.getResource("icons/occ_read.gif")));
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				if (!textArea.getText().equals("")) {
					clear();
				}
				textArea.append(I18n.getResourceString("text.ip.records") + seeker.getRecords() + CRLF);
				textArea.append(I18n.getResourceString("text.ip.dataedition") + seeker.getDataEdition() + CRLF);
				textArea.append(I18n.getResourceString("text.ip.updatedate") + seeker.getUpdateDate() + CRLF);
			}
		});
		toolBar.add(button_6);
		button_6.setText(I18n.getResourceString("button.datainfo"));
		final JButton button_5 = new JButton();
		button_5.setIcon(new ImageIcon(IPSearchUI.class.getResource("icons/rem_co.gif")));
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				System.exit(0);
			}
		});
		toolBar.add(button_5);
		button_5.setText(I18n.getResourceString("button.exit"));
		final JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		getContentPane().add(panel, BorderLayout.CENTER);
		final JLabel label_1 = new JLabel();
		final GridBagConstraints gridBagConstraints_8 = new GridBagConstraints();
		gridBagConstraints_8.fill = GridBagConstraints.BOTH;
		panel.add(label_1, gridBagConstraints_8);
		label_1.setText(I18n.getResourceString("label.searchtype"));
		final JRadioButton radioButton_1 = new JRadioButton();
		radioButton_1.setSelected(true);
		buttonGroup.add(radioButton_1);
		final GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
		gridBagConstraints_1.fill = GridBagConstraints.BOTH;
		gridBagConstraints_1.gridx = 1;
		panel.add(radioButton_1, gridBagConstraints_1);
		radioButton_1.setText(I18n.getResourceString("label.ip2addr"));
		final JRadioButton radioButton_2 = new JRadioButton();
		buttonGroup.add(radioButton_2);
		final GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
		gridBagConstraints_2.fill = GridBagConstraints.BOTH;
		gridBagConstraints_2.gridx = 2;
		panel.add(radioButton_2, gridBagConstraints_2);
		radioButton_2.setText(I18n.getResourceString("label.addr2ip"));
		final JRadioButton radioButton_3 = new JRadioButton();
		buttonGroup.add(radioButton_3);
		final GridBagConstraints gridBagConstraints_3 = new GridBagConstraints();
		gridBagConstraints_3.anchor = GridBagConstraints.WEST;
		gridBagConstraints_3.gridx = 3;
		panel.add(radioButton_3, gridBagConstraints_3);
		radioButton_3.setText(I18n.getResourceString("label.ipsegment"));
		final JLabel label_2 = new JLabel();
		final GridBagConstraints gridBagConstraints_4 = new GridBagConstraints();
		gridBagConstraints_4.fill = GridBagConstraints.BOTH;
		gridBagConstraints_4.gridy = 1;
		panel.add(label_2, gridBagConstraints_4);
		label_2.setText(I18n.getResourceString("label.keyword"));
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				if (radioButton_1.isSelected()) {
					if (!textField.getText().equals("")) {
						locate();
					}
					else if (textField.getText().equals("")) {
						textArea.append(I18n.getResourceString("text.plz.input") + CRLF);
					}
				}
				else if (radioButton_2.isSelected()) {
					if (!textField.getText().equals("")) {
						search();
					}
					else if (textField.getText().equals("")) {
						textArea.append(I18n.getResourceString("text.plz.input") + CRLF);
					}
				}
				else if (radioButton_3.isSelected()) {
					if (!textField.getText().equals("")) {
						query();
					}
					else if (textField.getText().equals("")) {
						textArea.append(I18n.getResourceString("text.plz.input") + CRLF);
					}
				}
			}
		});
		final GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();
		gridBagConstraints_5.fill = GridBagConstraints.BOTH;
		gridBagConstraints_5.gridwidth = 2;
		gridBagConstraints_5.gridy = 1;
		gridBagConstraints_5.gridx = 1;
		panel.add(textField, gridBagConstraints_5);
		final JButton button_4 = new JButton();
		button_4.setIcon(new ImageIcon(IPSearchUI.class.getResource("icons/insp_sbook.gif")));
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				if (radioButton_1.isSelected()) {
					if (!textField.getText().equals("")) {
						locate();
					}
					else if (textField.getText().equals("")) {
						textArea.append(I18n.getResourceString("text.plz.input") + CRLF);
					}
				}
				else if (radioButton_2.isSelected()) {
					if (!textField.getText().equals("")) {
						search();
					}
					else if (textField.getText().equals("")) {
						textArea.append(I18n.getResourceString("text.plz.input") + CRLF);
					}
				}
				else if (radioButton_3.isSelected()) {
					if (!textField.getText().equals("")) {
						query();
					}
					else if (textField.getText().equals("")) {
						textArea.append(I18n.getResourceString("text.plz.input") + CRLF);
					}
				}
			}
		});
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.anchor = GridBagConstraints.WEST;
		gridBagConstraints_6.gridy = 1;
		gridBagConstraints_6.gridx = 3;
		panel.add(button_4, gridBagConstraints_6);
		button_4.setText(I18n.getResourceString("button.search"));
		final JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BorderLayout());
		final GridBagConstraints gridBagConstraints_7 = new GridBagConstraints();
		gridBagConstraints_7.weightx = 1.0;
		gridBagConstraints_7.weighty = 1.0;
		gridBagConstraints_7.fill = GridBagConstraints.BOTH;
		gridBagConstraints_7.ipady = 68;
		gridBagConstraints_7.gridwidth = 4;
		gridBagConstraints_7.ipadx = 229;
		gridBagConstraints_7.gridy = 2;
		panel.add(panel_1, gridBagConstraints_7);
		final JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		textArea = new JTextArea();
		textArea.append(I18n.getResourceString("text.ip.records") + seeker.getRecords() + CRLF);
		textArea.append(I18n.getResourceString("text.ip.dataedition") + seeker.getDataEdition() + CRLF);
		textArea.append(I18n.getResourceString("text.ip.updatedate") + seeker.getUpdateDate() + CRLF);
		scrollPane.setViewportView(textArea);
		final JRadioButton radioButton = new JRadioButton();
		final GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridx = 1;
		radioButton.setText(I18n.getResourceString("label.ip2addr"));
		setTitle(I18n.getResourceString("title"));
		final JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		final JMenu menu = new JMenu();
		menu.setFont(new Font("宋体", Font.PLAIN, 12));
		menuBar.add(menu);
		menu.setText(I18n.getResourceString("menu.file"));
		final JMenuItem menuItem = new JMenuItem();
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				System.exit(0);
			}
		});
		menuItem.setFont(new Font("宋体", Font.PLAIN, 12));
		menu.add(menuItem);
		menuItem.setText(I18n.getResourceString("menu.file.exit"));

		JMenu lnf = new JMenu("Look & Feel", true);
		lnf.setFont(new Font("宋体", Font.PLAIN, 12));
		ButtonGroup buttonGroup1 = new ButtonGroup();
		final UIManager.LookAndFeelInfo[] info = UIManager.getInstalledLookAndFeels();

		for (int i = 0; i < info.length; i++) {
			JRadioButtonMenuItem item = new JRadioButtonMenuItem(info[i].getName(), i == 0);
			final String className = info[i].getClassName();
			item.addActionListener(new ActionListener() {
				public void actionPerformed (ActionEvent ae) {
					try {
						UIManager.setLookAndFeel(className);
					}
					catch (Exception e) {
						System.out.println(e);
					}
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							SwingUtilities.updateComponentTreeUI(IPSearchUI.this);
						}
					});
				}
			});
			buttonGroup1.add(item);
			lnf.add(item);
		}
		//add Alloy look and feel
		JRadioButtonMenuItem temp = new JRadioButtonMenuItem("Alloy");
		temp.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent evt) {
				try {
					LookAndFeel alloyLnF = new com.incors.plaf.alloy.AlloyLookAndFeel();
					UIManager.setLookAndFeel(alloyLnF);
				}
				catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						SwingUtilities.updateComponentTreeUI(IPSearchUI.this);
					}
				});
			}
		});
		buttonGroup1.add(temp);
		lnf.add(temp);

		JRadioButtonMenuItem metouia = new JRadioButtonMenuItem("Metouia");
		metouia.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent evt) {
				try {
					LookAndFeel metouiaLnF = new net.sourceforge.mlf.metouia.MetouiaLookAndFeel();
					UIManager.setLookAndFeel(metouiaLnF);
				}
				catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						SwingUtilities.updateComponentTreeUI(IPSearchUI.this);
					}
				});
			}
		});
		buttonGroup1.add(metouia);
		lnf.add(metouia);

		menuBar.add(lnf);


		final JMenu menu_1 = new JMenu();
		menu_1.setFont(new Font("宋体", Font.PLAIN, 12));
		menuBar.add(menu_1);
		menu_1.setText(I18n.getResourceString("menu.about"));
		final JMenuItem menuItem_1 = new JMenuItem();
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				AboutBox dlg = new AboutBox();
				Dimension dlgSize = dlg.getPreferredSize();
				Dimension frmSize = getSize();
				Point loc = getLocation();
				dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
				dlg.setModal(true);
				dlg.pack();
				dlg.setVisible(true);
			}
		});
		menuItem_1.setFont(new Font("宋体", Font.PLAIN, 12));
		menu_1.add(menuItem_1);
		menuItem_1.setText(I18n.getResourceString("menu.about.about"));
	}
}