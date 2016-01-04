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
package se.trixon.jotaclient.ui.editor.task_modules;

import se.trixon.jota.task.ExcludeSection;
import se.trixon.jota.task.Task;
import se.trixon.util.dictionary.Dict;

/**
 *
 * @author Patrik Karlsson
 */
public class ModuleExcludePanel extends ModulePanel {

    /**
     * Creates new form ModulePanel
     */
    public ModuleExcludePanel() {
        initComponents();
        init();
    }

    @Override
    public void loadTask(Task task) {
        ExcludeSection excludeSection = task.getExcludeSection();

        externalFilePanel.setSelected(excludeSection.isManualFileUsed());
        externalFilePanel.setPath(excludeSection.getManualFilePath());
        externalFilePanel.setEnabled(externalFilePanel.isSelected());

        backupCheckBox.setSelected(excludeSection.isTemplateBackup());
        cacheCheckBox.setSelected(excludeSection.isTemplateCache());
        gvfsCheckBox.setSelected(excludeSection.isTemplateGvfs());
        lostFoundCheckBox.setSelected(excludeSection.isTemplateLostFound());
        sysDirsCheckBox.setSelected(excludeSection.isTemplateSystemDirs());
        sysMountsCheckBox.setSelected(excludeSection.isTemplateSystemMountDirs());
        tempCheckBox.setSelected(excludeSection.isTemplateTemp());
        trashCheckBox.setSelected(excludeSection.isTemplateTrash());
    }

    @Override
    public Task saveTask(Task task) {
        ExcludeSection excludeSection = task.getExcludeSection();

        excludeSection.setManualFileUsed(externalFilePanel.isSelected());
        excludeSection.setManualFilePath(externalFilePanel.getPath());

        excludeSection.setTemplateBackup(backupCheckBox.isSelected());
        excludeSection.setTemplateCache(cacheCheckBox.isSelected());
        excludeSection.setTemplateGvfs(gvfsCheckBox.isSelected());
        excludeSection.setTemplateLostFound(lostFoundCheckBox.isSelected());
        excludeSection.setTemplateSystemDirs(sysDirsCheckBox.isSelected());
        excludeSection.setTemplateSystemMountDirs(sysMountsCheckBox.isSelected());
        excludeSection.setTemplateTemp(tempCheckBox.isSelected());
        excludeSection.setTemplateTrash(trashCheckBox.isSelected());

        return task;
    }

    private void init() {
        mTitle = Dict.EXCLUDE.getString();
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
        templatePanel = new javax.swing.JPanel();
        template1Panel = new javax.swing.JPanel();
        tempCheckBox = new javax.swing.JCheckBox();
        backupCheckBox = new javax.swing.JCheckBox();
        cacheCheckBox = new javax.swing.JCheckBox();
        trashCheckBox = new javax.swing.JCheckBox();
        template2Panel = new javax.swing.JPanel();
        sysMountsCheckBox = new javax.swing.JCheckBox();
        sysDirsCheckBox = new javax.swing.JCheckBox();
        lostFoundCheckBox = new javax.swing.JCheckBox();
        gvfsCheckBox = new javax.swing.JCheckBox();
        manualPanel = new javax.swing.JPanel();
        externalFilePanel = new se.trixon.util.swing.dialogs.FileChooserPanel();

        setLayout(new java.awt.BorderLayout());

        tabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        templatePanel.setLayout(new java.awt.GridLayout(1, 2));

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("se/trixon/jotaclient/ui/editor/task_modules/Bundle"); // NOI18N
        tempCheckBox.setText(bundle.getString("ModuleExcludePanel.tempCheckBox.text")); // NOI18N

        backupCheckBox.setText(bundle.getString("ModuleExcludePanel.backupCheckBox.text")); // NOI18N

        cacheCheckBox.setText(bundle.getString("ModuleExcludePanel.cacheCheckBox.text")); // NOI18N

        trashCheckBox.setText(bundle.getString("ModuleExcludePanel.trashCheckBox.text")); // NOI18N

        javax.swing.GroupLayout template1PanelLayout = new javax.swing.GroupLayout(template1Panel);
        template1Panel.setLayout(template1PanelLayout);
        template1PanelLayout.setHorizontalGroup(
            template1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, template1PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(template1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(trashCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(backupCheckBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tempCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cacheCheckBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        template1PanelLayout.setVerticalGroup(
            template1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(template1PanelLayout.createSequentialGroup()
                .addComponent(tempCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backupCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cacheCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(trashCheckBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        templatePanel.add(template1Panel);

        sysMountsCheckBox.setText(bundle.getString("ModuleExcludePanel.sysMountsCheckBox.text")); // NOI18N

        sysDirsCheckBox.setText(bundle.getString("ModuleExcludePanel.sysDirsCheckBox.text")); // NOI18N

        lostFoundCheckBox.setText(bundle.getString("ModuleExcludePanel.lostFoundCheckBox.text")); // NOI18N

        gvfsCheckBox.setText(bundle.getString("ModuleExcludePanel.gvfsCheckBox.text")); // NOI18N

        javax.swing.GroupLayout template2PanelLayout = new javax.swing.GroupLayout(template2Panel);
        template2Panel.setLayout(template2PanelLayout);
        template2PanelLayout.setHorizontalGroup(
            template2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(template2PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(template2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sysMountsCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sysDirsCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lostFoundCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gvfsCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        template2PanelLayout.setVerticalGroup(
            template2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(template2PanelLayout.createSequentialGroup()
                .addComponent(sysMountsCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sysDirsCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lostFoundCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gvfsCheckBox)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        templatePanel.add(template2Panel);

        tabbedPane.addTab(Dict.TEMPLATES.toString(), templatePanel);

        externalFilePanel.setCheckBoxMode(true);
        externalFilePanel.setHeader(bundle.getString("ModuleExcludePanel.externalFilePanel.header")); // NOI18N

        javax.swing.GroupLayout manualPanelLayout = new javax.swing.GroupLayout(manualPanel);
        manualPanel.setLayout(manualPanelLayout);
        manualPanelLayout.setHorizontalGroup(
            manualPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manualPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(externalFilePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addContainerGap())
        );
        manualPanelLayout.setVerticalGroup(
            manualPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manualPanelLayout.createSequentialGroup()
                .addComponent(externalFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 102, Short.MAX_VALUE))
        );

        tabbedPane.addTab(Dict.MANUAL.toString(), manualPanel);

        add(tabbedPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox backupCheckBox;
    private javax.swing.JCheckBox cacheCheckBox;
    private se.trixon.util.swing.dialogs.FileChooserPanel externalFilePanel;
    private javax.swing.JCheckBox gvfsCheckBox;
    private javax.swing.JCheckBox lostFoundCheckBox;
    private javax.swing.JPanel manualPanel;
    private javax.swing.JCheckBox sysDirsCheckBox;
    private javax.swing.JCheckBox sysMountsCheckBox;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JCheckBox tempCheckBox;
    private javax.swing.JPanel template1Panel;
    private javax.swing.JPanel template2Panel;
    private javax.swing.JPanel templatePanel;
    private javax.swing.JCheckBox trashCheckBox;
    // End of variables declaration//GEN-END:variables
}
