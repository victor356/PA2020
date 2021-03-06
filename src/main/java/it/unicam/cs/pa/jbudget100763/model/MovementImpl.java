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
package it.unicam.cs.pa.jbudget100763.model;

import java.util.HashSet;
import java.util.GregorianCalendar;
import java.util.Set;

/**
 * questa classe implementa la responsabilità di gestire un singolo movimento.
 * Permette di accedere e modificare le informazioni associate al movimento:
 * descrizione, importo, account associato, lista dei tag associati al
 * movimento. Le operazioni di lettura e modifica di queste operazioni vengono
 * effettuate per mezzo degli opportuni getter e setter. Il movimento è associato
 * ad una transazione da cui deriva la data. I tag inseriti nei movimenti vengono
 * raccolti dalla transazione senza ripetersi , i tag aggiunti alla transazione
 * vengono distribuiti a tutti i movimenti a lei associati
 * 
 * @author Vittorio
 *
 */
public class MovementImpl implements Movement {

	private String description;
	private MovementType type;
	private double amount;
	private Transaction transaction;
	private Account account;
	private int id;
	private GregorianCalendar date;
	Set<Tag> tag = new HashSet<Tag>();

	/**
	 * Costruttore del movimento, esso viene automaticamente collegato alla
	 * transazione ed inserito nella sua lista
	 * 
	 * @param type    - tipo di movimento
	 * @param amount  - ammontare
	 * @param trans   - transazione di cui fa parte
	 * @param account - conto di cui fa parte
	 */
	public MovementImpl(MovementType type, double amount, Transaction trans, Account account) {
		this.type = type;
		this.amount = amount;
		this.transaction = trans;
		this.account = account;
		transaction.addMovement(this);
		this.date = trans.getDate();
	}

	public MovementImpl(MovementType ty, double amo, GregorianCalendar da, Tag t, String desc, Account acc,
			Transaction trans) {
		type = ty;
		amount = amo;
		date = da;
		tag.add(t);
		description = desc;
		account = acc;
		transaction = trans;
		trans.addMovement(this);
		this.date = trans.getDate();

	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MovementType getType() {
		return this.type;
	}

	public void setType(MovementType type) {
		this.type = type;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Transaction getTransaction() {
		return this.transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public GregorianCalendar getDate() {
		return this.date;
	}

	public void setDate(GregorianCalendar date) {
		this.date = date;
	}

	public Set<Tag> getTag() {
		return this.tag;
	}

	@Override
	public void addTag(Tag t) {
		this.tag.add(t);
	}

	@Override
	public void removeTag(Tag t) {
		this.tag.remove(t);
	}

}