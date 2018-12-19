/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :报表导出图片action
 * Author     :xuhj
 * Create Date:Jun 1, 2012
 */
package com.banger.mobile.webapp.action.report;

import java.io.*;

import javax.servlet.ServletOutputStream;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;


import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author xuhj
 * @version $Id: ExportReportAction.java,v 0.1 Jun 1, 2012 4:54:57 PM xuhj Exp $
 */
public class ExportReportAction extends BaseAction {

	private static final long serialVersionUID = 3727973329715732566L;
	/**
	 * 导出图片
	 * @return
	 */
	public String exportImage(){
		try{
			request.setCharacterEncoding("utf-8");//注意编码
			getResponse().setCharacterEncoding("utf-8");
			getResponse().setContentType("image/jpeg;charset=UTF-8"); 
			ServletOutputStream out = getResponse().getOutputStream();
			String type = request.getParameter("type");
			String svg = new String(request.getParameter("svg").getBytes("gbk"),"gbk");
			String filename = request.getParameter("filename");
		    if (null != type && null != svg){
		    	svg = svg.replaceAll(":rect", "rect"); 
		        String ext = "";
		        Transcoder t = null;

		        if (type.equals("image/png")) {
		           ext = "png";
		           t = new PNGTranscoder();
		            
		        } else if (type.equals("image/jpeg")) {
		           ext = "jpg";
		           t = new JPEGTranscoder();
	            }else if (type.equals("image/svg+xml")) {
		           ext = "svg";   
		        }
		        getResponse().addHeader("Content-Disposition","attachment; filename="+new String(filename.getBytes("gbk"),"iso8859-1")+'.' + ext); 
		        getResponse().addHeader("Content-Type", type);
		       
		        if (null != t){
		            t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, new Float(.8));
		            TranscoderInput input = new TranscoderInput(new StringReader(svg));
		            TranscoderOutput output = new TranscoderOutput(out);
		            try {
		               t.transcode(input,output);
		           } catch (TranscoderException e){
		               out.print("Problem transcoding stream. See the web logs for more details.");
		               e.printStackTrace();
		            }
		   
		        } else if (ext == "svg"){
		            out.print(svg);
		        } else {
		        	out.print("Invalid type: " + type);
		        }
		    }else {
		    	getResponse().addHeader("Content-Type", "text/html");
	        	out.println("Usage:\n\tParameter [svg]: The DOM Element to be converted.\n\tParameter [type]: The destination MIME type for the elment to be transcoded.");
	      }
	      out.flush();
	      out.close();  
	      return null;
		}catch (Exception e) {
			return "error";
		}
	}
}
