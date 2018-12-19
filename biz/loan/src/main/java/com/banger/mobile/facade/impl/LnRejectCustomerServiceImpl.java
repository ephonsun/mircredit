package com.banger.mobile.facade.impl;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.loan.LnRejectCustomerDao;
import com.banger.mobile.domain.model.loan.LnRejectCustomer;
import com.banger.mobile.facade.loan.LnRejectCustomerService;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by BH-TCL on 14-12-3.
 */
public class LnRejectCustomerServiceImpl implements LnRejectCustomerService {
    private static Logger logger= Logger.getLogger(LnRejectCustomerServiceImpl.class);
    private LnRejectCustomerDao lnRejectCustomerDao;

    public void setLnRejectCustomerDao(LnRejectCustomerDao lnRejectCustomerDao) {
        this.lnRejectCustomerDao = lnRejectCustomerDao;
    }

    @Override
    public PageUtil<LnRejectCustomer> queryLnRejectCustomerPage(Map<String, Object> conds, Page curPage) {
        return lnRejectCustomerDao.queryLnRejectCustomerPage(conds,curPage);
    }

    @Override
    public void addLnRejectCustomer(LnRejectCustomer lnRejectCustomer) {
        lnRejectCustomerDao.addLnRejectCustomer(lnRejectCustomer);
    }

    @Override
    public void updateLnRejectCustomer(LnRejectCustomer lnRejectCustomer) {
        lnRejectCustomerDao.updateLnRejectCustomer(lnRejectCustomer);
    }

    /**
     * 根据id集合查询记录集
     * @param map
     * @return
     */
    @Override
    public List<LnRejectCustomer> queryRejectCustomerByIds(Map<String, Object> map) {
        return lnRejectCustomerDao.queryRejectCustomerByIds(map);
    }

    public void getlnRejectCustomerExportListFile(List<LnRejectCustomer> dataList, String path) {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Workbook book = new HSSFWorkbook();
            Sheet sheet = book.createSheet("拒绝客户");
            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("客户姓名");
            row.createCell(1).setCellValue("电话");
            row.createCell(2).setCellValue("身份证");
            row.createCell(3).setCellValue("用途");
            row.createCell(4).setCellValue("提交人");
            row.createCell(5).setCellValue("提交时间");
            row.createCell(6).setCellValue("备注");
            for (int i = 0; i < dataList.size(); i++) {
                LnRejectCustomer lnRejectCustomer = dataList.get(i);
                row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(lnRejectCustomer.getCustomerName());
                row.createCell(1).setCellValue(lnRejectCustomer.getMobliePhone());
                row.createCell(2).setCellValue(lnRejectCustomer.getIdCard());
                row.createCell(3).setCellValue(lnRejectCustomer.getUseage());
                row.createCell(4).setCellValue(lnRejectCustomer.getUserName());
                row.createCell(5).setCellValue(sdf.format(lnRejectCustomer.getCreateDate()));
                row.createCell(6).setCellValue(lnRejectCustomer.getRemark());
            }
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);

            FileOutputStream os = new FileOutputStream(new File(path));
            book.write(os);
            os.close();
        }catch(Exception e){
            logger.error("", e);
        }

    }

}
