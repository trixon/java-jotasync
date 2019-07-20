/*
 * Copyright 2019 Patrik Karlström.
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
package se.trixon.jota.client.ui_swing.editor;

import java.awt.Component;
import java.util.Arrays;
import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;
import se.trixon.almond.util.AlmondUI;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.icons.material.MaterialIcon;

/**
 *
 * @author Patrik Karlström
 */
public abstract class EditPanel extends javax.swing.JPanel {

    private static final int ICON_SIZE = AlmondUI.ICON_SIZE_NORMAL;
    private DefaultListModel mModel = new DefaultListModel();

    /**
     * Creates new form EditPanel
     */
    public EditPanel() {
        initComponents();
        init();
    }

    public abstract void save();

    public DefaultListModel getModel() {
        return mModel;
    }

    public void setModel(DefaultListModel model) {
        mModel = model;
        list.setModel(model);
    }

    public void sortModel() {
        Object[] objects = mModel.toArray();
        Arrays.sort(objects);
        mModel.clear();

        for (Object object : objects) {
            mModel.addElement(object);
        }
    }

    protected Component getRoot() {
        return SwingUtilities.getRoot(this);
    }

    private void init() {
        for (Component component : toolBar.getComponents()) {
            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                button.setVisible(false);
            }
        }

        toggleButton.setIcon(MaterialIcon._Action.SCHEDULE.getImageIcon(ICON_SIZE));
        addButton.setIcon(MaterialIcon._Content.ADD.getImageIcon(ICON_SIZE));
        cloneButton.setIcon(MaterialIcon._Content.CONTENT_COPY.getImageIcon(ICON_SIZE));
        editButton.setIcon(MaterialIcon._Editor.MODE_EDIT.getImageIcon(ICON_SIZE));
        removeButton.setIcon(MaterialIcon._Content.REMOVE.getImageIcon(ICON_SIZE));
        removeAllButton.setIcon(MaterialIcon._Content.CLEAR.getImageIcon(ICON_SIZE));

        activateButton.setIcon(MaterialIcon._Navigation.ARROW_BACK.getImageIcon(ICON_SIZE));
        deactivateButton.setIcon(MaterialIcon._Navigation.ARROW_FORWARD.getImageIcon(ICON_SIZE));

        moveDownButton.setIcon(MaterialIcon._Navigation.ARROW_DOWNWARD.getImageIcon(ICON_SIZE));
        moveUpButton.setIcon(MaterialIcon._Navigation.ARROW_UPWARD.getImageIcon(ICON_SIZE));
        moveFirstButton.setIcon(MaterialIcon._Editor.VERTICAL_ALIGN_TOP.getImageIcon(ICON_SIZE));
        moveLastButton.setIcon(MaterialIcon._Editor.VERTICAL_ALIGN_BOTTOM.getImageIcon(ICON_SIZE));
        //list.setModel(mModel);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label = new javax.swing.JLabel();
        toolBar = new javax.swing.JToolBar();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(8, 0), new java.awt.Dimension(8, 0), new java.awt.Dimension(8, 32767));
        toggleButton = new javax.swing.JToggleButton();
        addButton = new javax.swing.JButton();
        cloneButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        removeAllButton = new javax.swing.JButton();
        checkBox = new javax.swing.JCheckBox();
        moveFirstButton = new javax.swing.JButton();
        moveUpButton = new javax.swing.JButton();
        moveDownButton = new javax.swing.JButton();
        moveLastButton = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        activateButton = new javax.swing.JButton();
        deactivateButton = new javax.swing.JButton();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(8, 0), new java.awt.Dimension(8, 0), new java.awt.Dimension(8, 32767));
        scrollPane = new javax.swing.JScrollPane();
        list = new javax.swing.JList();

        label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | java.awt.Font.BOLD, label.getFont().getSize()+2));
        label.setText("Label"); // NOI18N

        toolBar.setFloatable(false);
        toolBar.setRollover(true);
        toolBar.add(filler1);

        toggleButton.setToolTipText(Dict.SCHEDULE.toString());
        toggleButton.setFocusable(false);
        toggleButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toggleButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(toggleButton);

        addButton.setToolTipText(Dict.ADD.toString());
        addButton.setFocusable(false);
        addButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(addButton);

        cloneButton.setToolTipText(Dict.CLONE.toString());
        cloneButton.setFocusable(false);
        cloneButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cloneButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(cloneButton);

        editButton.setToolTipText(Dict.EDIT.toString());
        editButton.setFocusable(false);
        editButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(editButton);

        removeButton.setToolTipText(Dict.REMOVE.toString());
        removeButton.setFocusable(false);
        removeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        removeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(removeButton);

        removeAllButton.setToolTipText(Dict.REMOVE_ALL.toString());
        removeAllButton.setFocusable(false);
        removeAllButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        removeAllButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(removeAllButton);

        checkBox.setFocusable(false);
        checkBox.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        checkBox.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(checkBox);

        moveFirstButton.setToolTipText(Dict.MOVE_TOP.toString());
        moveFirstButton.setFocusable(false);
        moveFirstButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        moveFirstButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(moveFirstButton);

        moveUpButton.setToolTipText(Dict.MOVE_UP.toString());
        moveUpButton.setFocusable(false);
        moveUpButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        moveUpButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(moveUpButton);

        moveDownButton.setToolTipText(Dict.MOVE_DOWN.toString());
        moveDownButton.setFocusable(false);
        moveDownButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        moveDownButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(moveDownButton);

        moveLastButton.setToolTipText(Dict.MOVE_BOTTOM.toString());
        moveLastButton.setFocusable(false);
        moveLastButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        moveLastButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(moveLastButton);
        toolBar.add(filler2);

        activateButton.setToolTipText(Dict.ACTIVATE.toString());
        activateButton.setFocusable(false);
        activateButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        activateButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(activateButton);

        deactivateButton.setToolTipText(Dict.DEACTIVATE.toString());
        deactivateButton.setFocusable(false);
        deactivateButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deactivateButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(deactivateButton);
        toolBar.add(filler3);

        scrollPane.setViewportView(list);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton activateButton;
    protected javax.swing.JButton addButton;
    protected javax.swing.JCheckBox checkBox;
    protected javax.swing.JButton cloneButton;
    protected javax.swing.JButton deactivateButton;
    protected javax.swing.JButton editButton;
    protected javax.swing.Box.Filler filler1;
    protected javax.swing.Box.Filler filler2;
    protected javax.swing.Box.Filler filler3;
    protected javax.swing.JLabel label;
    protected javax.swing.JList list;
    protected javax.swing.JButton moveDownButton;
    protected javax.swing.JButton moveFirstButton;
    protected javax.swing.JButton moveLastButton;
    protected javax.swing.JButton moveUpButton;
    protected javax.swing.JButton removeAllButton;
    protected javax.swing.JButton removeButton;
    protected javax.swing.JScrollPane scrollPane;
    protected javax.swing.JToggleButton toggleButton;
    protected javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables
}
