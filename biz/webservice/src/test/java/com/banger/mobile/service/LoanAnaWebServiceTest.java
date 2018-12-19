package com.banger.mobile.service;

import com.banger.mobile.facade.webservice.LoanAnaWebService;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.junit.Test;

import java.net.MalformedURLException;

/**
 * @author zhangfp
 * @version $Id: LoanAnaWebServiceTest v 0.1 ${} 下午2:41 zhangfp Exp $
 */
public class LoanAnaWebServiceTest {
    @Test
    public void getAnalyzesAppInfo(){
        try {
            Service serviceModel = new ObjectServiceFactory().create(LoanAnaWebService.class);
            LoanAnaWebService service = (LoanAnaWebService) new XFireProxyFactory().create(
                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanAnaService");
            String result = service.getAnalyzesAppInfo("wo",1);
            System.out.println(result);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveAnalyzesAppInfo(){
        try {
            Service serviceModel = new ObjectServiceFactory().create(LoanAnaWebService.class);
            LoanAnaWebService service = (LoanAnaWebService) new XFireProxyFactory().create(
                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanAnaService");
            String json="{\"customerName\":\"vtgvyvvy\",\n" +
                    "\"familyCount\":2,\n" +
                    "\"idCard\":\"325807412586398528\",\n" +
                    "\"applyInfoId\":2,\n" +
                    "\"livingCity\":353,\n" +
                    "\"companyPhone\":\"147858066324\",\n" +
                    "\"livingProvince\":5,\n" +
                    "\"nativeDistrict\":310,\n" +
                    "\"job\":\"dxtcgvybub\",\n" +
                    "\"familyPhone\":\"\",\n" +
                    "\"livingDistrict\":2749,\n" +
                    "\"nativeCity\":20,\n" +
                    "\"mobilePhone\":\"12458756398\",\n" +
                    "\"company\":\"rccttvygynuji\",\n" +
                    "\"livingAddress\":\"fcvgbybuniniimomommo\",\n" +
                    "\"childrenList\":[{\n" +
                    "\"childrenId\":6,\n" +
                    "\"childrenInfoOther\":\"aaaa\",\n" +
                    "\"childrenInfo\":\"学习\"}],\n" +
                    "\"nativeAddress\":\"rxcrvtbyuinimomom\",\n" +
                    "\"annualIncoming\":\"12.45\",\n" +
                    "\"nativeProvince\":4,\n" +
                    "\"loanId\":501}\n";
            String json1 = "{\"customerName\":\"hhzubzu\",\n" +
                    "\"familyCount\":4,\n" +
                    "\"idCard\":\"369780541361369852\",\n" +
                    "\"applyInfoId\":-1,\n" +
                    "\"livingCity\":129,\n" +
                    "\"companyPhone\":\"13698057423\",\n" +
                    "\"livingProvince\":15,\n" +
                    "\"nativeDistrict\":185,\n" +
                    "\"job\":\"hbsbhsjzhnj\",\n" +
                    "\"familyPhone\":\"\",\n" +
                    "\"livingDistrict\":1206,\n" +
                    "\"nativeCity\":9,\n" +
                    "\"mobilePhone\":\"13698074523\",\n" +
                    "\"company\":\"bhzhbehnnuenu\",\n" +
                    "\"livingAddress\":\"hbshzbnhsznuenu\",\n" +
                    "\"childrenList\":[],\n" +
                    "\"nativeAddress\":\"hbshbzuns(jnnuez)\",\n" +
                    "\"annualIncoming\":\"12.36\",\n" +
                    "\"nativeProvince\":3,\n" +
                    "\"loanId\":501}";
            String result = service.saveAnalyzesAppInfo("wo",501,json1);
            System.out.println(result);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAnalyzesCobList(){
        try {
            Service serviceModel = new ObjectServiceFactory().create(LoanAnaWebService.class);
            LoanAnaWebService service = (LoanAnaWebService) new XFireProxyFactory().create(
                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanAnaService");
            String ret = service.getAnalyzesCobList("wo",2);
            System.out.println(ret);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
