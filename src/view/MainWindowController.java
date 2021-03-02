package view;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import utility.DoubleStringConverter;
import viewmodel.MainWindowViewModel;

public class MainWindowController
{

  @FXML public TextField closestField;
  @FXML public TextField furthestField;
  @FXML public TextField outDoorField;
  @FXML public TextField minTempField;
  @FXML public TextField maxTempField;
  @FXML public Label heaterPowerStateLabel;
  @FXML public Label errorLabel;
  private Region root;
  private ViewHandler viewHandler;
  private MainWindowViewModel mainWindowViewModel;

  public void init(ViewHandler viewHandler,
      MainWindowViewModel mainWindowViewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.mainWindowViewModel = mainWindowViewModel;
    this.root = root;

    closestField.setEditable(false);
    furthestField.setEditable(false);
    outDoorField.setEditable(false);

    //    closestField.textProperty().bind(Bindings.createStringBinding(() -> String
    //        .valueOf(
    //            mainWindowViewModel.getTemperatureInsideClosestProperty().get())));

    Bindings.bindBidirectional(closestField.textProperty(),
        mainWindowViewModel.getTemperatureInsideClosestProperty(),
        new DoubleStringConverter());

    Bindings.bindBidirectional(furthestField.textProperty(),
        mainWindowViewModel.getTemperatureInsideFurthestProperty(),
        new DoubleStringConverter());

    Bindings.bindBidirectional(outDoorField.textProperty(),
        mainWindowViewModel.getTemperatureOutsideProperty(),
        new DoubleStringConverter());

    Bindings.bindBidirectional(minTempField.textProperty(),
        mainWindowViewModel.getMinTemperature(), new DoubleStringConverter());

    Bindings.bindBidirectional(maxTempField.textProperty(),
        mainWindowViewModel.getMaxTemperature(), new DoubleStringConverter());

    heaterPowerStateLabel.textProperty().bind(mainWindowViewModel.getHeaterStateProperty());
    errorLabel.textProperty().bind(mainWindowViewModel.getWarningProperty());

  }

  public void reset()
  {
    mainWindowViewModel.clear();
  }

  public Region getRoot()
  {
    return root;
  }

  @FXML public void powerUpPressed()
  {
    mainWindowViewModel.powerUp();
  }

  @FXML public void powerDownPressed()
  {
    mainWindowViewModel.powerDown();
  }

  @FXML public void detailsPressed()
  {
    viewHandler.openView("details");
  }
}
