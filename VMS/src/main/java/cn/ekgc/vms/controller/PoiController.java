package cn.ekgc.vms.controller;

import cn.ekgc.vms.base.controller.BaseController;
import cn.ekgc.vms.pojo.entity.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Controller("poiController")
@RequestMapping("/poi")
public class PoiController extends BaseController {

	@GetMapping(value = "/ward")
	public String forward()throws Exception{
		return "poi/poi_index";
	}

	@PostMapping(value = "/import")
	@ResponseBody
	public boolean importExcel(@RequestParam(value = "uploadFile") MultipartFile uploadFile)throws Exception{
		//通过使用 MulitipartFile 获得上传文件
		//文件输入流对象
		InputStream input = uploadFile.getInputStream();
		//通过输入流，获得POI操作 Excel 对应的对象 XSSWorkBoot
		System.out.println(input);
		Workbook workbook = new XSSFWorkbook(input);
		//加载输入流
		//通过Workbook对象 获得第一个sheet对象
		Sheet sheet = workbook.getSheetAt(0);

		//通过获得的sheet对象，获得此时有多少行数据
		int rows = sheet.getPhysicalNumberOfRows();
		System.out.println("该sheet有"+rows+"数据");
		//循环数据循环
		for(int i = 0;i< rows;i++){
			Row row = sheet.getRow(i);
			//获得该行数据共有的单元格数量
			int cells = row.getPhysicalNumberOfCells();
			System.out.println("该行具有多少个"+cells+"单元格数量");
			//获得该行每个单元格的数量
			for (int j = 0; j < cells; j++){
				Cell cell = row.getCell(j);
			    if (cell.getCellType().equals(CellType.STRING)){
			    	System.out.println(cell.getStringCellValue() + "\t");
			    }else if (cell.getCellType().equals(CellType.NUMERIC)){
			    	System.out.println(cell.getNumericCellValue() + "\t");
			    }
			}
		}
		return true;
	}
	@GetMapping(value = "/download")
	@ResponseBody
	public int downloadExcel()throws Exception{
		XSSFWorkbook workbook = new XSSFWorkbook();
		User user = new User();
		//通过 XSSFWorkbook 创建对应的 XSSFsheet 对象
		Sheet sheet = workbook.createSheet("sheet");
		//通过 XSSFSheet 创建对应的 XSSFRow
		Row row = sheet.createRow(0);
		//通过 XSSFRow 创建第一个单元格XSSFCell
		Cell cell = row.createCell(0);
		cell.setCellValue("冷暖");
		Cell cell2 = row.createCell(1);
		cell2.setCellValue("女");
		if (cell2.equals("女")){
			Integer integer = new Integer((String.valueOf(cell2)));
			return 0;
		}else if (cell2.equals("男")){
			return 1;
		}
		Cell cell3 = row.createCell(2);
		cell3.setCellValue("18165789968");
		user.setUsername(String.valueOf(cell));
        System.out.println(user.getGender());
        user.setCellphone(String.valueOf(cell3));
        System.out.println(user.getCellphone());
		//进行 Excel 下载
		//设置消息内容，属于流文件
		response.setContentType("application/octet-stream");
		//设置消息头
		//filename=text.xlsx 格式名
		response.setHeader("Content-disposition", "attachment;filename=text.xlsx");
		//运用输出流将文件读取
	    workbook.write(response.getOutputStream());
		return 0;
	}
}
