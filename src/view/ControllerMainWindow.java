package view;


import javafx.scene.layout.Region;
import viewmodel.MainWindowViewModel;

public class ControllerMainWindow
{

  private Region root;
  private ViewHandler viewHandler;
  private MainWindowViewModel mainWindowViewModel;

  public void init(ViewHandler viewHandler, MainWindowViewModel mainWindowViewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.mainWindowViewModel = mainWindowViewModel;
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
