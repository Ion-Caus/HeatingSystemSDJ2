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

public class TemperatureListController
{
  @FXML private TableView<TemperatureViewModel> temperatureTable;
  @FXML private TableColumn<TemperatureViewModel, String> nameColumn;
  @FXML private TableColumn<TemperatureViewModel, String> temperatureColumn;
  @FXML private TableColumn<TemperatureViewModel, String> timeDateColumn;

  private Region root;
  private ViewHandler viewHandler;
  private TemperatureListViewModel viewModel;

  public void init(ViewHandler viewHandler,
      TemperatureListViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
    temperatureColumn.setCellValueFactory(cellData -> cellData.getValue().getValueProperty());
    timeDateColumn.setCellValueFactory(cellData -> cellData.getValue().getTimeProperty());

    this.temperatureTable.setItems(this.viewModel.getAll());

  }

  public void reset()
  {
  }

  public Region getRoot()
  {
    return root;
  }

  @FXML public void onBack()
  {
    this.viewHandler.openView("main");
  }
}
