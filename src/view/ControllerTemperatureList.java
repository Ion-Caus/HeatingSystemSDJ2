package view;

import javafx.scene.layout.Region;
import viewmodel.MainWindowViewModel;
import viewmodel.TemperatureListViewModel;

public class ControllerTemperatureList {

  private Region root;
  private ViewHandler viewHandler;
  private TemperatureListViewModel temperatureListViewModel;

  public void init(ViewHandler viewHandler, TemperatureListViewModel temperatureListViewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.temperatureListViewModel = temperatureListViewModel;
    this.root = root;

    //bindings
  }

  public void reset()
  {

  }

  public Region getRoot()
  {
    return root;
  }
}
