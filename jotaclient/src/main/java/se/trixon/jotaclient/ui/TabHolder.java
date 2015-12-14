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
package se.trixon.jotaclient.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import se.trixon.jota.ProcessEvent;
import se.trixon.jota.ServerEvent;
import se.trixon.jota.ServerEventListener;
import se.trixon.jota.job.Job;
import se.trixon.jota.task.Task;
import se.trixon.jotaclient.ConnectionListener;
import se.trixon.jotaclient.Manager;
import se.trixon.util.icon.Pict;

/**
 *
 * @author Patrik Karlsson
 */
public class TabHolder extends JTabbedPane implements ConnectionListener, ServerEventListener {

    private SpeedDialPanel mSpeedDialPanel;
    private HashMap<Long, TabItem> mJobMap = new HashMap<>();
    private final Manager mManager = Manager.getInstance();
    private ActionListener mMenuActionListener;

    /**
     * Creates new form ProgressPane
     */
    public TabHolder() {
        initComponents();
        init();
    }

    public SpeedDialPanel getSpeedDialPanel() {
        return mSpeedDialPanel;
    }

    @Override
    public void onConnectionConnect() {
        // nvm
    }

    @Override
    public void onConnectionDisconnect() {
        setSelectedComponent(mSpeedDialPanel);

        mJobMap.entrySet().stream().forEach((entry) -> {
            TabItem tabItem = entry.getValue();
            remove(tabItem);
        });

        mJobMap = new HashMap<>();
    }

    @Override
    public void onProcessEvent(ProcessEvent processEvent, Job job, Task task, Object object) {
        TabItem tabItem = getTabItem(job);

        switch (processEvent) {
            case STARTED:
                tabItem.start();
                setSelectedComponent(tabItem);
                break;
            case OUT:
            case ERR:
                tabItem.log(processEvent, (String) object);
                break;
            case CANCELED:
                tabItem.log(ProcessEvent.OUT, "\n\nJob interrupted.");
                tabItem.enableSave();
                break;
            case FINISHED:
                tabItem.enableSave();
                break;
            default:
                break;
        }
    }

    @Override
    public void onServerEvent(ServerEvent serverEvent) {
    }

    private void displayTab(int index) {
        try {
            setSelectedIndex(index);
        } catch (IndexOutOfBoundsException e) {
            // nvm
        }
    }

    private void displayTabNext() {
        System.out.println("next");
    }

    private void displayTabPrev() {
        System.out.println("prev");
    }

    void closeTab() {
        if (getSelectedComponent() instanceof TabItem) {
            TabItem tabItem = (TabItem) getSelectedComponent();
            if (tabItem.isClosable()) {
                close(tabItem.getJob());
            }
        }
    }

    void initActions() {
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getRootPane().getActionMap();

        for (int i = 0; i < 10; i++) {
            KeyStroke keyStroke = KeyStroke.getKeyStroke(0x31 + i, InputEvent.CTRL_MASK);
            String key = "key_" + i;
            final int tabIndex = i;
            AbstractAction action = new AbstractAction("Tab") {

                @Override
                public void actionPerformed(ActionEvent e) {
                    displayTab(tabIndex);
                }
            };
            inputMap.put(keyStroke, key);
            actionMap.put(key, action);
        }

        AbstractAction action = new AbstractAction("TabNext") {

            @Override
            public void actionPerformed(ActionEvent e) {
                displayTabNext();
            }
        };

        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.CTRL_MASK);
        String key = "nextTab";
        inputMap.put(keyStroke, key);
        actionMap.put(key, action);

        action = new AbstractAction("TabPrev") {

            @Override
            public void actionPerformed(ActionEvent e) {
                displayTabPrev();
            }

        };

        keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK + InputEvent.SHIFT_MASK);
        key = "prevTab";
        inputMap.put(keyStroke, key);
        actionMap.put(key, action);

        action = new AbstractAction("DisplayMenu") {

            @Override
            public void actionPerformed(ActionEvent e) {
                mMenuActionListener.actionPerformed(null);
            }
        };

        key = "DisplayMenu";
        actionMap.put(key, action);
        keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK);
        inputMap.put(keyStroke, key);
        keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0);
        inputMap.put(keyStroke, key);

        action = new AbstractAction("Cancel") {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (getSelectedComponent() instanceof TabItem) {
                    TabItem tabItem = (TabItem) getSelectedComponent();
                    if (tabItem.isCancelable()) {
                        tabItem.cancel();
                    }
                }
            }
        };

        key = "Cancel";
        actionMap.put(key, action);
        keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        inputMap.put(keyStroke, key);
    }

    void close(Job job) {
        TabItem panel = getTabItem(job);
        mJobMap.remove(job.getId());
        remove(panel);
    }

    private synchronized TabItem getTabItem(Job job) {
        TabItem tabItem;

        if (mJobMap.containsKey(job.getId())) {
            tabItem = mJobMap.get(job.getId());
        } else {
            tabItem = new TabItem(job);
            tabItem.getMenuButton().addActionListener(mMenuActionListener);

            add(tabItem, job.getName());
            mJobMap.put(job.getId(), tabItem);
            setSelectedComponent(tabItem);
        }

        return tabItem;
    }

    private void init() {
        mSpeedDialPanel = new SpeedDialPanel();
        add(mSpeedDialPanel, Pict.Actions.GO_HOME.get(UI.ICON_SIZE_LARGE));

        mManager.addConnectionListeners(this);
        mManager.getClient().addServerEventListener(this);

        mMenuActionListener = (ActionEvent e) -> {

            Component component = ((TabListener) getSelectedComponent()).getMenuButton();
            JPopupMenu popupMenu = MainFrame.getPopupMenu();

            boolean closable = false;
            if (getSelectedComponent() instanceof TabItem) {
                closable = ((TabItem) getSelectedComponent()).isClosable();
            }
            popupMenu.getComponent(popupMenu.getComponentCount() - 3).setEnabled(closable);

            if (popupMenu.isVisible()) {
                popupMenu.setVisible(false);
            } else {
                popupMenu.show(component, component.getWidth() - popupMenu.getWidth(), component.getHeight());

                int x = component.getLocationOnScreen().x + component.getWidth() - popupMenu.getWidth();
                int y = component.getLocationOnScreen().y + component.getHeight();

                popupMenu.setLocation(x, y);
            }
        };
        mSpeedDialPanel.getMenuButton().addActionListener(mMenuActionListener);
        setTabLayoutPolicy(SCROLL_TAB_LAYOUT);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
