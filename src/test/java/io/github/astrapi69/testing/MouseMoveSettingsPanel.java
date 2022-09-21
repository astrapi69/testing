/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package io.github.astrapi69.testing;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import io.github.astrapi69.swing.document.NumberValuesDocument;

import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.swing.base.BasePanel;
import lombok.Getter;

@Getter
public class MouseMoveSettingsPanel extends BasePanel<SettingsModelBean>
{
    private JComboBox<Integer> cmbVariableX;
    private JComboBox<Integer> cmbVariableY;
    private JLabel lblIntervalOfSeconds;
    private JLabel lblSettings;
    private JLabel lblVariableX;
    private JLabel lblVariableY;
    private JTextField txtIntervalOfSeconds;

	public MouseMoveSettingsPanel()
    {
        this(BaseModel.of(SettingsModelBean.builder().build()));
    }

    public MouseMoveSettingsPanel(final IModel<SettingsModelBean> model)
    {
        super(model);
    }

    @Override
    protected void onInitializeComponents()
    {
        lblVariableX = new JLabel();
        lblSettings = new JLabel();
        lblVariableY = new JLabel();
        lblIntervalOfSeconds = new JLabel();
        cmbVariableX = new JComboBox<>();
        cmbVariableY = new JComboBox<>();
        txtIntervalOfSeconds = new JTextField();
        txtIntervalOfSeconds.setDocument(new NumberValuesDocument());


        lblVariableX.setText("Move mouse on X axis in pixel");

        lblSettings.setText("Settings");

        lblVariableY.setText("Move mouse on Y axis in pixel");

        lblIntervalOfSeconds.setText("Move mouse every time (in seconds)");

        cmbVariableX.setModel(new DefaultComboBoxModel<>(
                new Integer[] { 1, 2, 3, 4 }));
        cmbVariableX.setName("cmbVariableX");

        cmbVariableY.setModel(new DefaultComboBoxModel<>(
                new Integer[] { 1, 2, 3, 4 }));
        cmbVariableY.setName("cmbVariableY");

        txtIntervalOfSeconds.setText("60");
        txtIntervalOfSeconds.setName("txtIntervalOfSeconds");
    }

    @Override
    protected void onInitializeLayout()
    {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout
                .setHorizontalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup().addContainerGap()
                                        .addGroup(layout
                                                .createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(lblVariableX, GroupLayout.PREFERRED_SIZE,
                                                                250, GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18).addComponent(
                                                                cmbVariableX, GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(lblIntervalOfSeconds,
                                                                GroupLayout.PREFERRED_SIZE, 250,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18).addComponent(txtIntervalOfSeconds))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(lblVariableY, GroupLayout.PREFERRED_SIZE,
                                                                250, GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18).addComponent(cmbVariableY,
                                                                GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.PREFERRED_SIZE))
                                                .addComponent(lblSettings, GroupLayout.PREFERRED_SIZE, 250,
                                                        GroupLayout.PREFERRED_SIZE))
                                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout
                .setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup().addContainerGap().addComponent(lblSettings)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblVariableX).addComponent(cmbVariableX,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblVariableY).addComponent(cmbVariableY,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblIntervalOfSeconds).addComponent(txtIntervalOfSeconds,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(21, Short.MAX_VALUE)));
    }

}
