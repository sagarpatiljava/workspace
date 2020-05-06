package com.rakuten.controller;

import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.dto.AuthClientRequest;
import com.rakuten.dto.AuthClientResponse;
import com.rakuten.dto.GetBoundProfilePackageRequest;
import com.rakuten.dto.GetBoundProfilePackageResponse;
import com.rakuten.dto.InitAuthRequest;
import com.rakuten.dto.InitAuthResponse;
import com.rakuten.service.AuthClient;
import com.rakuten.service.GetBoundProfilePackage;
import com.rakuten.service.InitiAuth;
import com.rakuten.sgpConstants.RspGlobal;
import com.rakuten.utils.utils;

@RestController
@RequestMapping("v1")
public class EsimController {
	
	@Autowired
	private InitiAuth initiAuth;
	
	@Autowired
	private AuthClient authClient;
	
	@Autowired
	private GetBoundProfilePackage getBoundPP;
	
	String TxID = utils.genearteTransactionId();
	//System.out.println("Transaction ID in Hex :"+TxID);
	
	RspGlobal rsp = new RspGlobal();
	

	@RequestMapping(value = "/gsma/rsp2/es9plus/initiateAuthentication", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<InitAuthResponse> initiateAuthentication(@RequestBody InitAuthRequest initRequest) throws Exception {
		
		System.out.println("<===================================>");
		System.out.println("Received request Initiate Authenticate :");
		System.out.println(initRequest.toString());
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(initiAuth.createInitAuthResonse(initRequest,TxID));
		
	}

	
	@RequestMapping(value = "/gsma/rsp2/es9plus/authenticateClient", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthClientResponse> authenticateClient(@RequestBody AuthClientRequest authClientRequest) throws Exception {
		
		System.out.println("<===================================>");
		System.out.println("Received request Authenticate Client:");
		System.out.println(authClientRequest.toString());
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(authClient.createAuthClientResonse(authClientRequest,TxID,rsp));
	}
	
	@RequestMapping(value = "/gsma/rsp2/es9plus/getBoundProfilePackage", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GetBoundProfilePackageResponse> gBPP(@RequestBody GetBoundProfilePackageRequest getBppReq) throws Exception {
			
			System.out.println("/n");
			System.out.println("Received request Authenticate Client :");
			System.out.println(getBppReq.toString());
			
			return ResponseEntity.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(getBoundPP.createBppResonse(getBppReq,TxID,rsp));	
	}
	
	@GetMapping
	public String getEmployee()
	{
		String msg="Hello World -Security : HTTPS-eSIM";
		System.out.println(msg);
		return msg;
	}

}