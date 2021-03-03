package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import model.Temperature;
import viewmodel.MainWindowViewModel;
import viewmodel.TemperatureListViewModel;
import viewmodel.TemperatureViewModel;

import java.util.ArrayList;

public class ControllerTemperatureList
{

  //@FXML private ListView<String> temperatureList;
  @FXML private TableView<TemperatureViewModel> temperatureTable;
  @FXML private TableColumn<TemperatureViewModel, String> nameColumn;
  @FXML private TableColumn<TemperatureViewModel, String> temperatureColumn;
  @FXML private TableColumn<TemperatureViewModel, String> timeDateColumn;

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
