/* 
 * Copyright 2016 Patrik Karlsson.
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
package se.trixon.jota.client.ui.editor.task_modules;

import se.trixon.jota.shared.task.OptionSection;
import se.trixon.jota.shared.task.Task;
import se.trixon.util.dictionary.Dict;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class ModuleOptionsPanel extends ModulePanel {

    /**
     * Creates new form ModulePanel
     */
    public ModuleOptionsPanel() {
        initComponents();
        init();
    }

    @Override
    public void loadTask(Task task) {
        OptionSection optionSection = task.getOptionSection();

        additionalOptionsTextArea.setText(optionSection.getAdditionalOptions());
        backupCheckBox.setSelected(optionSection.isBackup());
        checksumCheckBox.setSelected(optionSection.isChecksum());
        compressCheckBox.setSelected(optionSection.isCompress());
        deleteCheckBox.setSelected(optionSection.isDelete());
        devicesCheckBox.setSelected(optionSection.isDevices());
        dirsCheckBox.setSelected(optionSection.isDirs());
        existingCheckBox.setSelected(optionSection.isExisting());
        groupCheckBox.setSelected(optionSection.isGroup());
        hardlinksCheckBox.setSelected(optionSection.isHardLinks());
        ignoreExistingCheckBox.setSelected(optionSection.isIgnoreExisting());
        itemizeChangesCheckBox.setSelected(optionSection.isItemizeChanges());
        linksCheckBox.setSelected(optionSection.isLinks());
        modifyWindowCheckBox.setSelected(optionSection.isModifyWindow());
        numericIdsCheckBox.setSelected(optionSection.isNumericIds());
        oneFileSystemCheckBox.setSelected(optionSection.isOneFileSystem());
        ownerCheckBox.setSelected(optionSection.isOwner());
        partialProgressCheckBox.setSelected(optionSection.isPartialProgress());
        permsCheckBox.setSelected(optionSection.isPerms());
        progressCheckBox.setSelected(optionSection.isProgress());
        protectArgsCheckBox.setSelected(optionSection.isProtectArgs());
        sizeOnlyCheckBox.setSelected(optionSection.isSizeOnly());
        timesCheckBox.setSelected(optionSection.isTimes());
        updateCheckBox.setSelected(optionSection.isUpdate());
        verboseCheckBox.setSelected(optionSection.isVerbose());
    }

    @Override
    public Task saveTask(Task task) {
        OptionSection optionSection = task.getOptionSection();

        optionSection.setAdditionalOptions(additionalOptionsTextArea.getText());
        optionSection.setBackup(backupCheckBox.isSelected());
        optionSection.setChecksum(checksumCheckBox.isSelected());
        optionSection.setCompress(compressCheckBox.isSelected());
        optionSection.setDelete(deleteCheckBox.isSelected());
        optionSection.setDevices(devicesCheckBox.isSelected());
        optionSection.setDirs(dirsCheckBox.isSelected());
        optionSection.setExisting(existingCheckBox.isSelected());
        optionSection.setGroup(groupCheckBox.isSelected());
        optionSection.setHardLinks(hardlinksCheckBox.isSelected());
        optionSection.setIgnoreExisting(ignoreExistingCheckBox.isSelected());
        optionSection.setItemizeChanges(itemizeChangesCheckBox.isSelected());
        optionSection.setLinks(linksCheckBox.isSelected());
        optionSection.setModifyWindow(modifyWindowCheckBox.isSelected());
        optionSection.setNumericIds(numericIdsCheckBox.isSelected());
        optionSection.setOneFileSystem(oneFileSystemCheckBox.isSelected());
        optionSection.setOwner(ownerCheckBox.isSelected());
        optionSection.setPartialProgress(partialProgressCheckBox.isSelected());
        optionSection.setPerms(permsCheckBox.isSelected());
        optionSection.setProgress(progressCheckBox.isSelected());
        optionSection.setProtectArgs(protectArgsCheckBox.isSelected());
        optionSection.setSizeOnly(sizeOnlyCheckBox.isSelected());
        optionSection.setTimes(timesCheckBox.isSelected());
        optionSection.setUpdate(updateCheckBox.isSelected());
        optionSection.setVerbose(verboseCheckBox.isSelected());

        return task;
    }

    private void init() {
        mTitle = Dict.OPTIONS.getString();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();
        basicPanel = new javax.swing.JPanel();
        basic1Panel = new javax.swing.JPanel();
        basic11Panel = new javax.swing.JPanel();
        timesCheckBox = new javax.swing.JCheckBox();
        ownerCheckBox = new javax.swing.JCheckBox();
        basic12Panel = new javax.swing.JPanel();
        permsCheckBox = new javax.swing.JCheckBox();
        groupCheckBox = new javax.swing.JCheckBox();
        basic2Panel = new javax.swing.JPanel();
        basic21Panel = new javax.swing.JPanel();
        verboseCheckBox = new javax.swing.JCheckBox();
        ignoreExistingCheckBox = new javax.swing.JCheckBox();
        updateCheckBox = new javax.swing.JCheckBox();
        deleteCheckBox = new javax.swing.JCheckBox();
        basic22Panel = new javax.swing.JPanel();
        oneFileSystemCheckBox = new javax.swing.JCheckBox();
        progressCheckBox = new javax.swing.JCheckBox();
        sizeOnlyCheckBox = new javax.swing.JCheckBox();
        modifyWindowCheckBox = new javax.swing.JCheckBox();
        advancedPanel = new javax.swing.JPanel();
        advanced11Panel = new javax.swing.JPanel();
        checksumCheckBox = new javax.swing.JCheckBox();
        devicesCheckBox = new javax.swing.JCheckBox();
        partialProgressCheckBox = new javax.swing.JCheckBox();
        linksCheckBox = new javax.swing.JCheckBox();
        backupCheckBox = new javax.swing.JCheckBox();
        dirsCheckBox = new javax.swing.JCheckBox();
        advanced12Panel = new javax.swing.JPanel();
        compressCheckBox = new javax.swing.JCheckBox();
        existingCheckBox = new javax.swing.JCheckBox();
        numericIdsCheckBox = new javax.swing.JCheckBox();
        hardlinksCheckBox = new javax.swing.JCheckBox();
        itemizeChangesCheckBox = new javax.swing.JCheckBox();
        protectArgsCheckBox = new javax.swing.JCheckBox();
        additionalScrollPane = new javax.swing.JScrollPane();
        additionalOptionsTextArea = new javax.swing.JTextArea();

        setLayout(new java.awt.BorderLayout());

        tabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        basic1Panel.setLayout(new java.awt.GridLayout(1, 0));

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("se/trixon/jota/client/ui/editor/task_modules/Bundle"); // NOI18N
        timesCheckBox.setText(bundle.getString("ModuleOptionsPanel.timesCheckBox.text")); // NOI18N
        timesCheckBox.setToolTipText(bundle.getString("ModuleOptionsPanel.timesCheckBox.toolTipText")); // NOI18N

        ownerCheckBox.setText(bundle.getString("ModuleOptionsPanel.ownerCheckBox.text")); // NOI18N
        ownerCheckBox.setToolTipText(bundle.getString("ModuleOptionsPanel.ownerCheckBox.toolTipText")); // NOI18N

        javax.swing.GroupLayout basic11PanelLayout = new javax.swing.GroupLayout(basic11Panel);
        basic11Panel.setLayout(basic11PanelLayout);
        basic11PanelLayout.setHorizontalGroup(
            basic11PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basic11PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(basic11PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timesCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ownerCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        basic11PanelLayout.setVerticalGroup(
            basic11PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basic11PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(timesCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ownerCheckBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        basic1Panel.add(basic11Panel);

        permsCheckBox.setText(bundle.getString("ModuleOptionsPanel.permsCheckBox.text")); // NOI18N
        permsCheckBox.setToolTipText(bundle.getString("ModuleOptionsPanel.permsCheckBox.toolTipText")); // NOI18N

        groupCheckBox.setText(bundle.getString("ModuleOptionsPanel.groupCheckBox.text")); // NOI18N
        groupCheckBox.setToolTipText(bundle.getString("ModuleOptionsPanel.groupCheckBox.toolTipText")); // NOI18N

        javax.swing.GroupLayout basic12PanelLayout = new javax.swing.GroupLayout(basic12Panel);
        basic12Panel.setLayout(basic12PanelLayout);
        basic12PanelLayout.setHorizontalGroup(
            basic12PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basic12PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(basic12PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(permsCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(groupCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        basic12PanelLayout.setVerticalGroup(
            basic12PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basic12PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(permsCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(groupCheckBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        basic1Panel.add(basic12Panel);

        basic2Panel.setLayout(new java.awt.GridLayout(1, 0));

        verboseCheckBox.setText(bundle.getString("ModuleOptionsPanel.verboseCheckBox.text")); // NOI18N
        verboseCheckBox.setToolTipText(bundle.getString("ModuleOptionsPanel.verboseCheckBox.toolTipText")); // NOI18N

        ignoreExistingCheckBox.setText(bundle.getString("ModuleOptionsPanel.ignoreExistingCheckBox.text")); // NOI18N
        ignoreExistingCheckBox.setToolTipText(bundle.getString("ModuleOptionsPanel.ignoreExistingCheckBox.toolTipText")); // NOI18N

        updateCheckBox.setText(bundle.getString("ModuleOptionsPanel.updateCheckBox.text")); // NOI18N
        updateCheckBox.setToolTipText(bundle.getString("ModuleOptionsPanel.updateCheckBox.toolTipText")); // NOI18N

        deleteCheckBox.setText(bundle.getString("ModuleOptionsPanel.deleteCheckBox.text")); // NOI18N
        deleteCheckBox.setToolTipText(bundle.getString("ModuleOptionsPanel.deleteCheckBox.toolTipText")); // NOI18N

        javax.swing.GroupLayout basic21PanelLayout = new javax.swing.GroupLayout(basic21Panel);
        basic21Panel.setLayout(basic21PanelLayout);
        basic21PanelLayout.setHorizontalGroup(
            basic21PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basic21PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(basic21PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(verboseCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ignoreExistingCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updateCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        basic21PanelLayout.setVerticalGroup(
            basic21PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basic21PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(deleteCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(verboseCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ignoreExistingCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateCheckBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        basic2Panel.add(basic21Panel);

        oneFileSystemCheckBox.setText(bundle.getString("ModuleOptionsPanel.oneFileSystemCheckBox.text")); // NOI18N
        oneFileSystemCheckBox.setToolTipText(bundle.getString("ModuleOptionsPanel.oneFileSystemCheckBox.toolTipText")); // NOI18N

        progressCheckBox.setText(bundle.getString("ModuleOptionsPanel.progressCheckBox.text")); // NOI18N
        progressCheckBox.setToolTipText(bundle.getString("ModuleOptionsPanel.progressCheckBox.toolTipText")); // NOI18N

        sizeOnlyCheckBox.setText(bundle.getString("ModuleOptionsPanel.sizeOnlyCheckBox.text")); // NOI18N
        sizeOnlyCheckBox.setToolTipText(bundle.getString("ModuleOptionsPanel.sizeOnlyCheckBox.toolTipText")); // NOI18N

        modifyWindowCheckBox.setText(bundle.getString("ModuleOptionsPanel.modifyWindowCheckBox.text")); // NOI18N
        modifyWindowCheckBox.setToolTipText(bundle.getString("ModuleOptionsPanel.modifyWindowCheckBox.toolTipText")); // NOI18N

        javax.swing.GroupLayout basic22PanelLayout = new javax.swing.GroupLayout(basic22Panel);
        basic22Panel.setLayout(basic22PanelLayout);
        basic22PanelLayout.setHorizontalGroup(
            basic22PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basic22PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(basic22PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(oneFileSystemCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progressCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sizeOnlyCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(modifyWindowCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        basic22PanelLayout.setVerticalGroup(
            basic22PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basic22PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(oneFileSystemCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sizeOnlyCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modifyWindowCheckBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        basic2Panel.add(basic22Panel);

        javax.swing.GroupLayout basicPanelLayout = new javax.swing.GroupLayout(basicPanel);
        basicPanel.setLayout(basicPanelLayout);
        basicPanelLayout.setHorizontalGroup(
            basicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(basicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(basic2Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(basic1Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        basicPanelLayout.setVerticalGroup(
            basicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(basicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(basicPanelLayout.createSequentialGroup()
                    .addComponent(basic1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(basic2Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        tabbedPane.addTab(Dict.BASIC.toString(), basicPanel);

        advancedPanel.setLayout(new java.awt.GridLayout(1, 0));

        checksumCheckBox.setText(bundle.getString("ModuleOptionsPanel.checksumCheckBox.text")); // NOI18N
        checksumCheckBox.setToolTipText(bundle.getString("ModuleOptionsPanel.checksumCheckBox.toolTipText")); // NOI18N

        devicesCheckBox.setText(bundle.getString("ModuleOptionsPanel.devicesCheckBox.text")); // NOI18N
        devicesCheckBox.setToolTipText(bundle.getString("ModuleOptionsPanel.devicesCheckBox.toolTipText")); // NOI18N

        partialProgressCheckBox.setText(bundle.getString("ModuleOptionsPanel.partialProgressCheckBox.text")); // NOI18N
        partialProgressCheckBox.setToolTipText(bundle.getString("ModuleOptionsPanel.partialProgressCheckBox.toolTipText")); // NOI18N

        linksCheckBox.setText(bundle.getString("ModuleOptionsPanel.linksCheckBox.text")); // NOI18N
        linksCheckBox.setToolTipText(bundle.getString("ModuleOptionsPanel.linksCheckBox.toolTipText")); // NOI18N

        backupCheckBox.setText(bundle.getString("ModuleOptionsPanel.backupCheckBox.text")); // NOI18N
        backupCheckBox.setToolTipText(bundle.getString("ModuleOptionsPanel.backupCheckBox.toolTipText")); // NOI18N

        dirsCheckBox.setText(bundle.getString("ModuleOptionsPanel.dirsCheckBox.text")); // NOI18N
        dirsCheckBox.setToolTipText(bundle.getString("ModuleOptionsPanel.dirsCheckBox.toolTipText")); // NOI18N

        javax.swing.GroupLayout advanced11PanelLayout = new javax.swing.GroupLayout(advanced11Panel);
        advanced11Panel.setLayout(advanced11PanelLayout);
        advanced11PanelLayout.setHorizontalGroup(
            advanced11PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(advanced11PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(advanced11PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checksumCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(devicesCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(partialProgressCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(linksCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(backupCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dirsCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        advanced11PanelLayout.setVerticalGroup(
            advanced11PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(advanced11PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checksumCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(devicesCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(partialProgressCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linksCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backupCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dirsCheckBox)
                .addGap(0, 0, 0))
        );

        advancedPanel.add(advanced11Panel);

        compressCheckBox.setText(bundle.getString("ModuleOptionsPanel.compressCheckBox.text")); // NOI18N
        compressCheckBox.setToolTipText(bundle.getString("ModuleOptionsPanel.compressCheckBox.toolTipText")); // NOI18N

        existingCheckBox.setText(bundle.getString("ModuleOptionsPanel.existingCheckBox.text")); // NOI18N
        existingCheckBox.setToolTipText(bundle.getString("ModuleOptionsPanel.existingCheckBox.toolTipText")); // NOI18N

        numericIdsCheckBox.setText(bundle.getString("ModuleOptionsPanel.numericIdsCheckBox.text")); // NOI18N
        numericIdsCheckBox.setToolTipText(bundle.getString("ModuleOptionsPanel.numericIdsCheckBox.toolTipText")); // NOI18N

        hardlinksCheckBox.setText(bundle.getString("ModuleOptionsPanel.hardlinksCheckBox.text")); // NOI18N
        hardlinksCheckBox.setToolTipText(bundle.getString("ModuleOptionsPanel.hardlinksCheckBox.toolTipText")); // NOI18N

        itemizeChangesCheckBox.setText(bundle.getString("ModuleOptionsPanel.itemizeChangesCheckBox.text")); // NOI18N
        itemizeChangesCheckBox.setToolTipText(bundle.getString("ModuleOptionsPanel.itemizeChangesCheckBox.toolTipText")); // NOI18N

        protectArgsCheckBox.setText(bundle.getString("ModuleOptionsPanel.protectArgsCheckBox.text")); // NOI18N
        protectArgsCheckBox.setToolTipText(bundle.getString("ModuleOptionsPanel.protectArgsCheckBox.toolTipText")); // NOI18N

        javax.swing.GroupLayout advanced12PanelLayout = new javax.swing.GroupLayout(advanced12Panel);
        advanced12Panel.setLayout(advanced12PanelLayout);
        advanced12PanelLayout.setHorizontalGroup(
            advanced12PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(advanced12PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(advanced12PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(compressCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(existingCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(numericIdsCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hardlinksCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(itemizeChangesCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(protectArgsCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        advanced12PanelLayout.setVerticalGroup(
            advanced12PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(advanced12PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(compressCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(existingCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numericIdsCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hardlinksCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(itemizeChangesCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(protectArgsCheckBox)
                .addContainerGap())
        );

        advancedPanel.add(advanced12Panel);

        tabbedPane.addTab(Dict.ADVANCED.toString(), advancedPanel);

        additionalOptionsTextArea.setColumns(20);
        additionalOptionsTextArea.setRows(5);
        additionalScrollPane.setViewportView(additionalOptionsTextArea);

        tabbedPane.addTab(Dict.MANUAL.toString(), additionalScrollPane);

        add(tabbedPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea additionalOptionsTextArea;
    private javax.swing.JScrollPane additionalScrollPane;
    private javax.swing.JPanel advanced11Panel;
    private javax.swing.JPanel advanced12Panel;
    private javax.swing.JPanel advancedPanel;
    private javax.swing.JCheckBox backupCheckBox;
    private javax.swing.JPanel basic11Panel;
    private javax.swing.JPanel basic12Panel;
    private javax.swing.JPanel basic1Panel;
    private javax.swing.JPanel basic21Panel;
    private javax.swing.JPanel basic22Panel;
    private javax.swing.JPanel basic2Panel;
    private javax.swing.JPanel basicPanel;
    private javax.swing.JCheckBox checksumCheckBox;
    private javax.swing.JCheckBox compressCheckBox;
    private javax.swing.JCheckBox deleteCheckBox;
    private javax.swing.JCheckBox devicesCheckBox;
    private javax.swing.JCheckBox dirsCheckBox;
    private javax.swing.JCheckBox existingCheckBox;
    private javax.swing.JCheckBox groupCheckBox;
    private javax.swing.JCheckBox hardlinksCheckBox;
    private javax.swing.JCheckBox ignoreExistingCheckBox;
    private javax.swing.JCheckBox itemizeChangesCheckBox;
    private javax.swing.JCheckBox linksCheckBox;
    private javax.swing.JCheckBox modifyWindowCheckBox;
    private javax.swing.JCheckBox numericIdsCheckBox;
    private javax.swing.JCheckBox oneFileSystemCheckBox;
    private javax.swing.JCheckBox ownerCheckBox;
    private javax.swing.JCheckBox partialProgressCheckBox;
    private javax.swing.JCheckBox permsCheckBox;
    private javax.swing.JCheckBox progressCheckBox;
    private javax.swing.JCheckBox protectArgsCheckBox;
    private javax.swing.JCheckBox sizeOnlyCheckBox;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JCheckBox timesCheckBox;
    private javax.swing.JCheckBox updateCheckBox;
    private javax.swing.JCheckBox verboseCheckBox;
    // End of variables declaration//GEN-END:variables
}