package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBo;
import lk.ijse.gdse.dto.ReportDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReportBo extends SuperBo {
    ArrayList<ReportDto> getAllReport() throws SQLException;
    ArrayList<ReportDto> searchByDay(String day1, String day2) throws SQLException;
}
