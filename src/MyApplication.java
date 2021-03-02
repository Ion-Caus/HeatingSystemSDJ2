import external.Thermometer;
import javafx.application.Application;
import javafx.stage.Stage;
import model.TemperatureModel;
import model.TemperatureModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application
{
  private Thermometer thermometer1, thermometer2;
  public void start(Stage primaryStage)
  {
    // Model
    TemperatureModel model = new TemperatureModelManager();


    //ViewModel
    var viewModelFactory = new ViewModelFactory(model);

    var view = new ViewHandler(viewModelFactory);
    view.start(primaryStage);


    // Thermometer
    thermometer1 = new Thermometer("closest", 15, 1,model);
    Thread t1 = new Thread(thermometer1);
    t1.start();

    thermometer2 = new Thermometer("furthest", 15, 7,model);
    Thread t2 = new Thread(thermometer2);
    t2.start();

  }
  @Override
  public void stop(){
    thermometer1.stop();
    thermometer2.stop();
  }
}