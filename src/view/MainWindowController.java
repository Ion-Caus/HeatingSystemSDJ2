package view;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import utility.DoubleStringConverter;
import viewmodel.MainWindowViewModel;

public class MainWindowController
{

  @FXML private TextField closestField;
  @FXML private TextField furthestField;
  @FXML private TextField outDoorField;
  @FXML private TextField minTempField;
  @FXML private TextField maxTempField;
  @FXML private Label heaterPowerStateLabel;
  @FXML private Label errorLabel;
  @FXML private Button powerUpButton;
  @FXML private Button powerDownButton;
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

    powerDownButton.disableProperty().bindBidirectional(mainWindowViewModel.getTurnedOff());
    powerUpButton.disableProperty().bindBidirectional(mainWindowViewModel.getMaxedOut());



    reset();
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
