package viewmodel;

import model.TemperatureModel;

public class ViewModelFactory
{
  private MainWindowViewModel mainWindowViewModel;
  private TemperatureListViewModel temperatureListViewModel;

  public ViewModelFactory(TemperatureModel model)
  {
    mainWindowViewModel = new MainWindowViewModel(model);
    temperatureListViewModel = new TemperatureListViewModel(model);
  }

  public MainWindowViewModel getMainWindowViewModel()
  {
    return mainWindowViewModel;
  }

  public TemperatureListViewModel getTemperatureListViewModel()
  {
    return temperatureListViewModel;
  }
}
