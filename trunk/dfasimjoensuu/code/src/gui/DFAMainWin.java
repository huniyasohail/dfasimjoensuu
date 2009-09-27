/*
 * DFAMainWIn.java
 *
 * Created on 17.09.2009, 20:01:41
 */

package gui;

import controller.IncompleteAutomatonException;
import controller.Simulator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import models.Dfa;
import models.DfaEditor;
import models.EditorToolStates;
import models.State;
import models.Transition;

/**
 *
 * @author Fabian
 */
public class DFAMainWin extends javax.swing.JFrame {

    Simulator dfaSim = null;
    boolean simBarVisible = false;

    /** Creates new form DFAMainWIn */
    public DFAMainWin() {
        
        initComponents();
        this.setSize(new Dimension(700, 500));
        centreWindow(this);
      //  this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public Simulator getDfaSim() {
        return dfaSim;
    }

    public void setDfaSim(Simulator dfaSim) {
        this.dfaSim = dfaSim;
    }

  private void panelDrawAreaMouseClicked(java.awt.event.MouseEvent evt) {                                           
        if (evt.getClickCount() == 2)
        handleDoubleClick(evt);
    }

/**
 * connect GUI to DFA controller
 */
    public void connectGUItoDFA()
    {
        this.dfaSim.getDfaEditor().setdFAMainWin(this);
        this.dfaSim.getDfaEditor().getdFAPainter().setPaintPanel(this.panelDrawArea);
        this.dfaSim.getDfaEditor().getdFAPainter().setGraphics((Graphics2D)panelDrawArea.getGraphics());
        this.panelDrawArea.setdFAPainter(this.dfaSim.getDfaEditor().getdFAPainter());
        updateButtons();
    }

    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        togglePointer = new javax.swing.JToggleButton();
        toggleAddState = new javax.swing.JToggleButton();
        toggleAddTransition = new javax.swing.JToggleButton();
        panelContainer = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        panelTop = new javax.swing.JPanel();
        panelDrawArea = new gui.PaintPanel();
        panelConsole = new javax.swing.JPanel();
        panelConsoleTop = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        buttonNextStep = new javax.swing.JButton();
        buttonSimulateAll = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textareaInputWord = new javax.swing.JTextArea();
        buttonReset = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textareaOutput = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuitemOpen = new javax.swing.JMenuItem();
        menuitemSave = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        menuitemExit = new javax.swing.JMenuItem();
        menuDFA = new javax.swing.JMenu();
        menuitemProperties = new javax.swing.JMenuItem();
        menuSimulation = new javax.swing.JMenu();
        menuitemStartSim = new javax.swing.JMenuItem();
        menuItemStopSim = new javax.swing.JMenuItem();
        menuInfo = new javax.swing.JMenu();
        menuitemInfo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DFA Simulator");
        setBounds(new java.awt.Rectangle(0, 0, 600, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jToolBar1.setRollover(true);
        jToolBar1.setName("Tools"); // NOI18N

        togglePointer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/img/icon_pointer.png"))); // NOI18N
        togglePointer.setSelected(true);
        togglePointer.setToolTipText("Pointer");
        togglePointer.setFocusable(false);
        togglePointer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        togglePointer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        togglePointer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                togglePointerActionPerformed(evt);
            }
        });
        jToolBar1.add(togglePointer);

        toggleAddState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/img/icon_addstate.png"))); // NOI18N
        toggleAddState.setToolTipText("Add state");
        toggleAddState.setFocusable(false);
        toggleAddState.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toggleAddState.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toggleAddState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleAddStateActionPerformed(evt);
            }
        });
        jToolBar1.add(toggleAddState);
        toggleAddState.getAccessibleContext().setAccessibleDescription("Add State");

        toggleAddTransition.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/img/icon_addtransition.png"))); // NOI18N
        toggleAddTransition.setToolTipText("Add Transition");
        toggleAddTransition.setFocusable(false);
        toggleAddTransition.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toggleAddTransition.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toggleAddTransition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleAddTransitionActionPerformed(evt);
            }
        });
        jToolBar1.add(toggleAddTransition);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.NORTH);

        panelContainer.setLayout(new java.awt.BorderLayout());

        jSplitPane1.setDividerLocation(250);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        panelTop.setPreferredSize(new java.awt.Dimension(100, 600));
        panelTop.setLayout(new java.awt.BorderLayout());

        panelDrawArea.setBackground(new java.awt.Color(255, 255, 255));
        panelDrawArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelDrawAreaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelDrawAreaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panelDrawAreaMouseReleased(evt);
            }
        });
        panelDrawArea.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                panelDrawAreaComponentShown(evt);
            }
        });
        panelDrawArea.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelDrawAreaMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                panelDrawAreaMouseMoved(evt);
            }
        });
        panelDrawArea.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
                panelDrawAreaAncestorResized(evt);
            }
        });

        javax.swing.GroupLayout panelDrawAreaLayout = new javax.swing.GroupLayout(panelDrawArea);
        panelDrawArea.setLayout(panelDrawAreaLayout);
        panelDrawAreaLayout.setHorizontalGroup(
            panelDrawAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 653, Short.MAX_VALUE)
        );
        panelDrawAreaLayout.setVerticalGroup(
            panelDrawAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );

        panelTop.add(panelDrawArea, java.awt.BorderLayout.CENTER);

        jSplitPane1.setTopComponent(panelTop);

        panelConsole.setLayout(new java.awt.BorderLayout());

        panelConsoleTop.setMinimumSize(new java.awt.Dimension(10, 20));
        panelConsoleTop.setPreferredSize(new java.awt.Dimension(573, 90));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel1.setText("Simulation");

        buttonNextStep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/img/icon_play.png"))); // NOI18N
        buttonNextStep.setText("Next");
        buttonNextStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNextStepActionPerformed(evt);
            }
        });

        buttonSimulateAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/img/icon_fastfwd.png"))); // NOI18N
        buttonSimulateAll.setText("All");
        buttonSimulateAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSimulateAllActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel2.setText("Input word");

        textareaInputWord.setColumns(20);
        textareaInputWord.setRows(5);
        jScrollPane2.setViewportView(textareaInputWord);

        buttonReset.setText("Reset");
        buttonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonResetActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel3.setText("Current letter");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel4.setText("<>");

        javax.swing.GroupLayout panelConsoleTopLayout = new javax.swing.GroupLayout(panelConsoleTop);
        panelConsoleTop.setLayout(panelConsoleTopLayout);
        panelConsoleTopLayout.setHorizontalGroup(
            panelConsoleTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelConsoleTopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelConsoleTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelConsoleTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonNextStep)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSimulateAll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelConsoleTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelConsoleTopLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonReset))
                    .addComponent(jLabel3))
                .addGap(31, 31, 31))
        );
        panelConsoleTopLayout.setVerticalGroup(
            panelConsoleTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConsoleTopLayout.createSequentialGroup()
                .addGroup(panelConsoleTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelConsoleTopLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(panelConsoleTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelConsoleTopLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelConsoleTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                            .addGroup(panelConsoleTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(buttonNextStep)
                                .addComponent(buttonSimulateAll)
                                .addComponent(buttonReset)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        panelConsole.add(panelConsoleTop, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new java.awt.BorderLayout());

        textareaOutput.setColumns(20);
        textareaOutput.setEditable(false);
        textareaOutput.setRows(5);
        jScrollPane1.setViewportView(textareaOutput);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        panelConsole.add(jPanel2, java.awt.BorderLayout.CENTER);

        jSplitPane1.setRightComponent(panelConsole);

        panelContainer.add(jSplitPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelContainer, java.awt.BorderLayout.CENTER);

        jMenuBar1.setName("Tools"); // NOI18N

        menuFile.setText("File");

        menuitemOpen.setText("Open File...");
        menuitemOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemOpenActionPerformed(evt);
            }
        });
        menuFile.add(menuitemOpen);

        menuitemSave.setText("Save File...");
        menuitemSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemSaveActionPerformed(evt);
            }
        });
        menuFile.add(menuitemSave);
        menuFile.add(jSeparator1);

        menuitemExit.setText("Exit");
        menuitemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemExitActionPerformed(evt);
            }
        });
        menuFile.add(menuitemExit);

        jMenuBar1.add(menuFile);

        menuDFA.setText("DFA");

        menuitemProperties.setText("Properties");
        menuitemProperties.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemPropertiesActionPerformed(evt);
            }
        });
        menuDFA.add(menuitemProperties);

        jMenuBar1.add(menuDFA);

        menuSimulation.setText("Simulation");

        menuitemStartSim.setText("Start Simulation");
        menuitemStartSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemStartSimActionPerformed(evt);
            }
        });
        menuSimulation.add(menuitemStartSim);

        menuItemStopSim.setText("Stop Simulation");
        menuItemStopSim.setEnabled(false);
        menuItemStopSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemStopSimActionPerformed(evt);
            }
        });
        menuSimulation.add(menuItemStopSim);

        jMenuBar1.add(menuSimulation);

        menuInfo.setText("Info");
        menuInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuInfoActionPerformed(evt);
            }
        });

        menuitemInfo.setText("About DFA Simulator");
        menuitemInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemInfoActionPerformed(evt);
            }
        });
        menuInfo.add(menuitemInfo);

        jMenuBar1.add(menuInfo);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuInfoActionPerformed

    }//GEN-LAST:event_menuInfoActionPerformed

    private void menuitemInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemInfoActionPerformed
        //-- create and show new Info window --
        DFAInfoWin infowin = new DFAInfoWin();
        infowin.setVisible(true);
    }//GEN-LAST:event_menuitemInfoActionPerformed

    private void menuitemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemExitActionPerformed
       setVisible(false);
       dispose();
    }//GEN-LAST:event_menuitemExitActionPerformed

    private void menuitemOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemOpenActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("DFA Simulator Files", "dfa"));
        int retVal = fc.showOpenDialog(this);
        Dfa loaded_dfa = null;
        if(retVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();

            try {
                ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
                loaded_dfa = (Dfa) in.readObject();
                in.close();
            } catch (IOException ex) {
                String msg = "File not found!";
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error: File not found", JOptionPane.WARNING_MESSAGE);
            } catch (ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        }
        this.dfaSim.getDfaEditor().resetEditor();
        dfaSim.setDfa(loaded_dfa);

        connectGUItoDFA();
        panelDrawArea.repaint();
        
    }//GEN-LAST:event_menuitemOpenActionPerformed

    private void toggleAddTransitionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleAddTransitionActionPerformed
        getDfaSim().getDfaEditor().setToolState(EditorToolStates.addTransition);
        dfaSim.getDfaEditor().removeAllSelections();
        updateButtons();
        repaint();
    }//GEN-LAST:event_toggleAddTransitionActionPerformed

    private void togglePointerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_togglePointerActionPerformed
        getDfaSim().getDfaEditor().setToolState(EditorToolStates.handTool);
        dfaSim.getDfaEditor().removeAllSelections();
        updateButtons();
        repaint();
    }//GEN-LAST:event_togglePointerActionPerformed

    private void toggleAddStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleAddStateActionPerformed
        getDfaSim().getDfaEditor().setToolState(EditorToolStates.addState);
        dfaSim.getDfaEditor().removeAllSelections();
        updateButtons();
        repaint();
    }//GEN-LAST:event_toggleAddStateActionPerformed

    private void menuitemPropertiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemPropertiesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuitemPropertiesActionPerformed

    private void buttonSimulateAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSimulateAllActionPerformed
        while(dfaSim.getIsRunning()) {
            doNextStep();
        }
    }//GEN-LAST:event_buttonSimulateAllActionPerformed

    private void buttonNextStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNextStepActionPerformed
       doNextStep();
       panelDrawArea.repaint();
    }//GEN-LAST:event_buttonNextStepActionPerformed

    private void doNextStep() {
        String inputWord = textareaInputWord.getText();
        Dfa dfa = dfaSim.getDfa();
        State activeState = dfa.getCurrentState();
        State nextState;
        if (!dfaSim.getIsRunning()) {
            try {
               dfaSim.startSimulation(inputWord);
               textareaInputWord.setEditable(false);
            } catch (IncompleteAutomatonException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Cannot start simulation" , JOptionPane.WARNING_MESSAGE);
            }
        }
        int pos = dfa.getCurrentPosition();
        dfaSim.nextStep();
        nextState = dfa.getCurrentState();
        highlightCurrentPosition(pos);
        outputInfo(activeState, nextState, pos);
        if(!dfaSim.getIsRunning()) {
            //All input has been read
            buttonNextStep.setEnabled(false);
            buttonSimulateAll.setEnabled(false);
            String acceptMsg = "The DFA ";
            if(dfaSim.isAccepting()) {
                acceptMsg += "ACCEPTS the input word!";
            } else {
                acceptMsg += "does NOT accept the input word!";
            }
            textareaOutput.setText(textareaOutput.getText()+acceptMsg);
        }
    }
    private void outputInfo(State fromState, State toState, int pos) {
        String input = textareaInputWord.getText().substring(pos, pos+1);
        String msg = "Reading input '"+input+"' and taking Transition from State ";
        msg += fromState.getState_Properties().getName()+" to State ";
        msg += toState.getState_Properties().getName()+".\n";
        textareaOutput.setText(textareaOutput.getText()+msg);
    }
    private void highlightCurrentPosition(int pos) {
        textareaInputWord.requestFocus();
        textareaInputWord.setSelectionColor(Color.red);
        textareaInputWord.setSelectionStart(pos);
        textareaInputWord.setSelectionEnd(pos+1);
    }
    private void buttonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonResetActionPerformed
        textareaInputWord.setEditable(true);
        textareaOutput.setText("");
        buttonNextStep.setEnabled(true);
        buttonSimulateAll.setEnabled(true);
        dfaSim.resetDfa();
    }//GEN-LAST:event_buttonResetActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
       connectGUItoDFA();
       repaint();
    }//GEN-LAST:event_formWindowOpened

    private void panelDrawAreaAncestorResized(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_panelDrawAreaAncestorResized

    }//GEN-LAST:event_panelDrawAreaAncestorResized

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
       
    }//GEN-LAST:event_formWindowActivated

    private void panelDrawAreaComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelDrawAreaComponentShown

    }//GEN-LAST:event_panelDrawAreaComponentShown

    private void panelDrawAreaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelDrawAreaMousePressed
        getDfaSim().getDfaEditor().handleMousePressed(evt);

    }//GEN-LAST:event_panelDrawAreaMousePressed

    private void panelDrawAreaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelDrawAreaMouseReleased
        getDfaSim().getDfaEditor().handleMouseReleased(evt);
    }//GEN-LAST:event_panelDrawAreaMouseReleased

    private void panelDrawAreaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelDrawAreaMouseMoved
        getDfaSim().getDfaEditor().handleMouseMoved(evt);

    }//GEN-LAST:event_panelDrawAreaMouseMoved

    private void panelDrawAreaMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelDrawAreaMouseDragged
        getDfaSim().getDfaEditor().handleMouseDragged(evt);
    }//GEN-LAST:event_panelDrawAreaMouseDragged

    private void menuitemStartSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemStartSimActionPerformed
        this.simBarVisible = true;
        panelConsole.setVisible(true);
        jSplitPane1.setDividerLocation(0.65);
        menuItemStopSim.setEnabled(true);
        menuitemStartSim.setEnabled(false);
        updateButtons();
    }//GEN-LAST:event_menuitemStartSimActionPerformed

    private void menuitemSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemSaveActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("DFA Simulator Files", "dfa"));
        int retVal = fc.showSaveDialog(this);
        if (retVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String fpath = file.getPath();

            if (!fpath.toLowerCase().endsWith(".dfa")) {
                String newName = fpath + ".dfa";
                file = new File(newName);
            }
            if (file.exists()) {
                String msg = "The file you have chosen already exists! Do you want to overwrite this file?";
                retVal = JOptionPane.showConfirmDialog(this, msg, "Overwrite?", JOptionPane.YES_NO_CANCEL_OPTION);
            } else {
                retVal = JOptionPane.YES_OPTION;
            }
            switch (retVal) {
                case JOptionPane.YES_OPTION:
                    try {
                        ObjectOutputStream out = null;
                        out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
                        out.writeObject(this.dfaSim.getDfa());
                        out.close();
                    } catch (FileNotFoundException ex) {
                        //TODO
                        String msg = "File not found!";
                        JOptionPane.showMessageDialog(this, msg, "Error: File not found", JOptionPane.WARNING_MESSAGE);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
                    }
                    break;
                case JOptionPane.NO_OPTION:
                    menuitemSaveActionPerformed(evt);
                    break;
                default:
                    //do nothing
            }
        }
    }//GEN-LAST:event_menuitemSaveActionPerformed

    private void menuItemStopSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemStopSimActionPerformed
        this.simBarVisible = false;
        panelConsole.setVisible(false);
        menuItemStopSim.setEnabled(false);
        menuitemStartSim.setEnabled(true);
        updateButtons();
    }//GEN-LAST:event_menuItemStopSimActionPerformed


    private void handleDoubleClick(java.awt.event.MouseEvent evt)
    {
        getDfaSim().getDfaEditor().handleDoubleClick(evt);
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DFAMainWin().setVisible(true);
                
            }
        });
    }

public static void centreWindow(JFrame frame) {
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
    frame.setLocation(x, y);
}


public void updateButtons()
{
    panelConsole.setVisible(simBarVisible);
    updateToolButtons();
}

/**
 * update the UIButtons for the tools
 */
public void updateToolButtons()
{
    DfaEditor e = this.dfaSim.getDfaEditor();
    
    togglePointer.setSelected(e.getToolState() == EditorToolStates.handTool);
    toggleAddState.setSelected(e.getToolState() == EditorToolStates.addState);
    toggleAddTransition.setSelected(e.getToolState() == EditorToolStates.addTransition);
    
    togglePointer.setEnabled(e.isIsEditable());
    toggleAddState.setEnabled(e.isIsEditable());
    toggleAddTransition.setEnabled(e.isIsEditable());
}

public void showStateEditWin(State s)
{
        DFAStatePropertiesWin stprowin = new DFAStatePropertiesWin();
        stprowin.setAlwaysOnTop(true);
        stprowin.setState(s);
        stprowin.setdFAMainWin(this);
        stprowin.setVisible(true);
}

public void showTransEditWin(Transition t)
{
        DFATransitionWin trwin = new DFATransitionWin();
        trwin.setAlwaysOnTop(true);
        trwin.setTransition(t);
        trwin.setdFAMainWin(this);
        trwin.setVisible(true);
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonNextStep;
    private javax.swing.JButton buttonReset;
    private javax.swing.JButton buttonSimulateAll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenu menuDFA;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuInfo;
    private javax.swing.JMenuItem menuItemStopSim;
    private javax.swing.JMenu menuSimulation;
    private javax.swing.JMenuItem menuitemExit;
    private javax.swing.JMenuItem menuitemInfo;
    private javax.swing.JMenuItem menuitemOpen;
    private javax.swing.JMenuItem menuitemProperties;
    private javax.swing.JMenuItem menuitemSave;
    private javax.swing.JMenuItem menuitemStartSim;
    private javax.swing.JPanel panelConsole;
    private javax.swing.JPanel panelConsoleTop;
    private javax.swing.JPanel panelContainer;
    private gui.PaintPanel panelDrawArea;
    private javax.swing.JPanel panelTop;
    private javax.swing.JTextArea textareaInputWord;
    private javax.swing.JTextArea textareaOutput;
    private javax.swing.JToggleButton toggleAddState;
    private javax.swing.JToggleButton toggleAddTransition;
    private javax.swing.JToggleButton togglePointer;
    // End of variables declaration//GEN-END:variables

}
