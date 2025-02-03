package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.ReportBo;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.costom.QueryDao;
import lk.ijse.gdse.dto.ReportDto;
import lk.ijse.gdse.entity.Report;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReportBoImpl implements ReportBo {

    QueryDao queryDao = (QueryDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);

    public ArrayList<ReportDto> getAllReport() throws SQLException {
        ArrayList<Report> reports = queryDao.getAllReport();
        ArrayList<ReportDto> reportDtos = new ArrayList<>();

        for (Report report : reports){
            reportDtos.add(new ReportDto(
                    report.getPaymentId(),
                    report.getPaymentAmount(),
                    report.getDate(),
                    report.getPaymentStatus(),
                    report.getDiscountId(),
                    report.getDiscountAmount(),
                    report.getPaymentMethod()
            ));
        }

        return reportDtos;

    }

    public ArrayList<ReportDto> searchByDay(String day1, String day2) throws SQLException {
        ArrayList<Report> reports = queryDao.searchByDay(day1,day2);

        ArrayList<ReportDto> reportDtos = new ArrayList<>();

        for (Report report : reports){
            reportDtos.add(new ReportDto(
                    report.getPaymentId(),
                    report.getPaymentAmount(),
                    report.getDate(),
                    report.getPaymentStatus(),
                    report.getDiscountId(),
                    report.getDiscountAmount(),
                    report.getPaymentMethod()
            ));
        }

        return reportDtos;
    }
}
