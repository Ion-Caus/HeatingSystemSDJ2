package view;

import javafx.event.ActionEvent;
import javafx.scene.layout.Region;
import model.Temperature;
import viewmodel.MainWindowViewModel;
import viewmodel.TemperatureListViewModel;

import java.util.ArrayList;

public class ControllerTemperatureList
{

  // @FXML private ListView<String> temperatureList;
  private Region root;
  private ViewHandler viewHandler;
  private TemperatureListViewModel temperatureListViewModel;

  public void init(ViewHandler viewHandler,
      TemperatureListViewModel temperatureListViewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.temperatureListViewModel = temperatureListViewModel;
    this.root = root;

    //this.temperatureList.setItems(temperatureListViewModel.getList());

  }

  public void reset()
  {

  }

  public Region getRoot()
  {
    return root;
  }

  public void onBack()
  {
    this.viewHandler.openView("main");
  }
}
