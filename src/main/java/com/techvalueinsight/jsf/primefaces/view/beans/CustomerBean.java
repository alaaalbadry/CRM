package com.techvalueinsight.jsf.primefaces.view.beans;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.techvalueinsight.jsf.primefaces.model.entities.Country;
import com.techvalueinsight.jsf.primefaces.controllers.CustomerController;
import com.techvalueinsight.jsf.primefaces.model.entities.City;

import com.techvalueinsight.jsf.primefaces.model.entities.Customer;
import com.techvalueinsight.jsf.primefaces.util.configurations.ConfigProperties;
import com.techvalueinsight.jsf.primefaces.view.helpers.FileHelper;
import com.techvalueinsight.jsf.primefaces.view.helpers.JSFErrorMessageHelper;

@ManagedBean(name = "customerBean")
@ViewScoped
public class CustomerBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Customer customer;

	private List<Country> countries;

	private List<City> cities;

	private CustomerController customerController;

	private Long customerId;

	private InputStream stream;

	private boolean disableDownload;

	private List<Customer> searchCustomers;

	private Customer selectedCustomer;
	
	private UploadedFile uploadedFile;
	
	// Controls Configuration
	private boolean renderEditBtn = false;
	private boolean renderAddBtn = true;
	private boolean editMode = false;

	String filePath = null;
	String fileFullPath = null;
	private String fileName = null;


	public CustomerBean() {
		this.filePath = ConfigProperties.getProperty("path.uploadedFiles") + java.io.File.separator + "CustomerLogo";
		this.init();
	}

	private void init() {
		customerController = new CustomerController();
		this.countries = customerController.loadCountries();
		this.cities = customerController.loadCities();
		this.searchCustomers = customerController.SearchAllItems();

		if (null != customerId && customerId > 0) { // edit mode

			customer = customerController.SearchItem(customerId);
			this.enableEditMode();

		} else {

			disableEditMode();
			if (this.customer != null) {
				if (this.customer.getCountry() == null || this.customer.getCity() == null) {
					this.customer.setCountry(new Country());
					this.customer.setCity(new City());
				}
			} else {
				initCustomer();

			}
		}
	}

	private void initCustomer() {
		customer = new Customer();
		this.customer.setCountry(new Country());
		this.customer.setCity(new City());
	}

	public void searchById() {
		if (null != customerId && customerId > 0) { // edit mode
			customer = customerController.SearchItem(customerId);
			this.enableEditMode();

		}
	}

	private void enableEditMode() {
		editMode = customer != null ? true : false;
		renderEditBtn = true;
		renderAddBtn = false;
//		stream = customer.getFileInputStream();
	}

	private void disableEditMode() {
		editMode = false;
		renderEditBtn = false;
		renderAddBtn = true;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public InputStream getStream() {
		return stream;
	}

	public void setStream(InputStream stream) {
		this.stream = stream;
	}

	public boolean isDisableDownload() {
		return disableDownload;
	}

	public void setDisableDownload(boolean disableDownload) {
		this.disableDownload = disableDownload;
	}

	public boolean isRenderEditBtn() {
		return renderEditBtn;
	}

	public void setRenderEditBtn(boolean renderEditBtn) {
		this.renderEditBtn = renderEditBtn;
	}

	public boolean isRenderAddBtn() {
		return renderAddBtn;
	}

	public void setRenderAddBtn(boolean renderAddBtn) {
		this.renderAddBtn = renderAddBtn;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public List<Customer> getSearchCustomers() {
		return searchCustomers;
	}

	public void setSearchCustomers(List<Customer> searchCustomers) {
		this.searchCustomers = searchCustomers;
	}

	public Customer getSelectedCustomer() {
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}

	public void editCustomer() {
		System.out.println("start editcustomer");
		try {
			if (this.customerController.EditItem(this.customer)) {
				JSFErrorMessageHelper.addInfoMessage("Record edited Successfully", "Record edited Successfully");
			} else {
				JSFErrorMessageHelper.addErrorMessage("Error", "Record edited faild");
			}
		} catch (Exception e) {
			System.err.println("error in edit customer");
			e.printStackTrace();

		}
	}

	public void addNew() throws IOException {
		try {
			System.out.println("start addNew method");

//			if (customer.getCustomerPicPath() != null && !customer.getCustomerPicPath().isEmpty()) {
				if (this.customerController.NewItem(this.customer)) {
					JSFErrorMessageHelper.addInfoMessage("Record Added Successfully", "Record Added Successfully");
					disableDownload = false;

				} else {
					JSFErrorMessageHelper.addErrorMessage("Error", "لم يتم أضافه العقد.");
				}

//			} else {
//				JSFErrorMessageHelper.addErrorMessage("Error", "please choose picture");
//			}

		} catch (Exception e) {
			System.err.println("error in addNew");
			e.printStackTrace();
		}
	}

	public void getAllCustomers() {
		this.searchCustomers = new ArrayList<>();

		this.searchCustomers = customerController.SearchAllItems();

		if (searchCustomers.size() == 0)
			JSFErrorMessageHelper.addInfoMessage("No items to be viewed .", "No items to be viewed ");

	}

	public void newMode() {
		this.customer = new Customer(); // Reset placeholder.
		this.disableEditMode();
	}

	public void delete(Customer customer) {
		try {
			System.out.println("start delete function");
			if (customer != null) {
				boolean isDeleted = this.customerController.deleteItem(customer);
				if (isDeleted) {
					JSFErrorMessageHelper.addInfoMessage("Deleted Successfully.", " Deleted Successfully");
					this.searchCustomers.removeIf(e -> e.getId() == customer.getId());
				} else
					JSFErrorMessageHelper.addErrorMessage("Error while deleting customer.",
							"Error while deleting customer");
			} else {
				JSFErrorMessageHelper.addErrorMessage("Error while deleting customer.",
						"Error while deleting customer");
			}
		} catch (Exception e) {
			System.err.println("error in delete function");
			e.printStackTrace();
		}
	}
	
	public void addMessage() {
		String summary = this.customer.isActive() ? "active" : "inactive";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		System.out.println("start handleFileUpload function");
		uploadedFile = event.getFile();

		String extension = FilenameUtils.getExtension(uploadedFile.getFileName());
		fileName = FilenameUtils.getBaseName(uploadedFile.getFileName());

		fileFullPath = filePath + java.io.File.separator + fileName + "_" +this.customer.getId()+ "."
				+ extension;

		this.customer.setCustomerPicPath(fileFullPath);
		try {
			this.customer.setFileInputStream(uploadedFile.getInputstream());
			stream = uploadedFile.getInputstream();
			saveFilesToServer(this.customer.getFileInputStream(), fileFullPath, fileName);
			JSFErrorMessageHelper.addInfoMessage("Successful", uploadedFile.getFileName() + " is uploaded.");
		} catch (IOException e) {
			System.err.println("error in handleFileUpload function");
			e.printStackTrace();
		}
	}
	
	private boolean saveFilesToServer(InputStream input, String path, String fileName) {

		boolean hasCorrupted = false;

		if (input != null) {
			try {
				// FileHelper.CopyInputStream(input);
				// InputStream input1 = FileHelper.getCopy();
				FileHelper.saveFileInPath(input, path);
			} catch (Exception e) {
				e.printStackTrace();
				JSFErrorMessageHelper.addErrorMessage("Error while saving file.", "Failed to save file: " + fileName);
				hasCorrupted = true;
				System.err.println(e);
			}
		}
		return !hasCorrupted;
	}
	
}
