package com.jeesite.modules.basic.printer.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeesite.common.service.CrudService;
import com.jeesite.modules.basic.customers.entity.CustomersC;
import com.jeesite.modules.basic.customers.service.CustomersCService;
import com.jeesite.modules.basic.printer.dao.PrinterDao;
import com.jeesite.modules.basic.printer.entity.Printer;
import com.jeesite.modules.basic.printer.tool.BasicExcel;
import com.jeesite.modules.basic.printer.tool.DownloadUtil;
import com.jeesite.modules.presale.offer.entity.OfferC;
import com.jeesite.modules.presale.offer.entity.ReferenceProductC;

/**
 * 打印Service
 * @author longlou.d@foxmail.com
 * @version 2019-4-12
 */
@Service
public class PrinterService extends CrudService<PrinterDao, Printer> {
	BasicExcel be;
	@Autowired
	private CustomersCService customerService;
	@Autowired
	private Dictionaries dict;
	/**
	 * 报价单打印
	 * @throws IOException 
	 */
	public void printOffer(OfferC offerC,HttpServletResponse response) throws IOException{
		//初始化，获取工作页
		be = new BasicExcel();
		Workbook wb = be.initWork("报价单Template");
		Sheet sheet = wb.getSheetAt(0);
		
		Row nRow = null;
		Cell nCell = null;
		int rowNo= 15;
		int colNo = 0;
		
		//客户对象
		CustomersC customer = customerService.get(new CustomersC(offerC.getCustomersCId()));
		//货物
		List<ReferenceProductC> productList = offerC.getReferenceProductCList();
		//初始化字典
		dict.init();
		
		//获取指定列
		nRow = sheet.getRow(2);
		//报价日期
		nCell = nRow.getCell(1);
		CellStyle offerDate = nCell.getCellStyle();
		nCell = nRow.createCell(1);
		nCell.setCellValue(offerC.getOfferTime().toGMTString());
		nCell.setCellStyle(offerDate);
		//截止日期
		nCell = nRow.getCell(4);
		CellStyle deadLineDate = nCell.getCellStyle();
		nCell.setCellValue(offerC.getDeadline().toGMTString());
		nCell.setCellStyle(deadLineDate);
		
		//获取指定列
		nRow = sheet.getRow(3);
		//客户名称
		nCell = nRow.getCell(1);
		CellStyle cusName = nCell.getCellStyle();
		nCell = nRow.createCell(1);
		nCell.setCellValue(customer.getName());
		nCell.setCellStyle(cusName);
		//客户地址
		nCell = nRow.getCell(4);
		CellStyle cusAddress = nCell.getCellStyle();
		nCell = nRow.createCell(4);
		nCell.setCellValue(customer.getAddress());
		nCell.setCellStyle(cusAddress);
		
		//获取指定列
		nRow = sheet.getRow(4);
		//价格条款
		nCell = nRow.getCell(1);
		CellStyle priceItem = nCell.getCellStyle();
		nCell = nRow.createCell(1);
		nCell.setCellValue(dict.getPriceItem(offerC.getPriceTerm().trim()));
		nCell.setCellStyle(priceItem);
		//付款方式
		nCell = nRow.getCell(4);
		CellStyle payment = nCell.getCellStyle();
		nCell = nRow.createCell(4);
		nCell.setCellValue(dict.getPayment(offerC.getPaymentterrms().trim()));
		nCell.setCellStyle(payment);
		
		//获取指定列
		nRow = sheet.getRow(5);
		//运输方式
		nCell = nRow.getCell(1);
		CellStyle transWay = nCell.getCellStyle();
		nCell = nRow.createCell(1);
		String way = "";
		for(String s : offerC.getTransportWays().split(","))
			way=way+" "+dict.getTransportWay(s);
		nCell.setCellValue(way);
		nCell.setCellStyle(transWay);
		//预计到货
		nCell = nRow.getCell(4);
		CellStyle expect = nCell.getCellStyle();
		nCell = nRow.createCell(4);
		nCell.setCellValue(offerC.getExpectDeliverTime().toGMTString());
		nCell.setCellStyle(expect);
		
		//获取指定列
		nRow = sheet.getRow(6);
		//运费
		nCell = nRow.getCell(1);
		CellStyle transBill = nCell.getCellStyle();
		nCell = nRow.createCell(1);
		nCell.setCellValue(offerC.getCarriageCost());
		nCell.setCellStyle(transBill);
		//包装费用
		nCell = nRow.getCell(4);
		CellStyle packetCost = nCell.getCellStyle();
		nCell = nRow.createCell(4);
		nCell.setCellValue(offerC.getPackageCost());
		nCell.setCellStyle(packetCost);
		
		//获取指定列
		nRow = sheet.getRow(7);
		//商检费用
		nCell = nRow.getCell(1);
		CellStyle inspectionCost = nCell.getCellStyle();
		nCell = nRow.createCell(1);
		nCell.setCellValue(offerC.getInspectionCost());
		nCell.setCellStyle(inspectionCost);
		//报关费用
		nCell = nRow.getCell(4);
		CellStyle customsCost = nCell.getCellStyle();
		nCell = nRow.createCell(4);
		nCell.setCellValue(offerC.getCustomsCost());
		nCell.setCellStyle(customsCost);
		
		//获取指定列
		nRow = sheet.getRow(8);
		//保险费用
		nCell = nRow.getCell(1);
		CellStyle insuranceCost = nCell.getCellStyle();
		nCell = nRow.createCell(1);
		nCell.setCellValue(offerC.getInsuranceCost());
		nCell.setCellStyle(insuranceCost);
		//出口税率
		nCell = nRow.getCell(4);
		CellStyle taxRate = nCell.getCellStyle();
		nCell = nRow.createCell(4);
		nCell.setCellValue(offerC.getExportTax());
		nCell.setCellStyle(taxRate);
		
		//获取指定列
		nRow = sheet.getRow(9);
		//退税率
		nCell = nRow.getCell(1);
		CellStyle reTax = nCell.getCellStyle();
		nCell = nRow.createCell(1);
		nCell.setCellValue(offerC.getReturnTax());
		nCell.setCellStyle(reTax);
		//其他费用
		nCell = nRow.getCell(4);
		CellStyle otherCost = nCell.getCellStyle();
		nCell = nRow.createCell(4);
		nCell.setCellValue(offerC.getOthersCost());
		nCell.setCellStyle(otherCost);
		
		//获取指定列
		nRow = sheet.getRow(10);
		//退税率
		nCell = nRow.getCell(4);
		CellStyle totalAmount = nCell.getCellStyle();
		nCell = nRow.createCell(4);
		nCell.setCellValue(offerC.getTotalAmount());
		nCell.setCellStyle(totalAmount);
		
		//获取指定列
		nRow = sheet.getRow(12);
		//备注
		nCell = nRow.getCell(1);
		CellStyle remark = nCell.getCellStyle();
		nCell = nRow.createCell(1);
		nCell.setCellValue(offerC.getRemarks());
		nCell.setCellStyle(remark);
		
		//获取指定列
		nRow = sheet.getRow(13);
		//客户要求
		nCell = nRow.getCell(1);
		CellStyle cusRequire = nCell.getCellStyle();
		nCell = nRow.createCell(1);
		nCell.setCellValue(offerC.getCustRequire());
		nCell.setCellStyle(cusRequire);
		
		for(int i =0;i<productList.size();i++){
			colNo=0;
			nRow = sheet.createRow(rowNo++);
			nRow.setHeightInPoints(24);
			
			//序号
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(i+1);
			nCell.setCellStyle(deadLineDate);
			
			//货物名称
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(productList.get(i).getName());
			nCell.setCellStyle(deadLineDate);
			
			//规格
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(productList.get(i).getSpec());
			nCell.setCellStyle(cusRequire);
			
			//单价
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(productList.get(i).getPrice());
			nCell.setCellStyle(deadLineDate);
			
			//数量
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(productList.get(i).getNumber());
			nCell.setCellStyle(deadLineDate);
			
			//毛重
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(productList.get(i).getGrossWeight());
			nCell.setCellStyle(deadLineDate);
			
			//净重
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(productList.get(i).getNetWeight());
			nCell.setCellStyle(deadLineDate);
		}
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		DownloadUtil du = new DownloadUtil();
		wb.write(os);
		du.download(os, response, "报价单.xlsx");
		wb.close();
		os.close();
	}
	
}
