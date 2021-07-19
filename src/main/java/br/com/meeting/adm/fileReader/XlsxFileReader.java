package br.com.meeting.adm.fileReader;

import br.com.meeting.adm.entity.ParticipationFormEntity;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class XlsxFileReader {

    public List<ParticipationFormEntity> readFileBuildForms(MultipartFile multipartFile) throws IOException {

        Sheet sheet = getFirstSheetFromMultipartFile(multipartFile);

        List<ParticipationFormEntity> forms = new LinkedList<>();

        sheet.rowIterator().forEachRemaining(row -> {

            if (row.getRowNum() == sheet.getFirstRowNum() || row.getCell(0) == null) return;

            forms.add(ParticipationFormEntity.builder()
                    .id(Double.valueOf(cellValue(row, 1)).intValue())
                    .gender(cellValue(row, 5))
                    .age(Double.valueOf(row.getCell(7).getNumericCellValue()).intValue())
                    .height(Double.valueOf(cellValue(row, 4)))
                    .church(cellValue(row, 15).isEmpty() ? cellValue(row, 14) : cellValue(row, 15))
                    .location(cellValue(row, 10))
                    .build());
        });

        return forms;
    }

    private Sheet getFirstSheetFromMultipartFile(MultipartFile multipartFile) throws IOException {
        ByteArrayInputStream stream = new ByteArrayInputStream(multipartFile.getBytes());
        return WorkbookFactory.create(stream).getSheetAt(0);
    }

    private String cellValue(Row row, int cellNum) {
        return row.getCell(cellNum).toString();
    }

}
