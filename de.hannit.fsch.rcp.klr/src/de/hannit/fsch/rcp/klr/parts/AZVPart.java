/**
 * 
 */
package de.hannit.fsch.rcp.klr.parts;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import de.hannit.fsch.common.AppConstants;
import de.hannit.fsch.common.CSVConstants;
import de.hannit.fsch.common.ContextLogger;
import de.hannit.fsch.rcp.klr.azv.AZVDatei;
import de.hannit.fsch.rcp.klr.constants.Topics;

/**
 * @author fsch
 *
 */
public class AZVPart
{	
@Inject @Named(AppConstants.LOGGER) private ContextLogger log;	
private TableViewerColumn column = null;
private TableViewer	tableViewer = null;
private Label infoLabel = null;

	@Inject @Optional
	public void handleEvent(@UIEventTopic(Topics.AZV_DATEN) AZVDatei azvDatei)
	{
	log.info("Empfange AZV-Datei mit " + azvDatei.getDaten().size() + " Datensätzen.", this.getClass().getName() + ".handleEvent()");	
	azvDatei.resetLineCount();	
	tableViewer.setInput(azvDatei.getDaten().values().toArray());
	infoLabel.setText(azvDatei.getPath() + " [" + (azvDatei.getDaten().size()) + " Datensätze von " + azvDatei.getDatenDistinct().size() + " Mitarbeitern]");
	}	

	@Inject	
	public AZVPart(Composite parent, @Named(CSVConstants.AZV.CONTEXT_DATEN) AZVDatei azvDatei)
	{
	parent.setLayout(new GridLayout());

	infoLabel = new Label(parent, SWT.NONE);
	infoLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
	infoLabel.setText(azvDatei.getPath() + " [" + (azvDatei.getFields().size()) + " Datensätze]");

	tableViewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
	// Make lines and make header visible
	final Table table = tableViewer.getTable();
		
	table.setHeaderVisible(true);
	table.setLinesVisible(true); 
	tableViewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));

	column = new TableViewerColumn(tableViewer, SWT.LEFT, 0);
	column.getColumn().setText("Zeile");
	column.getColumn().setWidth(50);
	column.getColumn().setResizable(true);
	column.getColumn().setMoveable(true);
	
	column = new TableViewerColumn(tableViewer, SWT.LEFT, CSVConstants.AZV.PERSONALNUMMER_INDEX_TABLE);
	column.getColumn().setText("PNR");
	column.getColumn().setWidth(200);
	column.getColumn().setResizable(true);
	column.getColumn().setMoveable(true);
	
	column = new TableViewerColumn(tableViewer, SWT.LEFT, CSVConstants.AZV.TEAM_INDEX_TABLE);
	column.getColumn().setText("Team");
	column.getColumn().setWidth(200);
	column.getColumn().setResizable(true);
	column.getColumn().setMoveable(true);
	
	column = new TableViewerColumn(tableViewer, SWT.LEFT, CSVConstants.AZV.BERICHTSMONAT_INDEX_TABLE);
	column.getColumn().setText(CSVConstants.AZV.BERICHTSMONAT_LABEL_TABLE);
	column.getColumn().setWidth(200);
	column.getColumn().setResizable(true);
	column.getColumn().setMoveable(true);
	
	column = new TableViewerColumn(tableViewer, SWT.LEFT, CSVConstants.AZV.KOSTENSTELLE_INDEX_TABLE);
	column.getColumn().setText(CSVConstants.AZV.KOSTENSTELLE_LABEL_TABLE);
	column.getColumn().setWidth(200);
	column.getColumn().setResizable(true);
	column.getColumn().setMoveable(true);	
	
	column = new TableViewerColumn(tableViewer, SWT.LEFT, CSVConstants.AZV.KOSTENTRAEGER_INDEX_TABLE);
	column.getColumn().setText(CSVConstants.AZV.KOSTENTRAEGER_LABEL_TABLE);
	column.getColumn().setWidth(600);
	column.getColumn().setResizable(true);
	column.getColumn().setMoveable(true);	
	
	column = new TableViewerColumn(tableViewer, SWT.CENTER, CSVConstants.AZV.PROZENTANTEIL_INDEX_TABLE);
	column.getColumn().setText(CSVConstants.AZV.PROZENTANTEIL_LABEL_TABLE);
	column.getColumn().setWidth(100);
	column.getColumn().setResizable(true);
	column.getColumn().setMoveable(true);		
	
	tableViewer.setContentProvider(new ArrayContentProvider());
	tableViewer.setLabelProvider(azvDatei);
		if (azvDatei != null)
		{
		azvDatei.resetLineCount();	
		tableViewer.setInput(azvDatei.getDaten().values().toArray());	
		}
	}
}
