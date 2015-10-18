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
package se.trixon.jotaclient.ui.editor;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import se.trixon.jota.job.Job;
import se.trixon.jotaclient.Manager;
import se.trixon.util.BundleHelper;
import se.trixon.util.dictionary.Dict;
import se.trixon.util.swing.SwingHelper;
import se.trixon.util.swing.dialogs.Message;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class JobsPanel extends EditPanel {

    private final ResourceBundle mBundle = BundleHelper.getBundle(JobsPanel.class, "Bundle");
    private final HashSet<JobsListener> mJobsListeners = new HashSet<>();
    private Component mRoot;
    private final Manager mManager = Manager.getInstance();

    public JobsPanel() {
        init();
        initListeners();
    }

    public boolean addJobsListener(JobsListener jobsListener) {
        return mJobsListeners.add(jobsListener);
    }

    public Job getSelectedJob() {
        return (Job) list.getSelectedValue();
    }

    @Override
    public void save() {
        try {
            mManager.getServerCommander().setJobs(getModel());
        } catch (RemoteException ex) {
            Logger.getLogger(JobsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addButtonActionPerformed(ActionEvent evt) {
        edit(null);
    }

    private void edit(Job job) {
        String title;
        boolean add = job == null;
        if (job == null) {
            job = new Job();
            title = Dict.ADD.getString();
        } else {
            title = Dict.EDIT.getString();
        }

        JobPanel jobPanel = new JobPanel();
        jobPanel.setJob(job);
        SwingHelper.makeWindowResizable(jobPanel);

        int retval = JOptionPane.showOptionDialog(mRoot,
                jobPanel,
                title,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                null);

        if (retval == JOptionPane.OK_OPTION) {
            Job modifiedJob = jobPanel.getJob();
            if (modifiedJob.isValid()) {
                if (add) {
                    getModel().addElement(modifiedJob);
                } else {
                    getModel().set(getModel().indexOf(getSelectedJob()), modifiedJob);
                }
                sortModel();
                list.setSelectedValue(modifiedJob, true);
            } else {
                showInvalidJobDialog();
                edit(modifiedJob);
            }
        }
    }

    private void editButtonActionPerformed(ActionEvent evt) {
        if (getSelectedJob() != null) {
            edit(getSelectedJob());
        }
    }

    private void init() {
        mRoot = SwingUtilities.getRoot(this);
        label.setText(Dict.JOB.getString());

        addButton.setVisible(true);
        editButton.setVisible(true);
        removeButton.setVisible(true);
        removeAllButton.setVisible(true);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        try {
            setModel(mManager.getServerCommander().populateJobModel(getModel()));
        } catch (RemoteException ex) {
            Logger.getLogger(JobsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        list.setSelectedIndex(0);
    }

    private void initListeners() {
        addButton.addActionListener(this::addButtonActionPerformed);
        editButton.addActionListener(this::editButtonActionPerformed);
        removeButton.addActionListener(this::removeButtonActionPerformed);
        removeAllButton.addActionListener(this::removeAllButtonActionPerformed);

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                listMouseClicked(evt);
            }
        });
    }

    private void listMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2) {
            editButtonActionPerformed(null);
        }
    }

    private void notifyJobListenersAdded() {
        for (JobsListener jobsListener : mJobsListeners) {
            try {
                jobsListener.onJobAdded();
            } catch (Exception e) {
            }
        }
    }

    private void notifyJobListenersRemoved() {
        for (JobsListener jobsListener : mJobsListeners) {
            try {
                jobsListener.onJobRemoved();
            } catch (Exception e) {
            }
        }
    }

    private void removeAllButtonActionPerformed(ActionEvent evt) {
        if (!getModel().isEmpty()) {
            int retval = JOptionPane.showConfirmDialog(mRoot,
                    mBundle.getString("JobsPanel.message.removeAll"),
                    mBundle.getString("JobsPanel.title.removeAll"),
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (retval == JOptionPane.OK_OPTION) {
                getModel().removeAllElements();
                notifyJobListenersRemoved();
            }
        }
    }

    private void removeButtonActionPerformed(ActionEvent evt) {
        if (getSelectedJob() != null) {
            String message = String.format(mBundle.getString("JobsPanel.message.remove"), getSelectedJob().getName());
            int retval = JOptionPane.showConfirmDialog(mRoot,
                    message,
                    mBundle.getString("JobsPanel.title.remove"),
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (retval == JOptionPane.OK_OPTION) {
                getModel().removeElement(getSelectedJob());
                notifyJobListenersRemoved();
            }
        }
    }

    private void showInvalidJobDialog() {
        Message.error(mRoot, Dict.INVALID_INPUT.getString(), mBundle.getString("JobsPanel.invalid"));
    }

    public interface JobsListener {

        public void onJobAdded();

        public void onJobRemoved();
    }
}