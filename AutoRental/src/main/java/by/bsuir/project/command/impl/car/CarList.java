package by.bsuir.project.command.impl.car;

import by.bsuir.project.command.ActionCommand;
import by.bsuir.project.exception.CommandException;
import by.bsuir.project.exception.ServiceException;
import by.bsuir.project.model.Car;
import by.bsuir.project.service.impl.CarServiceImpl;
import by.bsuir.project.util.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by User on 14.05.2017.
 */
public class CarList implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = Configuration.getProperty("path.page.cars");
        CarServiceImpl carService = new CarServiceImpl();
        try {
            ArrayList<Car> cars = carService.getAllEntities();
            request.setAttribute("cars", cars);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
