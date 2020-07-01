package it.unicam.cs.pa.jbudget100763.controller;

import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import it.unicam.cs.pa.jbudget100763.model.Account;
import it.unicam.cs.pa.jbudget100763.model.AccountType;
import it.unicam.cs.pa.jbudget100763.model.Budget;
import it.unicam.cs.pa.jbudget100763.model.BudgetImpl;
import it.unicam.cs.pa.jbudget100763.model.LedgerImpl;
import it.unicam.cs.pa.jbudget100763.model.ScheduledTransaction;
import it.unicam.cs.pa.jbudget100763.model.ScheduledTransactionImpl;
import it.unicam.cs.pa.jbudget100763.model.Tag;
import it.unicam.cs.pa.jbudget100763.model.TagBudgetReport;
import it.unicam.cs.pa.jbudget100763.model.TagBudgetReportImpl;
import it.unicam.cs.pa.jbudget100763.model.Transaction;

/**
 * Ha la responsabilit� di ricevere i comandi dell'utente e di attuarli
 * modificando lo stato degli altri due componenti del MVC
 * 
 * @author Vittorio
 *gradl
 */
public class Controller {
	private TagBudgetReport tagBudgetReport= new TagBudgetReportImpl();
	private Budget budgetImpl= new BudgetImpl();
	
	public Account addAccount(AccountType type, String name, String description, double openingBalance) {

		if (name.isEmpty()) {
			throw new IllegalArgumentException("Inserire nome!!!!");
		}
		return LedgerImpl.getInstance().addAccount(Objects.requireNonNull(type), name, description,
				Objects.requireNonNull(openingBalance));
	}

	public void removeAccount(Account o) {
		this.getAccounts().remove(Objects.requireNonNull(o));
	}

	public Tag addTag(String name, String description) {
		if (name.isEmpty()|| description.isEmpty()) {
			throw new IllegalArgumentException("Inserire nome o Descrizione!!!!");
		}
		return LedgerImpl.getInstance().addTag(name, description);
	}

	public void removeTag(Tag t) {
		this.getTags().remove(Objects.requireNonNull(t));
	}

	public Set<Account> getAccounts() {
		return LedgerImpl.getInstance().getAccounts();
	}

	public void addTransaction(GregorianCalendar date) {

		LedgerImpl.getInstance().addTransaction(Objects.requireNonNull(date));
	}

	public Set<Tag> getTags() {
		return LedgerImpl.getInstance().getTags();
	}

	public Set<Transaction> getTransactions() {
		return LedgerImpl.getInstance().getTransactions();
	}

	public Set<Transaction> getTransactions(Predicate<Transaction> condition) {
		return LedgerImpl.getInstance().getTransactions(Objects.requireNonNull(condition));
	}

	public Set<ScheduledTransaction> getScheduled() {
		return LedgerImpl.getInstance().getScheduled();
	}

	public ScheduledTransaction spotScheduledTransaction(GregorianCalendar d) {

		return LedgerImpl.getInstance().searchScheduledTransaction(Objects.requireNonNull(d));
	}

	public void schedule(ScheduledTransaction st) {
		LedgerImpl.getInstance().schedule(Objects.requireNonNull(st));
	}

	public Map<Tag, Double> report(Predicate<Transaction> condition) {

		return (Objects.nonNull(condition)) ? tagBudgetReport.report(condition)
				: tagBudgetReport.getTagBalance(getTransactions());
	}

	public double getBalance(Tag t) {
		return budgetImpl.getBalance(Objects.requireNonNull(t));
	}

	public void setBalance(Tag t, Double expected) {
		budgetImpl.setBalance(Objects.requireNonNull(t), Objects.requireNonNull(expected));
	}

	public Set<Tag> tags(Predicate<Transaction> condition) {
		return budgetImpl.tags(Objects.requireNonNull(condition));
	}

	public void scheduleSpecificTransaction(Transaction transaction, ScheduledTransactionImpl st) {
		if (!LedgerImpl.getInstance().scheduleSpecificTransaction(Objects.requireNonNull(transaction),
				Objects.requireNonNull(st)))
			throw new IllegalArgumentException();
	}

}
