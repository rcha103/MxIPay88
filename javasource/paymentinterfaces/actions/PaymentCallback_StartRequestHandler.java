// This file was generated by Mendix Modeler.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package paymentinterfaces.actions;

import com.mendix.core.Core;
import com.mendix.externalinterface.connector.RequestHandler;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import paymentinterfaces.actions.custom.PaymentCallbackRH;

public class PaymentCallback_StartRequestHandler extends CustomJavaAction<java.lang.Boolean>
{
	private java.lang.String contextpath;

	public PaymentCallback_StartRequestHandler(IContext context, java.lang.String contextpath)
	{
		super(context);
		this.contextpath = contextpath;
	}

	@Override
	public java.lang.Boolean executeAction() throws Exception
	{
		// BEGIN USER CODE
		RequestHandler requestHandler = new PaymentCallbackRH(contextpath);
		Core.addRequestHandler(contextpath, requestHandler);
		Core.getLogger("PaymentCallback").info("Registered payment request handler at context path: "+ contextpath);
		return Boolean.TRUE;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@Override
	public java.lang.String toString()
	{
		return "PaymentCallback_StartRequestHandler";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
