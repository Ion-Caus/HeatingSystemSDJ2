package viewmodel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Temperature;
import model.TemperatureModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TemperatureListViewModel implements PropertyChangeListener
{
  private TemperatureModel temperatureModel;
  private ObservableList<TemperatureViewModel> temperatureList;

  public TemperatureListViewModel(TemperatureModel model)
  {
    this.temperatureModel = model;
    this.temperatureList = FXCollections.observableArrayList();
    this.temperatureModel.addListener(null, this);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    switch (evt.getPropertyName())   // <--- thermometer id
    {
//      case "outside":
//        Platform.runLater(() -> outsideThermometerList.add(evt.getNewValue().toString()));
//        break;
//      case "closest":
//        Platform
//            .runLater(() -> insideHeaterThermometerList.add(evt.getNewValue().toString()));
//        break;
//      case "furthest":
//        Platform.runLater(() -> insideThermometerList.add(evt.getNewValue().toString()));
//        break;
    }

  }
}
