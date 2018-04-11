package paymentinterfaces.actions.custom;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

import com.mendix.core.Core;
import com.mendix.core.CoreException;
import com.mendix.externalinterface.connector.RequestHandler;
import com.mendix.m2ee.api.IMxRuntimeRequest;
import com.mendix.m2ee.api.IMxRuntimeResponse;
import com.mendix.systemwideinterfaces.core.IContext;

/**
 * Class handles the callback from the payment provider as the requesthandler
 * 
 * @Author: Erwin 't Hoen
 * @version: 1.0
 * @since: 2014-10-02
 * @modified: Tim Hendricks
 */
public class PaymentCallbackRH_BE extends RequestHandler{
	@SuppressWarnings("unused")
	private String contextPath;

	public PaymentCallbackRH_BE(String contextPath) {
		this.contextPath = contextPath;
	}
	/**
	 * This method will start processing the incoming callback request from the payment provider
	 *
	 */
	@Override
	protected void processRequest(IMxRuntimeRequest request,IMxRuntimeResponse response, String path) throws Exception {
		HttpServletRequest servletRequest =  request.getHttpServletRequest();
		Core.getLogger("PaymentCallbackBackend").trace("Received process request event");
		try {
			Core.getLogger("PaymentCallbackBackend").debug("Request URI: "+ servletRequest.getRequestURI());
			paymentCallbackService(request, response);
		} catch (Exception ex) {
			Core.getLogger("PaymentCallbackBackend").error("Exception occurred while processing request "+ex);
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
			Core.getLogger("PaymentCallbackBackend").info("Processing PaymentCallback Backend. TransId: null");
		}
		else
		{
			Core.getLogger("PaymentCallbackBackend").info("Processing PaymentCallback Backend. TransId: "  + TransId);
		}
		 
		Core.getLogger("PaymentCallbackBackend").debug("Create HashMap"); 
		Map<String, Object> params = new HashMap<String, Object>();
		Core.getLogger("PaymentCallbackBackend").debug("Define action name");
		String actionName = "PaymentInterfaces.ProcessPaymentResponse"; 
		Core.getLogger("PaymentCallbackBackend").debug("Put params in hashmap");
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
		params.put("ResponseType", "BE");
		params.put("IsMobile", "0");
		
		
		IContext context = null; 
		
		context = Core.createSystemContext();
		Core.execute(context, actionName, params);
		Core.getLogger("PaymentCallbackBackend").trace("Exec MF with new system context");
		
		Core.getLogger("PaymentCallbackBackend").trace("Redirect to be_response.html");
		servletResponse.sendRedirect("/be_response.html");
		return;

	}
}
