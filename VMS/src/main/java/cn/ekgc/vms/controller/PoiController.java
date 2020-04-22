package cn.ekgc.vms.controller;

import cn.ekgc.vms.base.controller.BaseController;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
		Workbook workbook = new HSSFWorkbook(input);
		//加载输入流
		//通过Workbook对象 获得第一个sheet对象
		Sheet sheet = workbook.getSheetAt(0);

		//通过获得的sheet对象，获得此时有多少行数据
		int rows = sheet.getPhysicalNumberOfRows();
		System.out.println(rows);
		return true;
	}
}
