/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.guce.containers.tracking.models.MouvementConteneur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;

/**
 *
 * @author penda
 */
public class containersTrackingUtils {

    private static final Logger logger = LoggerFactory.getLogger(containersTrackingUtils.class);

    public static List<MouvementConteneur> getContainersMovementsFromFile(InputStream inputStream) throws FileNotFoundException, IOException, Exception {
        List<MouvementConteneur> mouvementConteneurList = new ArrayList<>();

        if (inputStream != null) {
            // création d'un objet XSSF Workbook pour le fichier excel
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            // récupératio de la première feuille contenant les données des mouvements des conteneurs
            XSSFSheet sheet = workbook.getSheetAt(0);

            // we iterate on rows
            Iterator<Row> rowIt = sheet.iterator();
            Integer rowNum = 0;
            Integer cellNum;
            MouvementConteneur mouvementConteneur;
            while (rowIt.hasNext()) {
                cellNum = 0;
                Row row = rowIt.next();
                // Iteration des cellules de la ligne courante
                Iterator<Cell> cellIterator = row.cellIterator();
                if (rowNum > 0) {
                    mouvementConteneur = new MouvementConteneur();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        switch (cellNum) {
                            case 0:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    mouvementConteneur.setNumeroConteneur(cell.getStringCellValue());
                                } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                                    mouvementConteneur.setNumeroConteneur(cell.getNumericCellValue() + "");
                                }
                                break;
                            case 1:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    String movementDateStr = cell.getStringCellValue();
                                    if (movementDateStr != null && !movementDateStr.equals("")) {
                                        mouvementConteneur.setDateMouvement(new SimpleDateFormat("dd/MM/yyyy").parse(movementDateStr));
                                    }
                                } else {
                                    mouvementConteneur.setDateMouvement(cell.getDateCellValue());
                                }
                                break;
                            case 2:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    String movementHourStr = cell.getStringCellValue();
                                    if (movementHourStr != null && !movementHourStr.equals("")) {
                                        mouvementConteneur.setHeureMouvement(new SimpleDateFormat("HH:mm").parse(movementHourStr));
                                    }
                                } else {
                                   mouvementConteneur.setHeureMouvement(cell.getDateCellValue());
                                    
                                }
                                break;
                            case 3:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    mouvementConteneur.setTypeMouvement(cell.getStringCellValue());
                                } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                                    mouvementConteneur.setTypeMouvement(cell.getNumericCellValue() + "");
                                }
                                break;
                            case 4:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    mouvementConteneur.setNumeroBL(cell.getStringCellValue());
                                } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                                    mouvementConteneur.setNumeroBL(cell.getNumericCellValue() + "");
                                }
                                break;
                            case 5:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    mouvementConteneur.setNomNavire(cell.getStringCellValue());
                                } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                                    mouvementConteneur.setNomNavire(cell.getNumericCellValue() + "");
                                }
                                break;
                            case 6:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    mouvementConteneur.setTypeConteneur(cell.getStringCellValue());
                                } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                                    mouvementConteneur.setTypeConteneur(cell.getNumericCellValue() + "");
                                }
                                break;
                            case 7:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    mouvementConteneur.setTailleConteneur(cell.getStringCellValue());
                                } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                                    mouvementConteneur.setTailleConteneur((int)cell.getNumericCellValue() + "");
                                }
                                break;
                            case 8:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    mouvementConteneur.setNumeroVoyage(cell.getStringCellValue());
                                } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                                    mouvementConteneur.setNumeroVoyage(cell.getNumericCellValue()+ "");
                                }
                                break;
                            case 9:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    mouvementConteneur.setLieuDepart(cell.getStringCellValue());
                                } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                                    mouvementConteneur.setLieuDepart(cell.getNumericCellValue() + "");
                                }
                                break;
                            case 10:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    mouvementConteneur.setLieuArrivee(cell.getStringCellValue());
                                } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                                    mouvementConteneur.setLieuArrivee(cell.getNumericCellValue() + "");
                                }
                                break;
                            case 11:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    mouvementConteneur.setDerniereLocalisation(cell.getStringCellValue());
                                } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                                    mouvementConteneur.setDerniereLocalisation(cell.getNumericCellValue() + "");
                                }
                                break;
                            case 12:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    String latitudeStr = cell.getStringCellValue().replaceAll(",", ".");
                                    mouvementConteneur.setLatitude(Double.parseDouble(latitudeStr));
                                } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                                    mouvementConteneur.setLatitude((double) cell.getNumericCellValue());
                                }
                                break;
                            case 13:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    String longitudeStr = cell.getStringCellValue().replaceAll(",", ".");
                                    mouvementConteneur.setLongitude(Double.parseDouble(longitudeStr));
                                } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                                    mouvementConteneur.setLongitude((double) cell.getNumericCellValue());
                                }
                                break;
                            case 14:
                                if (cell.getCellTypeEnum() == CellType.STRING) {
                                    String expectedArrivalDateStr = cell.getStringCellValue();
                                    if (expectedArrivalDateStr != null && !expectedArrivalDateStr.equals("")) {
                                        mouvementConteneur.setDatePrevisionnelle(new SimpleDateFormat("dd/MM/yyyy").parse(expectedArrivalDateStr));
                                    }
                                } else {
                                    mouvementConteneur.setDatePrevisionnelle(cell.getDateCellValue());
                                }
                                break;
                            default:
                                break;
                        }
                        cellNum++;
                    }
                    mouvementConteneurList.add(mouvementConteneur);
                }
                rowNum++;
            }
            workbook.close();
        }
        return mouvementConteneurList;
    }
    
    public static byte[] genererRapportRechercheMouvementConteneur(MouvementConteneur mouvementConteneur, ResourceLoader resourceLoader) throws JRException, IOException {
        String masterReportFileName = resourceLoader.getResource("classpath:reports/mouvement_conteneur.jrxml").getFile().getAbsolutePath();
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.FRENCH);
        numberFormat.setMaximumFractionDigits(0);

        
        JRDataSource ds = new JREmptyDataSource();
//        JREmptyDataSource beanColDataSource = new JREmptyDataSource();

        /* Compile the master */
        JasperReport jasperMasterReport = JasperCompileManager
                .compileReport(masterReportFileName);

        Map<String, Object> parameters = new HashMap<>();
        
        parameters.put("NUMERO_CONTENEUR", mouvementConteneur.getNumeroConteneur());
        parameters.put("TAILLE_CONTENEUR", mouvementConteneur.getTailleConteneur());
        parameters.put("TYPE_CONTENEUR", mouvementConteneur.getTypeConteneur());
        parameters.put("LIEU_DEPART", mouvementConteneur.getLieuDepart());
        parameters.put("LIEU_ARRIVEE", mouvementConteneur.getLieuArrivee());
        parameters.put("DATE_MOUVEMENT", mouvementConteneur.getDateMouvement()!= null ? new SimpleDateFormat("dd/MM/yyyy").format(mouvementConteneur.getDateMouvement()): "");
        parameters.put("HEURE_MOUVEMENT", mouvementConteneur.getHeureMouvement()!= null ? new SimpleDateFormat("HH:mm").format(mouvementConteneur.getHeureMouvement()): "");
        parameters.put("TYPE_MOUVEMENT", mouvementConteneur.getTypeMouvement());
        parameters.put("NUMERO_BL", mouvementConteneur.getNumeroBL());
        parameters.put("NUMERO_VOYAGE", mouvementConteneur.getNumeroVoyage());
        parameters.put("NOM_NAVIRE", mouvementConteneur.getNomNavire());
        parameters.put("ARRIVEE_PREVISIONNELLE", mouvementConteneur.getDatePrevisionnelle()!= null ? new SimpleDateFormat("dd/MM/yyyy").format(mouvementConteneur.getDatePrevisionnelle()): "");
        parameters.put("DERNIERE_LOCALISATION", mouvementConteneur.getDerniereLocalisation());
        parameters.put("LATITUDE", mouvementConteneur.getLatitude()+"");
        parameters.put("LONGITUDE", mouvementConteneur.getLongitude()+"");
        
        JasperPrint jp = JasperFillManager.fillReport(jasperMasterReport, parameters, ds);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jp, outputStream);

        return outputStream.toByteArray();
    }
}
