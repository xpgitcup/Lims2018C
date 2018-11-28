package cn.edu.cup.common

import jxl.Workbook
import jxl.write.Label
import jxl.write.WritableSheet
import jxl.write.WritableWorkbook

class ExcelByJxlService {

    /*
    * 对象导出到excel文件
    *
    * objectModel:
    * head
    * data
    * */

    def exportDataTable2ExcelFile(objectModel, fileName) {
        if (objectModel.head && objectModel.data) {
            File file = new File(fileName)
            WritableWorkbook book = Workbook.createWorkBook(file)
            WritableSheet sheet = book.createSheet("sheet1", 0)

            // 先输出标题
            objectModel.head.eachWidthIndex { e, i ->
                def label = new Label(i, 0, "${e}")
                sheet.addCell(label)
            }

            //然后输出数据
            objectModel.data.eachWidthIndex { e, i ->
                e.eachWidthIndex { ee, j ->
                    def label = new Label(j, i+1, "${ee}")
                    sheet.addCell(label)
                }
            }
        } else {
            println("${objectModel}格式不对！")
        }
    }
}

