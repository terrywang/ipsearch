/*
 * Creation date 2004-10-9
 * terry
 */
package ip;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * @author terry
 */
public class UI extends JFrame {
    BorderLayout borderLayout1 = new BorderLayout();
    JToolBar jToolBar1 = new JToolBar();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JPanel jPanel1 = new JPanel();
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    JLabel jLabel1 = new JLabel();
    ButtonGroup buttonGroup1 = new ButtonGroup();
    JRadioButton jRadioButton1 = new JRadioButton();
    JRadioButton jRadioButton2 = new JRadioButton();
    JRadioButton jRadioButton3 = new JRadioButton();
    JButton jButton3 = new JButton();
    JLabel jLabel2 = new JLabel();
    JTextField jTextField1 = new JTextField();
    JButton jButton4 = new JButton();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTextArea jTextArea1 = new JTextArea();
    JMenuBar jMenuBar1 = new JMenuBar();
    JMenu jMenu1 = new JMenu();
    JMenu jMenu2 = new JMenu();
    JMenuItem jMenuItem1 = new JMenuItem();
    JMenuItem jMenuItem2 = new JMenuItem();
    public UI() {
        try {
            initialize();
            SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                    SwingUtilities.updateComponentTreeUI(UI.this);
            }
    });

        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void initialize() throws Exception {

        getContentPane().setLayout(borderLayout1);
        jButton2.setText("jButton2");
        jPanel1.setLayout(gridBagLayout1);
        jLabel1.setText("jLabel1");
        jRadioButton1.setText("jRadioButton1");
        jRadioButton2.setText("jRadioButton2");
        jRadioButton3.setText("jRadioButton3");
        jButton3.setText("jButton3");
        jLabel2.setText("jLabel2");
        jTextField1.setText("jTextField1");
        jButton4.setText("jButton4");
        jTextArea1.setText("jTextArea1");
        jMenuItem1.setText("Exit");
        jMenu1.setText("File");
        jMenu2.setText("Help");
        jMenuItem2.setText("About");
        jToolBar1.add(jButton3);
        jToolBar1.add(jButton2);
        jToolBar1.add(jButton1);
        this.getContentPane().add(jToolBar1, java.awt.BorderLayout.NORTH);
        jButton1.setText("jButton1");
        buttonGroup1.add(jRadioButton2);
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton3);
        jPanel1.add(jRadioButton2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        jPanel1.add(jRadioButton1, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        jPanel1.add(jRadioButton3, new GridBagConstraints(3, 0, 1, 1, 100.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        jPanel1.add(jLabel2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        jPanel1.add(jLabel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        jPanel1.add(jTextField1, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        jPanel1.add(jButton4, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        jPanel1.add(jScrollPane1, new GridBagConstraints(0, 2, 4, 1, 100.0, 100.0
            , GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        this.getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jScrollPane1.getViewport().add(jTextArea1);
    }

    public static void main(String[] args) {
        JFrame frame = new UI();
        try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
                catch (Exception e) {
                        e.printStackTrace();
                }

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                //Center the window
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Dimension frameSize = frame.getSize();
                if (frameSize.height > screenSize.height) {
                        frameSize.height = screenSize.height;
                }
                if (frameSize.width > screenSize.width) {
                        frameSize.width = screenSize.width;
                }
                frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

                frame.setSize(480, 450);
                frame.setVisible(true);

    }


}
