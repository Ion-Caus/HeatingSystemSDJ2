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

    // Thermometer
    thermometer1 = new Thermometer("t1", 15, 1,model);
    Thread t1 = new Thread(thermometer1);
    t1.start();

    thermometer2 = new Thermometer("t2", 15, 7,model);
    Thread t2 = new Thread(thermometer2);
    t2.start();
  }
  @Override
  public void stop(){
    thermometer1.stop();
    thermometer2.stop();
  }
}