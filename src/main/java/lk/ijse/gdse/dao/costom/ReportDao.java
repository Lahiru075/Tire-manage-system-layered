package lk.ijse.gdse.dao.costom;

import lk.ijse.gdse.dto.ReportDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReportDao {
    ArrayList<ReportDto> getAllReport() throws SQLException;
    ArrayList<ReportDto> searchByDay(String day1, String day2) throws SQLException;
}
