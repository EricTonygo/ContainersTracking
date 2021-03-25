/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.guce.containers.tracking.models.ContainerMovement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author penda
 */
public class containersTrackingUtils {

    private static final Logger logger = LoggerFactory.getLogger(containersTrackingUtils.class);

    public static List<ContainerMovement> getContainersMovementsFromFile(InputStream inputStream) throws FileNotFoundException, IOException, Exception {
        List<ContainerMovement> containerMovementList = new ArrayList<>();

        if (inputStream != null) {
            // création d'un objet XSSF Workbook pour le fichier excel
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            // récupératio de la première feuille contenant les données des mouvements des conteneurs
            XSSFSheet sheet = workbook.getSheetAt(0);

            // we iterate on rows
            Iterator<Row> rowIt = sheet.iterator();
            Integer rowNum = 0;
            Integer cellNum;
            ContainerMovement containerMovement;
            while (rowIt.hasNext()) {
                cellNum = 0;
                Row row = rowIt.next();
                // Iteration des cellules de la ligne courante
                Iterator<Cell> cellIterator = row.cellIterator();
                if (rowNum > 0) {
                    containerMovement = new ContainerMovement();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        switch (cellNum) {
                            case 0:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    containerMovement.setContainerNumber(cell.getStringCellValue());
                                } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                                    containerMovement.setContainerNumber(cell.getNumericCellValue() + "");
                                }
                                break;
                            case 1:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    String movementDateStr = cell.getStringCellValue();
                                    if (movementDateStr != null && !movementDateStr.equals("")) {
                                        containerMovement.setMovementDate(new SimpleDateFormat("dd/MM/yyyy").parse(movementDateStr));
                                    }
                                } else {
                                    containerMovement.setMovementDate(cell.getDateCellValue());
                                }
                                break;
                            case 2:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    String movementHourStr = cell.getStringCellValue();
                                    if (movementHourStr != null && !movementHourStr.equals("")) {
                                        containerMovement.setMovementHour(new SimpleDateFormat("HH:mm").parse(movementHourStr));
                                    }
                                } else {
                                   containerMovement.setMovementHour(cell.getDateCellValue());
                                    
                                }
                                break;
                            case 3:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    containerMovement.setMovementType(cell.getStringCellValue());
                                } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                                    containerMovement.setMovementType(cell.getNumericCellValue() + "");
                                }
                                break;
                            case 4:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    containerMovement.setBlNumber(cell.getStringCellValue());
                                } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                                    containerMovement.setBlNumber(cell.getNumericCellValue() + "");
                                }
                                break;
                            case 5:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    containerMovement.setVesselName(cell.getStringCellValue());
                                } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                                    containerMovement.setVesselName(cell.getNumericCellValue() + "");
                                }
                                break;
                            case 6:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    containerMovement.setContainerType(cell.getStringCellValue());
                                } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                                    containerMovement.setContainerType(cell.getNumericCellValue() + "");
                                }
                                break;
                            case 7:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    containerMovement.setContainerSize(cell.getStringCellValue());
                                } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                                    containerMovement.setContainerType(cell.getNumericCellValue() + "");
                                }
                                break;
                            case 8:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    containerMovement.setTripNumber(cell.getStringCellValue());
                                } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                                    containerMovement.setTripNumber(cell.getNumericCellValue() + "");
                                }
                                break;
                            case 9:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    containerMovement.setDeparturePlace(cell.getStringCellValue());
                                } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                                    containerMovement.setDeparturePlace(cell.getNumericCellValue() + "");
                                }
                                break;
                            case 10:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    containerMovement.setArrivalPlace(cell.getStringCellValue());
                                } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                                    containerMovement.setArrivalPlace(cell.getNumericCellValue() + "");
                                }
                                break;
                            case 11:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    containerMovement.setLastLocalisation(cell.getStringCellValue());
                                } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                                    containerMovement.setLastLocalisation(cell.getNumericCellValue() + "");
                                }
                                break;
                            case 12:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    String latitudeStr = cell.getStringCellValue().replaceAll(",", ".");
                                    containerMovement.setLatitude(Double.parseDouble(latitudeStr));
                                } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                                    containerMovement.setLatitude((double) cell.getNumericCellValue());
                                }
                                break;
                            case 13:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    String longitudeStr = cell.getStringCellValue().replaceAll(",", ".");
                                    containerMovement.setLongitude(Double.parseDouble(longitudeStr));
                                } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                                    containerMovement.setLongitude((double) cell.getNumericCellValue());
                                }
                                break;
                            case 14:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    String expectedArrivalDateStr = cell.getStringCellValue();
                                    if (expectedArrivalDateStr != null && !expectedArrivalDateStr.equals("")) {
                                        containerMovement.setExpectedArrivalDate(new SimpleDateFormat("dd/MM/yyyy").parse(expectedArrivalDateStr));
                                    }
                                } else {
                                    containerMovement.setExpectedArrivalDate(cell.getDateCellValue());
                                }
                                break;
                            default:
                                break;
                        }
                        cellNum++;
                    }
                    containerMovementList.add(containerMovement);
                }
                rowNum++;
            }
            workbook.close();
        }
        return containerMovementList;
    }
}
