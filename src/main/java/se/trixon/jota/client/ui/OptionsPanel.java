/*
 * Copyright 2018 Patrik Karlström.
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
package se.trixon.jota.client.ui;

import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JSpinner;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.SystemHelper;
import se.trixon.jota.client.ClientOptions;
import se.trixon.jota.client.Manager;
import se.trixon.jota.shared.ServerCommander;

/**
 *
 * @author Patrik Karlström
 */
public class OptionsPanel extends javax.swing.JPanel {

    private final Manager mManager = Manager.getInstance();
    private final ServerCommander mServerCommander;
    private final ClientOptions mOptions = ClientOptions.INSTANCE;
    private final ResourceBundle mBundle = SystemHelper.getBundle(MainFrame.class, "Bundle");

    /**
     * Creates new form OptionsPanel
     */
    public OptionsPanel() {
        mServerCommander = mManager.getServerCommander();
        initComponents();

        tabbedPane.setEnabledAt(1, mServerCommander != null);
        portSpinner.setEditor(new JSpinner.NumberEditor(portSpinner, "#"));
        connectDelaySpinner.setEditor(new JSpinner.NumberEditor(connectDelaySpinner, "#"));
        load();
    }

    void save() {
        if (mManager.isConnected()) {
            try {
                mServerCommander.setRsyncPath(rsyncFileChooserPanel.getPath());
                mServerCommander.setLogDir(logDirFileChooserPanel.getPath());
            } catch (RemoteException ex) {
                Logger.getLogger(OptionsPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        mOptions.setCustomColors(customColorsCheckBox.isSelected());
        mOptions.setAutostartServer(autostartServerCheckBox.isSelected());
        mOptions.setAutostartServerPort((int) portSpinner.getValue());
        mOptions.setAutostartServerConnectDelay((int) connectDelaySpinner.getValue());
        mOptions.setWordWrap(wordWrapCheckBox.isSelected());
        mOptions.setDisplayDryRun(displayDryRunCheckBox.isSelected());
        mOptions.setSplitDeletions(splitDeletionsLogCheckBox.isSelected());
        mOptions.setSplitErrors(splitErrorsLogCheckBox.isSelected());
    }

    private void load() {
        if (mManager.isConnected()) {
            try {
                rsyncFileChooserPanel.setPath(mServerCommander.getRsyncPath());
                logDirFileChooserPanel.setPath(mServerCommander.getLogDir());
            } catch (NullPointerException | RemoteException ex) {
                rsyncFileChooserPanel.setEnabled(false);
                Logger.getLogger(OptionsPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        customColorsCheckBox.setSelected(mOptions.isCustomColors());
        autostartServerCheckBox.setSelected(mOptions.isAutostartServer());
        portSpinner.setValue(mOptions.getAutostartServerPort());
        connectDelaySpinner.setValue(mOptions.getAutostartServerConnectDelay());
        wordWrapCheckBox.setSelected(mOptions.isWordWrap());
        displayDryRunCheckBox.setSelected(mOptions.isDisplayDryRun());
        autostartServerCheckBoxActionPerformed(null);
        splitDeletionsLogCheckBox.setSelected(mOptions.isSplitDeletions());
        splitErrorsLogCheckBox.setSelected(mOptions.isSplitErrors());
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();
        clientPanel = new javax.swing.JPanel();
        customColorsCheckBox = new javax.swing.JCheckBox();
        autostartServerCheckBox = new javax.swing.JCheckBox();
        portLabel = new javax.swing.JLabel();
        portSpinner = new javax.swing.JSpinner();
        connectDelayLabel = new javax.swing.JLabel();
        connectDelaySpinner = new javax.swing.JSpinner();
        wordWrapCheckBox = new javax.swing.JCheckBox();
        displayDryRunCheckBox = new javax.swing.JCheckBox();
        outputLabel = new javax.swing.JLabel();
        splitErrorsLogCheckBox = new javax.swing.JCheckBox();
        splitDeletionsLogCheckBox = new javax.swing.JCheckBox();
        serverPanel = new javax.swing.JPanel();
        rsyncFileChooserPanel = new se.trixon.almond.util.swing.dialogs.FileChooserPanel();
        logDirFileChooserPanel = new se.trixon.almond.util.swing.dialogs.FileChooserPanel();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("se/trixon/jota/client/ui/Bundle"); // NOI18N
        customColorsCheckBox.setText(bundle.getString("OptionsPanel.customColorsCheckBox.text")); // NOI18N

        autostartServerCheckBox.setText(bundle.getString("OptionsPanel.autostartServerCheckBox.text")); // NOI18N
        autostartServerCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autostartServerCheckBoxActionPerformed(evt);
            }
        });

        portLabel.setText(Dict.PORT.toString());

        portSpinner.setModel(new javax.swing.SpinnerNumberModel(1024, 1024, 65535, 1));

        connectDelayLabel.setText(bundle.getString("OptionsPanel.connectDelayLabel.text")); // NOI18N

        connectDelaySpinner.setModel(new javax.swing.SpinnerNumberModel(500, 100, 3000, 100));

        wordWrapCheckBox.setText(Dict.DYNAMIC_WORD_WRAP.toString());

        displayDryRunCheckBox.setText(bundle.getString("OptionsPanel.displayDryRunCheckBox.text")); // NOI18N

        outputLabel.setFont(outputLabel.getFont().deriveFont((outputLabel.getFont().getStyle() | java.awt.Font.ITALIC) | java.awt.Font.BOLD, outputLabel.getFont().getSize()+2));
        outputLabel.setText(Dict.OUTPUT.toString());

        splitErrorsLogCheckBox.setText(bundle.getString("OptionsPanel.splitErrorsLogCheckBox.text")); // NOI18N

        splitDeletionsLogCheckBox.setText(bundle.getString("OptionsPanel.splitDeletionsLogCheckBox.text")); // NOI18N

        javax.swing.GroupLayout clientPanelLayout = new javax.swing.GroupLayout(clientPanel);
        clientPanel.setLayout(clientPanelLayout);
        clientPanelLayout.setHorizontalGroup(
            clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customColorsCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(clientPanelLayout.createSequentialGroup()
                        .addGroup(clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(clientPanelLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(portSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(portLabel))
                                .addGap(18, 18, 18)
                                .addGroup(clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(connectDelayLabel)
                                    .addComponent(connectDelaySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(autostartServerCheckBox)
                            .addComponent(wordWrapCheckBox)
                            .addComponent(displayDryRunCheckBox)
                            .addComponent(outputLabel)
                            .addComponent(splitErrorsLogCheckBox)
                            .addComponent(splitDeletionsLogCheckBox))
                        .addGap(0, 128, Short.MAX_VALUE)))
                .addContainerGap())
        );
        clientPanelLayout.setVerticalGroup(
            clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(customColorsCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(autostartServerCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(portLabel)
                    .addComponent(connectDelayLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(portSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(connectDelaySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayDryRunCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outputLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wordWrapCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(splitErrorsLogCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(splitDeletionsLogCheckBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabbedPane.addTab(Dict.CLIENT.toString(), clientPanel);

        rsyncFileChooserPanel.setHeader(bundle.getString("OptionsPanel.rsyncFileChooserPanel.header")); // NOI18N

        logDirFileChooserPanel.setHeader(Dict.LOG_DIRECTORY.toString());
        logDirFileChooserPanel.setMode(JFileChooser.DIRECTORIES_ONLY);

        javax.swing.GroupLayout serverPanelLayout = new javax.swing.GroupLayout(serverPanel);
        serverPanel.setLayout(serverPanelLayout);
        serverPanelLayout.setHorizontalGroup(
            serverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(serverPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(serverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rsyncFileChooserPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logDirFileChooserPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        serverPanelLayout.setVerticalGroup(
            serverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(serverPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rsyncFileChooserPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logDirFileChooserPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabbedPane.addTab(Dict.SERVER.toString(), serverPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void autostartServerCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autostartServerCheckBoxActionPerformed
        final boolean autoConnect = autostartServerCheckBox.isSelected();

        portLabel.setEnabled(autoConnect);
        portSpinner.setEnabled(autoConnect);
        connectDelayLabel.setEnabled(autoConnect);
        connectDelaySpinner.setEnabled(autoConnect);
    }//GEN-LAST:event_autostartServerCheckBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox autostartServerCheckBox;
    private javax.swing.JPanel clientPanel;
    private javax.swing.JLabel connectDelayLabel;
    private javax.swing.JSpinner connectDelaySpinner;
    private javax.swing.JCheckBox customColorsCheckBox;
    private javax.swing.JCheckBox displayDryRunCheckBox;
    private se.trixon.almond.util.swing.dialogs.FileChooserPanel logDirFileChooserPanel;
    private javax.swing.JLabel outputLabel;
    private javax.swing.JLabel portLabel;
    private javax.swing.JSpinner portSpinner;
    private se.trixon.almond.util.swing.dialogs.FileChooserPanel rsyncFileChooserPanel;
    private javax.swing.JPanel serverPanel;
    private javax.swing.JCheckBox splitDeletionsLogCheckBox;
    private javax.swing.JCheckBox splitErrorsLogCheckBox;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JCheckBox wordWrapCheckBox;
    // End of variables declaration//GEN-END:variables
}
