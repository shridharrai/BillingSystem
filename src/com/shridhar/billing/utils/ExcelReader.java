package com.shridhar.billing.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.shridhar.billing.dto.ProductDTO;

public class ExcelReader {
	private ExcelReader() {}
	public static ArrayList<ProductDTO> readExcel(File path) throws IOException {
		ArrayList<ProductDTO> products = new ArrayList<ProductDTO>();
		FileInputStream fs = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.rowIterator();
		boolean isFirstRow = true;
		while(rows.hasNext()) {
			ProductDTO productDTO = new ProductDTO();
			Row currentRow = rows.next();
			if(isFirstRow) {
				isFirstRow = false;
				continue;
			}
			int count = 1;
			Iterator<Cell> cells = currentRow.cellIterator();
			while(cells.hasNext()) {
				String cellValue = "";
				Cell currentCell = cells.next();
				if(currentCell.getCellType() == CellType.STRING) {
					cellValue = currentCell.getStringCellValue();
				}
				else
					if(currentCell.getCellType() == CellType.NUMERIC) {
						cellValue = String.valueOf(currentCell.getNumericCellValue());
					}
				
				if(count == 1) {
					productDTO.setId((int)Double.parseDouble(cellValue));
				}
				else
					if(count == 2) {
						productDTO.setName(cellValue);
					}
					else
						if(count == 3) {
							productDTO.setPrice((int)Double.parseDouble(cellValue));
						}
						else
							if(count == 4) {
								productDTO.setQuantity((int)Double.parseDouble(cellValue));
							}
				count++;
			}  // cell loop end
			products.add(productDTO);
		}  // row loop end
		return products;
	}

}
