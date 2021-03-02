package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

public class ViewHandler
{
  private Scene currentScene;
  private Stage primaryStage;
  private ViewModelFactory viewModelFactory;
  private ControllerMainWindow controllerMainWindow;
  private ControllerTemperatureList controllerTemperatureList;

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
    this.currentScene = new Scene(new Region());
  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    openView("main");
  }

  public void openView(String id)
  {
    Region root = null;
    switch (id)
    {
      case "main":
        root = loadMainWindowView("MainWindow.fxml");
        break;
      case "list":
        root = loadTempListStatsView("TemperatureListStatsView.fxml");
        break;
    }
    currentScene.setRoot(root);
    String title = "";
    if (root.getUserData() != null)
    {
      title += root.getUserData();
    }
    primaryStage.setTitle(title);
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.show();
  }

  private Region loadMainWindowView(String fxmlFile)
  {
    if (controllerMainWindow == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        controllerMainWindow = loader.getController();
        controllerMainWindow
            .init(this, viewModelFactory.getMainWindowViewModel(), root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      controllerMainWindow.reset();
    }
    return controllerMainWindow.getRoot();
  }

  private Region loadTempListStatsView(String fxmlFile)
  {
    if (controllerTemperatureList == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        controllerTemperatureList = loader.getController();
        controllerTemperatureList
            .init(this, viewModelFactory.getTemperatureListViewModel(), root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      controllerTemperatureList.reset();
    }
    return controllerTemperatureList.getRoot();
  }

  public void closeView()
  {
    primaryStage.close();
  }
}