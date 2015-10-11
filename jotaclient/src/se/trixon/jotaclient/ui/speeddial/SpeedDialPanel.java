/* 
 * Copyright 2015 Patrik Karlsson.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package se.trixon.jotaclient.ui.speeddial;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.PreferenceChangeEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.PanelUI;
import se.trixon.jota.ServerCommander;
import se.trixon.jota.job.Job;
import se.trixon.jota.job.JobManager;
import se.trixon.jotaclient.ConnectionListener;
import se.trixon.jotaclient.Manager;
import se.trixon.jotaclient.Options;
import se.trixon.util.dictionary.Dict;
import se.trixon.util.swing.SwingHelper;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class SpeedDialPanel extends JPanel implements ConnectionListener, JobManager.JobListener {
//public class SpeedDialPanel extends GradientPanel implements JobManager.JobListener {

    private final ArrayList<SpeedDialButton> mButtons = new ArrayList<>();
    private JMenuItem mResetMenuItem;
    private final JPopupMenu mPopupMenu = new JPopupMenu(Dict.JOB.getString());
    private SpeedDialButton mButton;
    private final HashSet<SpeedDialListener> mSpeedDialListeners = new HashSet<>();
    private ServerCommander mRemote = Manager.getInstance().getServerCommander();
    //private final ConnectionManager mConnectionManager = ConnectionManager.INSTANCE;
    private final Options mOptions = Options.INSTANCE;
    private final Manager mManager=Manager.getInstance();
    ServerCommander mServerCommander=mManager.getServerCommander();

    /**
     * Creates new form SpeedDialPanel
     */
    public SpeedDialPanel() {
        initComponents();
//        try {
//            //TODO Ev flytt till onJobSave
//            mServerOptions = mRemote.loadServerOptions();
//        } catch (RemoteException ex) {
//            Logger.getLogger(OptionsPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
        init();
    }

    public boolean addSpeedDialListener(SpeedDialListener speedDialListener) {
        return mSpeedDialListeners.add(speedDialListener);
    }

    @Override
    public void onConnectionClientConnect() {
//        try {
//            //TODO Ev flytt till onJobSave
//            mServerOptions = mRemote.loadServerOptions();
//        } catch (RemoteException ex) {
//            Logger.getLogger(OptionsPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
        SwingUtilities.invokeLater(() -> {
            SwingHelper.enableComponents(this, true);
            loadConfiguration();
        });
    }

    @Override
    public void onConnectionClientDisconnect() {
        SwingUtilities.invokeLater(() -> {
            SwingHelper.enableComponents(this, false);
            clearConfiguration();
        });
    }

//    public void setServerOptions(ServerOptions serverOptions) {
//        mServerOptions = serverOptions;
//    }

    @Override
    public void onJobSave() {
        loadConfiguration();
    }

    private void clearConfiguration() {
        Job job = new Job(-1, "", "", "");
        jobsComboBox.removeAllItems();
        jobsComboBox.addItem(job);
        jobsComboBox.setEnabled(false);
        mPopupMenu.removeAll();
        mButtons.stream().forEach((button) -> {
            button.setJobId(-1);
        });
    }

    private void loadConfiguration() {
        clearConfiguration();
        final JobManager mJobManager = JobManager.INSTANCE;

        if (mManager.isConnected() && mJobManager.hasJobs()) {
            DefaultComboBoxModel model = mJobManager.populateModel((DefaultComboBoxModel) jobsComboBox.getModel());
            jobsComboBox.setModel(model);
            jobsComboBox.setEnabled(true);

            mPopupMenu.removeAll();

            mResetMenuItem = new JMenuItem(Dict.RESET.getString());
            mResetMenuItem.addActionListener((ActionEvent e) -> {
                mButton.setJobId(-1);
                mServerCommander.setSpeedDial(mButton.getIndex(), -1);
                //saveSpeedDial();
            });
            mPopupMenu.add(mResetMenuItem);
            mPopupMenu.add(new JSeparator());

            for (final Job job : mJobManager.getJobs()) {
                final long jobId = job.getId();
                JMenuItem menuItem = new JMenuItem(job.toString());
                menuItem.addActionListener((ActionEvent e) -> {
                    mButton.setJobId(jobId);
                    mServerCommander.setSpeedDial(mButton.getIndex(), jobId);
                    //saveSpeedDial();
                });
                mPopupMenu.add(menuItem);
            }

            for (int i = 0; i < mButtons.size(); i++) {
                SpeedDialButton button = mButtons.get(i);
                button.setJobId(mServerCommander.getSpeedDial(i));
            }
        }
    }

//    private void saveSpeedDial() {
//        try {
//            ConnectionManager.INSTANCE.getServerCommander().saveServerOptions(mServerOptions);
//        } catch (RemoteException ex) {
//            Logger.getLogger(OptionsPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public void setButtonsVisibility(boolean visible) {
        for (SpeedDialButton button : mButtons) {
            button.setVisible(visible);
        }
    }

    @Override
    public void updateUI() {
        super.updateUI();
        if (mPopupMenu != null) {
            try {
                UIManager.setLookAndFeel(SwingHelper.getLookAndFeelClassName(mOptions.getLookAndFeel()));
                SwingUtilities.updateComponentTreeUI(mPopupMenu);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                //Xlog.timedErr(ex.getMessage());
            }
        }
    }

    private void init() {
        mButtons.clear();
        int index = -1;

        for (Component component : getComponents()) {
            if (component instanceof SpeedDialButton) {
                index++;
                SpeedDialButton button = (SpeedDialButton) component;
                button.setIndex(index);

                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent evt) {
                        SwingUtilities.invokeLater(() -> {
                            if (JobManager.INSTANCE.hasJobs()) {
                                mButton = (SpeedDialButton) evt.getSource();
                                mResetMenuItem.setEnabled(mButton.getJob() != null);
                                if (!mButton.isEnabled() && evt.getButton() == MouseEvent.BUTTON1 || evt.getButton() == MouseEvent.BUTTON3) {
                                    mPopupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
                                }
                            }
                        });
                    }
                });

                button.addActionListener((ActionEvent evt) -> {
                    mSpeedDialListeners.stream().forEach((speedDialListener) -> {
                        try {
                            speedDialListener.onSpeedDialButtonClicked(mButton);
                        } catch (Exception e) {
                            // nvm
                        }
                    });
                });
                mButtons.add(button);
            }
        }

//        mConnectionManager.addConnectionListeners(this);
//        JobManager.INSTANCE.addJobListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jobsComboBox = new javax.swing.JComboBox();
        speedDialButton0 = new se.trixon.jotaclient.ui.speeddial.SpeedDialButton();
        speedDialButton1 = new se.trixon.jotaclient.ui.speeddial.SpeedDialButton();
        speedDialButton2 = new se.trixon.jotaclient.ui.speeddial.SpeedDialButton();
        speedDialButton3 = new se.trixon.jotaclient.ui.speeddial.SpeedDialButton();
        speedDialButton4 = new se.trixon.jotaclient.ui.speeddial.SpeedDialButton();
        speedDialButton5 = new se.trixon.jotaclient.ui.speeddial.SpeedDialButton();
        speedDialButton6 = new se.trixon.jotaclient.ui.speeddial.SpeedDialButton();
        speedDialButton7 = new se.trixon.jotaclient.ui.speeddial.SpeedDialButton();
        speedDialButton8 = new se.trixon.jotaclient.ui.speeddial.SpeedDialButton();

        setLayout(new java.awt.GridBagLayout());

        jobsComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jobsComboBox, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        add(speedDialButton0, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        add(speedDialButton1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        add(speedDialButton2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        add(speedDialButton3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        add(speedDialButton4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        add(speedDialButton5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        add(speedDialButton6, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        add(speedDialButton7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        add(speedDialButton8, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jobsComboBox;
    private se.trixon.jotaclient.ui.speeddial.SpeedDialButton speedDialButton0;
    private se.trixon.jotaclient.ui.speeddial.SpeedDialButton speedDialButton1;
    private se.trixon.jotaclient.ui.speeddial.SpeedDialButton speedDialButton2;
    private se.trixon.jotaclient.ui.speeddial.SpeedDialButton speedDialButton3;
    private se.trixon.jotaclient.ui.speeddial.SpeedDialButton speedDialButton4;
    private se.trixon.jotaclient.ui.speeddial.SpeedDialButton speedDialButton5;
    private se.trixon.jotaclient.ui.speeddial.SpeedDialButton speedDialButton6;
    private se.trixon.jotaclient.ui.speeddial.SpeedDialButton speedDialButton7;
    private se.trixon.jotaclient.ui.speeddial.SpeedDialButton speedDialButton8;
    // End of variables declaration//GEN-END:variables

    public interface SpeedDialListener {

        public void onSpeedDialButtonClicked(SpeedDialButton speedDialButton);
    }
}
