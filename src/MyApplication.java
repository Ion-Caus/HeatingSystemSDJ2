import external.Heater;
import external.Thermometer;
import javafx.application.Application;
import javafx.stage.Stage;
import model.TemperatureModelManager;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application
{
  private Thermometer thermometer1, thermometer2;
  public void start(Stage primaryStage)
  {
    // Model
    var model = new TemperatureModelManager();

//    //ViewModel
//    var viewModelFactory = new ViewModelFactory(model);

    // View
//    var view = new ViewHandler(viewModelFactory);
//    view.start(primaryStage);

    //Heater
    Heater heater = new Heater();

    // Thermometer
    thermometer1 = new Thermometer("closest", 15, 1,model,heater);
    Thread t1 = new Thread(thermometer1);
    t1.start();

    thermometer2 = new Thermometer("furthest", 15, 7,model,heater);
    Thread t2 = new Thread(thermometer2);
    t2.start();

    heater.powerUp();

    heater.powerUp();

    heater.powerUp();
  }
  @Override
  public void stop(){
    thermometer1.stop();
    thermometer2.stop();
  }
}