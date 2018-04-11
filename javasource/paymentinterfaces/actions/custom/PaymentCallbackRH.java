package paymentinterfaces.actions.custom;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.mendix.core.Core;
import com.mendix.core.CoreException;
import com.mendix.externalinterface.connector.RequestHandler;
import com.mendix.m2ee.api.IMxRuntimeRequest;
import com.mendix.m2ee.api.IMxRuntimeResponse;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.ISession;

/**
 * Class handles the callback from the payment provider as the requesthandler
 * 
 * @Author: Erwin 't Hoen
 * @version: 1.0
 * @since: 2014-10-02
 * @modified: Tim Hendricks
 */
public class PaymentCallbackRH extends RequestHandler{
	@SuppressWarnings("unused")
	private String contextPath;
    private static final String XAS_SESSION_ID = "XASSESSIONID";
    private static final String XAS_ID = "XASID";
	
	public static final int SECONDS_PER_YEAR = 60 * 60 * 24 * 365;

    private static final String OriginURI = "originURI";
    private static final String OriginURIValue = "index.html";

	public PaymentCallbackRH(String contextPath) {
		this.contextPath = contextPath;
	}
	/**
	 * This method will start processing the incoming callback request from the payment provider
	 *
	 */
	@Override
	protected void processRequest(IMxRuntimeRequest request,IMxRuntimeResponse response, String path) throws Exception {
		HttpServletRequest servletRequest =  request.getHttpServletRequest();
		Core.getLogger("PaymentCallback").trace("Received process request event");
		try {
			Core.getLogger("PaymentCallback").debug("Request URI: "+ servletRequest.getRequestURI());
			paymentCallbackService(request, response);
		} catch (Exception ex) {
			Core.getLogger("PaymentCallback").error("Exception occurred while processing request "+ex);
			response.sendError("Exception occurred while processing request");
		}
	}
	/**
	 * This method will process the incoming request
	 */
	private void paymentCallbackService(IMxRuntimeRequest request, IMxRuntimeResponse response) throws ServletException, IOException, CoreException {
		HttpServletResponse servletResponse =  response.getHttpServletResponse();
		HttpServletRequest servletRequest =  request.getHttpServletRequest();
		if (request.getParameter("error") != null) {
			throw new ServletException("Error found");
		}
		 
		 String MerchantCode = request.getParameter("MerchantCode");
		 String PaymentId = request.getParameter("PaymentId");
		 String RefNo = request.getParameter("RefNo");
		 String Amount = request.getParameter("Amount");
		 String Currency = request.getParameter("Currency");
		 String Remark = request.getParameter("Remark");
		 String TransId = request.getParameter("TransId");
		 String AuthCode = request.getParameter("AuthCode");
		 String Status = request.getParameter("Status");
		 String ErrDesc = request.getParameter("ErrDesc");
		 String Signature = request.getParameter("Signature");
		
		if(StringUtils.isEmpty(TransId))
		{
			Core.getLogger("PaymentCallback").info("Processing PaymentCallback. TransId: null");
		}
		else
		{
			Core.getLogger("PaymentCallback").info("Processing PaymentCallback. TransId: "  + TransId);
		}
		
		Core.getLogger("PaymentCallback").debug("Create HashMap"); 
		Map<String, Object> params = new HashMap<String, Object>();
		Core.getLogger("PaymentCallback").debug("Define action name");
		String actionName = "PaymentInterfaces.ProcessPaymentResponse"; 
		Core.getLogger("PaymentCallback").debug("Put params in hashmap");
		params.put("MerchantCode", MerchantCode);
		params.put("PaymentId", PaymentId);
		params.put("RefNo", RefNo);
		params.put("Amount", Amount);
		params.put("Currency", Currency);
		params.put("Remark", Remark);
		params.put("TransId", TransId);
		params.put("AuthCode", AuthCode);
		params.put("Status", Status);
		params.put("ErrDesc", ErrDesc);
		params.put("Signature", Signature);
		params.put("ResponseType", "PR");
		params.put("IsMobile", "0");
		
		
		Core.getLogger("PaymentCallback").trace("getting xax cookie");
		String cookie = request.getCookie(XAS_SESSION_ID);
		if (cookie == null || cookie.isEmpty()) {
			Core.getLogger("PaymentCallback").trace("cookie not found");
			cookie = null;
		}
		else
		{
			Core.getLogger("PaymentCallback").trace("cookie found");
		}
		Core.getLogger("PaymentCallback").trace("getting session");
		ISession currentSession = null;
		for (ISession activeSession : Core.getActiveSessions()) {
			if (activeSession.getId().toString().equals(cookie)) {
				currentSession = activeSession;
				Core.getLogger("PaymentCallback").trace("found session: " + activeSession.getId().toString());
				break;
			}
		}
		
		Core.getLogger("PaymentCallback").trace("creating session context");
		
		IContext context = null; 
		
		if(currentSession != null)
		{
			context = currentSession.createContext(); 
			response.addCookie(XAS_SESSION_ID, currentSession.getId().toString(), "/", "", -1);
			response.addCookie(XAS_ID, "0." + Core.getXASId(), "/", "", -1);
			response.addCookie(OriginURI, OriginURIValue, "/", "", SECONDS_PER_YEAR);	
		}

		
		Core.getLogger("PaymentCallback").trace("Execute MF");
		if(context != null)
		{
			Core.getLogger("PaymentCallback").trace("Execute MF in session context: " + actionName);
			Core.execute(context, actionName, params);
		}
		else
		{
			context = Core.createSystemContext();
			Core.execute(context, actionName, params);
			Core.getLogger("PaymentCallback").trace("Exec MF with new system context");
		}
		Core.getLogger("PaymentCallback").trace("Redirect to index.html");
		servletResponse.sendRedirect("/index.html");
		return;

	}
}
