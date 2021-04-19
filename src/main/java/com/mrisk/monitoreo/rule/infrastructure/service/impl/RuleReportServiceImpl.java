package com.mrisk.monitoreo.rule.infrastructure.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import com.mrisk.monitoreo.rule.application.service.RuleReportService;
import com.mrisk.monitoreo.rule.domain.Rule;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class RuleReportServiceImpl implements RuleReportService {

    private static final String FORMAT_DATE = "dd-MM-yyyy";

    private MessageSource messageSource;

    private MessageSourceAccessor messages;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
        this.messages = new MessageSourceAccessor(this.messageSource, LocaleContextHolder.getLocale());
    }

    @Override
    public void setMessageSource(Locale locale) {
        this.messages = new MessageSourceAccessor(messageSource, locale);
    }

    @Override
    public String generateRuleReportEncode(List<String> headers, List<Rule> rules) {

        String fileEncode;
        try {
            File file = generateXlsxFile(headers, rules);
            if (Objects.isNull(file)) {
                return null;
            }
            fileEncode = Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            log.error("generateRuleReportEncode : error al generar encode reporte ", e);
            return null;
        }
        return fileEncode;
    }

    private File generateXlsxFile(List<String> headers, List<Rule> rule) {
        File excelFile = null;
        XSSFWorkbook workbook = new XSSFWorkbook(XSSFWorkbookType.XLSX);
        try {

            XSSFSheet sheet = workbook.createSheet(messages.getMessage("rule.report.sheet_name"));

            generateHeaders(headers, sheet);
            fillDataRows(rule, sheet);

            FileOutputStream out = null;

            LocalDateTime now = LocalDateTime.now();
            String date = DateTimeFormatter.ofPattern(FORMAT_DATE).format(now);
            String tDir = System.getProperty("java.io.tmpdir");
            excelFile = new File(tDir, messages.getMessage("rule.report.name") + date + ".xlsx");
            out = new FileOutputStream(excelFile);
            workbook.write(out);
            out.close();
            workbook.close();
        } catch (IOException e) {
            log.error("error generateXlsxFile", e);
            return null;
        }

        return excelFile;
    }

    private void fillDataRows(List<Rule> rule, XSSFSheet sheet) {

        for (int i = 0; i < rule.size(); i++) {
            Row dataRow = sheet.createRow(i + 1);
            dataRow.createCell(0).setCellValue(rule.get(i).getNormId());
            dataRow.createCell(1).setCellValue(rule.get(i).getName());

            if (Objects.nonNull(rule.get(i).getNumber())) {
                dataRow.createCell(2).setCellValue(rule.get(i).getNumber());
            }
            dataRow.createCell(3).setCellValue(rule.get(i).getDetail());
            dataRow.createCell(4).setCellValue(messages.getMessage("rule.type." + rule.get(i).getRuleType().getName()));
            dataRow.createCell(5)
                    .setCellValue(messages.getMessage("rule.components." + rule.get(i).getComponent().getName()));
            if (Objects.nonNull(rule.get(i).getOrganismIssuingId())) {
                dataRow.createCell(6).setCellValue(rule.get(i).getOrganismIssuingId());
            }

            if (Objects.nonNull(rule.get(i).getDatePublication())) {
                dataRow.createCell(7).setCellValue(
                        DateTimeFormatter.ofPattern(FORMAT_DATE).format(rule.get(i).getDatePublication()));
            }

        }
    }

    private void generateHeaders(List<String> headers, XSSFSheet sheet) {
        Row header = sheet.createRow(0);
        for (int i = 0; i < headers.size(); i++) {
            header.createCell(i).setCellValue(messages.getMessage("rule.report.header." + headers.get(i)));
        }
    }

}
