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
package se.trixon.jota.client.ui.editor.module.task;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.DefaultListModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import se.trixon.jota.client.ui.UI;
import se.trixon.jota.shared.task.Task;
import se.trixon.util.dictionary.Dict;
import se.trixon.util.icon.Pict;

/**
 *
 * @author Patrik Karlsson
 */
public class TaskOptionsPanel extends TaskModule {

    private static final int ICON_SIZE = UI.ICON_SIZE_LARGE;

    private final ArrayList<RsyncOption> mAvailableOptions = new ArrayList<>();
    private final ArrayList<RsyncOption> mSelectedOptions = new ArrayList<>();
    private DefaultListModel mAvailableModel;
    private DefaultListModel mSelectedModel;

    /**
     * Creates new form TaskOptionsPanel
     */
    public TaskOptionsPanel() {
        initComponents();
        init();
        initListeners();
    }

    @Override
    public void loadTask(Task task) {
    }

    @Override
    public Task saveTask(Task task) {
        return task;
    }

    private void activate() {
        for (int index : availableOptions.getList().getSelectedIndices()) {
            RsyncOption rsyncOption = (RsyncOption) mAvailableModel.elementAt(index);
            mSelectedOptions.add(rsyncOption);
            mAvailableOptions.remove(rsyncOption);
        }

        Collections.sort(mSelectedOptions, new Comparator<RsyncOption>() {
            @Override
            public int compare(RsyncOption o1, RsyncOption o2) {
                return o1.getDescription().compareTo(o2.getDescription());
            }
        });

        updateAvailableModel();
        updateSelectedModel();
    }

    private void deactivate() {
        for (int index : selectedOptions.getList().getSelectedIndices()) {
            RsyncOption rsyncOption = (RsyncOption) mSelectedModel.elementAt(index);
            mAvailableOptions.add(rsyncOption);
            mSelectedOptions.remove(rsyncOption);
        }

        Collections.sort(mAvailableOptions, new Comparator<RsyncOption>() {
            @Override
            public int compare(RsyncOption o1, RsyncOption o2) {
                return o1.getDescription().compareTo(o2.getDescription());
            }
        });

        updateAvailableModel();
        updateSelectedModel();
    }

    private void init() {
        mTitle = Dict.OPTIONS.getString();
        mAvailableModel = availableOptions.getModel();
        mSelectedModel = selectedOptions.getModel();

        mAvailableOptions.addAll(Arrays.asList(RsyncOption.values()));
        Collections.sort(mAvailableOptions, new Comparator<RsyncOption>() {
            @Override
            public int compare(RsyncOption o1, RsyncOption o2) {
                return o1.getDescription().compareTo(o2.getDescription());
            }
        });

        updateAvailableModel();
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

        optionsListPanel1 = new se.trixon.jota.client.ui.editor.module.task.OptionsListPanel();
        selectedOptions = new se.trixon.jota.client.ui.editor.module.task.RsyncOptionPanel();
        jToolBar1 = new javax.swing.JToolBar();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        activateButton = new javax.swing.JButton();
        deactivateButton = new javax.swing.JButton();
        removeAllButton = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        availableOptions = new se.trixon.jota.client.ui.editor.module.task.RsyncOptionPanel();

        setLayout(new java.awt.GridBagLayout());

        selectedOptions.setHeader(Dict.SELECTED.toString());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(selectedOptions, gridBagConstraints);

        jToolBar1.setFloatable(false);
        jToolBar1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jToolBar1.setRollover(true);
        jToolBar1.add(filler1);

        activateButton.setIcon(Pict.Actions.GO_PREVIOUS.get(ICON_SIZE));
        activateButton.setToolTipText(Dict.ACTIVATE.toString());
        activateButton.setFocusable(false);
        activateButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        activateButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        activateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activateButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(activateButton);

        deactivateButton.setIcon(Pict.Actions.GO_NEXT.get(ICON_SIZE));
        deactivateButton.setToolTipText(Dict.DEACTIVATE.toString());
        deactivateButton.setFocusable(false);
        deactivateButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deactivateButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deactivateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deactivateButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(deactivateButton);

        removeAllButton.setIcon(Pict.Actions.EDIT_DELETE.get(ICON_SIZE)
        );
        removeAllButton.setToolTipText(Dict.REMOVE_ALL.toString());
        removeAllButton.setFocusable(false);
        removeAllButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        removeAllButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        removeAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAllButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(removeAllButton);
        jToolBar1.add(filler2);

        add(jToolBar1, new java.awt.GridBagConstraints());

        availableOptions.setHeader(Dict.AVAILABLE.toString());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(availableOptions, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void activateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activateButtonActionPerformed
        activate();
    }//GEN-LAST:event_activateButtonActionPerformed

    private void deactivateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deactivateButtonActionPerformed
        deactivate();
    }//GEN-LAST:event_deactivateButtonActionPerformed

    private void initListeners() {
        availableOptions.getFilterTextField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filter();
            }

            private void filter() {
                updateAvailableModel();
            }
        });

        selectedOptions.getFilterTextField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filter();
            }

            private void filter() {
                updateSelectedModel();
            }

        });

        availableOptions.getList().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2) {
                    activate();
                }
            }
        });

        selectedOptions.getList().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2) {
                    deactivate();
                }
            }
        });
    }

    private void removeAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAllButtonActionPerformed
        mSelectedOptions.stream().forEach((rsyncOption) -> {
            mAvailableOptions.add(rsyncOption);
        });

        mSelectedOptions.clear();

        updateAvailableModel();
        updateSelectedModel();
    }//GEN-LAST:event_removeAllButtonActionPerformed

    private void updateAvailableModel() {
        String filter = availableOptions.getFilterTextField().getText();
        mAvailableModel.clear();
        for (RsyncOption rsyncOption : mAvailableOptions) {
            if (rsyncOption.filter(filter)) {
                mAvailableModel.addElement(rsyncOption);
            }
        }
    }

    private void updateSelectedModel() {
        String filter = selectedOptions.getFilterTextField().getText();
        mSelectedModel.clear();

        for (RsyncOption rsyncOption : mSelectedOptions) {
            if (rsyncOption.filter(filter)) {
                mSelectedModel.addElement(rsyncOption);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton activateButton;
    private se.trixon.jota.client.ui.editor.module.task.RsyncOptionPanel availableOptions;
    private javax.swing.JButton deactivateButton;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JToolBar jToolBar1;
    private se.trixon.jota.client.ui.editor.module.task.OptionsListPanel optionsListPanel1;
    private javax.swing.JButton removeAllButton;
    private se.trixon.jota.client.ui.editor.module.task.RsyncOptionPanel selectedOptions;
    // End of variables declaration//GEN-END:variables
}
