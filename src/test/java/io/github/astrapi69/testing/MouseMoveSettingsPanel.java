/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package io.github.astrapi69.testing;

import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JLabel;

import lombok.Getter;
import io.github.astrapi69.collection.array.ArrayFactory;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.swing.base.BasePanel;
import io.github.astrapi69.swing.combobox.model.GenericComboBoxModel;
import io.github.astrapi69.swing.component.JMComboBox;
import io.github.astrapi69.swing.component.JMTextField;
import io.github.astrapi69.swing.document.NumberValuesDocument;

@Getter
public class MouseMoveSettingsPanel extends BasePanel<SettingsModelBean>
{
	private JMComboBox<Integer, GenericComboBoxModel<Integer>> cmbVariableX;
	private JMComboBox<Integer, GenericComboBoxModel<Integer>> cmbVariableY;
	private JLabel lblIntervalOfSeconds;
	private JLabel lblSettings;
	private JLabel lblVariableX;
	private JLabel lblVariableY;
	private JMTextField txtIntervalOfSeconds;
	private JMTextField txtIntervalOfMouseMovementsCheckInSeconds;

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
		Integer[] cmbArray = ArrayFactory.newArray(1, 2, 3, 4);
		cmbVariableX = new JMComboBox<>(
			new GenericComboBoxModel<>(ArrayFactory.newArray(1, 2, 3, 4)));
		cmbVariableY = new JMComboBox<>(
			new GenericComboBoxModel<>(ArrayFactory.newArray(1, 2, 3, 4)));
		txtIntervalOfSeconds = new JMTextField();
		txtIntervalOfSeconds.setDocument(new NumberValuesDocument());
		txtIntervalOfMouseMovementsCheckInSeconds = new JMTextField();
		txtIntervalOfMouseMovementsCheckInSeconds.setDocument(new NumberValuesDocument());

		lblVariableX.setText("Move mouse on X axis in pixel");

		lblSettings.setText("Settings");

		lblVariableY.setText("Move mouse on Y axis in pixel");

		lblIntervalOfSeconds.setText("Move mouse every time (in seconds)");

		cmbVariableX.setModel(new DefaultComboBoxModel<>(new Integer[] { 1, 2, 3, 4 }));
		cmbVariableX.setName("cmbVariableX");

		cmbVariableX.addActionListener(this::onChangeCmbVariableX);
		cmbVariableY.setName("cmbVariableY");
		cmbVariableY.addActionListener(this::onChangeCmbVariableY);

		txtIntervalOfSeconds.setText("60");
		txtIntervalOfSeconds.setName("txtIntervalOfSeconds");
		txtIntervalOfSeconds.addActionListener(this::onChangeTxtIntervalOfSeconds);
		txtIntervalOfSeconds.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusLost(FocusEvent event)
			{
				JMTextField source = (JMTextField)event.getSource();
				final String text = source.getText();
				getModelObject().setIntervalOfSeconds(Integer.valueOf(text));
			}
		});
	}

	protected void onChangeCmbVariableY(final ActionEvent actionEvent)
	{
		JMComboBox<Integer, GenericComboBoxModel<Integer>> source =
			(JMComboBox<Integer, GenericComboBoxModel<Integer>>)actionEvent
			.getSource();
		final Object selectedItem = source.getModel().getSelectedItem();
		getModelObject().setYAxis(Integer.valueOf(selectedItem.toString()));
	}

	protected void onChangeCmbVariableX(final ActionEvent actionEvent)
	{
		JMComboBox<Integer, GenericComboBoxModel<Integer>> source =
			(JMComboBox<Integer, GenericComboBoxModel<Integer>>)actionEvent
			.getSource();
		final Object selectedItem = source.getModel().getSelectedItem();
		getModelObject().setXAxis(Integer.valueOf(selectedItem.toString()));
	}

	protected void onChangeTxtIntervalOfSeconds(final ActionEvent actionEvent)
	{
		JMTextField source = (JMTextField)actionEvent.getSource();
		IModel<String> propertyModel = source.getPropertyModel();
		getModelObject().setIntervalOfSeconds(Integer.valueOf(propertyModel.getObject()));
	}


	protected void onInitializeGroupLayout(){
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addGroup(layout.createSequentialGroup()
										.addComponent(lblVariableX, GroupLayout.PREFERRED_SIZE, 250,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18).addComponent(cmbVariableX, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup()
										.addComponent(lblIntervalOfSeconds, GroupLayout.PREFERRED_SIZE, 250,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18).addComponent(txtIntervalOfSeconds))
								.addGroup(layout.createSequentialGroup()
										.addComponent(lblVariableY, GroupLayout.PREFERRED_SIZE, 250,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18).addComponent(cmbVariableY, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblSettings, GroupLayout.PREFERRED_SIZE, 250,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout
				.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(lblSettings)
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(lblVariableX)
										.addComponent(cmbVariableX, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(lblVariableY)
										.addComponent(cmbVariableY, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(lblIntervalOfSeconds).addComponent(txtIntervalOfSeconds,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addContainerGap(21, Short.MAX_VALUE)));
	}

	@Override
	protected void onInitializeLayout()
	{
		this.onInitializeGroupLayout();
	}

}
