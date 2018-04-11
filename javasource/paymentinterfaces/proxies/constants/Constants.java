// This file was generated by Mendix Modeler 7.13.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package paymentinterfaces.proxies.constants;

import com.mendix.core.Core;

public class Constants
{
	// These are the constants for the PaymentInterfaces module

	public static java.lang.String getIPAY_BACKENDURL()
	{
		return (java.lang.String)Core.getConfiguration().getConstantValue("PaymentInterfaces.IPAY_BACKENDURL");
	}

	public static java.lang.String getIPAY_CALLBACKURL()
	{
		return (java.lang.String)Core.getConfiguration().getConstantValue("PaymentInterfaces.IPAY_CALLBACKURL");
	}

	public static java.lang.String getIPAY_MERCHANTCODE()
	{
		return (java.lang.String)Core.getConfiguration().getConstantValue("PaymentInterfaces.IPAY_MERCHANTCODE");
	}

	public static java.lang.String getIPAY_MERCHANTCODE_MOBILE()
	{
		return (java.lang.String)Core.getConfiguration().getConstantValue("PaymentInterfaces.IPAY_MERCHANTCODE_MOBILE");
	}

	public static java.lang.String getIPAY_MERCHANTKEY()
	{
		return (java.lang.String)Core.getConfiguration().getConstantValue("PaymentInterfaces.IPAY_MERCHANTKEY");
	}

	public static java.lang.String getIPAY_MERCHANTKEY_MOBILE()
	{
		return (java.lang.String)Core.getConfiguration().getConstantValue("PaymentInterfaces.IPAY_MERCHANTKEY_MOBILE");
	}

	public static java.lang.String getIPAY_PAYMENT_URL()
	{
		return (java.lang.String)Core.getConfiguration().getConstantValue("PaymentInterfaces.IPAY_PAYMENT_URL");
	}

	public static boolean getIPAY_TESTWITH1RMPAYMENT()
	{
		return (java.lang.Boolean)Core.getConfiguration().getConstantValue("PaymentInterfaces.IPAY_TESTWITH1RMPAYMENT");
	}
}