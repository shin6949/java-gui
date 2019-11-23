package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainController {

	public static String enter = System.getProperty("line.separator");
	
	@FXML private MenuItem menuItem_save;
	@FXML private MenuItem menuItem_open;
	@FXML private MenuItem menuItem_new;
	@FXML private MenuItem menuItem_exit;
	@FXML private MenuItem menuItem_cut;
	@FXML private MenuItem menuItem_copy;
	@FXML private MenuItem menuItem_paste;
	@FXML private MenuItem menuItem_login;
	@FXML private MenuItem menuItem_register;
	
	@FXML public TextArea input_text;
	
	@FXML private Button toolBar_save;
	@FXML private Button toolBar_new;
	@FXML private Button toolBar_load;
	@FXML private Button toolBar_copy;
	@FXML private Button toolBar_paste;
	@FXML private Button btn_Test;
	
	public void btn_save(ActionEvent event) {
		String text = input_text.getText();
		System.out.println(text);
		
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT 파일 (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showSaveDialog(menuItem_save.getParentPopup().getScene().getWindow());
		if (file != null) {
		     saveFile(file);
		}
	}
	
	 public void saveFile(File file){
		    try{
		    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));

		    writer.write(input_text.getText().replaceAll("\n", enter));
		    writer.close();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		  }
	
	public void fileOpen (ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT 파일 (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(menuItem_open.getParentPopup().getScene().getWindow());
		
		try {
			StringBuffer data = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String line = null;

			while((line = reader.readLine()) != null) {
				data.append(line);
				data.append(enter);
			}
			
			reader.close();
			
			String info = data.toString();
			
			input_text.setText(info);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void make_new (ActionEvent event) {
		if(!input_text.getText().toString().equals("")) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("경고");
		alert.setHeaderText("텍스트 삭제 알림");
		alert.setContentText("작성하신 내용이 저장되지 않고 삭제됩니다. 계속하시겠습니까?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){ input_text.clear(); }
		else { }
		}
	}
	
	public void exit (ActionEvent event) {
		if(!input_text.getText().toString().equals("")) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("확인");
		alert.setHeaderText("작성한 내용이 있습니다.");
		alert.setContentText("옵션을 선택해주세요.");

		ButtonType buttonTypeSave = new ButtonType("저장 후 닫기");
		ButtonType buttonTypedontSave = new ButtonType("저장 안 함");
		ButtonType buttonTypeCancel = new ButtonType("취소", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeSave, buttonTypedontSave, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == buttonTypeSave){
			btn_save(null);
			System.exit(0);
		} else if (result.get() == buttonTypedontSave) { System.exit(0);} 
		else if (result.get() == buttonTypeCancel) { } } 
		else { System.exit(0); }		
	}
	
	public void btn_cut (ActionEvent event) { input_text.cut(); }
	public void btn_copy (ActionEvent event) { input_text.copy(); }
	public void btn_paste (ActionEvent event) { input_text.paste(); }
	
	public void btn_login(ActionEvent event) throws Exception { 
		Login loginscene;
		Stage primStage = (Stage) input_text.getScene().getWindow();
		loginscene = new Login();
		loginscene.start(primStage);
	}
	
	public void change_title(ActionEvent event) {
		 Stage primStage = (Stage) input_text.getScene().getWindow();
		 primStage.setTitle(input_text.getText());
	}
	
	public void set_exit_action() {
		Stage primStage = (Stage) input_text.getScene().getWindow();
		primStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		    	exit(null);
		    }
		}); 	
	}
	
	public void btn_register(ActionEvent event) {
		/*private Login loginscene;
		Stage primStage = (Stage) input_text.getScene().getWindow();
		loginscene = new Login();
		loginscene.start(primStage); */
	}
}
