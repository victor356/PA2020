/*
This file is part of JBudget.

    JBudget is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    JBudget is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with JBudget.  If not, see <https://www.gnu.org/licenses/>.
*/
package it.unicam.cs.pa.jbudget100763.view.javafx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.ResourceBundle;

import it.unicam.cs.pa.jbudget100763.controller.Controller;
import it.unicam.cs.pa.jbudget100763.model.Account;
import it.unicam.cs.pa.jbudget100763.model.AccountType;
import it.unicam.cs.pa.jbudget100763.model.Tag;
import it.unicam.cs.pa.jbudget100763.view.View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Ha la responsabilità di avviare gestire la schermata di avvio dell'applicazione JAVAFX
 * 
 * 
 * 
 * @author Vittorio
 *
 */
public class FxController implements Initializable, View {

	private static Controller controller = new Controller();

	public static Controller getController() {
		return controller;
	}

	@FXML
	private void start() throws IOException {
		App.showStart();
	}

	@FXML
	private void backHome() throws IOException {
		App.showHome();
	}

	@FXML
	public void manageAccount() throws IOException {
		App.manageAccount();
	}

	@FXML
	public void createAccount() throws IOException {
		App.createAccount();
		createAccountTable();
	}

	/**
	 * Manage account fields
	 */
	@FXML
	private TableView<Account> accountTable = new TableView<Account>();

	@FXML
	private TableColumn<Account, String> AccountName = new TableColumn<Account, String>();
	@FXML
	private TableColumn<Account, String> AccountDesc = new TableColumn<Account, String>();

	@FXML
	private TableColumn<Account, AccountType> Type = new TableColumn<Account, AccountType>();

	@FXML
	private TableColumn<Account, Double> AccountOpeningB = new TableColumn<Account, Double>();

	@FXML
	private TableColumn<Account, Double> AccountB = new TableColumn<Account, Double>();

	private ObservableList<Account> getAccounts() throws IOException {
		ObservableList<Account> observableList = FXCollections.observableArrayList();
		observableList.addAll(controller.getAccounts());
		return observableList;
	}

	/**
	 * Match delle colonne della tabella con gli attributi delle classi
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			createAccountTable();
			createTagTable();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createAccountTable() throws IOException {
		AccountName.setCellValueFactory(new PropertyValueFactory<>("name"));
		AccountDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
		Type.setCellValueFactory(new PropertyValueFactory<>("type"));
		AccountOpeningB.setCellValueFactory(new PropertyValueFactory<>("openingBalance"));
		AccountB.setCellValueFactory(new PropertyValueFactory<>("balance"));
		accountTable.setItems(getAccounts());

	}

	private void createTagTable() throws IOException {
		tagName.setCellValueFactory(new PropertyValueFactory<Tag, String>("name"));
		tagDescription.setCellValueFactory(new PropertyValueFactory<Tag, String>("description"));
		tagTable.setItems(getTags());

	}

	/**
	 * new account fields
	 */
	@FXML
	private Button alertButton;

	@FXML
	private Button closeButton;

	@FXML
	private TextField accountNameField = new TextField();

	@FXML
	private TextField accountDescriptionField = new TextField();

	@FXML
	private TextField accountOpeningBalanceField = new TextField();

	@FXML
	private ChoiceBox<AccountType> accountTypeField = new ChoiceBox<AccountType>();

	ObservableList<AccountType> accountChoice = FXCollections
			.observableArrayList(new ArrayList<AccountType>(EnumSet.allOf(AccountType.class)));

	/**
	 * mostra i tipi di account nel choicebox
	 */
	@FXML
	private void typeScroll() {
		accountTypeField.setItems((ObservableList<AccountType>) accountChoice);

	}

	@FXML
	public void deleteAccount() {
		controller.removeAccount(accountTable.getSelectionModel().getSelectedItem());
		accountTable.getItems().removeAll(accountTable.getSelectionModel().getSelectedItem());

	}

	private ObservableList<Tag> getTags() throws IOException {
		ObservableList<Tag> observableList = FXCollections.observableArrayList();
		observableList.addAll(controller.getTags());
		return observableList;
	}

	/**
	 * tag table management
	 */
	@FXML
	public void manageTag() throws IOException {
		App.manageTag();

	}

	@FXML
	public void createTag() throws IOException {
		App.createTag();
		createTagTable();

	}

	@FXML
	private TableView<Tag> tagTable = new TableView<Tag>();

	@FXML
	private TableColumn<Tag, String> tagName = new TableColumn<Tag, String>();

	@FXML
	private TableColumn<Tag, String> tagDescription = new TableColumn<Tag, String>();

	@FXML
	private void deleteTag() {
		controller.getTags().removeIf(o -> o == tagTable.getSelectionModel().getSelectedItem());
		tagTable.getItems().removeAll(tagTable.getSelectionModel().getSelectedItem());

	}

}
