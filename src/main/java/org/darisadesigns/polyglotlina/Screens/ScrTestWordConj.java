/*
 * Copyright (c) 2019, Draque Thompson, draquemail@gmail.com
 * All rights reserved.
 *
 * Licensed under: Creative Commons Attribution-NonCommercial 4.0 International Public License
 *  See LICENSE.TXT included with this code to read the full license agreement.

 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.darisadesigns.polyglotlina.Screens;

import java.util.Map.Entry;
import org.darisadesigns.polyglotlina.CustomControls.InfoBox;
import org.darisadesigns.polyglotlina.CustomControls.PButton;
import org.darisadesigns.polyglotlina.CustomControls.PClassCheckboxPanel;
import org.darisadesigns.polyglotlina.CustomControls.PTextField;
import org.darisadesigns.polyglotlina.CustomControls.PDialog;
import org.darisadesigns.polyglotlina.DictCore;
import org.darisadesigns.polyglotlina.Nodes.ConWord;
import org.darisadesigns.polyglotlina.Nodes.DeclensionGenRule;

/**
 *
 * @author draque
 */
public final class ScrTestWordConj extends PDialog {
    
    private final int typeId;
    private final ScrDeclensionGenSetup parent;
    private final DeclensionGenRule classes = new DeclensionGenRule();

    /**
     * Creates new form ScrTestWordConj
     * @param _core
     * @param _typeId
     * @param _parent
     */
    public ScrTestWordConj(DictCore _core, int _typeId, ScrDeclensionGenSetup _parent) {
        super(_core);
        
        typeId = _typeId;
        parent = _parent;
        
        initComponents();
        
        classes.setTypeId(_typeId);
        ((PClassCheckboxPanel)pnlClasses).setRule(classes, core);
        pnlClasses.setToolTipText("Set any classes for test word here.");
        pnlClasses.setEnabled(true);
        this.setAlwaysOnTop(true);
        this.toFront();
    }
    
    private void testWord() {
        ConWord testWord = new ConWord();
        testWord.setValue(txtTestWord.getText());
        testWord.setWordTypeId(typeId);
        testWord.setCore(core);
        for (Entry<Integer, Integer> curPair : classes.getApplicableClasses()) {
            testWord.setClassValue(curPair.getKey(), curPair.getValue());
        }
        
        String newForm;
        try {
            String curCombinedId = parent.getCurSelectedCombId();
            
            if (curCombinedId == null || curCombinedId.isEmpty()) {
                throw new Exception("No conjugation/declension form selected!");
            }
            
            newForm = core.getDeclensionManager().declineWord(testWord, curCombinedId);
        } catch (Exception e) {
            // this is based on user error. Inform, but do not log.
            InfoBox.error("Declension Test Error", e.getLocalizedMessage(), this);
            newForm = "";
        }
    
        txtTestForm.setText(newForm);
        
        String debugString = "";
        for (String debugLine : core.getDeclensionManager().getDecGenDebug()) {
            debugString += debugLine + "\n";
        }
        txtDebug.setText(debugString);
    }

    @Override
    public void updateAllValues(DictCore _core) {
        // do nothing
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlClasses = new PClassCheckboxPanel(core, core.getTypes().getNodeById(typeId), false);
        txtTestWord = new PTextField(core, false, "base test word");
        jButton1 = new PButton(nightMode, menuFontSize);
        txtTestForm = new PTextField(core, false, "generated wordform");
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDebug = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Test Conjugation/Declension");
        setMaximumSize(new java.awt.Dimension(585, 2147483647));
        setMinimumSize(new java.awt.Dimension(585, 0));

        pnlClasses.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlClassesLayout = new javax.swing.GroupLayout(pnlClasses);
        pnlClasses.setLayout(pnlClassesLayout);
        pnlClassesLayout.setHorizontalGroup(
            pnlClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlClassesLayout.setVerticalGroup(
            pnlClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        txtTestWord.setToolTipText("Type test word base form here.");

        jButton1.setText("-> Test ->");
        jButton1.setToolTipText("Test currently selected conjugation/declension transformations.");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtTestForm.setEditable(false);
        txtTestForm.setToolTipText("Conjugated/declined word form appears here.");

        txtDebug.setEditable(false);
        jScrollPane1.setViewportView(txtDebug);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTestWord, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlClasses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTestForm, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTestWord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(txtTestForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlClasses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        testWord();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlClasses;
    private javax.swing.JTextPane txtDebug;
    private javax.swing.JTextField txtTestForm;
    private javax.swing.JTextField txtTestWord;
    // End of variables declaration//GEN-END:variables
}