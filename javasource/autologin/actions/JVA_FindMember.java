// This file was generated by Mendix Modeler.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package autologin.actions;

import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.core.Core;
import com.mendix.core.CoreException;
import com.mendix.core.CoreRuntimeException;
import com.mendix.externalinterface.connector.RequestHandler;
import com.mendix.m2ee.api.IMxRuntimeRequest;
import com.mendix.m2ee.api.IMxRuntimeResponse;
import com.mendix.systemwideinterfaces.core.ISession;
import com.mendix.systemwideinterfaces.core.IDataType;
import com.mendix.systemwideinterfaces.core.IUser;
import system.proxies.Session;
import system.proxies.User;

/**
 * This Java action is used to find if a user exists in the database given a username and password. If a user is found, the Java action will return a boolean value true, false otherwise.
 */
public class JVA_FindMember extends CustomJavaAction<java.lang.Boolean>
{
	private java.lang.String Name;
	private java.lang.String Password;

	public JVA_FindMember(IContext context, java.lang.String Name, java.lang.String Password)
	{
		super(context);
		this.Name = Name;
		this.Password = Password;
	}

	@Override
	public java.lang.Boolean executeAction() throws Exception
	{
		// BEGIN USER CODE
		try{
			ISession newSession = Core.login(this.Name, this.Password);
			return true;
		} catch (Exception ex) {
			Core.getLogger("JVA_FindMember").info("Exception occurred while processing request "+ex);
			return false;
		}
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@Override
	public java.lang.String toString()
	{
		return "JVA_FindMember";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}