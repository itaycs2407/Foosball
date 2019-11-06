import java.io.IOException;
import java.util.Scanner;

import Config.FoosballConfig;
import Controllers.GameController;
import Controllers.GameSettingsController;
import Controllers.HomeController;
import FoosballDal.CsvFoosballDal;
import FoosballDal.IFoosballDal;
import Utils.FoosballConfigWrapper;
import sun.invoke.empty.Empty;
public class Program {

	public static void main(String[] args) throws IOException {
		String filePath = "C:\\Users\\Nirch10\\Downloads\\Book1.csv";
		FoosballConfigWrapper aaa = new FoosballConfigWrapper();
		FoosballConfig conf = aaa.LoadConfig("C:\\code\\Foosball\\Config.Toml");
		IFoosballDal dal = new CsvFoosballDal(conf.appSettingsConfig.csvRelativePath);
		HomeController hCtrl = new HomeController();
		GameSettingsController gsCtrl = new GameSettingsController();
		hCtrl.OnStart(filePath);


		Scanner scanner = new Scanner(System.in);
		String a = scanner.nextLine();
		while(a != "a"){
			switch (a){
				case "s":{
					dal.Save();
					System.out.println("saved");
					break;
				}case "r":{
					hCtrl.OnStart(filePath);
					System.out.println("reloaded");
					break;
				}
				case "d":{
					int id = scanner.nextInt();
					hCtrl.DeleteGame(id);
					System.out.println("deleted game " + id);
					break;
				}
				case "open":{

				}
			}
			a = scanner.nextLine();
		}
	}

}
