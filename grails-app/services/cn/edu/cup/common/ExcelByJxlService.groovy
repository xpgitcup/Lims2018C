package cn.edu.cup.common

import jxl.Workbook
import jxl.write.Label
import jxl.write.WritableSheet
import jxl.write.WritableWorkbook

class ExcelByJxlService {

    /*
    * 对象导出到excel文件
    * */

    def exportDataTable2ExcelFile(List dataTable, fileName) {
        File file = new File(fileName)
        if (file) {
            WritableWorkbook book = Workbook.createWorkbook(file)
            WritableSheet sheet = book.createSheet("sheet1", 0)

            // 先输出标题
            dataTable.eachWithIndex { e, i ->
                e.eachWithIndex { ee, j ->
                    def label = new Label(i, j, "${ee}")
                    sheet.addCell(label)
                }
            }
            book.write()
            book.close()
        }
        return fileName
    }
}

