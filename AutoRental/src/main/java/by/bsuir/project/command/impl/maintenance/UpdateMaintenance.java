package by.bsuir.project.command.impl.maintenance;

import by.bsuir.project.command.ActionCommand;
import by.bsuir.project.exception.CommandException;
import by.bsuir.project.exception.ServiceException;
import by.bsuir.project.model.Maintenance;
import by.bsuir.project.service.impl.MaintenanceServiceImpl;
import by.bsuir.project.util.Charset;
import by.bsuir.project.util.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class UpdateMaintenance implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = Configuration.getProperty("path.page.maintenances");
        int maintenance_id = Integer.parseInt(request.getParameter("maintenance_id"));
        MaintenanceServiceImpl maintenanceService = new MaintenanceServiceImpl();
        try {
            Maintenance maintenance = maintenanceService.getEntityById(maintenance_id);
            String maintenance_defect = Charset.change(request.getParameter("maintenance_defect"));
            float maintenance_cost = Float.parseFloat(request.getParameter("maintenance_cost"));
            int maintenance_car = Integer.parseInt(request.getParameter("maintenance_car"));
            int maintenance_staff = Integer.parseInt(request.getParameter("maintenance_staff"));
            Date maintenance_date_begin = Date.valueOf(request.getParameter("maintenance_date_begin"));
            Date maintenance_date_expir = Date.valueOf(request.getParameter("maintenance_date_expir"));
            maintenance.setMaintenance_defect(maintenance_defect);
            maintenance.setMaintenance_cost(maintenance_cost);
            maintenance.setMaintenance_car(maintenance_car);
            maintenance.setMaintenance_staff(maintenance_staff);
            maintenance.setMaintenance_date_begin(maintenance_date_begin);
            maintenance.setMaintenance_date_expir(maintenance_date_expir);
            maintenanceService.updateEntity(maintenance);
            request.setAttribute("maintenances", maintenanceService.getAllEntities());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
