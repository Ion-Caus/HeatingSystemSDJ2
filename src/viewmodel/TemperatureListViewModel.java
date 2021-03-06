package viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Temperature;
import model.TemperatureModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TemperatureListViewModel implements PropertyChangeListener
{
  private TemperatureModel model;
  private ObservableList<TemperatureViewModel> temperatureList;

  public TemperatureListViewModel(TemperatureModel model)
  {
    this.model = model;
    this.model.addListener("closest", this);
    this.model.addListener("furthest", this);

    this.temperatureList = FXCollections.observableArrayList();

    loadFromModel();
  }

  private void loadFromModel()
  {
    temperatureList.clear();
    for (Temperature temperature : model.getTemperatureList()) {
      temperatureList.add(new TemperatureViewModel(temperature));
    }
  }

  public ObservableList<TemperatureViewModel> getAll() {
    return temperatureList;
  }

  public void addTemperature(Temperature temperature) {
    if (temperatureList.size() >= 20) {
      temperatureList.remove(temperatureList.size()-1);
    }
    temperatureList.add(0, new TemperatureViewModel(temperature));
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    addTemperature( (Temperature) evt.getNewValue());
  }
}
