/**
 *Project: Loki Render - A distributed job queue manager.
 *Version 0.7.2
 *Copyright (C) 2014 Daniel Petersen
 *Created on Aug 8, 2009, 8:09:39 PM
 */
/**
 *This program is free software: you can redistribute it and/or modify
 *it under the terms of the GNU General Public License as published by
 *the Free Software Foundation, either version 3 of the License, or
 *(at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.whn.loki.master;

import java.util.logging.Logger;
import net.whn.loki.brokersModel.BrokersModel;
import net.whn.loki.common.ProgressUpdate;
import net.whn.loki.common.ICommon;
import net.whn.loki.messaging.RemoveJobsMsg;
import net.whn.loki.messaging.AddJobMsg;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import net.whn.loki.common.AboutForm;
import net.whn.loki.common.Config;
import net.whn.loki.common.GruntDetails;
import net.whn.loki.common.LokiForm;
import net.whn.loki.common.Main;
import net.whn.loki.error.MasterFrozenException;
import net.whn.loki.common.PreferencesForm;
import net.whn.loki.error.ErrorHelper;
import net.whn.loki.messaging.Msg;
import net.whn.loki.messaging.ResetFailuresMsg;
import net.whn.loki.messaging.SelectedGruntMsg;

/**
 *The main UI for the MasterR role of Loki. Jobs and grunts are managed
 *here
 * @author daniel
 */
public class MasterForm extends LokiForm implements ICommon {

    /**
     * default constructor
     * @param m
     */
    public MasterForm(MasterR m) {
        manager = m;
        jobsModel = m.getJobsModel();
        brokersModel = m.getBrokersModel();
        initComponents();
        queueRunning = false;
        progressBarQueue.setStringPainted(true);
        setLocationRelativeTo(null);
        prefForm = new PreferencesForm(manager.getCfg());
        prefForm.setLocationRelativeTo(this);

        setupTables();
    }

    public Config getCfg() {
        return manager.getCfg();
    }

    /**
     * called by btnStartAction,
     * and by manager via EQCaller if all jobs are Status D (done and none
     * running)
     */
    public void stopQueue() {
        queueRunning = false;
        btnStart.setText("Start");
        btnStart.setSelected(queueRunning);
        manager.setQueueRunningFalse();
    }

    /**
     * called by manager via EventQueue when it has an update (range change, or
     * done has changed)
     */
    public void updateProgressBar(ProgressUpdate update) {
        String text = update.getDone() + "/" + update.getMax();
        progressBarQueue.setMaximum(update.getMax());
        progressBarQueue.setValue(update.getDone());
        progressBarQueue.setString(text);
    }

    public void updateCores(int cores) {
        lblCores.setText("cores: " + Integer.toString(cores));
    }

    /**
     * called by manager
     * @param details
     */
    public void viewGruntDetails(GruntDetails details) {
        GruntDetailsForm gdForm = new GruntDetailsForm(details);
        gdForm.setLocationRelativeTo(this);
        gdForm.setVisible(true);
    }

    public void viewJobDetails(Job job) {
        JobDetailsForm jdForm = new JobDetailsForm(job);
        jdForm.setLocationRelativeTo(this);
        jdForm.setVisible(true);
    }

    /*BEGIN PACKAGE*/
    /**
     * passes the newly created job to the MasterR
     * called by addJob in AddJobForm
     * @param j
     */
    void addJob(JobFormInput j) {
        /*
         Aqui eu atualizo a mensagem do master para adicionar um job na fila
        */
        try {
            AddingJobForm ajForm = new AddingJobForm();//mostra a telinha marota =)
            ajForm.setLocationRelativeTo(this);
            ajForm.setVisible(true);
            manager.deliverMessage(new AddJobMsg(j, ajForm));
        } catch (InterruptedException IntEx) {
            log.severe("interrupted exception: " + IntEx.toString());
        } catch (MasterFrozenException mfe) {
            ErrorHelper.outputToLogMsgAndKill(this, false, log,
                    "fatal error. click ok to exit.", mfe.getCause());
            System.exit(-1);
        }
    }

    /*BEGIN PRIVATE*/
    //logging
    private static final String className = "net.whn.loki.master.MasterForm";
    private static final Logger log = Logger.getLogger(className);
    private final MasterR manager;
    private final JobsModel jobsModel;
    private final BrokersModel brokersModel;
    private final PreferencesForm prefForm;
    private boolean queueRunning;

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pmenuJobOptions = new javax.swing.JPopupMenu();
        pmiViewJob = new javax.swing.JMenuItem();
        pmiRemoveJob = new javax.swing.JMenuItem();
        pmiResetFailures = new javax.swing.JMenuItem();
        pmiAbortAll = new javax.swing.JMenuItem();
        pmenuGruntOptions = new javax.swing.JPopupMenu();
        pmiViewGrunt = new javax.swing.JMenuItem();
        pmiQuitGrunt = new javax.swing.JMenuItem();
        pmiQuitAllGrunts = new javax.swing.JMenuItem();
        pmenuNewJob = new javax.swing.JPopupMenu();
        pmiNewJob = new javax.swing.JMenuItem();
        scrollJobs = new javax.swing.JScrollPane();
        jobsTable = new javax.swing.JTable();
        scrollGrunts = new javax.swing.JScrollPane();
        gruntsTable = new javax.swing.JTable();
        progressBarQueue = new javax.swing.JProgressBar();
        lblCores = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnStart = new javax.swing.JToggleButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        miPreferences = new javax.swing.JMenuItem();
        miQuit = new javax.swing.JMenuItem();
        jobsMenu = new javax.swing.JMenu();
        miNewJob = new javax.swing.JMenuItem();
        miViewJob = new javax.swing.JMenuItem();
        miRemoveJob = new javax.swing.JMenuItem();
        miResetFailures = new javax.swing.JMenuItem();
        miAbortJob = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        miViewGrunt = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        miAbout = new javax.swing.JMenuItem();

        pmiViewJob.setText("View details");
        pmiViewJob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pmiViewJobActionPerformed(evt);
            }
        });
        pmenuJobOptions.add(pmiViewJob);

        pmiRemoveJob.setText("Remove");
        pmiRemoveJob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pmiRemoveJobActionPerformed(evt);
            }
        });
        pmenuJobOptions.add(pmiRemoveJob);

        pmiResetFailures.setText("Reset failed tasks");
        pmiResetFailures.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pmiResetFailuresActionPerformed(evt);
            }
        });
        pmenuJobOptions.add(pmiResetFailures);

        pmiAbortAll.setText("Abort all and stop queue");
        pmiAbortAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pmiAbortAllActionPerformed(evt);
            }
        });
        pmenuJobOptions.add(pmiAbortAll);

        pmiViewGrunt.setText("View details");
        pmiViewGrunt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pmiViewGruntActionPerformed(evt);
            }
        });
        pmenuGruntOptions.add(pmiViewGrunt);

        pmiQuitGrunt.setText("Quit after task (quit now if idle)");
        pmiQuitGrunt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pmiQuitGruntActionPerformed(evt);
            }
        });
        pmenuGruntOptions.add(pmiQuitGrunt);

        pmiQuitAllGrunts.setText("Quit all grunts after tasks (quit now if idle)");
        pmiQuitAllGrunts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pmiQuitAllGruntsActionPerformed(evt);
            }
        });
        pmenuGruntOptions.add(pmiQuitAllGrunts);

        pmiNewJob.setText("New Job");
        pmiNewJob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pmiNewJobActionPerformed(evt);
            }
        });
        pmenuNewJob.add(pmiNewJob);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Loki Render - master");
        setName("managerForm"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                windowClosingActionPerformed(evt);
            }
        });

        scrollJobs.setToolTipText("right-click for a context-sensitive popup menu");

        jobsTable.setModel(jobsModel);
        jobsTable.setToolTipText("right-click for a context-sensitive popup menu");
        scrollJobs.setViewportView(jobsTable);

        gruntsTable.setModel(brokersModel);
        gruntsTable.setToolTipText("right-click for a context-sensitive popup menu");
        scrollGrunts.setViewportView(gruntsTable);

        progressBarQueue.setToolTipText("displays the progress of finished tasks in the job queue");

        lblCores.setText("cores: 0");
        lblCores.setToolTipText("displays the total number of CPU cores currently in the farm");

        jLabel2.setText("Job Queue");

        jLabel3.setText("Grunts");

        btnStart.setText("Start");
        btnStart.setToolTipText("start and stop the job queue");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        fileMenu.setText("File");

        miPreferences.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        miPreferences.setText("Preferences   ");
        miPreferences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miPreferencesActionPerformed(evt);
            }
        });
        fileMenu.add(miPreferences);

        miQuit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        miQuit.setText("Quit");
        miQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miQuitActionPerformed(evt);
            }
        });
        fileMenu.add(miQuit);

        jMenuBar1.add(fileMenu);

        jobsMenu.setText("Jobs");

        miNewJob.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        miNewJob.setText("New");
        miNewJob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miNewJobActionPerformed(evt);
            }
        });
        jobsMenu.add(miNewJob);

        miViewJob.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_J, java.awt.event.InputEvent.CTRL_MASK));
        miViewJob.setText("View details");
        miViewJob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miViewJobActionPerformed(evt);
            }
        });
        jobsMenu.add(miViewJob);

        miRemoveJob.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        miRemoveJob.setText("Remove");
        miRemoveJob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miRemoveJobActionPerformed(evt);
            }
        });
        jobsMenu.add(miRemoveJob);

        miResetFailures.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        miResetFailures.setText("Reset failed tasks");
        miResetFailures.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miResetFailuresActionPerformed(evt);
            }
        });
        jobsMenu.add(miResetFailures);

        miAbortJob.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        miAbortJob.setText("Abort all and stop queue");
        miAbortJob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAbortJobActionPerformed(evt);
            }
        });
        jobsMenu.add(miAbortJob);

        jMenuBar1.add(jobsMenu);

        jMenu1.setText("Grunts");

        miViewGrunt.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        miViewGrunt.setText("View Details");
        miViewGrunt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miViewGruntActionPerformed(evt);
            }
        });
        jMenu1.add(miViewGrunt);

        jMenuBar1.add(jMenu1);

        helpMenu.setText("Help");

        miAbout.setText("About");
        miAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAboutActionPerformed(evt);
            }
        });
        helpMenu.add(miAbout);

        jMenuBar1.add(helpMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel2)
                            .addComponent(scrollJobs, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel3)
                            .addComponent(scrollGrunts, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblCores)
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(progressBarQueue, javax.swing.GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollGrunts, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                    .addComponent(scrollJobs, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCores)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnStart, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progressBarQueue, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * opens a new AddJobForm
     * @param evt
     */
    private void miNewJobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miNewJobActionPerformed
        newJob();
    }//GEN-LAST:event_miNewJobActionPerformed

    /**
     * sends shutdown message to the MasterR, then closes the application
     * @param evt
     */
    private void miQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miQuitActionPerformed
        shutdown();
    }//GEN-LAST:event_miQuitActionPerformed

    private void miRemoveJobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miRemoveJobActionPerformed
        removeJob();
    }//GEN-LAST:event_miRemoveJobActionPerformed

    private void windowClosingActionPerformed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_windowClosingActionPerformed
        shutdown();

    }//GEN-LAST:event_windowClosingActionPerformed

    private void miPreferencesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miPreferencesActionPerformed
        prefForm.updateCacheValues();
        prefForm.setVisible(true);
    }//GEN-LAST:event_miPreferencesActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        if (!queueRunning) { //start the queue
            queueRunning = true;
            try {/*
                 Após clicar no botão mandamos uma msg para o master iniciar
                os processos enfileirados
                */
                manager.deliverMessage(new Msg(MsgType.START_QUEUE));
                btnStart.setText("Stop");
            } catch (InterruptedException ex) {
                ErrorHelper.outputToLogMsgAndKill(this, false, log,
                        "fatal error. click ok to exit.", ex);
            } catch (MasterFrozenException mfe) {
                ErrorHelper.outputToLogMsgAndKill(this, false, log,
                        "fatal error. click ok to exit.", mfe.getCause());
                System.exit(-1);
            }
        } else {  //stop the queue
            stopQueue();
        }
        btnStart.setSelected(queueRunning);
    }//GEN-LAST:event_btnStartActionPerformed

    private void miAbortJobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAbortJobActionPerformed
        abortAllJobs();
    }//GEN-LAST:event_miAbortJobActionPerformed

    private void miViewGruntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miViewGruntActionPerformed
        viewGrunt();
    }//GEN-LAST:event_miViewGruntActionPerformed

    private void miViewJobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miViewJobActionPerformed
        viewJob();
    }//GEN-LAST:event_miViewJobActionPerformed

    private void pmiViewJobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pmiViewJobActionPerformed
        viewJob();
    }//GEN-LAST:event_pmiViewJobActionPerformed

    private void pmiRemoveJobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pmiRemoveJobActionPerformed
        removeJob();
    }//GEN-LAST:event_pmiRemoveJobActionPerformed

    private void pmiAbortAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pmiAbortAllActionPerformed
        abortAllJobs();
    }//GEN-LAST:event_pmiAbortAllActionPerformed

    private void pmiViewGruntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pmiViewGruntActionPerformed
        viewGrunt();
    }//GEN-LAST:event_pmiViewGruntActionPerformed

    private void pmiNewJobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pmiNewJobActionPerformed
        newJob();
    }//GEN-LAST:event_pmiNewJobActionPerformed

    private void miAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAboutActionPerformed
        AboutForm aboutForm = new AboutForm(manager.getCfg().getLokiVer());
        aboutForm.setLocationRelativeTo(this);
        aboutForm.setVisible(true);
    }//GEN-LAST:event_miAboutActionPerformed

    private void pmiResetFailuresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pmiResetFailuresActionPerformed
        resetFailures();
    }//GEN-LAST:event_pmiResetFailuresActionPerformed

    private void miResetFailuresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miResetFailuresActionPerformed
        resetFailures();
    }//GEN-LAST:event_miResetFailuresActionPerformed

    private void pmiQuitGruntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pmiQuitGruntActionPerformed
        quitGrunt();
    }//GEN-LAST:event_pmiQuitGruntActionPerformed

    private void pmiQuitAllGruntsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pmiQuitAllGruntsActionPerformed
        quitAllGrunts();
    }//GEN-LAST:event_pmiQuitAllGruntsActionPerformed

    private void setupColumnHeaderTooltips() {
        //TODO
    }

    private void newJob() {
        AddJobForm newJobForm = new AddJobForm(this);
        newJobForm.setLocationRelativeTo(this);
        newJobForm.setVisible(true);
    }

    private void abortAllJobs() {
        if (manager.areJobsRunning()) {
            int result = JOptionPane.showConfirmDialog(this,
                    "All running tasks will be aborted. Continue?",
                    "Abort all jobs and stop queue?",
                    JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE);
            if (result == 0) {
                sendMsg2Manager(new Msg(MsgType.ABORT_ALL));
            }
        } else {
            sendMsg2Manager(new Msg(MsgType.ABORT_ALL));
        }

    }

    private void quitGrunt() {
        int selectedGrunt = gruntsTable.getSelectedRow();
        if (selectedGrunt != -1) {
            sendMsg2Manager(new SelectedGruntMsg(
                    MsgType.QUIT_GRUNT, selectedGrunt));
        } else {
            JOptionPane.showMessageDialog(this,
                    "Please select a grunt first.");
        }
    }

    private void quitAllGrunts() {
        sendMsg2Manager(new Msg(
                MsgType.QUIT_ALL_GRUNTS));
    }

    private void viewGrunt() {
        int selectedGrunt = gruntsTable.getSelectedRow();
        if (selectedGrunt != -1) {
            sendMsg2Manager(new SelectedGruntMsg(
                    MsgType.VIEW_GRUNT, selectedGrunt));
        } else {
            JOptionPane.showMessageDialog(this,
                    "Please select a grunt first.");
        }
    }

    private void viewJob() {
        int selectedJob = jobsTable.getSelectedRow();
        if (selectedJob != -1) {
            sendMsg2Manager(new SelectedGruntMsg(MsgType.VIEW_JOB, selectedJob));
        } else {
            JOptionPane.showMessageDialog(this,
                    "Please select a job, then select 'View Job Details'.");
        }
    }

    private void resetFailures() {
        int[] rows = jobsTable.getSelectedRows();

        if (rows.length > 0) {
            sendMsg2Manager(new ResetFailuresMsg(MsgType.RESET_FAILURES, rows));
        } else {
            JOptionPane.showMessageDialog(this,
                    "Please select one or more jobs first.");
        }
    }

    private void removeJob() {
        int[] rows = jobsTable.getSelectedRows();

        if (rows.length > 0) {
            if (manager.areJobsRunning(rows)) {
                int result = JOptionPane.showConfirmDialog(this,
                        "One or more selected jobs have running tasks.\n" +
                        "The tasks will be aborted and the jobs will be\n" +
                        "removed from the queue. Continue?",
                        "Abort selected jobs and remove?",
                        JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE);
                if (result == 0) {
                    sendMsg2Manager(new RemoveJobsMsg(MsgType.REMOVE_JOBS, rows));
                }
            } else {
                sendMsg2Manager(new RemoveJobsMsg(MsgType.REMOVE_JOBS, rows));
            }

        } else {
            JOptionPane.showMessageDialog(this,
                    "Please select one or more jobs, then select 'Remove'.");
        }
    }

    private void shutdown() {
        boolean exit = false;
        if (!manager.areJobsRunning()) {
            exit = true;
        } else {
            int result = JOptionPane.showConfirmDialog(this,
                    "If you quit the master now, grunts will continue\n" +
                    "running tasks, then wait to send their output\n" +
                    "files to the master when it starts again. However,\n" +
                    "the local grunt's task progress (if a local grunt\n" +
                    "is running) will be lost. Are you sure you want to quit?",
                    "Quit the master?",
                    JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE);
            if (result == 0) {
                exit = true;
            }
        }

        if (exit) {
            prefForm.dispose();
            dispose();
            sendMsg2Manager(new Msg(MsgType.SHUTDOWN));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.exit(0);
            }
        }
    }

    /**
     * just a little helper method so we don't need try/catch everywhere
     * @param m
     */
    private void sendMsg2Manager(Msg m) {
        try {
            manager.deliverMessage(m);
        } catch (InterruptedException ex) {
            ErrorHelper.outputToLogMsgAndKill(this, false, log,
                    "fatal error. click ok to exit.", ex);
            System.exit(-1);
        } catch (MasterFrozenException mfe) {
            ErrorHelper.outputToLogMsgAndKill(this, false, log,
                    "fatal error. click ok to exit.", mfe.getCause());
            System.exit(-1);
        }
    }

    private void setupTables() {
        scrollJobs.setComponentPopupMenu(pmenuNewJob);
        jobsTable.setComponentPopupMenu(pmenuJobOptions);
        gruntsTable.setComponentPopupMenu(pmenuGruntOptions);
        gruntsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnStart;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JTable gruntsTable;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jobsMenu;
    private javax.swing.JTable jobsTable;
    private javax.swing.JLabel lblCores;
    private javax.swing.JMenuItem miAbortJob;
    private javax.swing.JMenuItem miAbout;
    private javax.swing.JMenuItem miNewJob;
    private javax.swing.JMenuItem miPreferences;
    private javax.swing.JMenuItem miQuit;
    private javax.swing.JMenuItem miRemoveJob;
    private javax.swing.JMenuItem miResetFailures;
    private javax.swing.JMenuItem miViewGrunt;
    private javax.swing.JMenuItem miViewJob;
    private javax.swing.JPopupMenu pmenuGruntOptions;
    private javax.swing.JPopupMenu pmenuJobOptions;
    private javax.swing.JPopupMenu pmenuNewJob;
    private javax.swing.JMenuItem pmiAbortAll;
    private javax.swing.JMenuItem pmiNewJob;
    private javax.swing.JMenuItem pmiQuitAllGrunts;
    private javax.swing.JMenuItem pmiQuitGrunt;
    private javax.swing.JMenuItem pmiRemoveJob;
    private javax.swing.JMenuItem pmiResetFailures;
    private javax.swing.JMenuItem pmiViewGrunt;
    private javax.swing.JMenuItem pmiViewJob;
    private javax.swing.JProgressBar progressBarQueue;
    private javax.swing.JScrollPane scrollGrunts;
    private javax.swing.JScrollPane scrollJobs;
    // End of variables declaration//GEN-END:variables
}
