package br.com.meeting.adm.fileReader;

import br.com.meeting.adm.entity.ParticipationFormEntity;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class XlsxFileReader {

    private final String PATH = "/home/ninogueira/Documents/Nilson/Desafios";
    private final String NAME = "Fichas Mrnta.xlsx";

    public List<ParticipationFormEntity> readFileBuildForms() throws IOException {
        File file = new File(PATH + "/" + NAME);

        if (file == null) {
            return new LinkedList<>();
        }

        Sheet sheet = new XSSFWorkbook(new FileInputStream(file)).getSheetAt(0);

        List<ParticipationFormEntity> forms = new LinkedList<>();

        sheet.rowIterator().forEachRemaining(row -> {

            if (row.getRowNum() == sheet.getFirstRowNum() || row.getCell(0) == null) return;

            forms.add(ParticipationFormEntity.builder()
                    .id(new Double(cellValue(row, 1)).intValue())
                    .gender(cellValue(row, 5))
                    .height(new Double(cellValue(row, 4)))
                    .church(cellValue(row, 15).isEmpty() ? cellValue(row, 14) : cellValue(row, 15))
                    .location(cellValue(row, 10))
                    .build());
        });

        return forms;
    }

    private String cellValue(Row row, int cellNum) {
        return row.getCell(cellNum).toString();
    }

}
