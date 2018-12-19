package com.banger.mobile.domain.model.record;

public class ReportRecordInfo extends RecordInfo {
	private static final long serialVersionUID = 1530132418586878168L;

    private String callTypeName; //联系类型
    private String state;       //状态
    private String recordSourceName;
    private String commProgressName;
    private String bizTypeName;

	public String getBizTypeName() {
		return bizTypeName;
	}

	public void setBizTypeName(String bizTypeName) {
		this.bizTypeName = bizTypeName;
	}

	public String getCommProgressName() {
		return commProgressName;
	}

	public void setCommProgressName(String commProgressName) {
		this.commProgressName = commProgressName;
	}

	public String getCallTypeName() {
		return callTypeName;
	}

	public void setCallTypeName(String callTypeName) {
		this.callTypeName = callTypeName;
	}
	
	public String getRecordSourceName() {
		return recordSourceName;
	}

	public void setRecordSourceName(String recordSourceName) {
		this.recordSourceName = recordSourceName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	/**
     * 格式化时长
     * @param len
     * @return
     */
	public String getFormatCallTime()
	{
		Integer len = this.getCallTime();
		String str="";
		if(len!=null && len>0)
		{
			int h = len/3600;
			int m = (len-3600*h)/60;
			int s = len-3600*h-60*m;
			str+=padLeft(String.valueOf(h),2,'0')+":";
			str+=padLeft(String.valueOf(m),2,'0')+":";
			str+=padLeft(String.valueOf(s),2,'0');
		}
		return str;
	}
	
	public String getFormatCallItemOld()
	{
		Integer len = this.getCallTime();
		String str="";
		if(len!=null && len>0)
		{
			int h = len/3600;
			int m = (len-3600*h)/60;
			int s = len-3600*h-60*m;
			if(h>0)str+=h+"小时";
			if(m>0)str+=m+"分";
			if(s>0)str+=s+"秒";
		}
		return str;
	}
	
	private String padLeft(String str,int totalLength,char c)
    {
        String str1 = str;
        int bit=totalLength;
        int n = str1.length();
        if (n < bit)
        {
            int m = bit - n;
            for (int i = 0; i < m; i++)
            {
                str1 =  String.valueOf(c)+ str1;
            }
            return str1;
        }
        else return str;
    }
}
