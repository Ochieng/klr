/**
 * 
 */
package de.hannit.fsch.common;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

/**
 * @author fsch
 * @since 11.07.2013
 * @see UIEvents
 * 
 * Beispiel f�r die Implementierung des LifeCycleManagers
 * Die Klasse UIEvents definiert sehr viele Topics, die genutzt werden k�nnen.
 *
 */
@SuppressWarnings("restriction")
public class LifeCycleManager 
{
	@PostContextCreate
	public void postContextCreate(final IEventBroker broker) 
	{
	broker.subscribe(UIEvents.Context.CONTEXT, new EventHandler()
	{
		
		@Override
		public void handleEvent(Event event)
		{
		}
	});	

	}	

	/**
	 * 
	 */
	public LifeCycleManager() 
	{
		
		// TODO Auto-generated constructor stub
	}
	

}
