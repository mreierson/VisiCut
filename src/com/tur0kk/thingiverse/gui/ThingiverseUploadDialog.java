/**
 * This file is part of VisiCut.
 * Copyright (C) 2011 - 2013 Thomas Oster <thomas.oster@rwth-aachen.de>
 * RWTH Aachen University - 52062 Aachen, Germany
 *
 *     VisiCut is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     VisiCut is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with VisiCut.  If not, see <http://www.gnu.org/licenses/>.
 **/
package com.tur0kk.thingiverse.gui;

import com.t_oster.visicut.gui.MainView;
import com.tur0kk.TakePhotoThread;
import com.tur0kk.thingiverse.model.Thing;
import com.frochr123.pluginicon.PluginIconLoader;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;

/**
 * This class handles the upload of a thing as I made one. It provides UI to take a picture and add a description. Then both are published to thingiverse.
 * @author Sven
 */
public class ThingiverseUploadDialog extends javax.swing.JDialog
{
  private Thread livecamThread; // thread for live preview of the cam
  private Thing thing; // thing to tag as I made one

  /** Creates new form ThingiverseUploadDialog */
  public ThingiverseUploadDialog(java.awt.Frame parent, boolean modal, Thing thing)
  {
    super(parent, modal);
    
    // auto generated code
    initComponents();
    
    this.thing = thing;
    
    // no need to keep state
    this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    
    // close camera on exit
    initWindowListener();
    
    // display thing
    // scale image to label size
    Image rawImage = this.thing.getImage().getImage();
    Image scaledImage = rawImage.getScaledInstance(
      lblThingImage.getWidth(),
      lblThingImage.getHeight(),
      Image.SCALE_SMOOTH);
    lblThingImage.setIcon(this.thing.getImage());
    lblThingName.setText(thing.getName());
    this.setTitle("I made one: " + this.thing.getName());
    
    // change cam source listener
    ItemListener selectChangeListener = new ItemListener() {

      public void itemStateChanged(ItemEvent e)
      {
        boolean selected = (e.getStateChange( ) == ItemEvent.SELECTED);
        if(selected == true){ // only if selected, otherwise fired twice, for the deselected also
          closeCamera();
          setupCamera();
        }
      
      }
    };
    rdbtnWebcam.addItemListener(selectChangeListener);
    rdbtnVisicam.addItemListener(selectChangeListener);
    
    // enable picture taking
    setupCamera();
  }


  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpCams = new javax.swing.ButtonGroup();
        pnlPhoto = new javax.swing.JPanel();
        btnPhoto = new javax.swing.JButton();
        btnPhotoRedo = new javax.swing.JButton();
        lblPhoto = new javax.swing.JLabel();
        btnPublish = new javax.swing.JButton();
        lblLoading = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();
        lblPublishSuccessStatus = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaPublish = new javax.swing.JTextArea();
        pnlSelectCamera = new javax.swing.JPanel();
        rdbtnWebcam = new javax.swing.JRadioButton();
        rdbtnVisicam = new javax.swing.JRadioButton();
        lblThingImage = new javax.swing.JLabel();
        lblThingName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(com.t_oster.visicut.gui.VisicutApp.class).getContext().getResourceMap(ThingiverseUploadDialog.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setLocationByPlatform(true);
        setName("Form"); // NOI18N
        setResizable(false);

        pnlPhoto.setBorder(javax.swing.BorderFactory.createLineBorder(resourceMap.getColor("pnlPhoto.border.lineColor"))); // NOI18N
        pnlPhoto.setName("pnlPhoto"); // NOI18N

        btnPhoto.setText(resourceMap.getString("btnPhoto.text")); // NOI18N
        btnPhoto.setName("btnPhoto"); // NOI18N
        btnPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhotoActionPerformed(evt);
            }
        });

        btnPhotoRedo.setText(resourceMap.getString("btnPhotoRedo.text")); // NOI18N
        btnPhotoRedo.setName("btnPhotoRedo"); // NOI18N
        btnPhotoRedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhotoRedoActionPerformed(evt);
            }
        });

        lblPhoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPhoto.setText(resourceMap.getString("lblPhoto.text")); // NOI18N
        lblPhoto.setBorder(javax.swing.BorderFactory.createLineBorder(resourceMap.getColor("lblPhoto.border.lineColor"))); // NOI18N
        lblPhoto.setName("lblPhoto"); // NOI18N

        btnPublish.setText(resourceMap.getString("btnPublish.text")); // NOI18N
        btnPublish.setName("btnPublish"); // NOI18N
        btnPublish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPublishActionPerformed(evt);
            }
        });

        lblLoading.setIcon(PluginIconLoader.loadIcon(PluginIconLoader.PLUGIN_LOADING_CIRCLE_SMALL));
        lblLoading.setText(resourceMap.getString("lblLoading.text")); // NOI18N
        lblLoading.setName("lblLoading"); // NOI18N

        lblDescription.setText(resourceMap.getString("lblDescription.text")); // NOI18N
        lblDescription.setName("lblDescription"); // NOI18N

        lblPublishSuccessStatus.setText(resourceMap.getString("lblPublishSuccessStatus.text")); // NOI18N
        lblPublishSuccessStatus.setName("lblPublishSuccessStatus"); // NOI18N

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(resourceMap.getColor("jScrollPane1.border.lineColor"))); // NOI18N
        jScrollPane1.setName("jScrollPane1"); // NOI18N

        txtaPublish.setColumns(20);
        txtaPublish.setRows(5);
        txtaPublish.setName("txtaPublish"); // NOI18N
        jScrollPane1.setViewportView(txtaPublish);

        javax.swing.GroupLayout pnlPhotoLayout = new javax.swing.GroupLayout(pnlPhoto);
        pnlPhoto.setLayout(pnlPhotoLayout);
        pnlPhotoLayout.setHorizontalGroup(
            pnlPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPhotoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPhotoLayout.createSequentialGroup()
                        .addComponent(btnPhoto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPhotoRedo))
                    .addComponent(lblPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlPhotoLayout.createSequentialGroup()
                        .addComponent(lblDescription)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPublishSuccessStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addComponent(lblLoading, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPublish))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlPhotoLayout.setVerticalGroup(
            pnlPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPhotoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPhoto)
                    .addComponent(btnPhotoRedo)
                    .addComponent(btnPublish)
                    .addComponent(lblLoading, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescription)
                    .addComponent(lblPublishSuccessStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(lblPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlSelectCamera.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("pnlSelectCamera.border.title"))); // NOI18N
        pnlSelectCamera.setName("pnlSelectCamera"); // NOI18N
        pnlSelectCamera.setLayout(new java.awt.GridLayout(2, 1));

        grpCams.add(rdbtnWebcam);
        rdbtnWebcam.setSelected(true);
        rdbtnWebcam.setText(resourceMap.getString("rdbtnWebcam.text")); // NOI18N
        rdbtnWebcam.setName("rdbtnWebcam"); // NOI18N
        pnlSelectCamera.add(rdbtnWebcam);

        grpCams.add(rdbtnVisicam);
        rdbtnVisicam.setText(resourceMap.getString("rdbtnVisicam.text")); // NOI18N
        rdbtnVisicam.setName("rdbtnVisicam"); // NOI18N
        pnlSelectCamera.add(rdbtnVisicam);

        lblThingImage.setText(resourceMap.getString("lblThingImage.text")); // NOI18N
        lblThingImage.setName("lblThingImage"); // NOI18N

        lblThingName.setFont(resourceMap.getFont("lblThingName.font")); // NOI18N
        lblThingName.setText(resourceMap.getString("lblThingName.text")); // NOI18N
        lblThingName.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblThingName.setName("lblThingName"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblThingImage, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblThingName, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addComponent(pnlSelectCamera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlSelectCamera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblThingImage, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblThingName, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  private void btnPublishActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnPublishActionPerformed
  {//GEN-HEADEREND:event_btnPublishActionPerformed
   
    // disable controls for publishing, enable feedback
    SwingUtilities.invokeLater(new Runnable() {
      public void run()
      {
        lblLoading.setVisible(true);
        btnPhotoRedo.setEnabled(false);
        btnPublish.setEnabled(false);
        txtaPublish.setEditable(false);
        txtaPublish.setBackground(Color.lightGray);
      }
    });
    
      // things to publish
      //ImageIcon icon = (ImageIcon)lblPhoto.getIcon();
      //final Image image = icon.getImage();    
     // final String message = txtaPublish.getText();
      
      // do publishing
      new Thread(new Runnable() {

        public void run()
        {
          // fake processing !!! to be changed later
          try
          {
            Thread.currentThread().sleep(2000);
          }
          catch (InterruptedException ex)
          {

          }
          
          // create user feedback message
          boolean success = true;
          String msg = "";
          if(success){
            msg = "Successful upload";
          }else{
            msg = "Error uploading photo";
          }
          
          // display success state
          final String message = msg;
          SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
              // enable upload dialog feedback
              lblPublishSuccessStatus.setText(message);
              lblPublishSuccessStatus.setVisible(true);
              btnPhotoRedo.setEnabled(true);
              lblLoading.setVisible(false);
              
              MainView.getInstance().getDialog().showSuccessMessage("Sucessfully published \"" + thing.getName() + "\" as I made one."); // main view feedback
              dispose(); // close on success
            }
          });
          
          
        }
      }).start();
  }//GEN-LAST:event_btnPublishActionPerformed

  private void btnPhotoRedoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnPhotoRedoActionPerformed
  {//GEN-HEADEREND:event_btnPhotoRedoActionPerformed
    setupCamera(); // set up a clean camera to try again
  }//GEN-LAST:event_btnPhotoRedoActionPerformed

  private void btnPhotoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnPhotoActionPerformed
  {//GEN-HEADEREND:event_btnPhotoActionPerformed
    closeCamera();

    // enable publishing
    btnPhoto.setEnabled(false);
    btnPhotoRedo.setEnabled(true);
    btnPublish.setEnabled(true);


  }//GEN-LAST:event_btnPhotoActionPerformed
  
  // close camera on window close
  private void initWindowListener(){
    this.addWindowListener(new WindowListener() {

        public void windowOpened(WindowEvent e)
        {

        }

        public void windowClosing(WindowEvent e)
        {
           closeCamera();
        }

        public void windowClosed(WindowEvent e)
        {

        }

        public void windowIconified(WindowEvent e)
        {

        }

        public void windowDeiconified(WindowEvent e)
        {

        }

        public void windowActivated(WindowEvent e)
        {

        }

        public void windowDeactivated(WindowEvent e)
        {

        }
      });
  }
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPhoto;
    private javax.swing.JButton btnPhotoRedo;
    private javax.swing.JButton btnPublish;
    private javax.swing.ButtonGroup grpCams;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblLoading;
    private javax.swing.JLabel lblPhoto;
    private javax.swing.JLabel lblPublishSuccessStatus;
    private javax.swing.JLabel lblThingImage;
    private javax.swing.JLabel lblThingName;
    private javax.swing.JPanel pnlPhoto;
    private javax.swing.JPanel pnlSelectCamera;
    private javax.swing.JRadioButton rdbtnVisicam;
    private javax.swing.JRadioButton rdbtnWebcam;
    private javax.swing.JTextArea txtaPublish;
    // End of variables declaration//GEN-END:variables
    
  /*
   * Camera functions
   */

  /*
     * Function starts the live preview thread depending on the selected source or displays error message.
     * This function ensures that the correct UI elements are enabled for the corresponding state (Disable publishing functionality at startup).
     */
  private void setupCamera(){
    // disable publish functions
    lblPhoto.setIcon(null);
    lblLoading.setVisible(false);
    lblPublishSuccessStatus.setVisible(false);
    btnPhotoRedo.setEnabled(false);
    btnPublish.setEnabled(false);

    // check selected cam mode and if corresponding hardware is available
    boolean start = false;
    if(rdbtnWebcam.isSelected()){
      if(TakePhotoThread.isWebCamDetected()){
        start = true;
      }
    }
    else{// visicam
      if(TakePhotoThread.isVisiCamDetected()){
        start = true;
      }
    }

    if(start){ // everything was correct, start preview thread
      // start picture taking thread to display live preview
      boolean webcamMode = rdbtnWebcam.isSelected(); // if false, then visicam
      livecamThread = new TakePhotoThread(lblPhoto, webcamMode);
      livecamThread.start();

      btnPhoto.setEnabled(true);
    }
    else{ // no correct set up found, enable error message
      // disable taking photos
      btnPhoto.setEnabled(false);
      lblPhoto.setText("Please attach webcam");
    }
  }

  // just interrupt live preview, closing camera is handled by thread itself.
  private void closeCamera(){
    if(livecamThread != null){
      livecamThread.interrupt(); // stop live stream thread
      livecamThread = null;
    }
  }

  
}
