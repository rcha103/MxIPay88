// This file was generated by Mendix Modeler 7.13.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package paymentinterfaces.proxies.microflows;

import java.util.HashMap;
import java.util.Map;
import com.mendix.core.Core;
import com.mendix.core.CoreException;
import com.mendix.systemwideinterfaces.MendixRuntimeException;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public class Microflows
{
	// These are the microflows for the PaymentInterfaces module
	public static java.lang.String cAL_Req_RefNo(IContext context, paymentinterfaces.proxies.IPay88Transaction _iPay88Transaction)
	{
		try
		{
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			params.put("IPay88Transaction", _iPay88Transaction == null ? null : _iPay88Transaction.getMendixObject());
			return (java.lang.String)Core.execute(context, "PaymentInterfaces.CAL_Req_RefNo", params);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	/**
	 * Processes and validates the signature of all payments (mobile and web) made through the iPay88 payment provider. This microflow is called by a custom HTTP handler through Java and will be executed when a user returns from the iPay88 payment portal. For web requests this action is executed twice: Once for the standard response and once for the backend response (See iPay88 documentation). Payment will only be processed by the first response received by iPay88.
	 */
	public static void processPaymentResponse(IContext context, java.lang.String _remark, java.lang.String _transId, java.lang.String _status, java.lang.String _authCode, java.lang.String _errDesc, java.lang.String _refNo, java.lang.String _paymentId, java.lang.String _merchantCode, java.lang.String _amount, java.lang.String _currency, java.lang.String _signature, java.lang.String _responseType, java.lang.String _isMobile)
	{
		try
		{
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			params.put("Remark", _remark);
			params.put("TransId", _transId);
			params.put("Status", _status);
			params.put("AuthCode", _authCode);
			params.put("ErrDesc", _errDesc);
			params.put("RefNo", _refNo);
			params.put("PaymentId", _paymentId);
			params.put("MerchantCode", _merchantCode);
			params.put("Amount", _amount);
			params.put("Currency", _currency);
			params.put("Signature", _signature);
			params.put("ResponseType", _responseType);
			params.put("IsMobile", _isMobile);
			Core.execute(context, "PaymentInterfaces.ProcessPaymentResponse", params);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	/**
	 * Used for generating all iPay88Transaction objects before redirect to iPay88. Fills all payment data and generates secutiry signature for iPay88.
	 */
	public static paymentinterfaces.proxies.IPay88Transaction sUB_CreateIPay88Transaction(IContext context, java.lang.String _req_ProductDescription, java.lang.String _req_UserName, java.lang.String _req_UserEmail, java.lang.String _req_UserContact, java.math.BigDecimal _amount)
	{
		try
		{
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			params.put("Req_ProductDescription", _req_ProductDescription);
			params.put("Req_UserName", _req_UserName);
			params.put("Req_UserEmail", _req_UserEmail);
			params.put("Req_UserContact", _req_UserContact);
			params.put("Amount", _amount);
			IMendixObject result = (IMendixObject)Core.execute(context, "PaymentInterfaces.SUB_CreateIPay88Transaction", params);
			return result == null ? null : paymentinterfaces.proxies.IPay88Transaction.initialize(context, result);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
}