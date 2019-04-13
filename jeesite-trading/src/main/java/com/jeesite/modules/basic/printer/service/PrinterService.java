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
import org.apache.poi.ss.util.CellRangeAddress;
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
import com.jeesite.modules.presale.send.entity.SendProductC;
import com.jeesite.modules.presale.send.entity.SendSpecimenC;
import com.jeesite.modules.purandsell.sales.entity.ContractC;
import com.jeesite.modules.purandsell.sales.entity.SaleProductC;

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
		nCell.setCellValue(dict.getPayment(""+offerC.getPaymentterrms().trim()));
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
		nCell.setCellValue(offerC.getExpectDeliverTime().toGMTString()+"");
		nCell.setCellStyle(expect);
		
		//获取指定列
		nRow = sheet.getRow(6);
		//运费
		nCell = nRow.getCell(1);
		CellStyle transBill = nCell.getCellStyle();
		nCell = nRow.createCell(1);
		nCell.setCellValue(offerC.getCarriageCost()+"");
		nCell.setCellStyle(transBill);
		//包装费用
		nCell = nRow.getCell(4);
		CellStyle packetCost = nCell.getCellStyle();
		nCell = nRow.createCell(4);
		nCell.setCellValue(offerC.getPackageCost()+"");
		nCell.setCellStyle(packetCost);
		
		//获取指定列
		nRow = sheet.getRow(7);
		//商检费用
		nCell = nRow.getCell(1);
		CellStyle inspectionCost = nCell.getCellStyle();
		nCell = nRow.createCell(1);
		nCell.setCellValue(offerC.getInspectionCost()+"");
		nCell.setCellStyle(inspectionCost);
		//报关费用
		nCell = nRow.getCell(4);
		CellStyle customsCost = nCell.getCellStyle();
		nCell = nRow.createCell(4);
		nCell.setCellValue(offerC.getCustomsCost()+"");
		nCell.setCellStyle(customsCost);
		
		//获取指定列
		nRow = sheet.getRow(8);
		//保险费用
		nCell = nRow.getCell(1);
		CellStyle insuranceCost = nCell.getCellStyle();
		nCell = nRow.createCell(1);
		nCell.setCellValue(offerC.getInsuranceCost()+"");
		nCell.setCellStyle(insuranceCost);
		//出口税率
		nCell = nRow.getCell(4);
		CellStyle taxRate = nCell.getCellStyle();
		nCell = nRow.createCell(4);
		nCell.setCellValue(offerC.getExportTax()+"");
		nCell.setCellStyle(taxRate);
		
		//获取指定列
		nRow = sheet.getRow(9);
		//退税率
		nCell = nRow.getCell(1);
		CellStyle reTax = nCell.getCellStyle();
		nCell = nRow.createCell(1);
		nCell.setCellValue(offerC.getReturnTax()+"");
		nCell.setCellStyle(reTax);
		//其他费用
		nCell = nRow.getCell(4);
		CellStyle otherCost = nCell.getCellStyle();
		nCell = nRow.createCell(4);
		nCell.setCellValue(offerC.getOthersCost()+"");
		nCell.setCellStyle(otherCost);
		
		//获取指定列
		nRow = sheet.getRow(10);
		//退税率
		nCell = nRow.getCell(4);
		CellStyle totalAmount = nCell.getCellStyle();
		nCell = nRow.createCell(4);
		nCell.setCellValue(offerC.getTotalAmount()+"");
		nCell.setCellStyle(totalAmount);
		
		//获取指定列
		nRow = sheet.getRow(12);
		//备注
		nCell = nRow.getCell(1);
		CellStyle remark = nCell.getCellStyle();
		nCell = nRow.createCell(1);
		nCell.setCellValue(offerC.getRemarks()+"");
		nCell.setCellStyle(remark);
		
		//获取指定列
		nRow = sheet.getRow(13);
		//客户要求
		nCell = nRow.getCell(1);
		CellStyle cusRequire = nCell.getCellStyle();
		nCell = nRow.createCell(1);
		nCell.setCellValue(offerC.getCustRequire()+"");
		nCell.setCellStyle(cusRequire);
		
		//导出货物
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
			nCell.setCellValue(productList.get(i).getSpec()+"");
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
		du.download(os, response, "报价单"+offerC.getId()+".xlsx");

		wb.close();
		os.close();
	}
	
	/**
	 * 打印寄样单
	 * @throws IOException 
	 */
	public void printSend(SendSpecimenC sendSpecimenC,HttpServletResponse response) throws IOException{
		//初始化，获取工作页
		be = new BasicExcel();
		Workbook wb = be.initWork("寄样单Template");
		Sheet sheet = wb.getSheetAt(0);
		

		Row nRow = null;
		Cell nCell = null;
		int rowNo= 9;
		int colNo = 0;
		
		//客户对象
		CustomersC customer = customerService.get(new CustomersC(sendSpecimenC.getCustomersCId()));
		//货物
		List<SendProductC> productList = sendSpecimenC.getSendProductCList();
		//初始化字典
		dict.init();
		
		//获取指定列
		nRow = sheet.getRow(2);
		//寄样日期
		nCell = nRow.getCell(2);
		CellStyle sendTime = nCell.getCellStyle();
		nCell.setCellValue(sendSpecimenC.getSendTime().toGMTString());
		nCell.setCellStyle(sendTime);
		//客户名称
		nCell = nRow.getCell(5);
		CellStyle custName = nCell.getCellStyle();
		nCell.setCellValue(customer.getName());
		nCell.setCellStyle(custName);
		
		//获取指定列
		nRow = sheet.getRow(3);
		//客户电话
		nCell = nRow.getCell(2);
		CellStyle custPhone = nCell.getCellStyle();
		nCell.setCellValue(customer.getPhone());
		nCell.setCellStyle(custPhone);
		//寄送地址
		nCell = nRow.getCell(5);
		CellStyle address = nCell.getCellStyle();
		nCell.setCellValue(sendSpecimenC.getAddress());
		nCell.setCellStyle(address);
		
		//获取指定列
		nRow = sheet.getRow(4);
		//总金额
		nCell = nRow.getCell(2);
		CellStyle totalAmount = nCell.getCellStyle();
		nCell.setCellValue(sendSpecimenC.getTotalAmount());
		nCell.setCellStyle(totalAmount);
		//物流单号
		nCell = nRow.getCell(5);
		CellStyle logisticBill = nCell.getCellStyle();
		nCell.setCellValue(sendSpecimenC.getLogisticsBill()+"");
		nCell.setCellStyle(logisticBill);
		
		//获取指定列
		nRow = sheet.getRow(5);
		//预计送达时间
		nCell = nRow.getCell(2);
		CellStyle expectArrive = nCell.getCellStyle();
		nCell.setCellValue(sendSpecimenC.getPreArriveTime().toGMTString());
		nCell.setCellStyle(expectArrive);
		//收货时间
		nCell = nRow.getCell(5);
		CellStyle takeGoods = nCell.getCellStyle();
		nCell.setCellValue(sendSpecimenC.getTakeGoodsTime().toGMTString()+"");
		nCell.setCellStyle(takeGoods);
		
		
		//获取指定列
		nRow = sheet.getRow(7);
		//备注
		nCell = nRow.getCell(2);
		CellStyle remarks = nCell.getCellStyle();
		nCell.setCellValue(sendSpecimenC.getRemarks()+"");
		nCell.setCellStyle(remarks);
		
		//导出货物
		for(int i =0;i<productList.size();i++){
			colNo=0;
			nRow = sheet.createRow(rowNo++);
			nRow.setHeightInPoints(24);
					
			//序号
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(i+1);
			
			//货物名称
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(productList.get(i).getName());
			nCell.setCellStyle(totalAmount);
			
			//规格
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(productList.get(i).getSpec()+"");
			nCell.setCellStyle(address);
			
			//单价
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(productList.get(i).getPrice());
			nCell.setCellStyle(totalAmount);
			
			//包装单位
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(productList.get(i).getPackageUnit()+"");
			nCell.setCellStyle(totalAmount);
			
			//毛重
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(productList.get(i).getGrossWeight());
			nCell.setCellStyle(totalAmount);
			
			//净重
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(productList.get(i).getNetWeight());
			nCell.setCellStyle(totalAmount);
			
			//数量
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(productList.get(i).getNumber());
			nCell.setCellStyle(totalAmount);
		}
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		DownloadUtil du = new DownloadUtil();
		wb.write(os);
		du.download(os, response, "寄样单"+sendSpecimenC.getId()+".xlsx");

		wb.close();
		os.close();
	}
	
	/**
	 * 打印销售合同
	 * @throws IOException 
	 */
	public void printSalesContract(ContractC contractC,HttpServletResponse response) throws IOException{
		//初始化，获取工作页
		be = new BasicExcel();
		Workbook wb = be.initWork("销售合同Template");
		Sheet sheet = wb.getSheetAt(0);				//合同正文
		Sheet sheet2 = wb.getSheetAt(1);			//合同副稿

		
		Row nRow = null;
		Cell nCell = null;
		Cell nCell2 = null;
		Row nRow2 = null;
		int rowNo= 15;
		int colNo = 0;
		CellRangeAddress region = null;
		
		//客户对象
		CustomersC customer = customerService.get(new CustomersC(contractC.getCustomersCId()));
		//货物
		List<SaleProductC> productList = contractC.getSaleProductCList();
		//初始化字典
		dict.init();
		
		//获取指定列
		nRow = sheet.getRow(2);
		//合同号
		nCell = nRow.getCell(1);
		CellStyle contractCode = nCell.getCellStyle();
		nCell.setCellValue(contractC.getContractCode());
		nCell.setCellStyle(contractCode);
		//合同日期
		nCell = nRow.getCell(4);
		CellStyle contractDate = nCell.getCellStyle();
		nCell.setCellValue(contractC.getSignTime().toGMTString()+"");
		nCell.setCellStyle(contractDate);
		
		//获取指定列
		nRow = sheet.getRow(8);
		//客户名称
		nCell = nRow.getCell(1);
		CellStyle custName = nCell.getCellStyle();
		nCell.setCellValue(customer.getName());
		nCell.setCellStyle(custName);
		
		//获取指定列
		nRow = sheet.getRow(9);
		//客户地址
		nCell = nRow.getCell(1);
		CellStyle custAddress = nCell.getCellStyle();
		nCell.setCellValue(customer.getAddress());
		nCell.setCellStyle(custAddress);
		
		//获取指定列
		nRow = sheet.getRow(10);
		//客户电话
		nCell = nRow.getCell(1);
		CellStyle custPhone = nCell.getCellStyle();
		nCell.setCellValue(customer.getPhone());
		nCell.setCellStyle(custPhone);
		
		//获取货物栏的格式
		nRow = sheet.getRow(15);
		//商品名称
		nCell = nRow.getCell(0);
		CellStyle name = nCell.getCellStyle();
		//数量
		nCell = nRow.getCell(3);
		CellStyle quantity = nCell.getCellStyle();
		
		for(int i=0;i<productList.size();i++) {
			colNo=0;
			//合并并添加单元格
			region = new CellRangeAddress(rowNo++, rowNo-1, 0, 2);
			sheet.addMergedRegion(region);
			//创建该行并设置行高
			nRow = sheet.createRow(rowNo-1);
			nRow.setHeightInPoints(24);
			
			//货物名称
			nCell = nRow.createCell(0);
			nCell.setCellValue(productList.get(i).getName());
			nCell.setCellStyle(name);
			
			//数量
			nCell = nRow.createCell(3);
			nCell.setCellValue(productList.get(i).getNumber());
			nCell.setCellStyle(quantity);
			
			//单价
			nCell = nRow.createCell(4);
			nCell.setCellValue(productList.get(i).getPrice());
			nCell.setCellStyle(quantity);
			
			//总值
			nCell = nRow.createCell(5);
			nCell.setCellValue(productList.get(i).getTotalAmount());
			nCell.setCellStyle(quantity);
			
			//规格
			//合并并添加单元格
			region = new CellRangeAddress(rowNo++, rowNo-1, 0, 2);
			sheet.addMergedRegion(region);
			region = new CellRangeAddress(rowNo-1, rowNo-1, 3, 5);
			sheet.addMergedRegion(region);
			//创建该行并设置行高
			nRow = sheet.createRow(rowNo-1);
			nRow.setHeightInPoints(30);
			//创建该单元格并赋值
			nCell = nRow.createCell(0);
			nCell.setCellValue(productList.get(i).getSpec());
			nCell.setCellStyle(name);
			nCell = nRow.createCell(3);
			nCell.setCellValue("（装运数量允许有    %的增减）");
			nCell.setCellStyle(name);
			
			//包装
			//合并并添加单元格
			region = new CellRangeAddress(rowNo++, rowNo-1, 0, 2);
			sheet.addMergedRegion(region);
			region = new CellRangeAddress(rowNo-1, rowNo-1, 3, 5);
			sheet.addMergedRegion(region);
			//创建该行并设置行高
			nRow = sheet.createRow(rowNo-1);
			nRow.setHeightInPoints(30);
			//创建该单元格并赋值
			nCell = nRow.createCell(0);
			StringBuffer sb = new StringBuffer();
			sb.append("包装单位：").append(dict.getPackageUnit(productList.get(i).getPackageUnit().trim()));
			sb.append("；单件包装方式：").append(dict.getSinglePackage(productList.get(i).getSinglePackageType().trim()));
			sb.append("；内包装方式：").append(dict.getSinglePackage(productList.get(i).getInnerPackageType().trim()));
			nCell.setCellValue(sb.toString());
			nCell.setCellStyle(name);
			nCell = nRow.createCell(3);
			nCell.setCellValue("(Shipment Quantity   % more of less allowed)");
			nCell.setCellStyle(name);
			
			//创建分行
			nRow = sheet.createRow(rowNo++);
			nRow.setHeightInPoints(7);
		}
		
		//获取附稿内容
		nRow2 = sheet2.getRow(19);
		nCell2 = nRow2.getCell(0);
		CellStyle shipmentTime = nCell2.getCellStyle();
		//合并并添加单元格
		region = new CellRangeAddress(rowNo++, rowNo-1, 0, 1);
		sheet.addMergedRegion(region);
		region = new CellRangeAddress(rowNo-1, rowNo-1, 2, 5);
		sheet.addMergedRegion(region);
		//创建该行并设置行高
		nRow = sheet.createRow(rowNo-1);
		nRow.setHeightInPoints(30);
		//装运期限
		nCell = nRow.createCell(0);
		nCell.setCellValue(nCell2.getStringCellValue());
		nCell.setCellStyle(custAddress);
		nCell = nRow.createCell(2);
		nCell.setCellValue(contractC.getShipmentTime().toGMTString());
		nCell.setCellStyle(shipmentTime);
		
		nRow2 = sheet2.getRow(20);
		nCell2 = nRow2.getCell(0);
		CellStyle startPlace = nCell2.getCellStyle();
		//合并并添加单元格
		region = new CellRangeAddress(rowNo++, rowNo-1, 0, 1);
		sheet.addMergedRegion(region);
		region = new CellRangeAddress(rowNo-1, rowNo-1, 2, 5);
		sheet.addMergedRegion(region);
		//创建该行并设置行高
		nRow = sheet.createRow(rowNo-1);
		nRow.setHeightInPoints(30);
		//起始地
		nCell = nRow.createCell(0);
		nCell.setCellValue(nCell2.getStringCellValue());
		nCell.setCellStyle(custAddress);
		nCell = nRow.createCell(2);
		nCell.setCellValue(contractC.getStartAddr());
		nCell.setCellStyle(startPlace);
		
		nRow2 = sheet2.getRow(21);
		nCell2 = nRow2.getCell(0);
		CellStyle endPlace = nCell2.getCellStyle();
		//合并并添加单元格
		region = new CellRangeAddress(rowNo++, rowNo-1, 0, 1);
		sheet.addMergedRegion(region);
		region = new CellRangeAddress(rowNo-1, rowNo-1, 2, 5);
		sheet.addMergedRegion(region);
		//创建该行并设置行高
		nRow = sheet.createRow(rowNo-1);
		nRow.setHeightInPoints(30);
		//目的地
		nCell = nRow.createCell(0);
		nCell.setCellValue(nCell2.getStringCellValue());
		nCell.setCellStyle(custAddress);
		nCell = nRow.createCell(2);
		nCell.setCellValue(contractC.getEndAddr());
		nCell.setCellStyle(endPlace);
		
		//保险条款中文
		nRow2 = sheet2.getRow(22);
		nCell2 = nRow2.getCell(0);
		CellStyle insuranceC = nCell2.getCellStyle();
		//合并并添加单元格
		region = new CellRangeAddress(rowNo++, rowNo-1, 0, 5);
		sheet.addMergedRegion(region);
		//创建该行并设置行高
		nRow = sheet.createRow(rowNo-1);
		nRow.setHeightInPoints(17);
		//创建该单元格并赋值
		nCell = nRow.createCell(0);
		nCell.setCellValue(nCell2.getStringCellValue());
		nCell.setCellStyle(insuranceC);
		
		//保险条款英文
		nRow2 = sheet2.getRow(23);
		nCell2 = nRow2.getCell(0);
		CellStyle insuranceE = nCell2.getCellStyle();
		//合并并添加单元格
		region = new CellRangeAddress(rowNo++, rowNo-1, 0, 5);
		sheet.addMergedRegion(region);
		//创建该行并设置行高
		nRow = sheet.createRow(rowNo-1);
		nRow.setHeightInPoints(15);
		//创建该单元格并赋值
		nCell = nRow.createCell(0);
		nCell.setCellValue(nCell2.getStringCellValue());
		nCell.setCellStyle(insuranceE);
		
		
		nRow2 = sheet2.getRow(24);
		nCell2 = nRow2.getCell(0);
		CellStyle paymentWay = nCell2.getCellStyle();
		//合并并添加单元格
		region = new CellRangeAddress(rowNo++, rowNo-1, 0, 1);
		sheet.addMergedRegion(region);
		region = new CellRangeAddress(rowNo-1, rowNo-1, 2, 5);
		sheet.addMergedRegion(region);
		//创建该行并设置行高
		nRow = sheet.createRow(rowNo-1);
		nRow.setHeightInPoints(30);
		//付款方式
		nCell = nRow.createCell(0);
		nCell.setCellValue(nCell2.getStringCellValue());
		nCell.setCellStyle(custAddress);
		nCell = nRow.createCell(2);
		nCell.setCellValue(dict.getPayment(contractC.getPaymentTerm()));
		nCell.setCellStyle(paymentWay);
		
		nRow2 = sheet2.getRow(25);
		nCell2 = nRow2.getCell(0);
		CellStyle payDeadline = nCell2.getCellStyle();
		//合并并添加单元格
		region = new CellRangeAddress(rowNo++, rowNo-1, 0, 1);
		sheet.addMergedRegion(region);
		region = new CellRangeAddress(rowNo-1, rowNo-1, 2, 5);
		sheet.addMergedRegion(region);
		//创建该行并设置行高
		nRow = sheet.createRow(rowNo-1);
		nRow.setHeightInPoints(30);
		//付款截止
		nCell = nRow.createCell(0);
		nCell.setCellValue(nCell2.getStringCellValue());
		nCell.setCellStyle(custAddress);
		nCell = nRow.createCell(2);
		nCell.setCellValue(contractC.getPaymentTime().toGMTString());
		nCell.setCellStyle(payDeadline);
		
		nRow2 = sheet2.getRow(26);
		nCell2 = nRow2.getCell(0);
		CellStyle totalAmount = nCell2.getCellStyle();
		//合并并添加单元格
		region = new CellRangeAddress(rowNo++, rowNo-1, 0, 1);
		sheet.addMergedRegion(region);
		region = new CellRangeAddress(rowNo-1, rowNo-1, 2, 5);
		sheet.addMergedRegion(region);
		//创建该行并设置行高
		nRow = sheet.createRow(rowNo-1);
		nRow.setHeightInPoints(30);
		//交易总额
		nCell = nRow.createCell(0);
		nCell.setCellValue(nCell2.getStringCellValue());
		nCell.setCellStyle(custAddress);
		nCell = nRow.createCell(2);
		nCell.setCellValue(contractC.getTotalAmount());
		nCell.setCellStyle(totalAmount);
		
		nRow2 = sheet2.getRow(27);
		nCell2 = nRow2.getCell(0);
		CellStyle inspectionC = nCell2.getCellStyle();
		//合并并添加单元格
		region = new CellRangeAddress(rowNo++, rowNo-1, 0, 5);
		sheet.addMergedRegion(region);
		//创建该行并设置行高
		nRow = sheet.createRow(rowNo-1);
		nRow.setHeightInPoints(27);
		//商检条款中文
		nCell = nRow.createCell(0);
		nCell.setCellValue(nCell2.getStringCellValue());
		nCell.setCellStyle(inspectionC);
		
		nRow2 = sheet2.getRow(28);
		nCell2 = nRow2.getCell(0);
		CellStyle inspectionE = nCell2.getCellStyle();
		//合并并添加单元格
		region = new CellRangeAddress(rowNo++, rowNo-1, 0, 5);
		sheet.addMergedRegion(region);
		//创建该行并设置行高
		nRow = sheet.createRow(rowNo-1);
		nRow.setHeightInPoints(30);
		//商检条款英文
		nCell = nRow.createCell(0);
		nCell.setCellValue(nCell2.getStringCellValue());
		nCell.setCellStyle(inspectionE);

		nRow2 = sheet2.getRow(29);
		nCell2 = nRow2.getCell(0);
		CellStyle marks = nCell2.getCellStyle();
		//合并并添加单元格
		region = new CellRangeAddress(rowNo++, rowNo-1, 0, 1);
		sheet.addMergedRegion(region);
		region = new CellRangeAddress(rowNo-1, rowNo-1, 2, 5);
		sheet.addMergedRegion(region);
		//创建该行并设置行高
		nRow = sheet.createRow(rowNo-1);
		nRow.setHeightInPoints(48);
		//装运唛头
		nCell = nRow.createCell(0);
		nCell.setCellValue(nCell2.getStringCellValue());
		nCell.setCellStyle(custAddress);
		nCell = nRow.createCell(2);
		StringBuffer sb = new StringBuffer();
		sb.append(customer.getName()).append(";\n");
		sb.append(contractC.getEndAddr()).append(";\n");
		sb.append(contractC.getContractCode()).append(";");
		nCell.setCellValue(sb.toString());
		nCell.setCellStyle(marks);
		
		nRow2 = sheet2.getRow(30);
		nCell2 = nRow2.getCell(0);
		CellStyle breach = nCell2.getCellStyle();
		//合并并添加单元格
		region = new CellRangeAddress(rowNo++, rowNo-1, 0, 1);
		sheet.addMergedRegion(region);
		region = new CellRangeAddress(rowNo-1, rowNo-1, 2, 5);
		sheet.addMergedRegion(region);
		//创建该行并设置行高
		nRow = sheet.createRow(rowNo-1);
		nRow.setHeightInPoints(35);
		//违约条款
		nCell = nRow.createCell(0);
		nCell.setCellValue(nCell2.getStringCellValue());
		nCell.setCellStyle(custAddress);
		nCell = nRow.createCell(2);
		nCell.setCellValue(contractC.getBreachContract()+"");
		nCell.setCellStyle(breach);
		
		nRow2 = sheet2.getRow(31);
		nCell2 = nRow2.getCell(0);
		CellStyle otherItem = nCell2.getCellStyle();
		//合并并添加单元格
		region = new CellRangeAddress(rowNo++, rowNo-1, 0, 1);
		sheet.addMergedRegion(region);
		region = new CellRangeAddress(rowNo-1, rowNo-1, 2, 5);
		sheet.addMergedRegion(region);
		//创建该行并设置行高
		nRow = sheet.createRow(rowNo-1);
		nRow.setHeightInPoints(35);
		//其他条款
		nCell = nRow.createCell(0);
		nCell.setCellValue(nCell2.getStringCellValue());
		nCell.setCellStyle(otherItem);
		
		nRow2 = sheet2.getRow(32);
		nCell2 = nRow2.getCell(0);
		CellStyle discrepancyC = nCell2.getCellStyle();
		//合并并添加单元格
		region = new CellRangeAddress(rowNo++, rowNo-1, 0, 5);
		sheet.addMergedRegion(region);
		//创建该行并设置行高
		nRow = sheet.createRow(rowNo-1);
		nRow.setHeightInPoints(46);
		//异议中文
		nCell = nRow.createCell(0);
		nCell.setCellValue(nCell2.getStringCellValue());
		nCell.setCellStyle(discrepancyC);
		
		nRow2 = sheet2.getRow(33);
		nCell2 = nRow2.getCell(0);
		CellStyle discrepancyE = nCell2.getCellStyle();
		//合并并添加单元格
		region = new CellRangeAddress(rowNo++, rowNo-1, 0, 5);
		sheet.addMergedRegion(region);
		//创建该行并设置行高
		nRow = sheet.createRow(rowNo-1);
		nRow.setHeightInPoints(118);
		//异议英文
		nCell = nRow.createCell(0);
		nCell.setCellValue(nCell2.getStringCellValue());
		nCell.setCellStyle(discrepancyE);
		
		nRow2 = sheet2.getRow(34);
		nCell2 = nRow2.getCell(0);
		CellStyle insuranceItemC = nCell2.getCellStyle();
		//合并并添加单元格
		region = new CellRangeAddress(rowNo++, rowNo-1, 0, 5);
		sheet.addMergedRegion(region);
		//创建该行并设置行高
		nRow = sheet.createRow(rowNo-1);
		nRow.setHeightInPoints(41);
		//保险条款中文
		nCell = nRow.createCell(0);
		nCell.setCellValue(nCell2.getStringCellValue());
		nCell.setCellStyle(insuranceItemC);
		
		nRow2 = sheet2.getRow(35);
		nCell2 = nRow2.getCell(0);
		CellStyle insuranceItemE = nCell2.getCellStyle();
		//合并并添加单元格
		region = new CellRangeAddress(rowNo++, rowNo-1, 0, 5);
		sheet.addMergedRegion(region);
		//创建该行并设置行高
		nRow = sheet.createRow(rowNo-1);
		nRow.setHeightInPoints(77);
		//保险条款英文
		nCell = nRow.createCell(0);
		nCell.setCellValue(nCell2.getStringCellValue());
		nCell.setCellStyle(insuranceItemE);
		
		nRow2 = sheet2.getRow(36);
		nCell2 = nRow2.getCell(0);
		CellStyle resposibilityC = nCell2.getCellStyle();
		//合并并添加单元格
		region = new CellRangeAddress(rowNo++, rowNo-1, 0, 5);
		sheet.addMergedRegion(region);
		//创建该行并设置行高
		nRow = sheet.createRow(rowNo-1);
		nRow.setHeightInPoints(63);;
		//责任条款中文
		nCell = nRow.createCell(0);
		nCell.setCellValue(nCell2.getStringCellValue());
		nCell.setCellStyle(resposibilityC);
		
		nRow2 = sheet2.getRow(37);
		nCell2 = nRow2.getCell(0);
		CellStyle resposibilityE = nCell2.getCellStyle();
		//合并并添加单元格
		region = new CellRangeAddress(rowNo++, rowNo-1, 0, 5);
		sheet.addMergedRegion(region);
		//创建该行并设置行高
		nRow = sheet.createRow(rowNo-1);
		nRow.setHeightInPoints(118);
		//责任条款英文
		nCell = nRow.createCell(0);
		nCell.setCellValue(nCell2.getStringCellValue());
		nCell.setCellStyle(resposibilityE);
		
		nRow2 = sheet2.getRow(38);
		nCell2 = nRow2.getCell(0);
		CellStyle arbitrationC = nCell2.getCellStyle();
		//合并并添加单元格
		region = new CellRangeAddress(rowNo++, rowNo-1, 0, 5);
		sheet.addMergedRegion(region);
		//创建该行并设置行高
		nRow = sheet.createRow(rowNo-1);
		nRow.setHeightInPoints(47);
		//仲裁条款中文
		nCell = nRow.createCell(0);
		nCell.setCellValue(nCell2.getStringCellValue());
		nCell.setCellStyle(arbitrationC);
		
		nRow2 = sheet2.getRow(39);
		nCell2 = nRow2.getCell(0);
		CellStyle arbitrationE = nCell2.getCellStyle();
		//合并并添加单元格
		region = new CellRangeAddress(rowNo++, rowNo-1, 0, 5);
		sheet.addMergedRegion(region);
		//创建该行并设置行高
		nRow = sheet.createRow(rowNo-1);
		nRow.setHeightInPoints(91);
		//仲裁条款英文
		nCell = nRow.createCell(0);
		nCell.setCellValue(nCell2.getStringCellValue());
		nCell.setCellStyle(arbitrationE);
		
		//买卖双方签字栏
		nRow2 = sheet2.getRow(41);
		nCell2 = nRow2.getCell(0);
		CellStyle seller = nCell2.getCellStyle();
		nRow = sheet.createRow(rowNo+=2);
		nRow.setHeightInPoints(24);
		nCell = nRow.createCell(0);
		nCell.setCellValue(nCell2.getStringCellValue());
		nCell.setCellStyle(seller);

		nCell2 = nRow2.getCell(3);
		CellStyle buyer = nCell2.getCellStyle();
		nCell = nRow.createCell(3);
		nCell.setCellValue(nCell2.getStringCellValue());
		nCell.setCellStyle(buyer);
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		DownloadUtil du = new DownloadUtil();
		wb.write(os);
		du.download(os, response, "销售合同"+contractC.getId()+".xlsx");

		wb.close();
		os.close();
	}
	
}
