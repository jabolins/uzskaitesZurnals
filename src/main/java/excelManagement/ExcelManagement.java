package excelManagement;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelManagement {

    static final String EXCEL_PATH = "testaExcel.xlsx";
    static final String NAME_OF_USER = "Juris Āboliņš 231273-10324";
    private static final int COUNT_OF_SHEETS_IN_FILE = 12;
    private static final int COUNT_OF_ROW_FOR_HEAD = 11;

    public void createExcelFile() throws IOException {
        Workbook workbook = new XSSFWorkbook();

        createSheetsForExcelFile(workbook);

        FileOutputStream fileOutputStream = new FileOutputStream(EXCEL_PATH); // šeit vēlāk vajag pārtaisīt uz iespēju izvēlēties vai konstanti
        workbook.write(fileOutputStream);

        fileOutputStream.close();

        createRowAndColonForHead(workbook);
        createMergedAreasForHead(workbook);
        createBasicHeaders(workbook);
        createTopOfTables(workbook);
    }

    public static void createSheetsForExcelFile(Workbook workbook) {
        String[] nameOfMonths = {"Janvāris", "Februāris", "Marts", "Aprīlis", "Maijs", "Jūnijs", "Jūlijs", "Augusts", "Septembris", "Oktobris", "Novembris", "Decembris"};

        for (int i = 0; i < COUNT_OF_SHEETS_IN_FILE; i++) {
            Sheet sheet = workbook.createSheet(nameOfMonths[i]);
        }

    }

    private void createRowAndColonForHead(Workbook workbook) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(EXCEL_PATH);
        for (int i = 0; i < COUNT_OF_SHEETS_IN_FILE; i++) {
            for (int j = 0; j < COUNT_OF_ROW_FOR_HEAD; j++) {
                getRow(workbook.getSheetAt(i), j);
            }
            defineSizeOfCells(workbook.getSheetAt(i));
        }
        fileInputStream.close();

        FileOutputStream fileOutputStream = new FileOutputStream(EXCEL_PATH);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }

    private void defineSizeOfCells(Sheet sheet){

        int FirstRowNrOfTopOfTable = 6;
        int SecondRowNrOfTopOfTable = 7;
        sheet.getRow(FirstRowNrOfTopOfTable).setHeight((short) 500);
        sheet.getRow(SecondRowNrOfTopOfTable).setHeight((short) 1700);

        int defaultWidth = 8;
        int largeWidth = 14 * 256;
        int veryLargeWidth = 20 * 256;
        sheet.setDefaultColumnWidth(defaultWidth);
        sheet.setColumnWidth(2, largeWidth);
        sheet.setColumnWidth(3, veryLargeWidth);
        sheet.setColumnWidth(4, veryLargeWidth);
        sheet.setColumnWidth(5, largeWidth);
        sheet.setColumnWidth(16, largeWidth);
        sheet.setColumnWidth(17, largeWidth);
        sheet.setColumnWidth(19, largeWidth);
        sheet.setColumnWidth(20, largeWidth);
        sheet.setColumnWidth(21, largeWidth);
        sheet.setColumnWidth(22, largeWidth);


    }

    private void createMergedAreasForHead(Workbook workbook) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(EXCEL_PATH);
        for (int i = 0; i < COUNT_OF_SHEETS_IN_FILE; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            createMergedCells(sheet, 0, 0, 2, 12);
            createMergedCells(sheet, 1, 1, 2, 12);
            createMergedCells(sheet, 3, 3, 2, 12);

            createMergedCellsWidthBorders(sheet, 6, 6, 0, 1);
            createMergedCellsWidthBorders(sheet, 6, 7, 2, 2);
            createMergedCellsWidthBorders(sheet, 6, 7, 3, 3);
            createMergedCellsWidthBorders(sheet, 6, 7, 4, 4);
            createMergedCellsWidthBorders(sheet, 6, 7, 5, 5);
            createMergedCellsWidthBorders(sheet, 6, 6, 6, 7);
            createMergedCellsWidthBorders(sheet, 6, 6, 8, 9);
            createMergedCellsWidthBorders(sheet, 6, 6, 10, 11);
            createMergedCellsWidthBorders(sheet, 6, 6, 12, 17);
            createMergedCellsWidthBorders(sheet, 6, 6, 18, 23);
        }
        fileInputStream.close();

        FileOutputStream fileOutputStream = new FileOutputStream(EXCEL_PATH);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }

    private void createBasicHeaders(Workbook workbook) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(EXCEL_PATH);
        for (int i = 0; i < COUNT_OF_SHEETS_IN_FILE; i++) {
            Sheet sheet = workbook.getSheetAt(i);

            Cell cell1 = getCell(sheet, 0, 2);
            cell1.setCellValue("Saimnieciskās darbības ieņēmumu un izdevumu uzskaites žurnāls");
            cell1.setCellStyle(stileOfHeader(workbook));

            Cell cell2 = getCell(sheet, 1, 2);
            cell2.setCellValue(getTextForBasicHeader(i, 2021)); // vēlāk šo jāpārtaisa uz automātisku gada  un mēneša izvēli
            cell2.setCellStyle(stileOfHeader(workbook));

            Cell cell3 = getCell(sheet, 3, 2);
            cell3.setCellValue(NAME_OF_USER);
            cell3.setCellStyle(stileOfHeader(workbook));
            fileInputStream.close();

            FileOutputStream fileOutputStream = new FileOutputStream(EXCEL_PATH);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        }
    }

    private String getTextForBasicHeader(int NrOfSheet, int currentYear) {
        switch (NrOfSheet) {
            case 0:
                return " par " + currentYear + ". gada janvāri";
            case 1:
                return " par " + currentYear + ". gada februāri";
            case 2:
                return " par " + currentYear + ". gada martu";
            case 3:
                return " par " + currentYear + ". gada aprīli";
            case 4:
                return " par " + currentYear + ". gada maiju";
            case 5:
                return " par " + currentYear + ". gada jūniju";
            case 6:
                return " par " + currentYear + ". gada jūliju";
            case 7:
                return " par " + currentYear + ". gada augustu";
            case 8:
                return " par " + currentYear + ". gada septembri";
            case 9:
                return " par " + currentYear + ". gada oktobri";
            case 10:
                return " par " + currentYear + ". gada novembri";
            case 11:
                return " par " + currentYear + ". gada decebri";
        }
        return null;
    }

    private void createTopOfTables(Workbook workbook) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(EXCEL_PATH);
        for (int i = 0; i < COUNT_OF_SHEETS_IN_FILE; i++) {
            Sheet sheet = workbook.getSheetAt(i);

            fillCellsForTopOfTable(workbook, sheet);

            fileInputStream.close();

            FileOutputStream fileOutputStream = new FileOutputStream(EXCEL_PATH);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        }
    }

    private void fillCellsForTopOfTable(Workbook workbook, Sheet sheet) {

        Cell cell1 = getCell(sheet, 6, 0);
        cell1.setCellValue("Ieraksta");
        cell1.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell2 = getCell(sheet, 7, 0);
        cell2.setCellValue("kārtas numurs");
        cell2.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell3 = getCell(sheet, 7, 1);
        cell3.setCellValue("datums");
        cell3.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell4 = getCell(sheet, 6, 2);
        cell4.setCellValue("Dokumenta nosaukums, numurs un datums");
        cell4.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell5 = getCell(sheet, 6, 3);
        cell5.setCellValue("Dokumenta autors, darījuma partneris (fiziskās personas vārds, uzvārds vai juridiskās personas nosaukums)");
        cell5.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell6 = getCell(sheet, 6, 4);
        cell6.setCellValue("Saimnieciskā darījuma apraksts");
        cell6.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell7 = getCell(sheet, 6, 5);
        cell7.setCellValue("Analītiskās uzskaites reģistra Nr. vai nosaukums");
        cell7.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell8 = getCell(sheet, 6, 6);
        cell8.setCellValue("Kase, EUR");
        cell8.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell9 = getCell(sheet, 7, 6);
        cell9.setCellValue("saņemts");
        cell9.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell10 = getCell(sheet, 7, 7);
        cell10.setCellValue("izsniegts");
        cell10.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell11 = getCell(sheet, 6, 8);
        cell11.setCellValue("Kredītiestāžu konti, EUR");
        cell11.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell12 = getCell(sheet, 7, 8);
        cell12.setCellValue("saņemts");
        cell12.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell13 = getCell(sheet, 7, 9);
        cell13.setCellValue("izsniegts");
        cell13.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell14 = getCell(sheet, 6, 10);
        cell14.setCellValue("Citi maksāšanas līdzekļi, EUR");
        cell14.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell15 = getCell(sheet, 7, 10);
        cell15.setCellValue("saņemts");
        cell15.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell16 = getCell(sheet, 7, 11);
        cell16.setCellValue("izsniegts");
        cell16.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell17 = getCell(sheet, 6, 12);
        cell17.setCellValue("Ieņēmumi, EUR");
        cell17.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell18 = getCell(sheet, 7, 12);
        cell18.setCellValue("ieņēmumi no lauksaimnieciskās ražošanas");
        cell18.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell19 = getCell(sheet, 7, 13);
        cell19.setCellValue("ieņēmumi no citiem saimnieciskās darbības veidiem");
        cell19.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell20 = getCell(sheet, 7, 14);
        cell20.setCellValue("subsīdijas");
        cell20.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell21 = getCell(sheet, 7, 15);
        cell21.setCellValue("neapliekamie ienākumi");
        cell21.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell22 = getCell(sheet, 7, 16);
        cell22.setCellValue("ieņēmumi, kas nav attiecināmi uz ienākuma nodokļa aprēķināšanu");
        cell22.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell23 = getCell(sheet, 7, 17);
        cell23.setCellValue("kopā (13.-17.aile)");
        cell23.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell24 = getCell(sheet, 6, 18);
        cell24.setCellValue("izdevumi, EUR");
        cell24.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell25 = getCell(sheet, 7, 18);
        cell25.setCellValue("izdevumi, kas saistīti ar lauksaimniecisko ražošanu");
        cell25.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell26 = getCell(sheet, 7, 19);
        cell26.setCellValue("izdevumi, kas saistīti ar citiem saimnieciskās darbības veidiem");
        cell26.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell27 = getCell(sheet, 7, 20);
        cell27.setCellValue("proporcionāli sadalāmie izdevumi");
        cell27.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell28 = getCell(sheet, 7, 21);
        cell28.setCellValue("ar saimniecisko darbību nesaistītās izmaksas");
        cell28.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell29 = getCell(sheet, 7, 22);
        cell29.setCellValue("izdevumi, kas nav attiecināmi uz ienākuma nodokļa aprēķināšanu");
        cell29.setCellStyle(stileOfTopOfTable(workbook));

        Cell cell30 = getCell(sheet, 7, 23);
        cell30.setCellValue("kopā (19.-23.aile)");
        cell30.setCellStyle(stileOfTopOfTable(workbook));
    }

    private void createMergedCells(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
        CellRangeAddress range = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
        sheet.addMergedRegion(range);
    }

    private void createMergedCellsWidthBorders(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
        CellRangeAddress range = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
        sheet.addMergedRegion(range);
        RegionUtil.setBorderBottom(BorderStyle.THIN, range, sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN, range, sheet);
        RegionUtil.setBorderTop(BorderStyle.THIN, range, sheet);
        RegionUtil.setBorderLeft(BorderStyle.THIN, range, sheet);
    }

    private Row getRow(Sheet sheet, int rowNr) {
        Row row = sheet.getRow(rowNr);
        if (row == null) {
            row = sheet.createRow(rowNr);
        }
        return row;
    }

    private Cell getCell(Sheet sheet, int rowNr, int colonNr) {
        Cell cell;
        Row row = sheet.getRow(rowNr);
        if (row == null) {
            row = sheet.createRow(rowNr);
        }
        cell = row.getCell(colonNr);
        if (cell == null) {
            cell = row.createCell(colonNr);
        }
        return cell;
    }

    public void addInformationToCell(int sheetNr, int colonNr, int rowNr, String cellInformation) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(EXCEL_PATH);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(sheetNr);
        Cell cell = getCell(sheet, rowNr, colonNr);
        cell.setCellValue(cellInformation);
        fileInputStream.close();

        FileOutputStream fileOutputStream = new FileOutputStream(EXCEL_PATH);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }

    public static CellStyle stileOfHeader(Workbook workbook) {

        CellStyle cellstyle = workbook.createCellStyle();

        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 15);
        font.setBold(true);
        cellstyle.setFont(font);
        cellstyle.setAlignment(HorizontalAlignment.CENTER);
        return cellstyle;
    }

    private CellStyle stileOfTopOfTable(Workbook workbook) {

        CellStyle cellstyle = workbook.createCellStyle();
        cellstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellstyle.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
        cellstyle.setAlignment(HorizontalAlignment.CENTER);
        cellstyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellstyle.setWrapText(true);

        cellstyle.setBorderBottom(BorderStyle.THIN);
        cellstyle.setBorderRight(BorderStyle.THIN);
        cellstyle.setBorderLeft(BorderStyle.THIN);
        cellstyle.setBorderTop(BorderStyle.THIN);


        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 10);
        cellstyle.setFont(font);
        return cellstyle;
    }

}

