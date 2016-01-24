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
package se.trixon.jota.client.ui.editor.module.job;

import java.awt.Color;
import org.apache.commons.lang3.StringUtils;
import se.trixon.jota.shared.job.Job;
import se.trixon.util.GraphicsHelper;
import se.trixon.util.dictionary.Dict;
import se.trixon.util.swing.dialogs.SimpleDialog;

/**
 *
 * @author Patrik Karlsson
 */
public class JobAppearancePanel extends JobModule {

    /**
     * Creates new form JobCron
     */
    public JobAppearancePanel() {
        initComponents();
        init();
    }

    @Override
    public void loadJob(Job job) {
        if (StringUtils.isNotBlank(job.getColorBackground())) {
            previewButton.setBackground(Color.decode(job.getColorBackground()));
        }

        if (StringUtils.isNotBlank(job.getColorForeground())) {
            previewButton.setForeground(Color.decode(job.getColorForeground()));
        }
    }

    @Override
    public Job saveJob(Job job) {
        if (!previewButton.getBackground().equals(resetButton.getBackground())) {
            job.setColorBackground(GraphicsHelper.colorToString(previewButton.getBackground()));
        }

        //if (!previewButton.getForeground().equals(resetButton.getForeground())) {
        job.setColorForeground(GraphicsHelper.colorToString(previewButton.getForeground()));
        //}

        return job;
    }

    private void init() {
        mTitle = Dict.APPEARANCE.toString();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        previewButton = new javax.swing.JButton();
        colorButton = new javax.swing.JButton();
        backgroundButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();

        previewButton.setText("Lorem Ipsum"); // NOI18N

        colorButton.setText(Dict.FOREGROUND.toString());
        colorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorButtonActionPerformed(evt);
            }
        });

        backgroundButton.setText(Dict.BACKGROUND.toString());
        backgroundButton.setEnabled(false);
        backgroundButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backgroundButtonActionPerformed(evt);
            }
        });

        resetButton.setText(Dict.RESET.toString());
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(previewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(colorButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backgroundButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resetButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(previewButton)
                    .addComponent(resetButton)
                    .addComponent(backgroundButton)
                    .addComponent(colorButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void colorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorButtonActionPerformed
        SimpleDialog.setParent(this);
        previewButton.setForeground(SimpleDialog.selectColor(previewButton.getForeground()));
    }//GEN-LAST:event_colorButtonActionPerformed

    private void backgroundButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backgroundButtonActionPerformed
        SimpleDialog.setParent(this);
        previewButton.setBackground(SimpleDialog.selectColor(previewButton.getBackground()));
    }//GEN-LAST:event_backgroundButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        previewButton.setBackground(null);
        previewButton.setForeground(null);
    }//GEN-LAST:event_resetButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backgroundButton;
    private javax.swing.JButton colorButton;
    private javax.swing.JButton previewButton;
    private javax.swing.JButton resetButton;
    // End of variables declaration//GEN-END:variables
}
